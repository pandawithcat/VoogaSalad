package Configs;

import javafx.scene.Node;
import javafx.scene.image.ImageView;

public interface ImmutableImageView {
    //pixel location
    double getX();
    //pixel location
    double getY();
    double getFitWidth();
    double getFitHeight();
//    ImageView getImageView();
    Node getAsNode();

}
