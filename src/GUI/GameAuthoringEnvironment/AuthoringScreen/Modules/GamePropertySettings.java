package GUI.GameAuthoringEnvironment.AuthoringScreen.Modules;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GamePropertySettings {


    public GamePropertySettings(double width, double height, String moduleName){

        Stage popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle(moduleName);

        Label GameNameLabel = new Label("Game Name");
        TextField GameNameTf = new TextField();
        Button confirmButton1 = new Button("Confirm");

        Label gameTypeLabel = new Label("GameType");
        MenuButton GameTypeMenu = new MenuButton();


        Label numberOfLevelsLabel = new Label("Number of Levels");
        TextField numberOfLevelTf = new TextField();
        Button confirmButton2= new Button("Confirm");


        Button createButton = new Button("Create Your Game");

        //TODO When this button pressed, check if all required fields are filled
        createButton.setOnAction(e -> popupwindow.close());


        VBox layout= new VBox(10);
        layout.getChildren().addAll(createButton);
        layout.setAlignment(Pos.BOTTOM_CENTER);

        Scene scene1= new Scene(layout, width/2, height/2);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }


}
