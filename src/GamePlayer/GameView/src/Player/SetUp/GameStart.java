package Player.SetUp;

import Player.GamePlay.GamePlayMain;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class GameStart extends Application {
    private Stage stage;
    private StackPane root;
    private double width;
    private double height;

    @Override
    public void start(Stage primaryStage){
        stage = primaryStage;

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        width = primScreenBounds.getWidth();
        height = primScreenBounds.getHeight();
        var scene = new Scene(root, width, height);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
