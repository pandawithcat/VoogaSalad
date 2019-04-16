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

public class PropertySetting {

    private Stage popupwindow;

    public PropertySetting(String screenName, double width, double height){

        popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle(screenName);

        Button createButton = new Button("Create Your Game");

        //TODO When this button pressed, check if all required fields are filled
        createButton.setOnAction(this::handleCreateGameButton);


        VBox layout= new VBox();
        layout.getChildren().addAll( createButton);

        Scene scene= new Scene(layout, width, height);
        popupwindow.setScene(scene);
        popupwindow.showAndWait();
    }

    private void handleCreateGameButton(ActionEvent event) {

        myGameController = new GameController(gameName, screenSize, numberOfLevels, numberOfLives);
        popupwindow.close();

    }



}
*/
