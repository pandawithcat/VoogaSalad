package GUI.GameAuthoringEnvironment.AuthoringComponents;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class CloseButton extends ButtonComponent{

    public CloseButton() {
        Image closeImage= new Image(getClass().getResourceAsStream("/ButtonImages/Close"));
        myButton.setGraphic(new ImageView(closeImage));
    }

}

