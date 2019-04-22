package Player.GamePlay.GamePlayRight;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class SettingsButton extends Button {
    private String icon = "settings.png";
    private Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(icon));
    private ImageView imageView = new ImageView(image);
    public SettingsButton(double width, double height){
        imageView.setFitWidth(width/3);
        imageView.setFitHeight(height/3);
        setGraphic(imageView);
        setPrefWidth(width/3);
        setPrefHeight(height/2);
        setPrefWidth(width);
        setOnAction(e -> displaySettings());
    }

    private Dialog displaySettings(){
        Dialog dialog = new Dialog();
        dialog.setTitle("Game Settings");
        dialog.setHeaderText("Adjust Game Settings Below");
        DialogPane dialogPane = dialog.getDialogPane();
        TextField musicInput = new TextField();
        TextField soundInput = new TextField();
        Slider sound = new Slider(0,1,0.5);
        sound.setShowTickLabels(true);
        sound.setShowTickMarks(true);
        sound.setBlockIncrement(10);
        Label l = new Label("Change the Music");
        sound.valueProperty().addListener(
                new ChangeListener<Number>() {
                    public void changed(ObservableValue<? extends Number >
                                                observable, Number oldValue, Number newValue)
                    {
                        System.out.println("New Value:" + newValue);
                        l.setText("Sound: " + newValue);
                    }
                });
        dialogPane.setContent(new VBox(20, musicInput,soundInput,sound));
        dialog.showAndWait();
        return dialog;
    }
}
