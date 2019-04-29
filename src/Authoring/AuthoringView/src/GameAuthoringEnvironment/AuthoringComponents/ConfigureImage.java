package GameAuthoringEnvironment.AuthoringComponents;

import BackendExternalAPI.Model;
import Configs.Configurable;
import ExternalAPIs.AuthoringData;
import GameAuthoringEnvironment.AuthoringScreen.GameController;
import GameAuthoringEnvironment.AuthoringScreen.ImageBox;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ConfigureImage {

    List<Class> myList;
    Map<String, Object> myMap;
    Stage popUpWindow;
    VBox layout;
    Configurable myConfigurable;
    GameController myGameController;
    List<Integer> allImages;
    TextField myTextField;
    AuthoringData.ImageType myImageType;

    public ConfigureImage(TextField textField, String s){
        myTextField = textField;
        myImageType = AuthoringData.ImageType.valueOf(s.toUpperCase());
        setContent();

    }

    private void setContent(){
        popUpWindow = new Stage();
        //popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Image Editor");
        layout = new VBox(10.00);
        Model model = new Model();


        Label imageLabel = new Label("Selected Image");
        TextField imageTf = new TextField();

        VBox nameVbox = new VBox(10);
        nameVbox.getChildren().addAll(imageLabel, imageTf);

        VBox imageVbox = new VBox(10);

        //TODO CHANGE This
        //List<Integer> allImages = model.getImageOptions(AuthoringData.ImageType.TERRAIN);
        List<Integer> allImages = model.getImageOptions(myImageType);
        List<Image> allImagesList = new ArrayList<>();
        for(int a=0; a< allImages.size(); a++) {

            allImagesList.add(model.getImage(allImages.get(a)));

        }


        for(int a=0; a< allImages.size(); a++) {

            ImageView imageView = new ImageView(allImagesList.get(a));
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            imageVbox.getChildren().add(imageView);
            imageView.setOnMouseClicked((new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    if (event.getButton().equals(MouseButton.PRIMARY)) {
                        if (event.getClickCount() == 2) {

                            imageTf.setText("");
                            myTextField.setText("");
                            }

                        }

                }
            }));
            imageVbox.getChildren().add(imageView);
        }


        layout.getChildren().addAll(nameVbox, imageVbox);
        Scene scene= new Scene(layout, 800, 800);
        popUpWindow.setScene(scene);
        popUpWindow.show();
    }

}
