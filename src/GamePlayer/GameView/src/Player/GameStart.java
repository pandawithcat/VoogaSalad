package Player;

import BackendExternal.GameInfo;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
        root = new StackPane();
        root.setId("pane");
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
        title.setTranslateY(-100);
        Text subtitle = new Text(gameInfo.getGameDescription());
        subtitle.setTranslateY(-60);
        root.getChildren().add(title);
        root.getChildren().add(subtitle);
        root.applyCss();
        root.layout();
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        root.getChildren().add(imageView);
        Button play = new Button("Play");
        play.setTranslateX(0);
        play.setTranslateY(100);
        root.getChildren().add(play);
        play.setOnAction(e-> startGame(gameInfo));
    }
    private void startGame(GameInfo gameInfo){
        this.stage.close();
        LogInPreloader logInPreloader = new LogInPreloader();
        logInPreloader.start(new Stage());
        logInPreloader.setTransitionEvent(e->transitionToScreen(gameInfo));
    }
    private void transitionToScreen(GameInfo gameInfo){
        System.out.println("prints");
        GamePlayVisualization gamePlayVisualization = new GamePlayVisualization();
        gamePlayVisualization.start(new Stage());
        gamePlayVisualization.setGameInfo(gameInfo);
    }
}
