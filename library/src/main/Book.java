package main;

import java.time.LocalDate;

public class Book {

    private String title;

    private String author;

    private String borrower;

    private LocalDate checkOutDate;

    private LocalDate dueDate;

    private static int id;

    public Book (String titleIn, String authorIn, int idIn) {
        title = titleIn;
        author = authorIn;
        id = idIn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String titleUpdate) {
        title = titleUpdate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String authorUpdate) {
        author = authorUpdate;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrowerUpdate) {
        borrower = borrowerUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId (int idStatic) { id = idStatic; }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
