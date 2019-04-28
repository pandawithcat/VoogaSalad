package Player;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class LogInGrid extends GridPane {
    private TextField userTextField;
    private PasswordField password;
    public LogInGrid(){
        logIn();
    }
    private void logIn(){
        setId("login");
        setPadding(new Insets(25, 25, 25, 25));
        setHgap(10);
        setVgap(10);
        Label userName = new Label("User Name:");
        add(userName, 0, 1);
        userTextField = new TextField();

        add(userTextField, 1, 1);
        Label pw = new Label("Password:");
        add(pw, 0, 2);
        password = new PasswordField();
        add(password, 1, 2);
        setAlignment(Pos.CENTER);
    }

    public String getUserName(){
        return userTextField.getText();
    }

    public String getPassword(){
        return password.getText();
    }

}
