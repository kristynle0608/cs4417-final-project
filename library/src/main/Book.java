package main;

import java.time.LocalDate;

public class Book implements Comparable<Book>{

    private String title;

    private String author;

    private String borrower;

    private LocalDate checkOutDate;

    private LocalDate dueDate;

    private int id;

    public Book (int idIn, String titleIn, String authorIn) {
        id = idIn;
        title = titleIn;
        author = authorIn;
    }

    @Override
    public int compareTo(Book other) {
        return this.id - other.id;
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
