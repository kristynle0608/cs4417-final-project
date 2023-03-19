package main;

public class Book {

    private String title;

    private String author;

    private String borrower;

    private static int idStatic = 0;

    private static int id;

    public Book (String titleIn, String authorIn) {
        title = titleIn;
        author = authorIn;
        borrower = null;
        id = idStatic;
        idStatic++;
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
}
