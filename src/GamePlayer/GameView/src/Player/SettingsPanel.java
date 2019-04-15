package Player;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

public class SettingsPanel extends HBox {

    private HomeButton homeButton;
    private SettingsButton settingsButton;
    private SaveButton saveButton;

    public SettingsPanel(double width, double height){
        setPadding(new Insets(10, 10, 10, 10));
        setSpacing(10);
        setPrefHeight(height);
        setPrefWidth(width);
        homeButton = new HomeButton(width/3,height);
        settingsButton = new SettingsButton(width/3,height);
        saveButton = new SaveButton(width/3,height);
        getChildren().addAll(homeButton, settingsButton, saveButton);
    }
}
