package GameAuthoringEnvironment.AuthoringComponents;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class PrimitiveContainer {

    Label myLabel;
    TextField myTextField;
    Button confirmButton;

    public PrimitiveContainer(String key, VBox layout){
        myLabel = new Label(key);
        myTextField = new TextField();
        confirmButton = new Button("Confirm");
        layout.getChildren().addAll(myLabel, myTextField, confirmButton);
    }

    public String getUserInput(){
        return myTextField.getText();
    }

}
