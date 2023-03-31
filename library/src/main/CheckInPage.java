package main;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class CheckInPage extends Application {

    private static Book selectedBook;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ScrollPane scrollPane = new ScrollPane(addGridPane());
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        scrollPane.setPadding(new Insets(30, 10, 30, 10));

        // Create scene
        Scene scene = new Scene(scrollPane, 700, 400);

        primaryStage.setTitle("Library - Check In Books");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static Scene getScene() {
        ScrollPane scrollPane = new ScrollPane(addGridPane());
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        scrollPane.setPadding(new Insets(30, 10, 30, 10));

        // Create scene
        Scene scene = new Scene(scrollPane, 700, 400);

        return scene;
    }

    private static GridPane addGridPane() {
        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(30));
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        Label chooseBookLabel = new Label("Book (Select a book by clicking on a row):");

        ObservableList<Book> bookList = Library.getBookList("src/resources/checkOut.txt");

        TableView<Book> tableView = new TableView<>();

        tableView.setItems(bookList);

        TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Book, String> titleColumn = new TableColumn<>("Book Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));


        // Add the columns to the TableView
        tableView.getColumns().addAll(idColumn, titleColumn, authorColumn);

        // table view setOnMouseClicked
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                selectedBook = tableView.getSelectionModel().getSelectedItem();
            }
        });

        Label checkInDateLabel = new Label("Check In Date");
        DatePicker checkInDatePicker = new DatePicker();

        checkInDatePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(date.isBefore(today));
            }
        });

        Button checkInButton = new Button("Check In Book");
        Button clear = new Button("Clear");

        // checkInButton setOnAction
        checkInButton.setOnAction(event -> {
            Library.checkInSelectedBook(selectedBook, bookList);
        });

        // Add the TableView to the GridPane
        gridPane.add(chooseBookLabel, 0, 0);
        gridPane.add(tableView, 1, 0, 3, 1);
        gridPane.add(checkInDateLabel, 0, 1);
        gridPane.add(checkInDatePicker, 1, 1);
        gridPane.add(clear, 0, 2);
        gridPane.add(checkInButton, 1, 2);

        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }
}
