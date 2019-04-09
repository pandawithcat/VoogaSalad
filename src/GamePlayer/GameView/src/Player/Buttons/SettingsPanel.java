package Player.Buttons;

import javafx.scene.layout.HBox;

public class SettingsPanel extends HBox {

    private HomeButton homeButton;
    private SettingsButton settingsButton;
    private SaveButton saveButton;

    public SettingsPanel(){
        homeButton = new HomeButton();
        settingsButton = new SettingsButton();
        saveButton = new SaveButton();
        getChildren().add(homeButton);
        getChildren().add(homeButton);
        getChildren().add(homeButton);

    }
}
