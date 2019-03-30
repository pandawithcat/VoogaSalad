package GUI.GameAuthoringEnvironment.AuthoringComponents;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class NewGameButton extends ButtonComponent {

    public NewGameButton(){
        Image saveImage = new Image(getClass().getResourceAsStream("/ButtonImages/Save"));
        myButton.setGraphic(new ImageView(saveImage));
    }
}
