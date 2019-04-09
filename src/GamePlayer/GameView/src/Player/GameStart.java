package Player;

import BackendExternal.GameInfo;
import BackendExternal.Logic;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class GameStart extends Application {
    private Stage stage;
    private Pane root;
    private double width;
    private double height;

    @Override
    public void start(Stage primaryStage){
        stage = primaryStage;
        root = new Pane();
        root.setId("pane");
        root.applyCss();
        root.layout();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        width = primScreenBounds.getWidth();
        height = primScreenBounds.getHeight();
        var scene = new Scene(root, width, height);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setTitle(GameInfo gameInfo, Image image){

        Text title = new Text(gameInfo.getGameTitle());
        title.setX(width/2 - title.getBoundsInLocal().getWidth()/2);
        title.setY(height * 0.25);
        root.getChildren().add(title);

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        imageView.setX(width/2 - imageView.getBoundsInLocal().getWidth()/2);
        imageView.setY(height/2);
        root.getChildren().add(imageView);
        Button play = new Button("Play");
        root.getChildren().add(play);
        play.setTranslateY(height*.75);
        play.setLayoutX(width/2 - imageView.getBoundsInLocal().getWidth()/2);
        play.setOnAction(e-> startGame());
    }
    private void startGame(){
        this.stage.close();
//        GamePlayVisualization gamePlayVisualization = new GamePlayVisualization(new Logic());
//        gamePlayVisualization.start(new Stage());
    }
}
