package GameAuthoringEnvironment.AuthoringScreen;

import Configs.Configurable;
import Configs.GamePackage.Game;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.lang.reflect.*;
import java.util.*;
import java.util.List;


public class GameController {

    private GameController myGameController;
    private Game myGame;
    private Map<String, List<Object>> configuredObjects;

    public GameController() throws NoSuchFieldException {
        myGameController = this;
        myGame = new Game();
        configuredObjects = new HashMap<>();
        createConfigurable(myGame);
    }

    public Game getMyGame(){
        return myGame;
    }


    public void createConfigurable(Configurable myConfigurable) throws NoSuchFieldException {

        Stage popupwindow = new Stage();

        List<Button> allButton = new ArrayList<>();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle(myConfigurable.getClass().getSimpleName() + " Property Settings");

        VBox layout = new VBox(10.00);
        VBox.setMargin(layout, new Insets(20, 20, 20, 20));

        Map<String, Object> myAttributesMap = displayScreens(myConfigurable, popupwindow, allButton, layout);

        Button setButton = setConfigureCompleteButton(myConfigurable, popupwindow, allButton, myAttributesMap);

        layout.getChildren().add(setButton);

        Scene scene= new Scene(layout, 500, 500);
        popupwindow.setScene(scene);
        popupwindow.showAndWait();

    }

    private Map<String, Object> displayScreens(Configurable myConfigurable, Stage popupwindow, List<Button> allButton, VBox layout) throws NoSuchFieldException {
        Map<String, Object> myAttributesMap = new HashMap<>();
        Map<String, Class> attributesMap = myConfigurable.getConfiguration().getAttributes();

        for (String key : attributesMap.keySet()) {
            var value = attributesMap.get(key);

            //handle booleans
            if(value.equals(boolean.class)){
                handleBooleanField(allButton, layout, myAttributesMap, key);
            }

            //handle special case: require image
            else if(key.toLowerCase().contains("thumbnail") || key.toLowerCase().contains("imagepath")){
                handleImageField(popupwindow, allButton, layout, myAttributesMap, key);

            }
            //handle string and primitives except boolean
            else if(value.equals(String.class) || value.isPrimitive()){
                handlePrimitivesAndString(allButton, layout, myAttributesMap, key, value);
            }

            //handle single object
            else if(!value.isArray()){
                handleSingleObject(myConfigurable, layout, myAttributesMap, key, value);
            }

            //handle Array
            else if(value.isArray() && value.getComponentType().getClass().isInstance(Configurable.class)) {
                    handleConfigurableArray(myConfigurable, allButton, layout, myAttributesMap, key, value);
                }
            }
        return myAttributesMap;
    }

