package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Created by user on 12-Dec-15.
 */
public class AdminLoginController {

    @FXML
    private TextField Username;

    @FXML
    private PasswordField Password;

    private AdminMain main;

    @FXML
    void LoginAction(ActionEvent event) {
        String ValidUsername = "admin";
        String ValidPassword = "123456";
        String userName = Username.getText().toString();
        String password = Password.getText().toString();
        if(userName.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("LOGIN ERROR");
            alert.setHeaderText("Username cannot be Empty");
            alert.setContentText("Enter a valid username");
            alert.getDialogPane().setPrefSize(430, 150);
            alert.showAndWait();
        }
        else if(password.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("LOGIN ERROR");
            alert.setHeaderText("Password cannot be empty");
            alert.setContentText("Enter a valid password");
            alert.getDialogPane().setPrefSize(430, 150);
            alert.showAndWait();
        }
        else if (userName.equals(ValidUsername) && password.equals(ValidPassword))  {
            try {
                main.AdminPage();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {
            // failed login
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("LOGIN ERROR");
            alert.setHeaderText("Incorrect username or password");
            alert.setContentText("The username and password you provided is not correct.");
            alert.getDialogPane().setPrefSize(430,150);
            alert.showAndWait();
        }

    }

    @FXML
    public  void ResetAction(ActionEvent event) {
        Username.setText("");
        Password.setText("");
    }

    public void setMain(AdminMain main) {
        this.main = main;
    }
}
