package Player.SetUp;

import BackendExternal.GameInfo;
import BackendExternal.Logic;
import Player.ScreenSize;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class GameSelection extends Application {

    public static final String RESOURCES_PATH = "resources/";

    private VBox root;
    private Stage stage;
    private ScrollPane scrollPane = new ScrollPane();
    private double width = ScreenSize.getWidth();
    private double height = ScreenSize.getHeight();
    private Logic logic;
    @Override
    public void start(Stage primaryStage) {
        scrollPane.setId("scrollpane");
        scrollPane.setPrefSize(width/2,height/2);
        
        logic = new Logic();
        stage = primaryStage;
        stage.setX(width);
        stage.setY(height);
        root = new VBox();
        root.setId("pane");
        Label text = new Label("Select a Game");
        text.setPrefHeight(100);
        root.getChildren().add(text);
        root.getChildren().add(scrollPane);
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
            HBox hBox = new HBox();
            hBox.setPrefWidth(width/3);
            for(GameInfo gameInfo: uploadAvailableGames()){
                Text title = new Text(gameInfo.getGameTitle());
                title.setX(300);
                hBox.getChildren().add(title);
                Image image = new Image(new FileInputStream(RESOURCES_PATH + gameInfo.getGameThumbnail()));
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                imageView.setX(500);
                imageView.setOnMouseClicked(e -> startGame(gameInfo, image));
                hBox.getChildren().add(imageView);
            }
            vBox.getChildren().add(hBox);

        scrollPane.setContent(hBox);
    }
    private void startGame(GameInfo gameInfo, Image image){
        this.stage.close();
        GameStart gameStart = new GameStart();
        gameStart.start(new Stage());
        gameStart.setTitle(gameInfo, image);
    }
}
