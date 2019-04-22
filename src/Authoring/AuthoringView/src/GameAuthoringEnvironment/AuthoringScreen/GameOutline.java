package GameAuthoringEnvironment.AuthoringScreen;

import Configs.Configurable;
import Configs.GamePackage.Game;
import Configs.MapPackage.MapConfig;
import GameAuthoringEnvironment.AuthoringComponents.AlertScreen;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

//TODO Change all magic values

public class GameOutline extends Screen {

    private Pane myContent;
    private ImageView myImage;
    private int myHeight;
    private int myWidth;
    private TreeView<Configurable> myTreeView = new TreeView<>();
    private Game myGame;
    private GameOutline gameOutline;

    public ImageView getMyImage(){
        Image test = new Image(getClass().getResourceAsStream("/resources/" +"Folder.png"));
        ImageView imageView = new ImageView(test);
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);

        return imageView;
    }

    public GameOutline(int width, int height) {
        super(width, height);
        gameOutline = this;
        myHeight = height;
        myWidth = width;
        myContent = getContent();
        Game myGame = new Game();
    }


    public void makeTreeView(Game game) {
        myContent.getChildren().remove(myTreeView);
        myGame = game;
        TreeItem<Configurable> myRoot = new TreeItem<>(game);
        createTreeView(myRoot);
        myTreeView.setRoot(myRoot);
        setCellFactory();
        myContent.getChildren().add(myTreeView);
    }

    //recursively creates a treeview - only creates something that has been defined
    private void createTreeView(TreeItem<Configurable> myConfigurable) {


        Map<String, Object> myMap = myConfigurable.getValue().getConfiguration().getDefinedAttributes();

        for (String key : myMap.keySet()) {
            var value = myMap.get(key);
            //value is not an array, just an object
            if (value instanceof Configurable) {
                try {
                    TreeItem<Configurable> treeItem = new TreeItem<>((Configurable) value);
                    myConfigurable.getChildren().add(treeItem);
                    if(!key.toLowerCase().equals("mymap")){
                        createTreeView(treeItem);
                    }
                } catch (Exception e) {
                    //TODO Handle Error
                    e.printStackTrace();
                }
            } else if (value.getClass().isArray()) {

                Object[] valueArray = (Object[]) value;
                for (Object object : valueArray) {
                    TreeItem<Configurable> treeItem = new TreeItem<>((Configurable) object);
                    myConfigurable.getChildren().add(treeItem);
                    createTreeView(treeItem);
                }
            }
        }
    }

    private void setCellFactory() {

        myTreeView.setCellFactory(tree -> {
            //TODO Set Images Accordingly
            TreeCell<Configurable> cell = new TreeCell<>() {
                @Override
                public void updateItem(Configurable item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                            try {
                                setText(item.getClass().getDeclaredField("myLabel").get(null) + ": " + item.getName());
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (NoSuchFieldException e) {
                                e.printStackTrace();
                            }
                        setGraphic(getMyImage());
                        }

                }
            };
            controlTreeCellMouseClick(cell);
            return cell;
        });
    }


    private void controlTreeCellMouseClick(TreeCell<Configurable> cell) {

        cell.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                Object object = cell.getTreeItem().getValue();
                try {
                    findMyClass(object, myGame);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    //recursively search the right class
    private void findMyClass(Object myObject, Configurable configurable) throws NoSuchFieldException {

        //if myobject is myGame
        if (myObject.equals(configurable)) {
            showTheScreen((Configurable) myObject);
        }

        Map<String, Object> myMap = configurable.getConfiguration().getDefinedAttributes();
        System.out.println(myMap);
        for (String key : myMap.keySet()) {
            var value = myMap.get(key);
            //base case
            if (value.equals(myObject)) {
                showTheScreen((Configurable) value);
            }
            // if value configurable recurse!
            else if (!value.getClass().isArray() && value instanceof Configurable) {
                Configurable temp = (Configurable) value;
                findMyClass(myObject, temp);
                // if value is an array
            } else if (value.getClass().isArray()) {

                Object[] valueArray = (Object[]) value;
                for (int b = 0; b < valueArray.length; b++) {
                    if (valueArray[b].equals(myObject)) {
                        showTheScreen((Configurable) valueArray[b]);
                    } else {
                        Configurable myConfigurable = (Configurable) valueArray[b];
                        findMyClass(myObject, myConfigurable);
                    }
                }
            }
        }
    }


   public void showTheScreen(Configurable myConfigurable) throws NoSuchFieldException {

       Map<String, Object> myAttributesMap = new HashMap<>();
       Map<String, Class> attributesMap = myConfigurable.getConfiguration().getAttributes();
       Map<String, Object> definedAttributesMap = myConfigurable.getConfiguration().getDefinedAttributes();

       if (myConfigurable.getClass().getSimpleName().toLowerCase().equals("mapconfig")) {
           if (!definedAttributesMap.keySet().equals(null)){
               MapConfig mapConfig = (MapConfig) myConfigurable;
               ConfigurableMap configurableMap = new ConfigurableMap(mapConfig, myAttributesMap, ((MapConfig) myConfigurable).getLevel());
               configurableMap.resetConfigurations();
           } else {
               ConfigurableMap configurableMap = new ConfigurableMap(myAttributesMap, myConfigurable);
               configurableMap.setConfigurations();
           }
       } else {
           Stage popupwindow = new Stage();

           List<Button> allButton = new ArrayList<>();

           popupwindow.initModality(Modality.APPLICATION_MODAL);
           popupwindow.setTitle(myConfigurable.getClass().getSimpleName() + " Property Settings");

           VBox layout = new VBox(10.00);
           VBox.setMargin(layout, new Insets(20, 20, 20, 20));


           for (String key : attributesMap.keySet()) {
               var value = attributesMap.get(key);

               //handle booleans
               if (value.equals(boolean.class)) {
                   HBox box = new HBox(10);
                   Label myLabel = new Label(key);
                   RadioButton trueButton = new RadioButton("True");
                   RadioButton falseButton = new RadioButton("False");
                   if (definedAttributesMap.keySet().contains(key)) {
                       if (definedAttributesMap.get(key).equals(true)) {
                           trueButton.setSelected(true);
                       } else {
                           falseButton.setSelected(true);
                       }
                   }
                   Button confirmButton = new Button("Confirm");
                   box.getChildren().addAll(myLabel, trueButton, falseButton);
                   confirmButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                       //TODO DO Errorchecking/Refactor
                       @Override
                       public void handle(MouseEvent event) {
                           if (trueButton.isSelected()) {
                               myAttributesMap.put(key, true);
                           } else {
                               myAttributesMap.put(key, false);
                           }

                       }
                   }));
                   allButton.add(confirmButton);
                   layout.getChildren().add(box);
               }
               //handle special case: require image
               else if (key.toLowerCase().contains("thumbnail") || key.toLowerCase().contains("imagepath")) {
                   Label myLabel = new Label(key);
                   TextField myTextField = new TextField();
                   if (definedAttributesMap.keySet().contains(key)) {
                       myTextField.setText(value.toString());
                   }
                   Button chooseImageButton = new Button("Choose Image");
                   Button confirmButton = new Button("Confirm");

                   var nameAndTfBar = new HBox();
                   nameAndTfBar.getChildren().addAll(myLabel, myTextField, chooseImageButton, confirmButton);
                   chooseImageButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                       //TODO(Louis) Change this so that image is called in from the server
                       @Override
                       public void handle(MouseEvent event) {
                           FileChooser fileChooser = new FileChooser();
                           File selectedFile = fileChooser.showOpenDialog(popupwindow);
                           String filepath = selectedFile.toString();
                           myTextField.setText(filepath);
                       }
                   }));

                   confirmButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                       @Override
                       public void handle(MouseEvent event) {
                           myAttributesMap.put(key, myTextField.getText());
                       }
                   }));

                   allButton.add(confirmButton);
                   layout.getChildren().addAll(nameAndTfBar);

               }
               //handle string and primitives except boolean
               else if (value.equals(java.lang.String.class) || value.isPrimitive()) {
                   Label myLabel = new Label(key);
                   TextField myTextField = new TextField();
                   if (definedAttributesMap.keySet().contains(key)) {
                       myTextField.setText(definedAttributesMap.get(key).toString());
                   }
                   Button confirmButton = new Button("Confirm");


                   var nameAndTfBar = new HBox();
                   nameAndTfBar.getChildren().addAll(myLabel, myTextField, confirmButton);
                   confirmButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                       //TODO DO Errorchecking/Refactor
                       @Override
                       public void handle(MouseEvent event) {
                           if (value.getName().equals("int")) {
                               Integer a = Integer.parseInt(myTextField.getText());
                               myAttributesMap.put(key, a.intValue());
                           } else if (value.getName().equals("long")) {
                               Long b = Long.parseLong(myTextField.getText());
                               myAttributesMap.put(key, b.longValue());
                           } else if (value.getName().equals("double")) {
                               Double c = Double.parseDouble(myTextField.getText());
                               myAttributesMap.put(key, c.doubleValue());
                           } else {
                               myAttributesMap.put(key, myTextField.getText());
                           }
                       }
                   }));
                   allButton.add(confirmButton);
                   layout.getChildren().addAll(nameAndTfBar);
               }

               //handle single object
               //this single object is a link to others so it doesn't save anything to the myAttributesMap
               //TODO Need a special case for classes that do not implement configurables
               else if (!value.isArray()) {

                   Button myButton = new Button("Configure " + value.getSimpleName());
                   myButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                       @Override
                       public void handle(MouseEvent event) {
                           try {
                               Class<?> clazz = Class.forName(value.getName());
                               //special case: map TODO use reflection for this
                               if (clazz.getSimpleName().equals("MapConfig")) {
                                   if (definedAttributesMap.keySet().contains(key)) {
                                       MapConfig mapConfig = (MapConfig) definedAttributesMap.get(key);
                                       ConfigurableMap configurableMap = new ConfigurableMap(mapConfig, myAttributesMap, myConfigurable);
                                       configurableMap.resetConfigurations();
                                   } else {
                                       ConfigurableMap configurableMap = new ConfigurableMap(myAttributesMap, myConfigurable);
                                       configurableMap.setConfigurations();
                                   }
                               } else if (clazz.getSimpleName().equals("View")) {
                                   Constructor<?> cons = clazz.getConstructor(Configurable.class);
                                   var object = cons.newInstance(myConfigurable);
                                   try {
                                       showTheScreen((Configurable) object);
                                   } catch (NoSuchFieldException e) {
                                       e.printStackTrace();
                                   }
                                   myAttributesMap.put(key, object);
                               } else {
                                   //TODO idf clazz does not taken in myconfigurable as a parameter, then error
                                   Constructor<?> cons = clazz.getConstructor(myConfigurable.getClass());
                                   var object = cons.newInstance(myConfigurable);
                                   try {
                                       showTheScreen((Configurable) object);
                                   } catch (NoSuchFieldException e) {
                                       e.printStackTrace();
                                   }
                                   myAttributesMap.put(key, object);
                               }

                           } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
                               //TODO ErrorChecking
                               e.printStackTrace();
                           }

                       }
                   }));
                   layout.getChildren().add(myButton);
               }

               //handle list
               else if (value.isArray()) {
                   if (value.getComponentType().getClass().isInstance(Configurable.class)) {

                       List<Object> tempList = new ArrayList<>();
                       Label listLabel = new Label("Add new " + value.getComponentType().getSimpleName() + " here");
                       VBox tempVBOx = new VBox();
                       tempVBOx.setSpacing(10);
                       var buttonBar = new HBox();
                       buttonBar.setSpacing(10);
                       Button addNew = new Button("Add new " + value.getComponentType().getSimpleName());
                       Button confirmButton = new Button("Confirm");
                       Button removeButton = new Button("Remove");

                       buttonBar.getChildren().addAll(addNew, confirmButton, removeButton);
                       ListView sourceView = new ListView<>();
                       if (definedAttributesMap.keySet().contains(key)) {
                           Object[] objects = (Object[]) definedAttributesMap.get(key);
                           for (Object object : objects) {
                               tempList.add(object);
                               //TODO Check if it is configurable?
                               Configurable temp = (Configurable) object;
                               sourceView.getItems().add(temp.getName());
                           }
                       }

                       addNew.setOnMouseClicked((new EventHandler<MouseEvent>() {
                           @Override
                           public void handle(MouseEvent event) {
                               //adds to the visual
                               sourceView.getItems().add(value.getComponentType().getSimpleName() + (sourceView.getItems().size() + 1));
                               try {
                                   //adds to the list
                                   Class<?> cl = Class.forName(value.getComponentType().getName());
                                   Constructor<?> cons = cl.getConstructor(myConfigurable.getClass());
                                   var object = cons.newInstance(myConfigurable);
                                   tempList.add(object);

                               } catch (Exception e) {
                                   //TODO(Hyunjae) ErrorChecking

                               }

                           }
                       }));

                       sourceView.setOnMouseClicked((new EventHandler<MouseEvent>() {

                           @Override
                           public void handle(MouseEvent mouseEvent) {
                               if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                                   if (mouseEvent.getClickCount() == 2) {
                                       try {

                                           Class<?> cl = Class.forName(value.getComponentType().getName());
                                           if (cl.getSimpleName().contains("Behavior")) {
                                               Field myField = cl.getDeclaredField("IMPLEMENTING_BEHAVIORS");
                                               List<Class> behaviorList = (List<Class>) myField.get(null);
                                               ConfigureBehavior configureBehavior = new ConfigureBehavior(gameOutline, myConfigurable, myAttributesMap, behaviorList);
                                           } else {
                                               showTheScreen((Configurable) tempList.get(sourceView.getSelectionModel().getSelectedIndex()));
                                           }

                                       } catch (Exception e) {
                                           //TODO(Hyunjae) Errorchecking
                                           System.out.println(e);

                                       }
                                   }
                               }
                           }
                       }));

                       confirmButton.setOnMouseClicked((new EventHandler<MouseEvent>() {

                           @Override
                           public void handle(MouseEvent event) {
                               try {
                                   Class c = Class.forName(value.getComponentType().getName());
                                   Object[] ob = (Object[]) Array.newInstance(c, tempList.size());
                                   for (int a = 0; a < tempList.size(); a++) {
                                       ob[a] = tempList.get(a);
                                   }
                                   myAttributesMap.put(key, ob);
                                   List<Object> newObjects = Arrays.asList(ob);
                               } catch (ClassNotFoundException e) {
                                   //TODO(Hyunjae) Errorchecking
                               }
                           }
                       }));

                       removeButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                           @Override
                           public void handle(MouseEvent event) {
                               int index = sourceView.getSelectionModel().getSelectedIndex();
                               sourceView.getItems().remove(index);
                               tempList.remove(index);

                           }
                       }));


                       allButton.add(confirmButton);
                       tempVBOx.getChildren().addAll(listLabel, sourceView, buttonBar);
                       layout.getChildren().add(tempVBOx);
                   }
               }
           }

           Button setButton = new Button("This config completed");
           setButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
               @Override
               public void handle(MouseEvent event) {
                   //TODO(Hyunjae) Refactor
                   if (!myConfigurable.getConfiguration().isConfigurationComplete()) {
                       AlertScreen alertScreen = new AlertScreen();
                   } else {

                       for (Button button : allButton) {
                           button.fireEvent(event);
                       }
                       myConfigurable.getConfiguration().setAllAttributes(myAttributesMap);
                       popupwindow.close();
                   }

               }
           }));

           layout.getChildren().add(setButton);

           Scene scene = new Scene(layout, 500, 500);
           popupwindow.setScene(scene);
           popupwindow.showAndWait();

       }
   }


}

