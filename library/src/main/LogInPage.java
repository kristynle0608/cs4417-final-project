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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.io.*;

public class LogInPage extends Application {

    private Text errorText;
    private TextField loginText;
    PasswordField passwordField;
    private Stage tempStage;

    public static void main(String[] args) { launch(args); }

    @Override
    public void start(Stage primaryStage) {

        System.out.println(new File(".").getAbsolutePath());

        BorderPane borderPane = new BorderPane();
        HBox hBox = addHBox();
        borderPane.setTop(hBox);

        borderPane.setCenter(addGridPane());
        borderPane.setBottom(addBottomPane());

        // Create scene
        Scene scene = new Scene(borderPane, 700, 400);

        primaryStage.setTitle("Library - Login");
        primaryStage.setScene(scene);

        tempStage = primaryStage;
        primaryStage.show();

    }

    private GridPane addGridPane() {
        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(30));
        gridPane.setHgap(25);
        gridPane.setVgap(20);

        Label loginLabel = new Label("Username");
        loginText = new TextField();

        Label passwordLabel = new Label("Password");
        passwordField = new PasswordField();

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            boolean validate = validateLogin(loginText.getText(), passwordField.getText());

            if (!validate) {
                loginText.setText("");
                passwordField.setText("");
            }
            else {
                errorText.setText(" ");
                Scene newScene = LibraryMainPage.getScene();
                Stage newStage = new Stage();
                newStage.setTitle("Library - Main Page");
                newStage.setScene(newScene);
                newStage.show();
                tempStage.close();
            }


            //TODO: After validation is successful, move to another scene that allows user to check in/check out
        });

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

    private HBox addBottomPane() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10));

        errorText = new Text();
        errorText.setFill(Color.RED);

        hBox.getChildren().add(errorText);
        hBox.setAlignment(Pos.CENTER);

        return hBox;
    }

    /**
     * Check if username and password are correct
     * @param userNameIn entered username
     * @param passwordIn entered password
     * @return boolean to confirm if username and password are correct or not
     */
    private boolean validateLogin(String userNameIn, String passwordIn) {

        try {
            FileReader reader = new FileReader("src/resources/userLogin.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            if ((line = bufferedReader.readLine().trim()) != null) {
                if (!userNameIn.equals(line)) {
                    reader.close();

                    errorText.setText("Username or Password is Incorrect");
                    return false;
                }
            }
            else {
                reader.close();

                errorText.setText("Username or Password is Incorrect");
                return false;
            }

            if ((line = bufferedReader.readLine().trim()) != null) {
                if (!passwordIn.equals(line)) {
                    reader.close();

                    errorText.setText("Username or Password is Incorrect");
                    return false;
                }
                else {
                    reader.close();


                    errorText.setText("");
                    return true;
                }
            }
            else {
                reader.close();

                errorText.setText("Username or Password is Incorrect");
                return false;
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
