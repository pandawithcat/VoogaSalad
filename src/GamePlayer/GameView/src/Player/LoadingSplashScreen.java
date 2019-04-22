package Player;

import Player.SetUp.GameSelection;
import Player.SetUp.LogInPreloader;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
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
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;


public class LoadingSplashScreen extends Application{

    private final String WELCOME_MUSIC = "resources/gameMusic.mp3";
    private final String TITLE_IMAGE = "title.png";
    private StackPane root;
    private ImageView title;
    private Stage stage;
    private MediaPlayer mediaPlayer;
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        root = new StackPane();
        root.setId("pane");
        root.applyCss();
        root.layout();

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth()));
        stage.setY((primScreenBounds.getHeight()));

        var scene = new Scene(root, primScreenBounds.getWidth(), primScreenBounds.getHeight());
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        root.getChildren().add(createWelcomeMusic());
        root.getChildren().add(createLogoBackground());
        Button start = createStartButton("shiny-yelow", "Start", 0, 100);
        start.setOnAction(e -> transitionToLogIn(start));
        root.getChildren().add(start);

    }

    private MediaView createWelcomeMusic(){
        Media sound = new Media(new File(WELCOME_MUSIC).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setAutoPlay(true);
        MediaView mediaView = new MediaView(mediaPlayer);
        return mediaView;
    }

    private ImageView createLogoBackground(){
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(TITLE_IMAGE));
        title = new ImageView(image);
        title.setFitWidth(400);
        title.setFitHeight(150);
        return title;
    }
    private Button createStartButton(String id, String title, int x, int y){
        Button button = new Button(title);
        button.setId(id);
        button.setTranslateY(y);
        button.setTranslateX(x);
        return button;
    }
    private GridPane userLogIn(){
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setHgap(10);
        grid.setVgap(10);
        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);
        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);
        grid.setAlignment(Pos.CENTER);
        return grid;
    }
    private void transitionToLogIn(Button button){
        root.getChildren().remove(button);

        ScaleTransition st = new ScaleTransition(Duration.millis(1000), title);
        st.setByX(-0.5f);
        st.setByY(-0.5f);
        st.setCycleCount(1);
        int xPos = (int)Math.round(title.getX() + title.getBoundsInLocal().getWidth()/2);
        PathTransition bannerTransition = generatePathTransition(generatePath(xPos, 0), title);
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(st,bannerTransition);
        parallelTransition.play();
        parallelTransition.setOnFinished(e-> createSignInSettings());
    }
    private void createSignInSettings(){
        Rectangle rect = new Rectangle();
        rect.setWidth(300);
        rect.setHeight(200);
        rect.setArcWidth(20);
        rect.setArcHeight(20);
        rect.getStyleClass().add("my-rect");
        root.getChildren().add(rect);
        root.getChildren().add(userLogIn());
        Button button = createStartButton("green","Log In", 0, 100);
        button.setOnAction(e-> availableGames());
        root.getChildren().add(button);
    }
    private void availableGames(){
        mediaPlayer.stop();
        this.stage.close();
        LogInPreloader logInPreloader = new LogInPreloader();
        logInPreloader.start(new Stage());
        logInPreloader.setTitle("Loading Available Games");
        logInPreloader.setTransitionEvent(e -> {
            GameSelection gameSelection = new GameSelection();
            gameSelection.start(new Stage());
        });
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
        pathTransition.setDuration(Duration.seconds(0.2));
        pathTransition.setPath(path);
        pathTransition.setCycleCount(1);
        pathTransition.setNode(node);
        return pathTransition;
    }
    public static void main(String [] args){
        Application.launch(args);}
}
