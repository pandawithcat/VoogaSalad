package GameAuthoringEnvironment.AuthoringScreen;

import javafx.scene.control.Alert;

public class AlertFactory {
    public AlertFactory(){}

    public void createAlert(String message){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error during Configuration");
            alert.setContentText(message);
            alert.showAndWait();
        }
    }


