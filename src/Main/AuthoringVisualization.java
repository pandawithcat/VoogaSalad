package Main;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class AuthoringVisualization {

    private String Title = "VogaSalad";
    private Integer ScreenWIDTH = 500;
    private Integer ScreenHEIGHT = 500;


    private Scene myScene;

    public void start (Stage stage) {
        myScene = setUpVisualization(stage);
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
    }

    private Scene setUpVisualization(Stage stage) {
        Group root = new Group();
        Scene myScene = new Scene(root, ScreenWIDTH, ScreenHEIGHT, Color.GRAY);
        //myScene.getStylesheets().add(cssfile);
        return myScene;
    }




}
