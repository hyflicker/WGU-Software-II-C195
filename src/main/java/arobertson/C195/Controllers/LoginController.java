package arobertson.C195.Controllers;

import Utilities.Alerts;
import arobertson.C195.DAO.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private Button exit_btn;
    @FXML
    private Button login_btn;
    @FXML
    private Label timezone_label;
    @FXML
    private Label location_label;
    @FXML
    private Label username_label;
    @FXML
    private Label title;
    @FXML
    private Label password_label;
    @FXML
    private PasswordField password;
    @FXML
    private TextField username;

    private static final ZoneId zoneId = ZoneId.systemDefault();
    private static final String location = zoneId.toString();

    ResourceBundle lang = ResourceBundle.getBundle("language");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username_label.setText(lang.getString("Username"));
        password_label.setText(lang.getString("Password"));
        timezone_label.setText(lang.getString("Location"));
        title.setText(lang.getString("Login"));
        login_btn.setText(lang.getString("Login"));
        exit_btn.setText(lang.getString("Exit"));
        location_label.setText(location);
    }

    @FXML
    void setExit_btn (ActionEvent event){
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Alerts.alertExit(stage);
    }
    @FXML
    void setLogin_btn () throws IOException, SQLException {
        String usernameInput = username.getText();
        String passwordInput = password.getText();

        if(usernameInput.isBlank()){
            Alerts.alertError(1);
            return;
        } else if (passwordInput.isBlank()) {
            Alerts.alertError(2);
            return;
        }
        if(!UserDAO.userLogin(usernameInput,passwordInput)){
            Alerts.alertError(3);
            loginAttemptLog(usernameInput,false);
        }else{
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/arobertson/c195/views/Main.fxml"));
          Parent parent = fxmlLoader.load();
          Scene scene = new Scene(parent);
            Stage stage = (Stage)  login_btn.getScene().getWindow();
            stage.setTitle("Main Menu");
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
            loginAttemptLog(usernameInput, true);
        }
    }
    private void loginAttemptLog(String username,boolean success) throws IOException {
        LocalDateTime timestamp = LocalDateTime.now();
        String status = success ? "SUCCESS" : "FAILURE";
        String logEntry = String.format("%s | %s | User: %s | Zone: %s%n",
                timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                status,
                username,
                zoneId
        );

        try (FileWriter fw = new FileWriter("login_activity.txt", true)) {
            fw.write(logEntry);
        }
    }
 }
