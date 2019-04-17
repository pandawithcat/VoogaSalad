package GameAuthoringEnvironment.AuthoringScreen;

import Configs.Configurable;
import Configs.GamePackage.Game;
import Configs.LevelPackage.Level;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
//import javafx.scene.input.MouseEvent;
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
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TODO Change all magic values

public class GameOutline extends Screen{

    private Pane myContent;
    private ImageView myImage;
    private int myHeight;
    private int myWidth;
    private TreeView<Configurable> myTreeView = new TreeView<>();
    private Game myGame;

    public GameOutline(int width, int height){
        super(width, height);
        myHeight = height;
        myWidth = width;
        myContent = getContent();
        Game myGame = new Game();
        setContent(myGame);
    }



    public void setContent(Game game) {

       /* Image test = new Image(getClass().getResourceAsStream("/ButtonImages/"+"Folder.png"));
        myImage = new ImageView(test);
        //TODO magic numbers should be changed based on the screensize
        myImage.setFitHeight(50);
        myImage.setFitWidth(50);*/
    }

    public void makeTreeView(Game game){
        myGame = game;
        TreeItem<Configurable> myRoot = new TreeItem<>(game);
        createTreeView(myRoot);
        myTreeView.setRoot(myRoot);
        setCellFactory();
        myContent.getChildren().add(myTreeView);
    }

    //recursively create a treeview
    private void createTreeView(TreeItem<Configurable> myConfigurable) {


        Map<String, Object> myMap = myConfigurable.getValue().getConfiguration().getDefinedAttributes();

        for (String key : myMap.keySet()) {
            var value = myMap.get(key);
            if (!value.getClass().isArray()  && value.getClass().isInstance(Configurable.class)) {
                try {
                    TreeItem<Configurable> treeItem = new TreeItem<>((Configurable) value);
                    myConfigurable.getChildren().add(treeItem);
                    createTreeView(treeItem);
                } catch (Exception e) {
                    //TODO Handle Error
                    e.printStackTrace();
                }
            }else if(value.getClass().isArray()){

                Object[] valueArray = (Object[]) value;
                for(int b=0; b<valueArray.length ; b++){
                    Configurable configurable = (Configurable) valueArray[b];
                    TreeItem<Configurable> treeItem = new TreeItem<>(configurable);
                    myConfigurable.getChildren().add(treeItem);
                    createTreeView(treeItem);
                }
            }
        }
    }

