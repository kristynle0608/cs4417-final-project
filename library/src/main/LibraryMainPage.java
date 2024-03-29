package main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LibraryMainPage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public static Scene getScene() {
        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(addFlowPane());
        borderPane.setTop(addTop());

        return new Scene(borderPane, 1500, 800);
    }

    private static FlowPane addFlowPane() {
        FlowPane flowPane = new FlowPane();
        flowPane.setPadding(new Insets(5));
        flowPane.setHgap(5);
        flowPane.setVgap(5);

        Button addNewBook = new Button("Add New Book");
        Button checkOutBook = new Button("Check Out Book");
        Button allBooks = new Button("All Books");
        Button checkInBooks = new Button("Check In Book");

        addNewBook.setOnAction(event -> {
            Scene newScene = AddBooksPage.getScene();
            Stage newStage = new Stage();
            newStage.setTitle("Library - Add New Book");
            newStage.setScene(newScene);
            newStage.show();
        });

        checkOutBook.setOnAction(event -> {
            Scene newScene = CheckOutBooksPage.getScene();
            Stage newStage = new Stage();
            newStage.setTitle("Library - Check Out Books");
            newStage.setScene(newScene);
            newStage.show();
        });

        checkInBooks.setOnAction(event -> {
            Scene newScene = CheckInBooksPage.getScene();
            Stage newStage = new Stage();
            newStage.setTitle("Library - Check In Books");
            newStage.setScene(newScene);
            newStage.show();
        });

        allBooks.setOnAction(event -> {
            Scene newScene = AllBooksPage.getScene();
            Stage newStage = new Stage();
            newStage.setTitle("Library - Get All Books");
            newStage.setScene(newScene);
            newStage.show();
        });

        addNewBook.setPrefSize(200, 200);
        checkOutBook.setPrefSize(200, 200);
        allBooks.setPrefSize(200, 200);
        checkInBooks.setPrefSize(200, 200);

        flowPane.getChildren().addAll(addNewBook, checkOutBook, checkInBooks, allBooks);

        flowPane.setAlignment(Pos.CENTER);

        return flowPane;
    }

    public static HBox addTop() {

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(50, 20, 0, 20));
        hBox.setSpacing(10);

        Label title = new Label("Library - Main Menu");
        title.setFont(Font.font(40));
        hBox.getChildren().add(title);
        hBox.setAlignment(Pos.CENTER);

        return hBox;
    }

    @Override
    public void start(Stage primaryStage) {

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(addFlowPane());
        borderPane.setTop(addTop());

        // Create scene
        Scene scene = new Scene(borderPane, 1500, 800);

        primaryStage.setTitle("Library - Main Page");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
