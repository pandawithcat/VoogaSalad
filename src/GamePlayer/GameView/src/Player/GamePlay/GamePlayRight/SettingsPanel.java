package Player.GamePlay.GamePlayRight;

import Player.GamePlay.Buttons.HomeButton;
import Player.GamePlay.Buttons.SaveButton;
import Player.GamePlay.SelectionInterface;
import Player.SetUp.GameSelection;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class SettingsPanel extends HBox {

    private HomeButton homeButton;
    private SettingsButton settingsButton;
    private SaveButton saveButton;

    public SettingsPanel(double width, double height, SelectionInterface home, MediaPlayer mediaPlayer){
        setPadding(new Insets(10, 10, 10, 10));
        setSpacing(10);
        setPrefHeight(height);
        setPrefWidth(width);
        homeButton = new HomeButton(width/3,height);
        homeButton.setOnAction(e->{
            QuitConfirmation quit = new QuitConfirmation(home);
        });
        settingsButton = new SettingsButton(width/3,height, mediaPlayer);
        saveButton = new SaveButton(width/3,height);
        getChildren().addAll(homeButton, settingsButton, saveButton);
    }
}
