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
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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
        var root = new Group();
        myContainer = root;
        setScene();
    }


    private void setScene() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        screenHeight = primaryScreenBounds.getHeight();
        screenWidth = primaryScreenBounds.getWidth();
        screenMinX = primaryScreenBounds.getMinX();
        screenMinY = primaryScreenBounds.getMinY();

        TopMenuBar topMenuBar = new TopMenuBar();

        //TODO Refactor
        GameOutline gameOutline = new GameOutline((int) screenHeight, (int) screenWidth);
        VBox myGameOutline = gameOutline.getModule();
        //myGameOutline.setLayoutX();
        //myGameOutline.setLayoutY();



        //TODO Change this part - maybe even add the logo and logo also disappears
        Text instructions = new Text("Write down instructions and make this text disappear once the user clicks new game button");
        instructions.setX(300);
        instructions.setY(300);
        instructions.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
               myContainer.getChildren().remove(instructions);
            }
        });


        myContainer.getChildren().addAll(topMenuBar.getTopMenuBar(), myGameOutline, instructions);
        myScene = new Scene(myContainer);


        Stage myStage = new Stage();
        myStage.setScene(myScene);
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

}
