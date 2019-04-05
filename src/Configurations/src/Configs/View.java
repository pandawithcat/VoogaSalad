package Configs;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import java.awt.*;

public class View {
    public View(String imageName, int x, int y, int width, int height){
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(imageName));//classloader probably has to be set
        ImageView myImageView = new ImageView(image);
        myImageView.setX(x);
        myImageView.setY(y);
        myImageView.setFitWidth(width);
        myImageView.setFitWidth(height);
        myImageView.getFitWidth();
    }
}
