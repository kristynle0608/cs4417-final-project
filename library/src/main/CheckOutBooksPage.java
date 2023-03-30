package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CheckOutBooksPage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ScrollPane scrollPane = new ScrollPane(addGridPane());
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // Create scene
        Scene scene = new Scene(scrollPane, 700, 400);

        primaryStage.setTitle("Library - Check Out Books");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private static GridPane addGridPane() {
        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(30));
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        Label chooseBookLabel = new Label("Click on a book in the table");

        TableView<Book> tableView = new TableView<>();

        TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

// Add the columns to the TableView
        tableView.getColumns().addAll(idColumn, titleColumn, authorColumn);

        Label borrowersNameTable = new Label("Borrower's Name");
        TextField borrowersNameText = new TextField();

        Label checkOutDate = new Label("Check Out Date");

        // Add the TableView to the GridPane
        gridPane.add(chooseBookLabel, 0, 0);
        gridPane.add(tableView, 1, 0, 2, 1);

        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }
}
