package Player.GamePlay.GamePlayRight;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Window;

public class SettingsButton extends Button {
    private String icon = "settings.png";
    private Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(icon));
    private ImageView imageView = new ImageView(image);
    private MediaPlayer mediaPlayer;
    public SettingsButton(double width, double height, MediaPlayer mediaPlayer){
        this.mediaPlayer = mediaPlayer;
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
        setupTicker(sound);
        sound.valueProperty().addListener((observable, oldValue, newValue) -> checkVolumeChange(newValue));
        dialogPane.setContent(new VBox(20,musicInput,soundInput, new Label("Sound Level"),  sound));
        dialog.show();
        Window window = dialog.getDialogPane().getScene().getWindow();
        window.setOnCloseRequest(event -> window.hide());
        return dialog;
    }

    private void setupTicker(Slider sound){
        sound.setShowTickLabels(true);
        sound.setShowTickMarks(true);
        sound.setBlockIncrement(10);
        sound.setMajorTickUnit(0.25f);
        sound.setBlockIncrement(0.1f);
    }

    private void checkVolumeChange(Number newValue){
        double vol = newValue.doubleValue();
        mediaPlayer.setVolume(vol);
    }
}
