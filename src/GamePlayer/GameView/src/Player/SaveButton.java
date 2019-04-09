package Player;

import javafx.scene.control.Button;

public class SaveButton extends Button {
    public SaveButton(double width, double height){
        setPrefHeight(height);
        setPrefWidth(width);
        setOnAction(e -> System.out.println("Save Button pressed"));

    }
}
