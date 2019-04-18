package GameAuthoringEnvironment.AuthoringScreen;

import BackendExternalAPI.Model;
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
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

public class StartingScreen {

    private VBox myContatiner;
    private Stage myStage;

    public void start (Stage stage) {
        myStage = stage;
        setScene();
        setStage();
    }


    private void setScene(){
        myContatiner = new VBox(10.00);
        myContatiner.setStyle("-fx-background-color: #898989");
        Image test = new Image(getClass().getResourceAsStream("/ButtonImages/"+ "logo" +".png"));
        ImageView imageView = new ImageView(test);
        Button newGameButton = new Button("Create New Game");
        newGameButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white; -fx-alignment: center");
        newGameButton.setOnMouseClicked(this:: handleNewGameButton);
        Button importGameButton = new Button("Import Game");
        importGameButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white; -fx-alignment: center");
        importGameButton.setOnMouseClicked(this::importGame);
        Text description = new Text("Welcome to NoMergeConflicts. This is a Tower defense game editor in which you can create your own unique tower defenese game!");

        myContatiner.getChildren().addAll(imageView, newGameButton, importGameButton, description);

        Scene scene= new Scene(myContatiner, 800, 800);

        myStage.setScene(scene);
    }

    private void makeGame(Game game){
        AuthoringVisualization authoringVisualization = new AuthoringVisualization(game);
        myStage.close();
    }

    private void handleNewGameButton(MouseEvent event){
        Game newGame = new Game();
        makeGame(newGame);
    }

    private void importGame(MouseEvent evemt){
        FileChooser fileChooser = new FileChooser();

        File selectedFile = fileChooser.showOpenDialog(myStage);
        if (selectedFile != null) {
            //TODO Make Game based on this
            String filepath = selectedFile.toString();
            // TODO game should be created by reading in the xml
            /*Game importedGame = new Game();
            importedGame = new Model(filepath);*/

            if (!filepath.endsWith("XML")) {
                //TODO Alert
            }
        }
        makeGame(new Game());
    }

    private void setStage(){

        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        double screenHeight = primaryScreenBounds.getHeight();
        double screenWidth = primaryScreenBounds.getWidth();
        double screenMinX = primaryScreenBounds.getMinX();
        double screenMinY = primaryScreenBounds.getMinY();

        //set Stage boundaries to visible bounds of the main screen
        myStage.setX(screenWidth/3);
        myStage.setY(screenHeight/3);
        myStage.setWidth(screenWidth/1.5);
        myStage.setHeight(screenHeight/1.5);
        myStage.setTitle("Welcome to NoMergeConflicts");

        myStage.show();
    }

}
