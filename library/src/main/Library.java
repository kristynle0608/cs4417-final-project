package main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        Book newBook = new Book(bookTitle, author, idTemp);

        String[] bookInfo = {bookTitle, author, String.valueOf(idTemp)};

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
        String fileContent = "";

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/resources/books.txt"));

            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] bookInfo = line.split(",");

                fileContent += line;

                if (!fileContent.trim().isEmpty()) {
                    lastAddedId = Integer.parseInt(bookInfo[2]);
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


}
