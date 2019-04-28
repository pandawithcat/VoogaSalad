package GameAuthoringEnvironment.AuthoringComponents;

import BackendExternalAPI.Model;
import Configs.Configurable;
import GameAuthoringEnvironment.AuthoringScreen.GameController;
import GameAuthoringEnvironment.AuthoringScreen.ImageBox;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
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

    public ConfigureImage(TextField textField){
        myTextField = textField;
        setContent();

    }

    private void setContent(){
        popUpWindow = new Stage();
        //popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Behavior Editor");
        layout = new VBox(10.00);
        Model model = new Model();

        Label imageLabel = new Label("Selected Image");
        TextField imageTf = new TextField();

        VBox nameVbox = new VBox(10);
        nameVbox.getChildren().addAll(imageLabel, imageTf);

        VBox imageVbox = new VBox(10);
        List<Integer> allImages = model.getImageOptions(ExternalAPIs.AuthoringData.ImageType.TERRAIN);
        for(int a=0; a< allImages.size(); a++) {

            ImageBox imageBox = new ImageBox(model.getImage(allImages.get(a)), allImages.get(a));
            //ImageView newImage = new ImageView(imageBox.getMyImage());
            imageBox.setOnMouseClicked((new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    if (event.getButton().equals(MouseButton.PRIMARY)) {
                        if (event.getClickCount() == 2) {
                            imageTf.setText(""+imageBox.getMyNumber());
                            myTextField.setText(""+imageBox.getMyNumber());
                            }

                        }

                }
            }));
            imageVbox.getChildren().add(imageBox);
        }


        layout.getChildren().addAll(nameVbox, imageVbox);
        Scene scene= new Scene(layout, 800, 800);
        popUpWindow.setScene(scene);
        popUpWindow.show();
    }

}
