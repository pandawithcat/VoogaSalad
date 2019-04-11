package GameAuthoringEnvironment.AuthoringComponents.Buttons;

import javafx.scene.control.ButtonBase;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.URISyntaxException;

public class CloseButton extends ButtonComponent{

    public CloseButton() {
        setImage("Close", myButton);
    }

    @Override
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
        myImage.setFitHeight(15);
        myImage.setFitWidth(15);
        button.setGraphic(myImage);
    }

}

