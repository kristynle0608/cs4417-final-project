package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LibraryMainPage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(addFlowPane());

        // Create scene
        Scene scene = new Scene(borderPane, 1500, 800);

        primaryStage.setTitle("Library - Main Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private FlowPane addFlowPane() {
        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(5));
        flowPane.setHgap(5);
        flowPane.setVgap(5);

        Button addNewBook = new Button("Add New Book");
        Button checkOutBook = new Button("Check Out Book");
        Button allBooks = new Button("All Books");
        Button checkInBooks = new Button("Check In Book");

        addNewBook.setPrefSize(200,200);
        checkOutBook.setPrefSize(200, 200);
        allBooks.setPrefSize(200, 200);
        checkInBooks.setPrefSize(200, 200);

        flowPane.getChildren().addAll(addNewBook, checkOutBook, checkInBooks, allBooks);

        flowPane.setAlignment(Pos.CENTER);

        return flowPane;
    }

    public HBox addTop() {
        HBox hBox = new HBox();
    }
}
