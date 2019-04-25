package GameAuthoringEnvironment.AuthoringScreen;

import Configs.GamePackage.Game;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

public class StartingScreen {

    private VBox myContatiner;
    private Stage myStage;
    private TextField idTf, pwTf;
    private Button newGameButton, importGameButton;
    private HBox loginBox;
    private Text loginDescription;

    public void start (Stage stage) {
        myStage = stage;
        setScene();
        setStage();
    }


    private void setScene(){
        myContatiner = new VBox(10.00);
//<<<<<<< HEAD
//        Image test = new Image(getClass().getResourceAsStream("/ButtonImages/"+ "logo" +".png"));
//        ImageView imageView = new ImageView(test);
//        Button newGameButton = new Button("Create New Game");
//        newGameButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white; -fx-alignment: center");
//        newGameButton.setOnMouseClicked(this:: handleNewGameButton);
//        Button importGameButton = new Button("Import Game");
//        importGameButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white; -fx-alignment: center");
//=======
        myContatiner.setStyle("-fx-background-color: #898989");

        Image test = new Image(getClass().getResourceAsStream("/resources/" + "logo" +".png"));
        ImageView imageView = new ImageView(test);
        idTf = new TextField();
        idTf.setText("Enter ID");
        pwTf = new TextField();
        pwTf.setText("Enter Password");
        Button loginButton = new Button("Login");
        loginButton.setOnMouseClicked(this::handleLogin);
        Button createIDButton = new Button("Create Account");
        createIDButton.setOnMouseClicked(this::handleCreateAccount);

        loginBox = new HBox(10);
        loginBox.getChildren().addAll(loginButton, createIDButton);


        newGameButton = new Button("Create New Game");
        newGameButton.setOnMouseClicked(this:: handleNewGameButton);
        importGameButton = new Button("Import Game");
        importGameButton.setOnMouseClicked(this::importGame);


        loginDescription = new Text("Welcome to NoMergeConflicts. First login or create your new account");

        myContatiner.getChildren().addAll(imageView, idTf, pwTf, loginBox, loginDescription);

        Scene scene= new Scene(myContatiner, 800, 800);

        myStage.setScene(scene);
    }

    private void handleCreateAccount(MouseEvent event){
        //TODO Complete this part
        CreateAccount createAccount = new CreateAccount(this);
    }

    private void handleLogin(MouseEvent event){
        //TODO Call APIs to confirm password
        //if(log in successful)
        Text instructions = new Text("Now Click New Game to make a new Game or click import Game to import a existing game");

        myContatiner.getChildren().removeAll(idTf, pwTf, loginBox, loginDescription);
        myContatiner.getChildren().addAll(newGameButton, importGameButton, instructions);
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

            String filepath = selectedFile.toString();
            // TODO(Louis) game should be created by reading in the xml
            /*Game importedGame = new Game();
            importedGame = new Model(filepath);*/

            if (!filepath.endsWith("XML")) {
                //TODO(Hyunjae) alert!
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
