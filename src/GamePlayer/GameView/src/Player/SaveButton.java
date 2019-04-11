package Player;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SaveButton extends Button {
    private String icon = "save.png";
    private Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(icon));
    private ImageView imageView = new ImageView(image);
    public SaveButton(double width, double height){
        imageView.setFitWidth(width/3);
        imageView.setFitHeight(height/3);
        setGraphic(imageView);
        setPrefWidth(width/3);
        setPrefHeight(height/2);
        setPrefWidth(width);
        setOnAction(e -> System.out.println("Save Button pressed"));

    }
}
