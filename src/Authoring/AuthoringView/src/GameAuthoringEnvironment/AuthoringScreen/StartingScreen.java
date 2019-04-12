package GameAuthoringEnvironment.AuthoringScreen;

import Configs.GamePackage.Game;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class StartingScreen {

    private VBox myContatiner;
    private Game myGame;

    public void start (Stage stage) {
        var root = new Group();
        setScene(stage, root);
        setStage(stage);
    }


    private void setScene(Stage stage, Group root){

        myContatiner = new VBox(10.00);
        Image test = new Image(getClass().getResourceAsStream("/ButtonImages/"+ "logo" +".png"));
        ImageView imageView = new ImageView(test);
        Button newGameButton = new Button("Create New Game");
        newGameButton.setOnMouseClicked(this:: handleNewGameButton);
        Button importGameButton = new Button("Import Game");
        importGameButton.setOnMouseClicked(this::importGame);
        Text description = new Text("Welcome to NoMergeConflicts. This is a Tower defense game editor in which you can create your own unique tower defenese game!");

        myContatiner.getChildren().addAll(imageView, newGameButton, importGameButton, description);

        Scene scene= new Scene(myContatiner, 800, 800);
        stage.setScene(scene);
    }

    private void handleNewGameButton(MouseEvent event){
        AuthoringVisualization authoringVisualization = new AuthoringVisualization(myGame);
    }

    private void importGame(MouseEvent evemt){


    }

    private void setStage(Stage stage){

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        double screenHeight = primaryScreenBounds.getHeight();
        double screenWidth = primaryScreenBounds.getWidth();
        double screenMinX = primaryScreenBounds.getMinX();
        double screenMinY = primaryScreenBounds.getMinY();

        //set Stage boundaries to visible bounds of the main screen
        stage.setX(screenMinX/2.5);
        stage.setY(screenMinY/2.5);
        stage.setWidth(screenWidth/2.5);
        stage.setHeight(screenHeight/2.5);
        stage.setTitle("Welcome to NoMergeConflicts");

        stage.show();
    }

}
