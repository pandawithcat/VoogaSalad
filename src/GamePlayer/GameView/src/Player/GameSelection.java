package Player;

import BackendExternal.GameInfo;
import BackendExternal.Logic;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GameSelection extends Application {
    private VBox root;
    private Stage stage;
    private ScrollPane scrollPane = new ScrollPane();
    private double width;
    private double height;
    private Logic logic;
    @Override
    public void start(Stage primaryStage) {
//        logic = new Logic();
        stage = primaryStage;
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        width = primScreenBounds.getWidth();
        height = primScreenBounds.getHeight();
        stage.setX(width);
        stage.setY(height);
        root = new VBox();
        Label text = new Label("Select a Game");
        text.setPrefHeight(100);
        root.getChildren().add(text);
        root.getChildren().add(scrollPane);
        var scene = new Scene(root, width, height);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        createGameSelectionScreen();
    }
    private List<GameInfo> uploadAvailableGames(){
//        List<GameInfo> gameInfoList= logic.getGameOptions();
        List<GameInfo> gameInfos = new ArrayList<>();
        for(int x = 1; x < 5; x++){
            GameInfo gameInfo = new GameInfo("Trial" + x, "tower" + x + ".png","");
            gameInfos.add(gameInfo);
        }
        return gameInfos;
    }

    private void createGameSelectionScreen(){
        VBox vBox = new VBox();
        vBox.setPrefWidth(width);
        for(int x = 0; x < 20; x ++) {
            HBox hBox = new HBox();
            hBox.setPrefWidth(width);
            for(GameInfo gameInfo: uploadAvailableGames()){
                Text title = new Text(gameInfo.getGameTitle());
                title.setX(300);
                hBox.getChildren().add(title);
                String gameImage = gameInfo.getGameThumbnail();
                Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(gameImage));
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                imageView.setX(500);
                imageView.setOnMouseClicked(e -> startGame(gameInfo, image));
                hBox.getChildren().add(imageView);
            }
            vBox.getChildren().add(hBox);
        }
        scrollPane.setContent(vBox);
    }
    private void startGame(GameInfo gameInfo, Image image){
        this.stage.close();
        GameStart gameStart = new GameStart();
        gameStart.start(new Stage());
        gameStart.setTitle(gameInfo, image);
    }

    public static void main (String [] args){launch(args);}

}
