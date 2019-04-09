package Player;

import javafx.scene.layout.HBox;

public class SettingsPanel extends HBox {

    private HomeButton homeButton;
    private SettingsButton settingsButton;
    private SaveButton saveButton;

    public SettingsPanel(double width, double height){
        setPrefHeight(height);
        setPrefWidth(width);
        homeButton = new HomeButton(width/3,height/2);
        settingsButton = new SettingsButton(width/3,height/2);
        saveButton = new SaveButton(width/3,height/2);
        getChildren().add(homeButton);
        getChildren().add(settingsButton);
        getChildren().add(saveButton);

    }
}
