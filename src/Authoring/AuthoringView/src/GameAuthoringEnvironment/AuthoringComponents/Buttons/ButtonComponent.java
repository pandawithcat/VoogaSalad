package GameAuthoringEnvironment.AuthoringComponents.Buttons;

import javafx.scene.control.Button;

public abstract class ButtonComponent extends Component{

    Button myButton;
    /**
     * Creates button instance and calls superclass to set graphic.
     *
     */
    public ButtonComponent() {
        this.myButton = new Button();

        myButton.setGraphic(myImage);
        //myButton.setOnMouseClicked(action());
    }


   /* @Override
    protected abstract EventHandler<MouseEvent> action();
*/
    /**
     * Returns button object
     *
     * @return this button
     */
    public Button getButton() {
        return myButton;
    }

}
