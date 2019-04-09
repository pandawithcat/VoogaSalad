package Configs;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.nio.file.Path;
//import java.awt.*;

public class View implements Configurable {
    private TransferImageView myImageView;
    private Configuration myConfiguration;

    @XStreamOmitField
    @Configure
    private File imagePath;
    @Configure
    private String imageName;
    @Configure
    private double width;
    @Configure
    private double height;

    public View(File file, double width, double height) {
        //TODO: set instance variables
    }



    public View(Configurable configurableParent) {
        myConfiguration = new Configuration(configurableParent);

    }

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    //    public View(String imageName, int x, int y, int width, int height){
//        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(imageName));//classloader probably has to be set
//        ImageView myImageView = new TransferImageView(image);
//        myImageView.setX(x);
//        myImageView.setY(y);
//        myImageView.setFitWidth(width);
//        myImageView.setFitWidth(height);
//        myImageView.getFitWidth();
//    }

    //TODO: grid or pixel
    public void updatePosition(int gridXPos, int gridYPos) {

    }

    public TransferImageView getImageView() {
        return myImageView;
    }



}
