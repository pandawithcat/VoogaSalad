package GameAuthoringEnvironment.AuthoringScreen;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateAccount {

    private VBox layout;
    private TextField newIDTf, pwTf, confirmPwTf;
    private Button makeAccountButton;
    private Stage popupwindow;

    public CreateAccount(StartingScreen startingScreen){
        popupwindow = new Stage();
        layout = new VBox(10.00);
        setContent();
        VBox.setMargin(layout, new Insets(20, 20, 20, 20));
        Scene scene= new Scene(layout, 500, 500);
        popupwindow.setScene(scene);
        popupwindow.showAndWait();
    }


    private void setContent(){

        newIDTf = new TextField("Enter New ID");
        pwTf = new TextField("Enter Password");
        confirmPwTf = new TextField("Re-enter Password");
        makeAccountButton = new Button("Create Account");
        makeAccountButton.setOnMouseClicked(this::handleMakeAccountButton);

        layout.getChildren().addAll(newIDTf, pwTf, confirmPwTf, makeAccountButton);
    }

    private void handleMakeAccountButton(MouseEvent event){
        //TODO Set up APIs
        //if(true)
        if(pwTf.getText().equals(confirmPwTf.getText())){
            //TODO USE regex to check the id, pw etc
            popupwindow.close();

        }

        //if(false) - alert
    }

}