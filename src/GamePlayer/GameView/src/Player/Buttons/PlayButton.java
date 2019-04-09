package Player.Buttons;

import javafx.scene.control.Button;
public class PlayButton extends Button {

    public PlayButton(){
       setOnAction(e -> System.out.println("play pressed"));
    }
}
