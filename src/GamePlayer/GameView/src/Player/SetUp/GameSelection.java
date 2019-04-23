package Player.SetUp;

import BackendExternal.GameInfo;
import BackendExternal.Logic;
import Player.GamePlay.GamePlayMain;
import Player.ScreenSize;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class GameSelection extends Application {

    public static final String RESOURCES_PATH = "resources/";

    private HBox root;
    private Stage stage;
    private ScrollPane scrollPane = new ScrollPane();
    private double width = ScreenSize.getWidth();
    private double height = ScreenSize.getHeight();
    private Logic logic;
    private StackPane gameStart;
    @Override
    public void start(Stage primaryStage) {
        scrollPane.setId("scrollpane");
        scrollPane.setPrefSize(width/3,height);
        scrollPane.setOpacity(0.8);
        logic = new Logic();
        stage = primaryStage;
        stage.setX(width);
        stage.setY(height);
        root = new HBox();
        root.setId("pane");
        Label text = new Label("Select a Game");
        text.setPrefHeight(100);
        text.setId("selectGame");
        VBox left = new VBox();
        left.getChildren().add(text);
        left.getChildren().add(scrollPane);
        root.getChildren().add(left);
        text.setLayoutX(ScreenSize.getWidth()/2);
        var scene = new Scene(root, width, height);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        try {
            createGameSelectionScreen();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private List<GameInfo> uploadAvailableGames(){
        List<GameInfo> gameInfoList= logic.getGameOptions();
        return gameInfoList;
    }

    private void createGameSelectionScreen() throws FileNotFoundException {
        VBox vBox = new VBox();
        vBox.setPrefWidth(width/3);
        vBox.setAlignment(Pos.CENTER);
        for(GameInfo gameInfo: uploadAvailableGames()){
            Rectangle container = new Rectangle();
            Text title = new Text(gameInfo.getGameTitle());
            title.setX(300);
            vBox.getChildren().add(title);
            Image image = new Image(new FileInputStream(RESOURCES_PATH + gameInfo.getGameThumbnail()));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            imageView.setX(500);
            imageView.setOnMouseClicked(e -> startGame(gameInfo, image));
            vBox.getChildren().add(imageView);
        }
        scrollPane.setContent(vBox);
    }
    private void startGame(GameInfo gameInfo, Image image){
        setTitle(gameInfo, image);
    }
    public void setTitle(GameInfo gameInfo, Image image){
        if(root.getChildren().contains(gameStart)){
            root.getChildren().remove(gameStart);
        }
        gameStart = new StackPane();
        gameStart.setAlignment(Pos.CENTER);
        Text title = new Text(gameInfo.getGameTitle());
        title.setTranslateY(-100);
        Text subtitle = new Text(gameInfo.getGameDescription());
        subtitle.setTranslateY(-60);
        gameStart.getChildren().add(title);
        gameStart.getChildren().add(subtitle);
        gameStart.applyCss();
        gameStart.layout();
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        gameStart.getChildren().add(imageView);
        Button play = new Button("Play");
        play.setTranslateX(0);
        play.setTranslateY(100);
        gameStart.getChildren().add(play);
        play.setOnAction(e-> startGame(gameInfo));
        gameStart.setPrefWidth(width* 2 /3);
        root.getChildren().add(gameStart);
    }
    private void startGame(GameInfo gameInfo){
        this.stage.close();
        LogInPreloader logInPreloader = new LogInPreloader();
        logInPreloader.start(new Stage());
        logInPreloader.setTitle("Get Ready to Play!");
        logInPreloader.setTransitionEvent(e->transitionToScreen(gameInfo));
    }
    private void transitionToScreen(GameInfo gameInfo){
        GamePlayMain gamePlayMain = new GamePlayMain();
        gamePlayMain.setGameInfo(gameInfo);
        gamePlayMain.start(new Stage());
    }
}
