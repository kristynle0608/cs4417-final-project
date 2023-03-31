package main;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Comparator;

public class AllBooksPage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();
        FlowPane flowPane = addFlowPane();
        flowPane.setAlignment(Pos.CENTER);
        borderPane.setCenter(addFlowPane());

        HBox hBox = new HBox(getHBox());
        hBox.setAlignment(Pos.CENTER);
        borderPane.setTop(hBox);

        // Create scene
        Scene scene = new Scene(borderPane, 700, 600);

        primaryStage.setTitle("Library - Get All Books");
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static Scene getScene() {
        BorderPane borderPane = new BorderPane();
        FlowPane flowPane = addFlowPane();
        flowPane.setAlignment(Pos.CENTER);
        borderPane.setCenter(addFlowPane());

        HBox hBox = new HBox(getHBox());
        hBox.setAlignment(Pos.CENTER);
        borderPane.setTop(hBox);

        // Create scene
        Scene scene = new Scene(borderPane, 700, 600);

        return scene;
    }

    private static FlowPane addFlowPane() {

        FlowPane flowPane = new FlowPane();

        TableView<Book> tableView = new TableView<>();

        ObservableList<Book> bookList1 = Library.getBookList("src/resources/books.txt");
        ObservableList<Book> bookList2 = Library.getBookList("src/resources/checkOut.txt");

        ObservableList<Book> combinedList = FXCollections.observableArrayList();
        combinedList.addAll(bookList1);
        combinedList.addAll(bookList2);

        combinedList.sort(bookComparator);

        tableView.setItems(combinedList);

        // set columns
        TableColumn<Book, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        tableView.getColumns().addAll(idColumn, titleColumn, authorColumn);

        tableView.setPrefHeight(300);
        tableView.setPrefWidth(500);

        flowPane.getChildren().add(tableView);

        flowPane.setAlignment(Pos.CENTER);
        return flowPane;
    }

    static Comparator<Book> bookComparator = new Comparator<Book>() {
        public int compare(Book book1, Book book2) {
            return book1.getId() - book2.getId();
        }
    };

    private static HBox getHBox() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(50, 20, 50 , 20));
        hBox.setSpacing(10);

        Label title = new Label("All Books that the Library Owns");
        title.setFont(Font.font(30));
        hBox.getChildren().add(title);
        hBox.setAlignment(Pos.CENTER);

        return hBox;
    }

}
