package GUI.GameAuthoringEnvironment.AuthoringScreen;

import GUI.GameAuthoringEnvironment.AuthoringComponents.*;
import GUI.GameAuthoringEnvironment.AuthoringScreen.Modules.GameOutline;
import GUI.GameAuthoringEnvironment.AuthoringScreen.Modules.GamePropertySettings;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
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
    private BorderPane myContainer;
    private static final KeyCombination keyCombinationCommandN = new KeyCodeCombination(KeyCode.ESCAPE);

    public void start (Stage stage) {
        var root = new BorderPane();
        setScene(stage, root);
        root.setOnKeyPressed(event -> handleKeyInput(event));
        setStage(stage);
    }

    private void setStage(Stage stage){

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        screenHeight = primaryScreenBounds.getHeight();
        screenWidth = primaryScreenBounds.getWidth();
        screenMinX = primaryScreenBounds.getMinX();
        screenMinY = primaryScreenBounds.getMinY();

        stage.setScene(myScene);

        //set Stage boundaries to visible bounds of the main screen
        stage.setX(screenMinX);
        stage.setY(screenMinY);
        stage.setWidth(screenWidth);
        stage.setHeight(screenHeight);
        stage.setTitle(Title);
        stage.setResizable(true);
        stage.show();
        setAnimation();
    }

    private void setScene(Stage stage, BorderPane myRoot) {
        myContainer = myRoot;

        //This is the only pane that should be fixed on the screen
        var leftGridPane = new GridPane();
        leftGridPane.setGridLinesVisible(true);
        setLeftGridPane(leftGridPane);

        var rightBorderPane = new BorderPane();
        setRightBorderPane(rightBorderPane);
        var centerBorderPane = new BorderPane();
        setCenterBorderPane(centerBorderPane);

        myContainer.setTop(addTopBar());
        myContainer.setLeft(leftGridPane);

        myScene = new Scene(myRoot);


    }


    private void setLeftGridPane(GridPane leftGridPane){
        GameOutline gameOutline = new GameOutline(myContainer,300, 1000, "GameOutline");
        leftGridPane.getChildren().addAll(gameOutline.getContent());
    }

    private void setRightBorderPane(BorderPane rightBorderPane){

    }

    private void setCenterBorderPane(BorderPane centerBorderPane){

    }

    // add all the other modules that can be close
    private void addNotStaticModule(){

    }

    // add all the buttons - ex) save, load etc
    private HBox addTopBar(){

        var TopMenuBar = new HBox();

        HelpButton helpButton = new HelpButton();
        SaveButton saveButton = new SaveButton();
        ImageButton imageButton = new ImageButton();
        LoadButton loadButton = new LoadButton();
        PlayButton playButton = new PlayButton();
        ViewButton viewButton = new ViewButton();
        NewGameButton newGameButton = new NewGameButton();
        newGameButton.getButton().setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent me) {
                System.out.println("Mouse entered");
                // TODO Make This pop up window that sets the Game Properties
                GamePropertySettings gamePropertySettings = new GamePropertySettings(screenWidth, screenHeight, "Game Property Settings");
            }
        });



        TopMenuBar.getChildren().addAll(newGameButton.getButton(), saveButton.getButton(), loadButton.getButton(), imageButton.getButton(), playButton.getButton(),
                viewButton.getButton(), helpButton.getButton());

        return  TopMenuBar;
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
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.show();
            // add shortcuts
        }
    }

    //TODO This will handle how the closing buttons work - automatic resizing of modules is necesary
    public void close(){

    }
}