    private void setCellFactory(){

        myTreeView.setCellFactory(tree -> {
            //TODO Set Images Accordingly
            TreeCell<Configurable> cell = new TreeCell<>() {
                @Override
                public void updateItem(Configurable item, boolean empty) {
                    super.updateItem(item, empty) ;
                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(item.getLabel());
                    }
                }
            };

            controlTreeCellMouseClick(cell);
            return cell ;
        });
    }


    private void controlTreeCellMouseClick(TreeCell<Configurable> cell) {

        cell.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                    Object object = cell.getTreeItem().getValue();
                    //Don't open pop up screen if Game is clicked;
                    if(!object.getClass().equals(Game.class)){
                    findMyClass(object, myGame);}
            }
        });

    }

    //recursively search the right class
    private void findMyClass(Object myObject, Configurable configurable) {

        Map<String, Object> myMap = configurable.getConfiguration().getDefinedAttributes();
        System.out.println(myMap);
        for (String key : myMap.keySet()) {
            var value = myMap.get(key);
            //base case
            if(value.equals(myObject)){
                showTheScreen((Configurable)value);
            }
            // if value configurable recurse!
            else if (!value.getClass().isArray()  && value instanceof Configurable) {
                Configurable temp = (Configurable) value;
                findMyClass(myObject, temp);
            // if value is an array
            } else if(value.getClass().isArray()){

                Object[] valueArray = (Object[]) value;
                for(int b=0; b<valueArray.length ; b++){
                    if(valueArray[b].equals(myObject)){
                        showTheScreen((Configurable) valueArray[b]);
                    }
                    else {
                        Configurable myConfigurable = (Configurable) valueArray[b];
                        findMyClass(myObject, myConfigurable);
                    }
                }
            }
        }
    }


    private void showTheScreen(Configurable selectedObject){

        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Change " + selectedObject.getLabel() + " Settings");


        VBox layout = new VBox(10.00);
        VBox.setMargin(layout, new Insets(20, 20, 20, 20));


        //TODO Add labels and textfields
        List<Button> allButton = new ArrayList<>();
        Map<String, Object> myAttributesMap = new HashMap<>();
        //myMap stores all the data from the game -- this should be changed to show all possible attributes
        Map<String, Object> myMap = selectedObject.getConfiguration().getDefinedAttributes();
        System.out.println("This is the saved attributes " + myMap);
        for(String key: myMap.keySet()){
            var value = myMap.get(key);
            if(key.toLowerCase().contains("thumbnail") || key.toLowerCase().contains("imagepath")){
                Label myLabel = new Label(key);
                TextField myTextField = new TextField();
                String myValue = (String) value;
                myTextField.setText(myValue.toString());
                Button chooseImageButton = new Button("Choose Image");

                var nameAndTfBar = new HBox();
                nameAndTfBar.getChildren().addAll(myLabel, myTextField, chooseImageButton);
                chooseImageButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                    //TODO DO Errorchecking/Refactor
                    @Override
                    public void handle(MouseEvent event) {
                        FileChooser fileChooser = new FileChooser();
                        File selectedFile = fileChooser.showOpenDialog(popupwindow);
                        String filepath = selectedFile.toString();
                        myTextField.setText(filepath);
                        myAttributesMap.put(key, filepath);

                    }
                }));
                layout.getChildren().addAll(nameAndTfBar);

            }
            else if(value instanceof String || value.getClass().isPrimitive()){
                Label myLabel = new Label(key);
                TextField myTextField = new TextField();
                myTextField.setText(value.toString());
                Button confirmButton = new Button("Confirm");


                HBox nameAndTfBar = new HBox();
                nameAndTfBar.getChildren().addAll(myLabel, myTextField, confirmButton);
                confirmButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                    //TODO DO Errorchecking/Refactor
                    @Override
                    public void handle(MouseEvent event) {
                        if(value.getClass().getName().equals("int")){
                            myAttributesMap.put(key, Integer.parseInt(myTextField.getText()));
                        }
                        else if(value.getClass().getName().equals("long")){
                            myAttributesMap.put(key, Long.parseLong(myTextField.getText()));
                        }
                        else if(value.getClass().getName().equals("double")){
                            myAttributesMap.put(key, Double.parseDouble(myTextField.getText()));
                        }
                        else if(value.getClass().getName().equals("boolean")){
                            myAttributesMap.put(key, Boolean.parseBoolean(myTextField.getText()));
                        }
                        else{
                            myAttributesMap.put(key, myTextField.getText());
                        }
                    }
                }));
                allButton.add(confirmButton);
                layout.getChildren().addAll(nameAndTfBar);
            }

            //TODO Handle Paths
            else if(value.getClass().isInstance(Paths.class)){
                Label myLabel = new Label(key);
                TextField pathTf = new TextField();
                File myPath = (File) value;
                pathTf.setText(myPath.getPath());
                Button fileUploadButton = new Button("Upload Image");
                FileChooser fileChooser = new FileChooser();
                fileUploadButton.setOnMouseClicked(e -> {

                    File selectedFile = fileChooser.showOpenDialog(popupwindow);
                    if (selectedFile != null) {
                        myAttributesMap.put(key, selectedFile);
                    }
                });
                layout.getChildren().add(fileUploadButton);

            }

            //handle single object
            //this single object is a link to others so it doesn't save anything to the myAttributesMap
            else if(!value.getClass().isArray()){

                Button myButton = new Button("Configure " + value.getClass().getSimpleName());
                myButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            Class<?> clazz = Class.forName(value.getClass().getName());
                            //TODO Find a way to display the existing map
                            if(clazz.getSimpleName().equals("MapConfig")) {
                                ConfigurableMap configurableMap = new ConfigurableMap(myAttributesMap);
                                configurableMap.setConfigurations();
                            }
                            //TODO Find a way to handle view
                            else if(clazz.getSimpleName().equals("View")){
//                                FileChooser fileChooser = new FileChooser();
//                                File selectedFile = fileChooser.showOpenDialog(popupwindow);
//                                String filepath = selectedFile.toString();
//                                myAttributesMap.put(key, filepath);
//                                Constructor<?> cons = clazz.getConstructor(Configurable.class);
//                                var object = cons.newInstance(myConfigurable);
//                                createConfigurable((Configurable) object);
                            }

                            else{
                                //TODO idf clazz does not taken in myconfigurable as a parameter, then error
                                showTheScreen((Configurable) value);}
                        } catch (Exception e) {
                            //TODO ErrorChecking
                            e.printStackTrace();
                        }

                    }
                }));
                layout.getChildren().add(myButton);
            }

            //handle list
            else if(value.getClass().isArray() ) {
                //TODO Check if this if statement works
                Object[] myArray = (Object[]) value;
                if(value.getClass().getComponentType().isInstance(Configurable.class)) {

                    List<Object> tempList = new ArrayList<>();
                    //Add existing objects to the templist
                    for(int a=0; a<myArray.length ; a++){
                        tempList.add(myArray[a]);
                    }

                    Label listLabel = new Label("Add new " + value.toString() + " here");
                    VBox tempVBOx  = new VBox();
                    tempVBOx.setSpacing(10);
                    var buttonBar = new HBox();
                    buttonBar.setSpacing(10);
                    Button addNew = new Button("add new " + myArray.getClass().getComponentType().toString());
                    Button confirmButton = new Button("Confirm");

                    buttonBar.getChildren().addAll(addNew, confirmButton);
                    //Add existing objects to sourceview
                    ListView sourceView = new ListView<>();
                    for(int a=0; a<myArray.length ; a++){
                        sourceView.getItems().add(myArray[a].getClass().getSimpleName() + sourceView.getItems().size() + a);
                    }

                    addNew.setOnMouseClicked((new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            //adds to the visual
                            sourceView.getItems().add(value.getClass().getComponentType().getSimpleName() + (sourceView.getItems().size() + 1));
                            try {
                                //adds to the list
                                Class<?> cl = Class.forName(value.getClass().getComponentType().getName());
                                Constructor<?> cons = cl.getConstructor(selectedObject.getClass());
                                var object = cons.newInstance(selectedObject);
                                tempList.add(object);

                            } catch (Exception e) {

                            }

                        }
                    }));

                    sourceView.setOnMouseClicked((new EventHandler<MouseEvent>() {

                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                                if (mouseEvent.getClickCount() == 2) {
                                    try {
                                        Class<?> cl = Class.forName(value.getClass().getComponentType().getName());
                                        //TODO Use reflection to check this
                                        if(cl.getSimpleName().contains("Behavior")){
                                            Field myField = cl.getDeclaredField("IMPLEMENTING_BEHAVIORS");
                                            List<Class> behaviorList = (List<Class>) myField.get(null);
                                            //ConfigureBehavior configureBehavior = new ConfigureBehavior(myGameController, selectedObject, myAttributesMap, behaviorList);
                                        }
                                        else{
                                            showTheScreen((Configurable) tempList.get(sourceView.getSelectionModel().getSelectedIndex()));
                                        }

                                    } catch (Exception e) {
                                        //TODO Errorchecking
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
                                Class c = Class.forName(value.getClass().getComponentType().getName());
                                Object[] ob = (Object[]) Array.newInstance(c, tempList.size());
                                if (selectedObject instanceof Level){
                                    System.out.println(((Level) selectedObject).getMyMapConfig());
                                }
                                for(int a=0; a<tempList.size() ; a++){
                                    ob[a] = tempList.get(a);
                                }
                                myAttributesMap.put(key, ob);
                            }
                            catch (ClassNotFoundException e){

                            }
                        }
                    }));
                    allButton.add(confirmButton);
                    tempVBOx.getChildren().addAll(listLabel, sourceView, buttonBar);
                    layout.getChildren().add(tempVBOx);
                }
            }
        }

        Button setButton = new Button("Save Changes");
        setButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO Should close the screen but shows that game configuration is not complete
                /*if(!myConfigurable.getConfiguration().isConfigurationComplete()){
                    System.out.println(myAttributesMap);
                    System.out.println(myConfigurable.getConfiguration().getAttributes());
                    Alert alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setContentText("Atrributtes not all filled out");
                    alert.showAndWait();
                }
                else {*/
//                    System.out.println(myAttributesMap);
//                    System.out.println(myConfigurable.getConfiguration().getAttributes());
                for(Button button: allButton){
                    button.fireEvent(event);
                }
                selectedObject.getConfiguration().setAllAttributes(myAttributesMap);
                popupwindow.close();

            }
        }));


        Scene scene= new Scene(layout, 500, 500);
        popupwindow.setScene(scene);
        popupwindow.showAndWait();
    }
}
