package Player;

import javafx.scene.layout.VBox;

public class ButtonPanel extends VBox {

    private PlayButton myPlayButton;
    private SettingsPanel mySettingsPanel;

    public ButtonPanel(double width, double height){
        myPlayButton = new PlayButton(width, height/2);
        mySettingsPanel = new SettingsPanel(width, height/2);
        getChildren().add(myPlayButton);
        getChildren().add(mySettingsPanel);
    }
}
