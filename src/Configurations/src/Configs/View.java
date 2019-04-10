package Configs;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
<<<<<<< HEAD
//import java.awt.*;

public class View {
    ImageView myImageView;

    public View(String imageName, int x, int y, int width, int height){
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(imageName));//classloader probably has to be set
        ImageView myImageView = new ImageView(image);
        myImageView.setX(x);
        myImageView.setY(y);
        myImageView.setFitWidth(width);
        myImageView.setFitWidth(height);
        myImageView.getFitWidth();
    }

    //TODO: grid or pixel
    public void updatePosition(int gridXPos, int gridYPos) {

=======

import java.io.File;
import java.nio.file.Path;
//import java.awt.*;

public class View implements Configurable {
    private Configuration myConfiguration;

    @XStreamOmitField
    @Configure
    protected File imagePath;
//    @Configure
//    private String imageName;
    @Configure
    protected double width;
    @Configure
    protected double height;

    //this constructor is for the special case for the terrain blocks in the map
    public View(File file, double width, double height) {
        imagePath = file;
        this.width = width;
        this.height = height;
    }


    public View(Configurable configurableParent) {
        myConfiguration = new Configuration(configurableParent);

    }

    @Override
    public Configuration getConfiguration() {
        return myConfiguration;
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
>>>>>>> e359d39c535474f2a421b7b7920361e6858a7860
    }
    public String getImage() {
        return imagePath.toString();
    }



}
