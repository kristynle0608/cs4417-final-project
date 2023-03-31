package main;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.time.LocalDate;

public class CheckInBooksPage extends Application {

    private static Book selectedBook;

    public static void main(String[] args) {
        launch(args);
    }

    public static Scene getScene() {
        ScrollPane scrollPane = new ScrollPane(addGridPane());
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        scrollPane.setPadding(new Insets(30, 10, 30, 10));

        // Create scene
        Scene scene = new Scene(scrollPane, 800, 500);

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
        tableView.setPrefSize(350, 600);

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

        Label feedbackLabel = new Label("");

        // checkInButton setOnAction
        checkInButton.setOnAction(event -> {
            try {
                Library.checkInSelectedBook(selectedBook, bookList);
                feedbackLabel.setText("Book successfully checked out. You can now close this page.");
                feedbackLabel.setTextFill(Color.GREEN);
            } catch (NullPointerException e) {
                feedbackLabel.setText("Book unsuccessfully checked out. Invalid or Missing Inputs.");
                feedbackLabel.setTextFill(Color.RED);
            }
        });

        // clear button
        clear.setOnAction(event -> {
            checkInDatePicker.setValue(null);
            feedbackLabel.setText("");
        });

        // Add the TableView to the GridPane
        gridPane.add(chooseBookLabel, 0, 0);
        gridPane.add(tableView, 1, 0, 3, 1);
        gridPane.add(checkInDateLabel, 0, 1);
        gridPane.add(checkInDatePicker, 1, 1);
        gridPane.add(feedbackLabel, 0, 2, 2, 1);
        gridPane.add(clear, 0, 3);
        gridPane.add(checkInButton, 1, 3);

        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }

    @Override
    public void start(Stage primaryStage) {
        ScrollPane scrollPane = new ScrollPane(addGridPane());
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        scrollPane.setPadding(new Insets(30, 10, 30, 10));

        // Create scene
        Scene scene = new Scene(scrollPane, 800, 400);

        primaryStage.setTitle("Library - Check In Books");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
