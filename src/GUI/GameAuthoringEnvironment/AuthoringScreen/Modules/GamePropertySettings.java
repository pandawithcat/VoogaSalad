package GUI.GameAuthoringEnvironment.AuthoringScreen.Modules;

import GUI.GameAuthoringEnvironment.AuthoringConfig.Game;
import GUI.GameAuthoringEnvironment.AuthoringController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GamePropertySettings {

    private Stage popupwindow;
    private Label gameNameLabel, gameTypeLabel, screenSizeLabel, numberOfLivesLabel, numberOfLevelsLabel;
    private TextField gameNameTf, screenSizeTf, numberOfLivesTf, numberOfLevelTf;
    private MenuButton gameTypeMenu;

    public GamePropertySettings(double width, double height, String moduleName){

        popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle(moduleName);

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
        layout.getChildren().addAll(gameNameLabel, gameNameTf, gameTypeLabel, gameTypeMenu,numberOfLivesLabel, numberOfLivesTf,
                createButton, screenSizeLabel, screenSizeTf, numberOfLevelsLabel, numberOfLevelTf);
        //layout.setAlignment(Pos.BOTTOM_CENTER);

        initiate(width, height, popupwindow, layout);
    }

    private void handleCreateGameButton(ActionEvent event){
        Game myGame = new Game(gameNameTf.getText(), gameTypeMenu.getText(), numberOfLivesTf.getText(), numberOfLevelTf.getText());
        AuthoringController controller = new AuthoringController(myGame);

        popupwindow.close();

    }

    private void initiate(double width, double height, Stage popupwindow, VBox layout) {
        Scene scene= new Scene(layout, width/2, height/2);
        popupwindow.setScene(scene);
        popupwindow.showAndWait();
    }


}
