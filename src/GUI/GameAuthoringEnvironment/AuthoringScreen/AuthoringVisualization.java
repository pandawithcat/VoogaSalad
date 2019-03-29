package GUI.GameAuthoringEnvironment.AuthoringScreen;

import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class AuthoringVisualization {

    private String Title = "VogaSalad";
    private Integer ScreenWIDTH = 500;
    private Integer ScreenHEIGHT = 500;


    private Scene myScene;
    private Group myRoot;

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
        myRoot = new Group();
        Scene myScene = new Scene(myRoot, ScreenWIDTH, ScreenHEIGHT);
        addMenuBar(stage);

        //myScene.getStylesheets().add(cssfile);

        return myScene;
    }

    private void addMenuBar(Stage stage){
        MenuBar menuBar = new MenuBar();

        Menu FileMenu = new Menu("File");
        FileMenu.getItems().add(new MenuItem("New"));
        FileMenu.getItems().add(new MenuItem("Save"));
        FileMenu.getItems().add(new MenuItem("Exit"));

        menuBar.getMenus().add(FileMenu);
        myRoot.getChildren().add(menuBar);

    }

    public void close(Class<?> clazz) {
        String className = clazz.getSimpleName();
        moduleList.remove(className);

        closeModule(myModuleContainer.getString(className), myModulePosition.getString(className));

        if (clazz.equals(console.getClass())) {
            myStage.setMaxHeight(stageReducedHeight);
        }
        else if ((clazz.equals(palettes.getClass()) && (! moduleList.contains("CurrentState")))
                || (clazz.equals(currentState.getClass()) && (! moduleList.contains("Palettes")))) {
            myContainer.setLeft(null);
            buttonHandler.setMinWidth(stageReducedWidth);
            buttonHandler.setMaxWidth(stageReducedWidth);
            console.getContent().setMinWidth(stageReducedWidth);
            console.getContent().setMaxWidth(stageReducedWidth);
            console.getToolbarPane().setMaxWidth(stageReducedWidth);
            if (myStage.getWidth() != stageReducedWidth) {
                myStage.setMaxWidth(stageReducedWidth);
            }
        }
        else if ((!moduleList.contains("Editor") && !moduleList.contains("AvailableVars") && !moduleList.contains("UserCommands"))) {
            myContainer.setRight(null);
            buttonHandler.setMinWidth(stageReducedWidth);
            buttonHandler.setMaxWidth(stageReducedWidth);
            console.getContent().setMinWidth(stageReducedWidth);
            console.getContent().setMaxWidth(stageReducedWidth);
            if (myStage.getWidth() != stageReducedWidth) {
                myStage.setMaxWidth(stageReducedWidth);
            }
        }
    }







}
