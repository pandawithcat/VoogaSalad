package Player;

import javafx.scene.control.Button;
public class PlayButton extends Button {

    public PlayButton(double width, double height){
        setMinWidth(width);
        setMinHeight(height);
        setOnAction(e -> System.out.println("play pressed"));
    }
}
