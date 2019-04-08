package GameAuthoringEnvironment.AuthoringScreen.main;

import Configs.GamePackage.Game;
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


    public GamePropertySettings(double width, double height){

        popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Game Property Settings");

        Label gameNameLabel = new Label("Game Name");
        TextField gameNameTf = new TextField();
        //Button confirmButton1 = new Button("Confirm");

        Label gameTypeLabel = new Label("GameType");
        MenuButton gameTypeMenu = new MenuButton();

        Label screenSizeLabel = new Label("Game Size");
        TextField screenSizeTf = new TextField();
        //Button confirmButton2 = new Button("Confirm");

        Label numberOfLivesLabel = new Label("Number of Lives");
        TextField numberOfLivesTf = new TextField();


        Label numberOfLevelsLabel = new Label("Number of Levels");
        TextField numberOfLevelTf = new TextField();
        //Button confirmButton3= new Button("Confirm");


        Button createButton = new Button("Create Your Game");

        //TODO When this button pressed, check if all required fields are filled
        createButton.setOnAction(this::handleCreateGameButton);


        VBox layout= new VBox();
        layout.getChildren().addAll(gameNameLabel, gameNameTf, gameTypeLabel, gameTypeMenu,
                screenSizeLabel, screenSizeTf, numberOfLevelsLabel, numberOfLevelTf, createButton);
        //layout.setAlignment(Pos.BOTTOM_CENTER);

        Scene scene= new Scene(layout, width/2, height/2);
        popupwindow.setScene(scene);
        popupwindow.showAndWait();
    }

    private void handleCreateGameButton(ActionEvent event){
        Game myGame = new Game();

        popupwindow.close();

    }


}
