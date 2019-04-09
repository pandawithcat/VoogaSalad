package GameAuthoringEnvironment.AuthoringScreen;

import Configs.Configurable;
import Configs.Configuration;
import Configs.GamePackage.Game;
import GameAuthoringEnvironment.AuthoringComponents.DataHandleComponents.PrimitiveContainer;
import GameAuthoringEnvironment.AuthoringScreen.Editors.MapEditor;
import javafx.event.EventHandler;
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
import java.io.ObjectInputFilter;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.CheckedOutputStream;

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


        Map<String, Object> myAttributesMap = new HashMap<>();
        Map<String, Class> attributesMap = myConfigurable.getConfiguration().getAttributes();
        System.out.println(attributesMap);
        VBox layout = new VBox();
        for (String key : attributesMap.keySet()) {
            var value = attributesMap.get(key);

            //special case: map
            if(value.getClass().isInstance(MapEditor.class)){

            }

            //handle primitives
            if(value.equals(java.lang.String.class) || value.equals(java.lang.Integer.class) || value.equals(java.lang.Boolean.class)){
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

            else if(!value.isArray() && value.getClass().isInstance(Configuration.class)){

                Button myButton = new Button("Configure " + value.getSimpleName());
                myButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            Object object = Class.forName(value.getName());
                            if (object.getClass().isInstance(Configurable.class)){
                                createConfigurable((Configurable) object);
                            }
                        } catch (Exception e) {//TODO Write Some errors here
                        }

                    }
                }));
                layout.getChildren().add(myButton);
            }

            //handle list
            else if(value.isArray()) {
                if(value.getComponentType().getClass().isInstance(Configurable.class)) {

                    VBox tempVBOx  = new VBox();
                    var buttonBar = new HBox();
                    Button addNew = new Button("add new " + value.getComponentType().getSimpleName());
                    Button confirmButton = new Button("Confirm");
                    buttonBar.getChildren().addAll(addNew, confirmButton);
                    ListView sourceView = new ListView<>();
                    confirmButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            myAttributesMap.put(key, sourceView.getItems());
                        }
                    }));

                    //make new screen pop up when each level clicked

                    sourceView.setOnMouseClicked((new EventHandler<MouseEvent>() {

                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                                if (mouseEvent.getClickCount() == 2) {
                                    //TODO MAKE This Part Run
                                    try {
                                        Class<?> cl = Class.forName(value.getComponentType().getName());
                                        Constructor<?> cons = cl.getConstructor(myConfigurable.getClass());
                                        Object object = cons.newInstance(myConfigurable);
                                        createConfigurable((Configurable) object);
                                    } catch (Exception e) {

                                    }
                                }
                            }
                        }
                    }));
                    addNew.setOnMouseClicked((new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            sourceView.getItems().add(value.getComponentType().getSimpleName());

                        }
                    }));

                    tempVBOx.getChildren().addAll(sourceView, buttonBar);
                    layout.getChildren().add(tempVBOx);
                }
            }
        }

        Button setButton = new Button("This config completed");
        setButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
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
