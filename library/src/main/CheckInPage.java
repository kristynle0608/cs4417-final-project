package main;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class CheckInPage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }

    private static GridPane addGridPane() {
        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(30));
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        Label chooseBookLabel = new Label("Click on a book in the table");

        ObservableList<Book> bookList = Library.getBookList();

        TableView<Book> tableView = new TableView<>();

        tableView.setItems(bookList);

        TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
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

        Label borrowersNameLabel = new Label("Borrower's Name");
        TextField borrowersNameText = new TextField();

        Label checkOutDateLabel = new Label("Check Out Date");
        DatePicker checkOutDatePicker = new DatePicker();

        checkOutDatePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(date.isBefore(today));
            }
        });

        // due date
        Label dueDateLabel = new Label("Due Date");
        DatePicker dueDatePicker = new DatePicker();

        dueDatePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(date.isBefore(today));
            }
        });

        Button checkOutButton = new Button("Check Out Book");
        Button clear = new Button("Clear");

        // checkOutButton setOnAction
        checkOutButton.setOnAction(event -> {
            try {
                Library.checkOutSelectedBook(selectedBook, borrowersNameText.getText(), checkOutDatePicker.getValue(),
                        dueDatePicker.getValue(), bookList);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Add the TableView to the GridPane
        gridPane.add(chooseBookLabel, 0, 0);
        gridPane.add(tableView, 1, 0, 3, 1);
        gridPane.add(borrowersNameLabel, 0, 1);
        gridPane.add(borrowersNameText, 1, 1);
        gridPane.add(checkOutDateLabel, 0, 2);
        gridPane.add(checkOutDatePicker, 1, 2);
        gridPane.add(dueDateLabel, 0, 3);
        gridPane.add(dueDatePicker, 1, 3);
        gridPane.add(clear, 0, 4);
        gridPane.add(checkOutButton, 1, 4);

        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }
}
