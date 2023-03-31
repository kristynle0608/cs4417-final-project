package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.time.LocalDate;
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

    public static boolean addNewBookToStorage(String bookTitle, String author) {

        int idTemp = getLastAddedId()+1;
        Book newBook = new Book(idTemp, bookTitle, author);

        String[] bookInfo = {String.valueOf(idTemp), bookTitle, author};

        try {
            File fileToWrite = new File("src/resources/books.txt");
            FileWriter fileWriter = new FileWriter(fileToWrite, true);

            for (int i = 0; i < bookInfo.length; i++) {
                fileWriter.write(bookInfo[i]);
                if (i < bookInfo.length - 1) {
                    fileWriter.write(",");
                }
            }
            fileWriter.write(System.lineSeparator());

            fileWriter.close();
        }
        catch (IOException ioException) {
            ioException.printStackTrace();
        }

        return true;
    }

    private static int getLastAddedId() {
        int lastAddedId = 0;
        int tempId;
        String fileContent = "";

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/resources/books.txt"));

            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] bookInfo = line.split(",");

                fileContent += line;

                if (!fileContent.trim().isEmpty()) {
                    tempId = Integer.parseInt(bookInfo[0]);
                    if (lastAddedId < tempId) {
                        lastAddedId = tempId;
                    }
                }
            }

            return lastAddedId;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Book> getStorage() {
        return storage;
    }

    public static ObservableList<Book> getBookList() {
        ObservableList<Book> bookList = FXCollections.observableArrayList();
        String filePath = "src/resources/books.txt";

        Library.readDataFromFile(bookList, filePath);

        return bookList;
    }

    private static void readDataFromFile(ObservableList<Book> books, String filePath) {

        String fileContent = "";

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] bookInfo = line.split(",");

                fileContent += line;

                if (!fileContent.trim().isEmpty()) {
                    books.add(new Book(Integer.parseInt(bookInfo[0]), bookInfo[1], bookInfo[2]));
                }
                else {
                    books = null;
                }
            }

            bufferedReader.close();
        }
        catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void checkOutSelectedBook(Book selectedBook, String borrower, LocalDate checkOutDate, LocalDate dueDate,
                                               ObservableList<Book> bookList) throws IOException {

        String file = "src/resources/books.txt";
        String checkOutFile = "src/resources/checkOut.txt";

        for (Book book : bookList) {
            if (selectedBook.compareTo(book) == 0) {
                book.setBorrower(borrower);
                book.setCheckOutDate(checkOutDate);
                book.setDueDate(dueDate);

                selectedBook = book;
            }
        }

        String lineToRemove = selectedBook.getId() + "," + selectedBook.getTitle() + ","
                + selectedBook.getAuthor();

        removeDataFromFile(file, checkOutFile, lineToRemove);

    }

    private static void removeDataFromFile(String file, String checkOutFile, String lineToRemove) {
        try {
            File inFile = new File(file);
            File outCheckedOutFile = new File(checkOutFile);

            if (!inFile.isFile()) {
                System.out.println("Parameter is not an existing file");
            }

            // create a temporary file to write the modified contents
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
            BufferedWriter checkedOutWriter = new BufferedWriter(new FileWriter(outCheckedOutFile, true));

            String line;
            while ((line = reader.readLine()) != null) {
                // remove the line that matches the lineToRemove parameter
                if (!line.trim().equals(lineToRemove)) {
                    writer.println(line);
                    writer.flush();
                } else {
                    // write the checked-out line to the checkout file
                    checkedOutWriter.write(line);
                    checkedOutWriter.newLine();
                    checkedOutWriter.flush();
                }
            }

            // close the streams
            reader.close();
            writer.close();
            checkedOutWriter.close();

            // delete the original file
            if (!inFile.delete()) {
                System.out.println("Could not delete the original file");
            }

            // rename the temporary file to the original file name
            if (!tempFile.renameTo(inFile)) {
                System.out.println("Could not rename the temporary file");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
