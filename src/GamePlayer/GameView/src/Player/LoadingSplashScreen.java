package Player;

import Player.SetUp.GameSelection;
import Player.SetUp.LogInPreloader;
import javafx.animation.*;
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
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
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
    private StackPane background;
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        background = new StackPane();
        root = new StackPane();
        background.getChildren().add(root);
        root.setId("pane");
        root.applyCss();
        root.layout();
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth()));
        stage.setY((primScreenBounds.getHeight()));

        var scene = new Scene(background, primScreenBounds.getWidth(), primScreenBounds.getHeight());
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        root.getChildren().add(createWelcomeMusic());
        root.getChildren().add(createLogoBackground());
        Button start = createStartButton("shiny-yelow", "Start", 0, 150);
        start.setOnAction(e -> transitionToLogIn(start));
        root.getChildren().add(start);

    }

    private MediaView createWelcomeMusic(){
        Media sound = new Media(new File(WELCOME_MUSIC).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setAutoPlay(true);
        return new MediaView(mediaPlayer);
    }

    private ImageView createLogoBackground(){
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(TITLE_IMAGE));
        title = new ImageView(image);
        title.setFitWidth(500);
        title.setFitHeight(300);
        title.setTranslateY(-50);
        return title;
    }
    private Button createStartButton(String id, String title, int x, int y){
        Button button = new Button(title);
        button.setId(id);
        button.setTranslateY(y);
        button.setTranslateX(x);
        return button;
    }

    private void transitionToLogIn(Button button){
        root.getChildren().remove(button);
        ScaleTransition st = new ScaleTransition(Duration.millis(1500), title);
        st.setByX(-0.6f);
        st.setByY(-0.6f);
        st.setCycleCount(1);
        int xPos = (int)Math.round(title.getX() + title.getBoundsInLocal().getWidth()/2);
        PathTransition bannerTransition = generatePathTransition(generatePath(xPos, 0, -100), title);
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(st,bannerTransition);
        parallelTransition.play();
        parallelTransition.setOnFinished(e-> createSignInSettings());
    }
    private void createSignInSettings(){
        root.setStyle("-fx-opacity: 0.6; -fx-background-color: black;");
        Rectangle rect = new Rectangle();
        rect.setWidth(300);
        rect.setHeight(200);
        rect.setArcWidth(20);
        rect.setArcHeight(20);
        rect.getStyleClass().add("my-rect");
        final Button button = createStartButton("green","Log In", 0, 100);
        button.setOnAction(e-> availableGames());
        GridPane gridPane = userLogIn();
        background.getChildren().add(rect);
        background.getChildren().add(gridPane);
        background.getChildren().add(button);
    }
    private GridPane userLogIn(){
        final GridPane grid = new GridPane();
        grid.setId("login");
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setHgap(10);
        grid.setVgap(10);
        final Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);
        final TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);
        final Label pw = new Label("Password:");
        grid.add(pw, 0, 2);
        final PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);
        grid.setAlignment(Pos.CENTER);
        return grid;
    }
    private void availableGames(){
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(mediaPlayer.volumeProperty(), 0)));
        timeline.play();
        this.stage.close();
        LogInPreloader logInPreloader = new LogInPreloader();
        logInPreloader.start(new Stage());
        logInPreloader.setTitle("Loading Available Games");
        logInPreloader.setTransitionEvent(e -> {
            GameSelection gameSelection = new GameSelection();
            gameSelection.start(new Stage());
        });
    }
    private Path generatePath(int x, int y, int newY)
    {
        Path path = new Path();
        path.getElements().add(new MoveTo(x , y));
        path.getElements().add(new LineTo(x, newY));
        return path;
    }

    private PathTransition generatePathTransition(Path path, Node node)
    {
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.seconds(0.8));
        pathTransition.setPath(path);
        pathTransition.setCycleCount(1);
        pathTransition.setNode(node);
        return pathTransition;
    }
    public static void main(String [] args){
        Application.launch(args);}
}
