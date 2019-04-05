package GUI.GamePlay.GameScreen;


import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;


public class LoadingSplashScreen extends Application{

    private final String WELCOME_MUSIC = "resources/main-theme.mp3";
    private StackPane root;
    private Rectangle rect;
    private Text text;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        root = new StackPane();
        root.setId("pane");
        root.applyCss();
        root.layout();

        var scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();

        root.getChildren().add(createWelcomeMusic());
        root.getChildren().add(createLogoBackground());
        Button start = createStartButton("shiny-yelow", "Start", 0, 100);
        start.setOnAction(e -> transitionToLogIn(start));
        root.getChildren().add(start);
        text = new Text("Tower Defense!");
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        root.getChildren().add(text);
    }
    public Stage getStage(){
        return stage;
    }
    private MediaView createWelcomeMusic(){
        Media sound = new Media(new File(WELCOME_MUSIC).toURI().toString());
        MediaPlayer player = new MediaPlayer(sound);
        player.setAutoPlay(true);
        MediaView mediaView = new MediaView(player);
        return mediaView;
    }

    private Rectangle createLogoBackground(){
        rect = new Rectangle(500,100);
        rect.getStyleClass().add("my-rect");
        return rect;
    }
    private Button createStartButton(String id, String title, int x, int y){
        Button button = new Button(title);
        button.setId(id);
        button.setTranslateY(y);
        button.setTranslateX(x);
        return button;
    }
    private TextField userLogIn(){
        TextField logIn = new TextField();
        logIn.setPromptText("Enter your Username: ");
        logIn.setFocusTraversable(false);
        logIn.setMaxWidth(200);
        return logIn;
    }
    private void transitionToLogIn(Button button){
        root.getChildren().remove(button);

        ScaleTransition st = new ScaleTransition(Duration.millis(2000), rect);
        ScaleTransition txt = new ScaleTransition(Duration.millis(2000), text);
        txt.setByX(-0.5f);
        txt.setByY(-0.5f);
        txt.setCycleCount(1);

        st.setByX(-0.5f);
        st.setByY(-0.5f);
        st.setCycleCount(1);

        PathTransition bannerTransition = generatePathTransition(generatePath(250, 0), rect);
        PathTransition textTransition = generatePathTransition(generatePath(220, -70), text);
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(st,txt,bannerTransition, textTransition);
        parallelTransition.play();
        parallelTransition.setOnFinished(e-> createSignInSettings());
    }
    private void createSignInSettings(){
        root.getChildren().add(userLogIn());
        Button button = createStartButton("green","Log In", 0, 100);
        button.setOnAction(e-> availableGames());
        root.getChildren().add(button);
    }
    private void availableGames(){
    }
    private Path generatePath(int x, int y)
    {
        Path path = new Path();
        path.getElements().add(new MoveTo(x , y));
        path.getElements().add(new LineTo(x, y-100));
        return path;
    }

    private PathTransition generatePathTransition(Path path, Node node)
    {
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(1.0));
        pathTransition.setPath(path);
        pathTransition.setCycleCount(1);
        pathTransition.setNode(node);
        pathTransition.setAutoReverse(true);
        return pathTransition;
    }
}
