package GUI.GameAuthoringEnvironment.AuthoringComponents;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public abstract class ButtonComponent extends Component{

    Button myButton;

    /**
     * Creates button instance and calls superclass to set graphic.
     *
     * @param image graphic for button
     */
    public ButtonComponent(Image image) {
        super(image);
        this.myButton = new Button();

        myButton.setGraphic(myImage);
        myButton.setOnMouseClicked(action());
    }

    @Override
    protected abstract EventHandler<MouseEvent> action();

    /**
     * Returns button object
     *
     * @return this button
     */
    public Button getButton() {
        return myButton;
    }

}
