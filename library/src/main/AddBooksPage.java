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
import javafx.stage.Stage;

public class AddBooksPage extends Application {

    private Library library;

    public static void main(String[] args) {
        launch(args);
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
        gridPane.add(submit, 0, 3);

        submit.setOnAction(event -> {

            boolean find = addBooks();
        });

        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }

    private static boolean addBooks(String bookTitle, String authorName) {
        boolean add = false;

        return add;
    }
}
