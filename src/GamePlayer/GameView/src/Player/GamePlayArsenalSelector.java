package Player;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class GamePlayArsenalSelector extends HBox {

    public static final String WEAPON_IMAGE = "weapon.png";


    private Image weaponImage;
    private ImageView weaponImageView;


    public GamePlayArsenalSelector(double width, double height){
        weaponImage = new Image(WEAPON_IMAGE);
        weaponImageView = new ImageView(weaponImage);
        weaponImageView.setFitHeight(height);
        weaponImageView.setFitWidth(width /2);
        Button weaponButton = new Button("", weaponImageView);
        weaponButton.setOnAction(e -> System.out.println("Weapon Button is pressed"));
        getChildren().add(weaponButton);

    }

}
