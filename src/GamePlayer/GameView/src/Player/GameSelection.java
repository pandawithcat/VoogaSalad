package Player;

import BackendExternal.GameInfo;
import BackendExternal.Logic;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.List;

public class GameSelection extends Application {
    private StackPane root;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        root = new StackPane();
        root.setId("pane");
        var scene = new Scene(root, 1000, 600);
        scene.getStylesheets().add("style.css");
        primaryStage.setScene(scene);
        primaryStage.show();
        createGameSelectionScreen();
    }
    private List<GameInfo> uploadAvailableGames(){
        Logic logic = new Logic();
        List<GameInfo> gameInfoList= logic.getGameOptions();
        return gameInfoList;
    }

    private void createGameSelectionScreen(){
        for(GameInfo gameInfo: uploadAvailableGames()){
            root.getChildren().add(new Text(gameInfo.getGameTitle()));
            String gameImage = gameInfo.getMyGameThumbnail();
            Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(gameImage));
            ImageView imageView = new ImageView(image);
            imageView.setOnMouseClicked(e-> startGame(gameInfo));
            root.getChildren().add(imageView);
        }
    }
    private void startGame(GameInfo gameInfo){
        this.stage.close();
        GameStart gameStart = new GameStart();
        gameStart.start(new Stage());
        gameStart.setTitle(gameInfo);
    }

}
