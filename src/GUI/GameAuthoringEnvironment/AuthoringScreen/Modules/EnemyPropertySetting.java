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



public class EnemyPropertySetting{

    private Stage popupwindow;
    private MenuButton gameTypeMenu;

    public EnemyPropertySetting(double width, double height, String moduleName) {
        popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle(moduleName);

        MenuButton projectile = new MenuButton("Choose Enemy");
        MenuButton projectileSpeed = new MenuButton("Choose Enemy Speed");
        MenuButton health = new MenuButton("Choose Enemy Health");



        Button saveButton = new Button("Save properties");

        //TODO When this button pressed, check if all required fields are filled
        saveButton.setOnAction(this::handleSaveArsenalProperty);

        VBox layout= new VBox();
        layout.getChildren().addAll(projectile, projectileSpeed, health, saveButton);
        initiate(width, height, popupwindow, layout);
    }

    private void handleSaveArsenalProperty(ActionEvent event) {

    }


    private void initiate(double width, double height, Stage popupwindow, VBox layout) {
        Scene scene= new Scene(layout, width/2, height/2);
        popupwindow.setScene(scene);
        popupwindow.showAndWait();

    }

}
