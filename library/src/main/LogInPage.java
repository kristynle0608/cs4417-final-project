package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class LogInPage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane bPane = new BorderPane();
        VBox vBox = addLogin();

        // for login
        bPane.setCenter(addLogin());

        // Create scene
        Scene scene = new Scene(bPane);

        primaryStage.setTitle("Library - Login");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private VBox addLogin() {
        VBox vBox = new VBox();
        vBox.setSpacing(20.0);

        Label loginLabel = new Label("Login");
        TextField loginText = new TextField();

        Label passwordLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();

        vBox.getChildren().addAll(loginLabel, loginText, passwordLabel, passwordField);

        return vBox;
    }
}
