package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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

        BorderPane borderPane = new BorderPane();
        HBox hBox = addHBox();
        borderPane.setTop(hBox);

        borderPane.setCenter(addGridPane());

        // Create scene
        Scene scene = new Scene(borderPane, 700, 400);

        primaryStage.setTitle("Library - Login");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private GridPane addGridPane() {
        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(30));
        gridPane.setHgap(25);
        gridPane.setVgap(20);

        Label loginLabel = new Label("Username");
        TextField loginText = new TextField();

        Label passwordLabel = new Label("Password");
        PasswordField passwordField = new PasswordField();

        Button submitButton = new Button("Submit");

        gridPane.add(loginLabel, 0, 0);
        gridPane.add(loginText, 1, 0);
        gridPane.add(passwordLabel, 0,1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(submitButton, 0, 2);

        gridPane.setAlignment(Pos.CENTER);

        return gridPane;
    }

    private HBox addHBox() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(50, 20, 0 , 20));
        hBox.setSpacing(10);

        Label title = new Label("Library - Login Page");
        title.setFont(Font.font(40));
        hBox.getChildren().add(title);
        hBox.setAlignment(Pos.CENTER);

        return hBox;
    }

    private boolean validateLogin() {
        
    }

}
