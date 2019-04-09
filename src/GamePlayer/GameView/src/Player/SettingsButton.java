package Player;

import javafx.scene.control.Button;
import javafx.stage.Popup;

public class SettingsButton extends Button {

    public SettingsButton(double width, double height){
        setPrefHeight(height);
        setPrefWidth(width);
        setOnAction(e -> displaySettings());
    }

    private Popup displaySettings(){
        return new Popup();
    }
}
