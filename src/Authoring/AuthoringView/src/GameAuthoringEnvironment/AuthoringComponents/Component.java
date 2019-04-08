package GameAuthoringEnvironment.AuthoringComponents;

import javafx.scene.control.ButtonBase;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URISyntaxException;

public abstract class Component {

    ImageView myImage;

    /**
     * Sets graphic for button.
     * change the magic value based on the screen size
     */
    public Component() {

    }

    /**
     * Abstract EventHandler method to be flushed out in subclasses. There is no set EventHandler type because MenuButtton
     * and Button use different kinds of events. Lambdas are recommended.
     *
     * Example:
     * return event -> context.certainAction(parameter);
     *
     * @return an EventHandler for when button is clicked.
     */
    //abstract EventHandler<?> action();


    public void setImage(String fileName, ButtonBase button) {
            var url = this.getClass().getClassLoader().getResource("ButtonImages");
            try {
                File folder = new File(url.toURI());
                Image test = new Image(folder.toURI()+ fileName);
                System.out.println(test.isError());
                myImage = new ImageView(test);
                } catch (URISyntaxException e) {
                e.printStackTrace();
                }
            //TODO magic numbers should be changed based on the screensize
            myImage.setFitHeight(50);
            myImage.setFitWidth(125);
            button.setGraphic(myImage);
    }

}
