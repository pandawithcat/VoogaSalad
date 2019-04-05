package GameAuthoringEnvironment.AuthoringComponents;

import javafx.scene.control.ButtonBase;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CloseButton extends ButtonComponent{

    public CloseButton() {
        setImage("Close", myButton);
    }

    @Override
    public void setImage(String fileName, ButtonBase button) {
        myImage = new ImageView(new Image(getClass().getResourceAsStream("/ButtonImages/" + fileName)));
        //TODO magic numbers should be changed based on the screensize
        myImage.setFitHeight(15);
        myImage.setFitWidth(15);
        button.setGraphic(myImage);
    }

}

