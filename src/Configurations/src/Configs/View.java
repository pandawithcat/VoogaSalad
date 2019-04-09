package Configs;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.nio.file.Path;
//import java.awt.*;

public class View implements Configurable {
    private Configuration myConfiguration;

    @XStreamOmitField
    @Configure
    private File imagePath;
//    @Configure
//    private String imageName;
    @Configure
    private double width;
    @Configure
    private double height;

    //this constructor is for the special case for the terrain blocks in the map
    public View(Configurable configurableParent, File file, double width, double height) {
        imagePath = file;
        this.width = width;
        this.height = height;
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

    public double getWidth() {
        return width;
    }
    public double getHeight() {
        return height;
    }
    public String getImage() {
        return imagePath.toString();
    }




}
