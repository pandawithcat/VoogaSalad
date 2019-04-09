package Player.Buttons;

import javafx.scene.control.Button;
import javafx.stage.Popup;

public class SettingsButton extends Button {

    public SettingsButton(){
        setOnAction(e -> displaySettings());
    }

    private Popup displaySettings(){
        return new Popup();
    }
}
