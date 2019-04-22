package GameAuthoringEnvironment.AuthoringComponents;

import javafx.scene.control.Alert;

public class AlertScreen {

    public AlertScreen(){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setContentText("Atrributtes not all filled out");
        alert.showAndWait();
    }
}
