package GameAuthoringEnvironment.AuthoringScreen;

import Configs.ArsenalConfig.WeaponBehaviors.WeaponBehavior;
import Configs.Behaviors.Behavior;
import Configs.Configurable;
import Configs.Configuration;
import Configs.EnemyPackage.EnemyBehaviors.EnemyBehavior;
import Configs.GamePackage.Game;
import Configs.GamePackage.GameBehaviors.GameBehavior;
import Configs.LevelPackage.Level;
import Configs.LevelPackage.LevelBehaviors.LevelBehavior;
import Configs.MapPackage.MapConfig;
import Configs.MapPackage.TerrainBehaviors.TerrainBehavior;
import Configs.ProjectilePackage.ProjectileBehaviors.ProjectileBehavior;
import GameAuthoringEnvironment.AuthoringScreen.Editors.MapEditor;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.*;


public class GameController {

    private Stage popupwindow;
    private GameController myGameController;
    private Game myGame;
    AuthoringVisualization myAuthoringVisualization;

    public GameController() {
        myGameController = this;
        myGame = new Game();
        createConfigurable(myGame);
    }

    public Game getMyGame(){
        return myGame;
    }


    public void createConfigurable(Configurable myConfigurable){

        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle(myConfigurable.getClass().getSimpleName() + " Property Settings");

        Map<String, Object> myAttributesMap = new HashMap<>();
        Map<String, Class> attributesMap = myConfigurable.getConfiguration().getAttributes();

        VBox layout = new VBox(10.00);
        VBox.setMargin(layout, new Insets(20, 20, 20, 20));

        for (String key : attributesMap.keySet()) {
            var value = attributesMap.get(key);

            //handle primitives
            if(value.equals(java.lang.String.class) || value.isPrimitive()){
                Label myLabel = new Label(key);
                TextField myTextField = new TextField();
                Button confirmButton = new Button("Confirm");
                var nameAndTfBar = new HBox();
                nameAndTfBar.getChildren().addAll(myLabel, myTextField, confirmButton);
                confirmButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                    //TODO DO Errorchecking/Refactor
                    @Override
                    public void handle(MouseEvent event) {
                        if(value.equals(java.lang.Integer.class)){
                            myAttributesMap.put(key, Integer.parseInt(myTextField.getText()));
                        }
                        else if(value.equals(java.lang.Long.class)){
                            myAttributesMap.put(key, Long.parseLong(myTextField.getText()));
                        }
                        else if(value.equals(java.lang.Double.class)){
                            myAttributesMap.put(key, Double.parseDouble(myTextField.getText()));
                        }
                        else if(value.equals(java.lang.Boolean.class)){
                            myAttributesMap.put(key, Boolean.parseBoolean(myTextField.getText()));
                        }
                        else{
                            myAttributesMap.put(key, myTextField.getText());
                        }
                    }
                }));
                layout.getChildren().addAll(nameAndTfBar);
            }

            //Handle Paths
            else if(value.isInstance(Paths.class)){

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
            else if(!value.isArray()){

                Button myButton = new Button("Configure " + value.getSimpleName());
                myButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            Class<?> clazz = Class.forName(value.getName());
                            //special case: map TODO use reflection for this
                            if(clazz.getSimpleName().equals("MapConfig")) {
                                System.out.println("adfa");
                                ConfigurableMap configurableMap = new ConfigurableMap(myAttributesMap);
                                configurableMap.setConfigurations();
                            }
                            else{
                                Constructor<?> cons = clazz.getConstructor(myConfigurable.getClass());
                                var object = cons.newInstance(myConfigurable);
                                createConfigurable((Configurable) object);}
                        } catch (Exception e) {
                            //TODO ErrorChecking
                        }

                    }
                }));
                layout.getChildren().add(myButton);
            }

            //handle list
            else if(value.isArray() ) {
                if(value.getComponentType().getClass().isInstance(Configurable.class)) {

                    List<Object> tempList = new ArrayList<>();
                    Label listLabel = new Label("Add new " + value.getComponentType().getSimpleName() + " here");
                    VBox tempVBOx  = new VBox();
                    tempVBOx.setSpacing(10);
                    var buttonBar = new HBox();
                    buttonBar.setSpacing(10);
                    Button addNew = new Button("add new " + value.getComponentType().getSimpleName());
                    Button confirmButton = new Button("Confirm");
                    buttonBar.getChildren().addAll(addNew, confirmButton);
                    ListView sourceView = new ListView<>();

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
                                       /* System.out.println(cl.getSimpleName());
                                        System.out.println(cl.getClasses());
                                        System.out.println(value.getComponentType().getLabel());*/
                                        //TODO Use reflection to check this
                                        if(cl.getSimpleName().contains("Behavior")){
                                            Field myField = cl.getDeclaredField("IMPLEMENTING_BEHAVIORS");
                                            List<Class> behaviorList = (List<Class>) myField.get(null);
                                            ConfigureBehavior configureBehavior = new ConfigureBehavior(myGameController, myConfigurable, myAttributesMap, behaviorList);
                                        }
                                        else{
                                            Constructor<?> cons = cl.getConstructor(myConfigurable.getClass());
                                            var object = cons.newInstance(myConfigurable);
                                            createConfigurable((Configurable) object);
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
                            myAttributesMap.put(key, tempList);
                        }
                    }));

                    tempVBOx.getChildren().addAll(listLabel, sourceView, buttonBar);
                    layout.getChildren().add(tempVBOx);
                }
            }
        }

        Button setButton = new Button("This config completed");
        setButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
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
                    System.out.println(myAttributesMap);
                    System.out.println(myConfigurable.getConfiguration().getAttributes());
                    myConfigurable.getConfiguration().setAllAttributes(myAttributesMap);
                    popupwindow.close();

            }
        }));

        layout.getChildren().add(setButton);

        Scene scene= new Scene(layout, 500, 500);
        popupwindow.setScene(scene);
        popupwindow.showAndWait();

    }



}
