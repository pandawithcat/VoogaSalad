package Player;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CreateAccount extends GridPane {
    private TextField userTextField;
    private PasswordField passwordField;
    private PasswordField passwordCheck;

    public CreateAccount(){
        logInWithPasswordCheck();
    }
    private void logInWithPasswordCheck(){
        setId("login");
        setPadding(new Insets(25, 25, 25, 25));
        final Label userName = new Label("User Name:");
        add(userName, 1, 1);
        userTextField = new TextField();
        add(userTextField, 2, 1);

        Label pw = new Label("Password:");
        add(pw, 1, 2);
        passwordField = new PasswordField();
        add(passwordField , 2, 2);

        Label pwConfirm = new Label("Confirm Password:");
        add(pwConfirm, 1, 3);
        passwordCheck = new PasswordField();
        add(passwordCheck, 2, 3);

        setAlignment(Pos.CENTER);
    }
    public String getUserName(){
        return userTextField.getText();
    }

    public String getPasswordField(){
        return passwordField.getText();
    }

    public String getPasswordCheck(){
        return passwordCheck.getText();
    }
}