    private Button setConfigureCompleteButton(Configurable myConfigurable, Stage popupwindow, List<Button> allButton, Map<String, Object> myAttributesMap) {
        Button setButton = new Button("This config completed");
        setButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO(Hyunjae) Should tell the user what attribute is missing
                if(!myConfigurable.getConfiguration().isConfigurationComplete()){
                    Alert alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setContentText("Atrributtes not all filled out");
                    alert.showAndWait();
                }
                else {

                    for (Button button : allButton) {
                        button.fireEvent(event);
                    }
                    myConfigurable.getConfiguration().setAllAttributes(myAttributesMap);
                    popupwindow.close();
                }

            }
        }));
        return setButton;
    }

    private void handleConfigurableArray(Configurable myConfigurable, List<Button> allButton, VBox layout, Map<String, Object> myAttributesMap, String key, Class value) {
        List<Object> tempList = new ArrayList<>();
        Label listLabel = new Label("Add new " + value.getComponentType().getSimpleName() + " here");
        VBox tempVBOx  = new VBox();
        tempVBOx.setSpacing(10);
        var buttonBar = new HBox();
        buttonBar.setSpacing(10);
        Button addNew = new Button("Add new " + value.getComponentType().getSimpleName());
        Button confirmButton = new Button("Confirm");
        Button removeButton = new Button("Remove");

        buttonBar.getChildren().addAll(addNew, confirmButton, removeButton);
        ListView sourceView = new ListView<>();
        if(configuredObjects.get(key) != null){
            tempList.addAll(configuredObjects.get(key));
            for(Object object: configuredObjects.get(key)){
                Configurable temp = (Configurable) object;
                sourceView.getItems().add(temp.getClass().getSimpleName());
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
                if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        try {

                            Class<?> cl = Class.forName(value.getComponentType().getName());
                            if(cl.getSimpleName().contains("Behavior")){
                                Field myField = cl.getDeclaredField("IMPLEMENTING_BEHAVIORS");
                                List<Class> behaviorList = (List<Class>) myField.get(null);
                                ConfigureBehavior configureBehavior = new ConfigureBehavior(myGameController, myConfigurable, myAttributesMap, behaviorList);
                            }
                            else{
                                createConfigurable((Configurable) tempList.get(sourceView.getSelectionModel().getSelectedIndex()));
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
                    for(int a=0; a<tempList.size() ; a++){
                        ob[a] = tempList.get(a);
                    }
                    myAttributesMap.put(key, ob);
                    List<Object> newObjects = Arrays.asList(ob);
                    if(configuredObjects.get(key) != null){
                        configuredObjects.get(key).addAll(newObjects);
                    }else{
                        configuredObjects.put(key, newObjects);
                    }
                    System.out.println(configuredObjects.keySet());
                }
                catch (ClassNotFoundException e){
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

    private void handleSingleObject(Configurable myConfigurable, VBox layout, Map<String, Object> myAttributesMap, String key, Class value) throws NoSuchFieldException {
        Button myButton = null;
        /*try {
            myButton = new Button("Configure " + value.getDeclaredField("myLabel").get(null));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/
        myButton = new Button("Configure " + value.getSimpleName());
        myButton.setOnMouseClicked((new EventHandler<>() {
            @Override
            public void handle(MouseEvent event) {
                handleSingleObjectReflection(value, myAttributesMap, myConfigurable, key);

            }
        }));
        layout.getChildren().add(myButton);
    }

    private void handleSingleObjectReflection(Class value, Map<String, Object> myAttributesMap, Configurable myConfigurable, String key) {
        try {
            Class<?> clazz = Class.forName(value.getName());
            //special case: map
            if (clazz.getSimpleName().equals("MapConfig")) {
                ConfigurableMap configurableMap = new ConfigurableMap(myAttributesMap, myConfigurable);
                configurableMap.setConfigurations();
            } else if (clazz.getSimpleName().equals("View")) {
                Constructor<?> cons = clazz.getConstructor(Configurable.class);
                var object = cons.newInstance(myConfigurable);
                createConfigurable((Configurable) object);
                myAttributesMap.put(key, object);
                //TODO Maybe a dropdown menu?
            } else if(clazz.getSimpleName().toLowerCase().contains("behavior")){
                System.out.println("STOP HERE" + value.getSimpleName());
                Field myField = clazz.getDeclaredField("IMPLEMENTING_BEHAVIORS");
                List<Class> behaviorList = (List<Class>) myField.get(null);
                ConfigureBehavior configureBehavior = new ConfigureBehavior(myGameController, myConfigurable, myAttributesMap, behaviorList);
            }
            else {
                Constructor<?> cons = clazz.getConstructor(myConfigurable.getClass());
                var object = cons.newInstance(myConfigurable);
                createConfigurable((Configurable) object);
                myAttributesMap.put(key, object);
            }

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchFieldException e) {
            //TODO ErrorChecking
            e.printStackTrace();
        }
    }

    private void handlePrimitivesAndString(List<Button> allButton, VBox layout, Map<String, Object> myAttributesMap, String key, Class value) {
        Label myLabel = new Label(key);
        TextField myTextField = new TextField();
        Button confirmButton = new Button("Confirm");


        var nameAndTfBar = new HBox();
        nameAndTfBar.getChildren().addAll(myLabel, myTextField, confirmButton);
        confirmButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
            //TODO DO Errorchecking/Refactor
            @Override
            public void handle(MouseEvent event) {
                if(value.getName().equals("int")){
                    Integer a = Integer.parseInt(myTextField.getText());
                    myAttributesMap.put(key, a.intValue());
                }
                else if(value.getName().equals("long")){
                    Long b = Long.parseLong(myTextField.getText());
                    myAttributesMap.put(key, b.longValue());
                }
                else if(value.getName().equals("double")){
                    Double c =Double.parseDouble(myTextField.getText());
                    myAttributesMap.put(key, c.doubleValue());
                }
                else{
                    myAttributesMap.put(key, myTextField.getText());
                }
            }
        }));
        allButton.add(confirmButton);
        layout.getChildren().addAll(nameAndTfBar);
    }

    private void handleImageField(Stage popupwindow, List<Button> allButton, VBox layout, Map<String, Object> myAttributesMap, String key) {
        Label myLabel = new Label(key);
        TextField myTextField = new TextField();
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

    private void handleBooleanField(List<Button> allButton, VBox layout, Map<String, Object> myAttributesMap, String key) {
        HBox box = new HBox(10);
        Label myLabel = new Label(key);
        RadioButton trueButton = new RadioButton("True");
        RadioButton falseButton = new RadioButton("False");
        Button confirmButton = new Button("Confirm");
        box.getChildren().addAll(myLabel, trueButton, falseButton);
        confirmButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                    if(trueButton.isSelected()) {
                        myAttributesMap.put(key, true);
                    }
                    else {
                        myAttributesMap.put(key, false);
                    }

            }
        }));
        allButton.add(confirmButton);
        layout.getChildren().add(box);
    }


}
