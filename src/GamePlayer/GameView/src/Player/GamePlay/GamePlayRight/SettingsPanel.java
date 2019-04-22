package Player.GamePlay.GamePlayRight;

import Player.GamePlay.Buttons.HomeButton;
import Player.GamePlay.Buttons.SaveButton;
import Player.SetUp.GameSelection;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SettingsPanel extends HBox {

    private HomeButton homeButton;
    private SettingsButton settingsButton;
    private SaveButton saveButton;

    public SettingsPanel(double width, double height, Stage stage){
        setPadding(new Insets(10, 10, 10, 10));
        setSpacing(10);
        setPrefHeight(height);
        setPrefWidth(width);
        homeButton = new HomeButton(width/3,height);
        homeButton.setOnAction(e->{
            stage.close();
            GameSelection gameSelection = new GameSelection();
            gameSelection.start(new Stage());
        });
        settingsButton = new SettingsButton(width/3,height);
        saveButton = new SaveButton(width/3,height);
        getChildren().addAll(homeButton, settingsButton, saveButton);
    }
}
