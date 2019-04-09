package Player;

import javafx.scene.control.Button;

public class HomeButton extends Button {

    public HomeButton(double width, double height){
        setPrefHeight(height);
        setPrefWidth(width);
        setOnAction(e -> System.out.println("Home Button pressed"));
    }
}
