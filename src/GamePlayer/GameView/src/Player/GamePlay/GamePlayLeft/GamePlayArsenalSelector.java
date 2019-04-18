package Player.GamePlay.GamePlayLeft;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.FileInputStream;
import java.io.IOException;

public class GamePlayArsenalSelector extends HBox {

    public static final String WEAPON_IMAGE = "weapon.png";
    private Image weaponImage;
    private Image obstacleImage;
    private ImageView weaponImageView;
    private ImageView obstacleImageView;


    public GamePlayArsenalSelector(double width, double height){
        try {
            weaponImage = new Image(new FileInputStream("resources/" + WEAPON_IMAGE));
        }catch(IOException e){
            e.printStackTrace();
        }
        weaponImageView = new ImageView(weaponImage);
        weaponImageView.setFitHeight(height);
        weaponImageView.setFitWidth(width /2);
        Button weaponButton = new Button("", weaponImageView);
        weaponButton.setOnAction(e -> System.out.println("Weapon Button is pressed"));
        getChildren().add(weaponButton);

        //obstacles
        try {
            obstacleImage = new Image(new FileInputStream("resources/" + WEAPON_IMAGE));
        }catch(IOException e){
            e.printStackTrace();
        }
        obstacleImageView = new ImageView(obstacleImage);
        obstacleImageView.setFitHeight(height);
        obstacleImageView.setFitWidth(width /2);
        Button obstacleButton = new Button("", obstacleImageView);
        obstacleButton.setOnAction(e -> System.out.println("Obstacle Button is pressed"));
        getChildren().add(obstacleButton);

    }

}
