/*
package GameAuthoringEnvironment.AuthoringScreen.PropertySettings;

import GameAuthoringEnvironment.AuthoringScreen.GameController;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GamePropertySettings {

    private Stage popupwindow;
    private Label gameNameLabel, gameTypeLabel, screenSizeLabel, numberOfLivesLabel, numberOfLevelsLabel;
    private TextField gameNameTf, screenSizeTf, numberOfLivesTf, numberOfLevelTf;
    private MenuButton gameTypeMenu;
    private GameController myGameController;

    public GamePropertySettings(double width, double height){

        popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Game Property Settings");

        gameNameLabel = new Label("Game Name");
        gameNameTf = new TextField();
        //Button confirmButton1 = new Button("Confirm");

        gameTypeLabel = new Label("GameType");
        gameTypeMenu = new MenuButton();

        screenSizeLabel = new Label("Game Size");
        screenSizeTf = new TextField();
        //Button confirmButton2 = new Button("Confirm");

        numberOfLivesLabel = new Label("Number of Lives");
        numberOfLivesTf = new TextField();


        numberOfLevelsLabel = new Label("Number of Levels");
        numberOfLevelTf = new TextField();
        //Button confirmButton3= new Button("Confirm");


        Button createButton = new Button("Create Your Game");

        //TODO When this button pressed, check if all required fields are filled
        createButton.setOnAction(this::handleCreateGameButton);


        VBox layout= new VBox();
        layout.getChildren().addAll(gameNameLabel, gameNameTf, gameTypeLabel, gameTypeMenu,
                screenSizeLabel, screenSizeTf, numberOfLevelsLabel, numberOfLevelTf, numberOfLivesLabel, numberOfLivesTf, createButton);
        //layout.setAlignment(Pos.BOTTOM_CENTER);

        Scene scene= new Scene(layout, width/2, height/2);
        popupwindow.setScene(scene);
        popupwindow.showAndWait();
    }

    private void handleCreateGameButton(ActionEvent event) {

        String gameName = gameNameTf.getText();
        int screenSize = Integer.parseInt(screenSizeTf.getText());
        int numberOfLevels = Integer.parseInt(numberOfLevelTf.getText());
        int numberOfLives = Integer.parseInt(numberOfLivesTf.getText());

        myGameController = new GameController(gameName, screenSize, numberOfLevels, numberOfLives);
        popupwindow.close();

    }

    public GameController getMyGameController(){
        return myGameController;
    }


}
*/
