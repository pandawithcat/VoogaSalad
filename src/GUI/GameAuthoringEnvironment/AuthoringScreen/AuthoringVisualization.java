package GUI.GameAuthoringEnvironment.AuthoringScreen;

import GUI.GameAuthoringEnvironment.AuthoringComponents.NewGameButton;
import GUI.GameAuthoringEnvironment.AuthoringComponents.SaveButton;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AuthoringVisualization {

    private String Title = "VogaSalad";
    private Integer ScreenWIDTH = 500;
    private Integer ScreenHEIGHT = 500;
    private static final int FRAMES_PER_SECOND = 1;
    private static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;

    private Scene myScene;
    private BorderPane myRoot;
    private static final KeyCombination keyCombinationCommandN = new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN);

    public void start (Stage stage) {
        myRoot = new BorderPane();
        setUpScene(stage, myRoot);
        setStage(stage);
    }

    private void setStage(Stage stage){
        stage.setScene(myScene);
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        //set Stage boundaries to visible bounds of the main screen
        stage.setX(primaryScreenBounds.getMinX());
        stage.setY(primaryScreenBounds.getMinY());
        stage.setWidth(primaryScreenBounds.getWidth());
        stage.setHeight(primaryScreenBounds.getHeight());
        stage.setTitle(Title);
        stage.setResizable(true);
        stage.show();
        setAnimation();
    }

    private void setUpScene(Stage stage, BorderPane myRoot) {
        myScene = new Scene(myRoot, ScreenWIDTH, ScreenHEIGHT);
        addTopBar();
        addNotStaticModule();

        //myScene.getStylesheets().add(cssfile);

    }

    // add all the other modules that can be close
    private void addNotStaticModule(){

    }

    // add all the buttons - ex) save, load etc
    private void addTopBar(){

        SaveButton saveButton = new SaveButton();
        saveButton.getButton().setLayoutX(300);

        NewGameButton newGameButton = new NewGameButton();

        myRoot.getChildren().addAll(saveButton.getButton(), newGameButton.getButton());

        //myRoot.getChildren().add();

    }

    private void setAnimation(){
        var frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step());
        var animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
    }

    private void step(){

    }

    private void handleKeyInput(KeyEvent e) {
        if (keyCombinationCommandN.match(e)) {

            // add shortcuts
        }
    }


}
