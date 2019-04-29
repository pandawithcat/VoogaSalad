package GameAuthoringEnvironment.AuthoringComponents;

import BackendExternalAPI.Model;
import Configs.Configurable;
import ExternalAPIs.AuthoringData;
import GameAuthoringEnvironment.AuthoringScreen.GameController;
import GameAuthoringEnvironment.AuthoringScreen.ImageBox;
import GameAuthoringEnvironment.AuthoringScreen.ScreenSize;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ConfigureImage {
    private Text configuredPrompt;
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
    private void addImageID(Integer integer){
        myTextField.setText(Integer.toString(integer));
        configuredPrompt.setText("Your Image has been Configured!");
    }

    private void setContent(){
        configuredPrompt = new Text("");
        configuredPrompt.setFont(Font.font("Veranda", FontWeight.BOLD, 50));
        popUpWindow = new Stage();
        //popUpWindow.initModality(Modality.APPLICATION_MODAL);
        popUpWindow.setTitle("Image Editor");
        layout = new VBox(10.00);
        Model model = new Model();

        //TODO CHANGE This
        //List<Integer> allImages = model.getImageOptions(AuthoringData.ImageType.TERRAIN);
        List<Integer> allImages = model.getImageOptions(myImageType);
        List<Image> allImagesList = new ArrayList<>();
        for(int a=0; a< allImages.size(); a++) {
            allImagesList.add(model.getImage(allImages.get(a)));
        }
        for(int x = 0; x< allImages.size(); x ++){
            Image image = allImagesList.get(x);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(ScreenSize.getWidth()/3);
            imageView.setFitHeight(ScreenSize.getHeight()/3);
            layout.getChildren().add(imageView);
            Integer id = allImages.get(x);
            imageView.setOnMousePressed(e->addImageID(id));
        }
        ScrollPane sp = new ScrollPane();
        sp.setContent(layout);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(sp, configuredPrompt);
        Scene scene= new Scene(hBox, 800, 800);
        popUpWindow.setScene(scene);
        popUpWindow.show();
    }

}
