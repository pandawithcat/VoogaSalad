package GameAuthoringEnvironment.AuthoringScreen;

import Configs.GamePackage.Game;
import GameAuthoringEnvironment.AuthoringComponents.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.StackPane;
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
    private VBox myContainer;
    private GameOutline gameOutline;
    private static final KeyCombination keyCombinationCommandN = new KeyCodeCombination(KeyCode.ESCAPE);
    private Game myGame;

    public AuthoringVisualization(Game game){
        myGame = game;
        myContainer = new VBox();
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
        StackPane background = new StackPane();
        VBox myGameOutline = gameOutline.getModule();
        myGameOutline.setId("image_backdrop");
        //myGameOutline.setLayoutX();
        myGameOutline.setLayoutY(40);
        background.getChildren().add(myGameOutline);


        TopMenuBar topMenuBar = new TopMenuBar(gameOutline);
        StackPane menu = new StackPane();
        StackPane colored = new StackPane();
        colored.setStyle("-fx-background-color: linear-gradient(rgb(90, 253, 255), rgb(98, 222, 230)); -fx-opacity: 0.4;");
        menu.getChildren().add(colored);
        menu.getChildren().add(topMenuBar.getTopMenuBar());
        myContainer.getChildren().addAll(menu, background);
        myScene = new Scene(myContainer);
        myScene.getStylesheets().add("authoring_style.css");

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
