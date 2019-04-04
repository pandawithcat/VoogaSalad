package GUI.GameAuthoringEnvironment.AuthoringComponents;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SaveButton extends ButtonComponent{

    public SaveButton(){
        setImage("Save", myButton);
    }


  /*  @Override
    protected EventHandler<MouseEvent> action() {
        return event -> context.loadFile();
    }*/

}
