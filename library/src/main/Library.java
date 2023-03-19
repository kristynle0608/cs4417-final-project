package main;

import java.util.ArrayList;

public class Library {

    // List of checked-out books
    private ArrayList<Book> checkOutList;

    // List of current books that are in the library
    private ArrayList<Book> storage;

    public Library () {
        checkOutList = new ArrayList<Book>();
        storage = new ArrayList<Book>();
    }

    public boolean addNewBookToStorage(String bookTitle, String author) {
        // check if book exists in storage
        for (int i = 0; i < storage.size(); i++) {
            Book book = storage.get(i);

            if (bookTitle.equalsIgnoreCase(book.getTitle()) && author.equalsIgnoreCase(book.getAuthor())) {
                return false;
            }
        }

        Book newBook = new Book(bookTitle, author);
        storage.add(newBook);

        return true;
    }

}
