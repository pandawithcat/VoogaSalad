package GameAuthoringEnvironment.AuthoringScreen;

import Configs.GamePackage.Game;
import GameAuthoringEnvironment.AuthoringComponents.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AuthoringVisualization {

    private String Title = "VoogaSalad";
    private static final int FRAMES_PER_SECOND = 1;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
    private double screenWidth;
    private double screenHeight;
    private double screenMinX;
    private double screenMinY;
    private Scene myScene;
    private Group myContainer;
    private GameOutline gameOutline;
    private static final KeyCombination keyCombinationCommandN = new KeyCodeCombination(KeyCode.ESCAPE);
    private Game myGame;


    public AuthoringVisualization(Game game){
        myGame = game;
        setScene();
    }


    private void setScene() {
        var root = new Group();
        myContainer = root;
        TopMenuBar topMenuBar = new TopMenuBar();
        myContainer.getChildren().addAll(topMenuBar.getTopMenuBar());
        myScene = new Scene(myContainer);


        Stage myStage = new Stage();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        screenHeight = primaryScreenBounds.getHeight();
        screenWidth = primaryScreenBounds.getWidth();
        screenMinX = primaryScreenBounds.getMinX();
        screenMinY = primaryScreenBounds.getMinY();

        myStage.setScene(myScene);

        //set Stage boundaries to visible bounds of the main screen
        myStage.setX(screenMinX/2);
        myStage.setY(screenMinY/2);
        myStage.setWidth(screenWidth/2);
        myStage.setHeight(screenHeight/2);
        myStage.setTitle(Title);
        myStage.setResizable(true);
        myStage.show();

    }


    private void handleKeyInput(KeyEvent e) {
        if (keyCombinationCommandN.match(e)) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.show();
            // add shortcuts
        }
    }

    //TODO This will handle how the closing buttons work - automatic resizing of modules is neccesary. Would be ideal if we can move this to the modules class
    public void close(){

    }
}
