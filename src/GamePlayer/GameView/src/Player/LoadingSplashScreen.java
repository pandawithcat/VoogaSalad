package Player;

import Player.SetUp.GameSelection;
import Player.SetUp.LogInPreloader;
import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
    private Rectangle rect;
    private VBox accountGrid;
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        background = new StackPane();
        root = new StackPane();
        background.getChildren().add(root);
        root.setId("pane");
        root.applyCss();
        root.layout();
        stage.setX(ScreenSize.getWidth());
        stage.setY(ScreenSize.getHeight());
        var scene = new Scene(background, ScreenSize.getWidth(), ScreenSize.getHeight());
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
        ScaleTransition st = new ScaleTransition(Duration.seconds(0.8), title);
        st.setByX(-0.6f);
        st.setByY(-0.6f);
        st.setCycleCount(1);
        int xPos = (int)Math.round(title.getX() + title.getBoundsInLocal().getWidth()/2);
        PathTransition bannerTransition = generatePathTransition(generatePath(xPos, 0, -100), title);
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(st,bannerTransition);
        parallelTransition.play();
        parallelTransition.setOnFinished(e-> createSignInSettings(false));
    }
    private void createSignInSettings(boolean repeat){
        if(!repeat) {
            root.setStyle("-fx-opacity: 0.6; -fx-background-color: black;");
            rect = new Rectangle();
            rect.setArcWidth(20);
            rect.setArcHeight(20);
            rect.getStyleClass().add("my-rect");
            background.getChildren().add(rect);
        }else{
            background.getChildren().remove(accountGrid);
        }
        rect.setWidth(400);
        rect.setHeight(200);
        GridPane gridPane = userLogIn();
        background.getChildren().add(gridPane);
    }

    private void createAccount(GridPane gridPane){
        //TODO: ADD ERROR CHECKING TO SEE IF USERNAME ALREADY TAKEN
        background.getChildren().remove(gridPane);
        rect.setWidth(400);
        rect.setHeight(250);
        GridPane grid = new GridPane();
        grid.setId("login");
        grid.setPadding(new Insets(25, 25, 25, 25));
//        accountGrid.setHgap(10);
//        accountGrid.setVgap(10);

        final Label userName = new Label("User Name:");
        grid.add(userName, 1, 1);
        final TextField userTextField = new TextField();
        grid.add(userTextField, 2, 1);

        final Label pw = new Label("Password:");
        grid.add(pw, 1, 2);
        final TextField pwTextField = new TextField();
        grid.add(pwTextField, 2, 2);

        final Label pwConfirm = new Label("Confirm Password:");
        grid.add(pwConfirm, 1, 3);
        final PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 2, 3);

        Image back = new Image(this.getClass().getClassLoader().getResourceAsStream("back.png"));
        ImageView backToLogIn = new ImageView(back);
        backToLogIn.setFitWidth(30);
        backToLogIn.setFitHeight(30);
        backToLogIn.setOnMousePressed(e->createSignInSettings(true));
        grid.add(backToLogIn, 0,0);
        Button createAccount = new Button("Create Account");
        createAccount.setId("createAccount");
        grid.setAlignment(Pos.CENTER);
        accountGrid = new VBox(grid, createAccount);
        accountGrid.setAlignment(Pos.CENTER);
        background.getChildren().add(accountGrid);
    }

    private GridPane userLogIn(){
        final GridPane grid = new GridPane();
        grid.setId("login");
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
        loginButtonPanel(grid);
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
        logInPreloader.setTitle("We're loading your \n available games!!!");
        logInPreloader.setTransitionEvent(e -> {
            GameSelection gameSelection = new GameSelection();
            gameSelection.start(new Stage());
        });
    }
    private void loginButtonPanel(GridPane grid){
        Button login = new Button("Log In");
        login.setId("green");
        login.setOnAction(e-> availableGames());
        grid.add(login, 0, 4);
        Button newAccount = new Button("Create New Account");
        grid.add(newAccount, 1, 4);
        newAccount.setId("green");
        newAccount.setOnAction(e->createAccount(grid));
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
