package Player.Buttons;

import javafx.scene.control.Button;

public class HomeButton extends Button {

    public HomeButton(){
        setOnAction(e -> System.out.println("Home Button pressed"));
    }
}
