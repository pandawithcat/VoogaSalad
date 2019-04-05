package GUI.GamePlay.GameScreen;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;


public class LoadingSplashScreen extends Application{
    

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        root.setId("pane");
        var scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        Media sound = new Media(new File("resources/main-theme.mp3").toURI().toString());
        MediaPlayer player = new MediaPlayer(sound);
        player.setAutoPlay(true);
        MediaView mediaView = new MediaView(player);
        root.getChildren().add(mediaView);
        Rectangle rect = new Rectangle(500,100);
        rect.getStyleClass().add("my-rect");
        root.getChildren().add(rect);
        Button button = new Button("Start");
        button.setId("shiny-orange");
        Text text = new Text("Tower Defense!");
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));        root.getChildren().add(text);
        root.getChildren().add(button);
        root.applyCss();
        root.layout();
        button.setTranslateY(100);
    }



    public static void main (String[] args) {launch(args);}


}
