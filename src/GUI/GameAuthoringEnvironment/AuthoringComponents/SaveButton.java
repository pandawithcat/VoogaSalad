package GUI.GameAuthoringEnvironment.AuthoringComponents;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SaveButton extends ButtonComponent{

    public SaveButton(){
        Image saveImage = new Image(getClass().getResourceAsStream("/ButtonImages/Save"));
        myButton.setGraphic(new ImageView(saveImage));
    }


  /*  @Override
    protected EventHandler<MouseEvent> action() {
        return event -> context.loadFile();
    }*/

}
