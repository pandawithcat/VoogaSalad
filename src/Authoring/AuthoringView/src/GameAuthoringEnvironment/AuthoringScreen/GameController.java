package GameAuthoringEnvironment.AuthoringScreen;

import Configs.Configurable;
import Configs.Configuration;
import Configs.GamePackage.Game;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.ObjectInputFilter;
import java.lang.reflect.Array;
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

        popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        //TODO Should change the window title
        popupwindow.setTitle("What the fuck");

        Map<String, Object> myAttributesMap = new HashMap<>();
        Map<String, Class> attributesMap = myConfigurable.getConfiguration().getAttributes();
        VBox layout = new VBox();
        for (String key : attributesMap.keySet()) {
            var value = attributesMap.get(key);

            //handle primitives
            if(value.equals(java.lang.String.class) || value.equals(java.lang.Integer.class) || value.equals(java.lang.Boolean.class)){
                Label myLabel = new Label(key);
                TextField myTextField = new TextField();
                Button confirmButton = new Button("Confirm");
                layout.getChildren().addAll(myLabel, myTextField, confirmButton);
            }

            else if(value.isInstance(Paths.class)){

                Button fileUploadButton = new Button("Upload Image");
                layout.getChildren().add(fileUploadButton);
                FileChooser fileChooser = new FileChooser();
                fileUploadButton.setOnMouseClicked(e -> {

                    File selectedFile = fileChooser.showOpenDialog(popupwindow);
                    if (selectedFile != null) {
                        //TODO Save the file
                    }
                });

            }
            //handle single object

            else if(!value.isArray() && value.getClass().isInstance(Configuration.class)){

                Button myButton = new Button("Configure " + key);
                myButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        try {
                            Object myObject = value.getConstructor().newInstance();

                            if (myObject instanceof Configurable) {
                                createConfigurable((Configurable) myObject);
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
                   /* System.out.println(value);
                    System.out.println(value.getComponentType());
                    System.out.println(value.getComponentType().getName());*/
                    VBox tempVBOx  = new VBox();
                    Button addNew = new Button("add new " + key);
                    ListView sourceView = new ListView<>();

                    //make new screen pop up when each level clicked

                    sourceView.setOnMouseClicked((new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            try {
                                Object object = Class.forName(value.getComponentType().getName());
                                System.out.println(object.toString());
                                createConfigurable((Configurable) object);
                            } catch (Exception e){

                            }
                        }
                    }));
                    addNew.setOnMouseClicked((new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            sourceView.getItems().add(key);

                        }
                    }));

                    tempVBOx.getChildren().addAll(sourceView, addNew);
                    layout.getChildren().add(tempVBOx);
                }
            }
        }

        Button setButton = new Button("This config completed");
        setButton.setOnMouseClicked((new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //TODO
            }
        }));

        layout.getChildren().add(setButton);
        Scene scene= new Scene(layout, 500, 500);
        popupwindow.setScene(scene);
        popupwindow.showAndWait();

        myConfigurable.getConfiguration().setAllAttributes(myAttributesMap);
    }

    private void handlePrimitives(Configurable configurable, Map<String, Object> attributeMap, String key, Object value){
        String keyToCap = key.substring(0, 1).toUpperCase() + key.substring(1);
        try {
            Method method = configurable.getClass().getMethod("get"+keyToCap);
            Object object = method.invoke(configurable);
            attributeMap.put(key, object);
        } //TODO Error Handling needed
        catch (InvocationTargetException e) {}
        catch (IllegalArgumentException e) {}
        catch (IllegalAccessException e) {}
        catch (NoSuchMethodException e){}

    }


}
