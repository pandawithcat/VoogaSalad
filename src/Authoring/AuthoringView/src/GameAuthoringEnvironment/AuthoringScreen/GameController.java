package GameAuthoringEnvironment.AuthoringScreen;

import Configs.ArsenalConfig.WeaponBehaviors.WeaponBehavior;
import Configs.Behaviors.Behavior;
import Configs.Configurable;
import Configs.Configuration;
import Configs.EnemyPackage.EnemyBehaviors.EnemyBehavior;
import Configs.GamePackage.Game;
import Configs.GamePackage.GameBehaviors.GameBehavior;
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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.*;


public class GameController {

    private Stage popupwindow;

    public GameController() {
        Game myGame = new Game();
        createConfigurable(myGame);
    }


    public void createConfigurable(Configurable myConfigurable){

        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle(myConfigurable.getClass().getSimpleName() + " Property Settings");

        System.out.println("it reached here");
        Map<String, Object> myAttributesMap = new HashMap<>();
        Map<String, Class> attributesMap = myConfigurable.getConfiguration().getAttributes();

        VBox layout = new VBox(10.00);
        VBox.setMargin(layout, new Insets(20, 20, 20, 20));

        layout.autosize();
        for (String key : attributesMap.keySet()) {
            var value = attributesMap.get(key);

            //handle primitives
            if(value.equals(java.lang.String.class) || value.equals(java.lang.Integer.class) || value.isPrimitive()){
                Label myLabel = new Label(key);
                TextField myTextField = new TextField();
                Button confirmButton = new Button("Confirm");
                var nameAndTfBar = new HBox();
                nameAndTfBar.getChildren().addAll(myLabel, myTextField, confirmButton);
                confirmButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        myAttributesMap.put(key, myTextField.getText());

                    }
                }));
                layout.getChildren().addAll(nameAndTfBar);

            }

            //handle path
            else if(value.isInstance(Paths.class)){

                Button fileUploadButton = new Button("Upload Image");

                layout.getChildren().add(fileUploadButton);
                FileChooser fileChooser = new FileChooser();
                fileUploadButton.setOnMouseClicked(e -> {

                    File selectedFile = fileChooser.showOpenDialog(popupwindow);
                    if (selectedFile != null) {
                        myAttributesMap.put(key, selectedFile);
                    }
                });

            }
            //handle single object

            else if(!value.isArray()){

                Button myButton = new Button("Configure " + value.getSimpleName());
                myButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            //special case: map
                            Class<?> clazz = Class.forName(value.getName());
                            if(clazz.getSimpleName().equals("MapConfig")) {
                                ConfigurableMap configurableMap = new ConfigurableMap(myAttributesMap);
                                configurableMap.setConfigurations();
                            }
                            else{
                                Constructor<?> cons = clazz.getConstructor(myConfigurable.getClass());
                                var object = cons.newInstance(myConfigurable);
                                System.out.println("did it run dafdadfa");
                                    System.out.println(object.getClass());
                                    createConfigurable((Configurable) object);}
                        } catch (Exception e) {}

                    }
                }));
                layout.getChildren().add(myButton);
            }

            //handle list
            else if(value.isArray()) {
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
                            sourceView.getItems().add(value.getComponentType().getSimpleName() + (sourceView.getItems().size() + 1));
                            try {
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
                                        System.out.println(value.getComponentType().getName());
                                        Class<?> cl = Class.forName(value.getComponentType().getName());
                                        List<Class> behaviorList = WeaponBehavior.IMPLEMENTING_BEHAVIORS;

                                        if(!cl.getSimpleName().contains("Behavior")){
                                            Constructor<?> cons = cl.getConstructor(myConfigurable.getClass());;
                                            var object = cons.newInstance(myConfigurable);
                                            createConfigurable((Configurable) object);
                                            }
                                        else if(cl.getSimpleName().equals("WeaponBehavior") ){
                                            behaviorList = WeaponBehavior.IMPLEMENTING_BEHAVIORS;
                                        }
                                        else if (cl.getSimpleName().equals("EnemyBehavior") ){
                                            behaviorList = EnemyBehavior.IMPLEMENTING_BEHAVIORS;
                                        }
                                        else if (cl.getSimpleName().equals("GameBehavior") ){
                                            behaviorList = GameBehavior.IMPLEMENTING_BEHAVIORS;
                                        }
                                        else if (cl.getSimpleName().equals("LevelBehavior") ){
                                            behaviorList = LevelBehavior.IMPLEMENTING_BEHAVIORS;
                                        }
                                        else if (cl.getSimpleName().equals("TerrainBehavior") ){
                                            behaviorList = TerrainBehavior.IMPLEMENTING_BEHAVIORS;
                                        }
                                        else{
                                            behaviorList = ProjectileBehavior.IMPLEMENTING_BEHAVIORS;
                                        }

                                        /*else if (cl.getSimpleName().equals("ProjectileBehavior") ){
                                            behaviorList = ProjectileBehavior.IMPLEMENTING_BEHAVIORS;
                                        }*/


                                       /* else if (cl.getSimpleName().equals("ShooterBehavior") ){
                                            behaviorList = ShooterBehavior.IMPLEMENTING_BEHAVIORS;
                                        }*/

                                       ConfigureBehavior configureBehavior = new ConfigureBehavior(myAttributesMap, behaviorList);
                                       System.out.println("did it run");

                                    } catch (Exception e) {

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
                if(!myConfigurable.getConfiguration().isConfigurationComplete()){
                    Alert alert = new Alert(Alert.AlertType.NONE);
                    alert.setAlertType(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setContentText("Atrributtes not all filled out");
                    alert.showAndWait();
                }
                else {
                    myConfigurable.getConfiguration().setAllAttributes(myAttributesMap);
                    popupwindow.close();
                }
            }
        }));

        layout.getChildren().add(setButton);

        Scene scene= new Scene(layout, 500, 500);
        popupwindow.setScene(scene);
        popupwindow.showAndWait();

    }



}
