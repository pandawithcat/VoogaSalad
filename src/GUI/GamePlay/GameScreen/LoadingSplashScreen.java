package GUI.GamePlay.GameScreen;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
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
        Button button = new Button("Play Bitch");

        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream("tower4.gif"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        root.getChildren().add(imageView);
//        root.getChildren().add(button);

    }
    public static void main (String[] args) {launch(args);}


}
