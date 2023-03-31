package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AddBooksPage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static Scene getScene() {
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(addGridPane());

        // Create scene
        return new Scene(borderPane, 700, 400);
    }

    private static GridPane addGridPane() {
        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(30));
        gridPane.setHgap(25);
        gridPane.setVgap(10);

        Label instructions = new Label("Enter all the information below to add a new book:");

        // book
        Label bookTitleLabel = new Label("Title of the Book: ");
        TextField bookTitleText = new TextField();

        // author
        Label authorLabel = new Label("Author of the Book (type N/A if author is unknown:");
        TextField authorText = new TextField();

        Button submit = new Button("Submit");
        Button clear = new Button("Clear");

        gridPane.add(instructions, 0, 0, 2, 1);
        gridPane.add(bookTitleLabel, 0, 1);
        gridPane.add(bookTitleText, 1, 1);
        gridPane.add(authorLabel, 0, 2);
        gridPane.add(authorText, 1, 2);
        gridPane.add(clear, 0, 3);
        gridPane.add(submit, 1, 3);

        submit.setOnAction(event -> {
            boolean add = addBooks(bookTitleText.getText(), authorText.getText());

            if (!add) {
                //TODO-: clear every text box and shows an error
                instructions.setText("Unable to add book. Please try again.");
                instructions.setTextFill(Color.RED);
                bookTitleText.setText("");
                authorText.setText("");
            } else {
                instructions.setText("Successfully added book. Please close the window.");
                instructions.setTextFill(Color.GREEN);
            }
        });
        clear.setOnAction(event -> {
            instructions.setText("Enter all the information below to add a new book:");
            instructions.setTextFill(Color.BLACK);
            bookTitleText.setText("");
            authorText.setText("");
        });

        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }

    private static boolean addBooks(String bookTitle, String authorName) {
        boolean add;

        add = Library.addNewBookToStorage(bookTitle, authorName);

        return add;
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(addGridPane());

        // Create scene
        Scene scene = new Scene(borderPane, 700, 400);

        primaryStage.setTitle("Library - Add Books To Library");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
