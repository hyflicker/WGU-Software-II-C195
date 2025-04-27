package arobertson.C195;

import arobertson.C195.Utilities.Alerts;
import arobertson.C195.Utilities.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Main Class - Starts the application and loads the login view.
 */
public class MainApplication extends Application {
    ResourceBundle lang = ResourceBundle.getBundle("language", Locale.getDefault());
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 648, 402);
        stage.setTitle(lang.getString("loginTitle"));
        stage.setScene(scene);
        stage.resizableProperty().setValue(false);
        stage.setOnCloseRequest((e)->{
            e.consume();
            Alerts.alertExit(stage);
        });
        stage.show();
        Database.openConnection();
    }

    public static void main(String[] args) {
        launch();
    }
}