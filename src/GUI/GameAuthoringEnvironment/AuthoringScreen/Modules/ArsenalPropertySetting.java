package GUI.GameAuthoringEnvironment.AuthoringScreen.Modules;

import GUI.GameAuthoringEnvironment.AuthoringConfig.Arsenal;
import GUI.GameAuthoringEnvironment.AuthoringConfig.Game;
import GUI.GameAuthoringEnvironment.AuthoringController;
import GUI.GameAuthoringEnvironment.AuthoringScreen.Modules.Editors.ArsenalEditor;
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

import java.awt.event.TextEvent;


public class ArsenalPropertySetting {

    private Stage popupwindow;
    private MenuButton gameTypeMenu;
    private ArsenalEditor myArsenalEditor;
    private Label nameLabel, priceLabel;
    private TextField nameTf, priceTf;
    private MenuButton projectile, projectileSpeed, power;

    public ArsenalPropertySetting(double width, double height, ArsenalEditor arsenalEditor) {

        myArsenalEditor = arsenalEditor;
        popupwindow = new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Arsenal Property Setting");

        nameLabel = new Label("Arsenal Name");
        nameTf = new TextField();

        priceLabel = new Label("Arsenal Price");
        priceTf = new TextField();

        projectile = new MenuButton("Choose Projectile");
        projectileSpeed = new MenuButton("Choose Projectile Speed");
        power = new MenuButton("Choose Projectile Power");

        Button saveButton = new Button("Save properties");

        //TODO When this button pressed, check if all required fields are filled
        saveButton.setOnAction(this::handleSaveArsenalProperty);

        VBox layout= new VBox();
        layout.getChildren().addAll(nameLabel, nameTf, priceLabel, priceTf, projectile, projectileSpeed, power, saveButton);
        initiate(width, height, popupwindow, layout);
    }

    private void handleSaveArsenalProperty(ActionEvent event) {
        Arsenal newArsenal = new Arsenal(nameTf.getText());
        myArsenalEditor.addArsenals(newArsenal);
        myArsenalEditor.getSourceView().getItems().add(newArsenal);
        popupwindow.close();
    }


    private void initiate(double width, double height, Stage popupwindow, VBox layout) {
            Scene scene= new Scene(layout, width/2, height/2);
            popupwindow.setScene(scene);
            popupwindow.showAndWait();

    }

}
