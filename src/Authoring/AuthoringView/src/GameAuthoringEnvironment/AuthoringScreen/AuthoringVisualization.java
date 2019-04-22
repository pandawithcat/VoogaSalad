package GameAuthoringEnvironment.AuthoringScreen;

import Configs.GamePackage.Game;
import GameAuthoringEnvironment.AuthoringComponents.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class AuthoringVisualization {

    private String Title = "VoogaSalad";
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


        //TODO(Hyunjae) Refactor and don't use magic numbers
        gameOutline = new GameOutline((int) screenHeight, (int) screenWidth);
        VBox myGameOutline = gameOutline.getModule();
        //myGameOutline.setLayoutX();
        myGameOutline.setLayoutY(25);

        TopMenuBar topMenuBar = new TopMenuBar(gameOutline);

        //TODO(Hyunjae) Change this part - add specific instructions on how to use the editor(make it fancy)
        Text instructions = new Text("Write down instructions and make this look super fancy!");
        instructions.setX(300);
        instructions.setY(300);

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

    //TODO(Hyunjae) When ctrl+N is pressed, show a new screen
    private void handleKeyInput(KeyEvent e) {
        if (keyCombinationCommandN.match(e)) {

        }
    }

}
