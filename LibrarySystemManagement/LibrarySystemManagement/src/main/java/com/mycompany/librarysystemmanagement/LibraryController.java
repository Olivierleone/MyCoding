/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarysystemmanagement;

import static com.mycompany.librarysystemmanagement.DatabaseHandler.connect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * The controller class for managing library operations. Acts as an interface
 * between the GUI and the database handler. Handles adding, issuing, returning,
 * and viewing books, as well as searching and removing books.
 *
 * @author Olivier
 */
public class LibraryController {

    private DatabaseHandler databaseHandler;

    /**
     * Creates a new instance of LibraryController.
     *
     * @param databaseHandler The DatabaseHandler for handling database
     * operations.
     */
    public LibraryController(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    /**
     * Adds a book to the library catalog.
     *
     * @param book The Book object representing the book to be added.
     */
    public void addBook(Book book) {
        databaseHandler.insertBook(book);
    }

    /**
     * Issues a book to a student.
     *
     * @param book The Book object representing the book to be issued.
     * @param student The Student object representing the student to whom the
     * book is issued.
     * @return true if the book is issued successfully, false otherwise.
     */
    public boolean issueBook(Book book, Student student) {
        try {
            if (databaseHandler.issueBook(book, student)) {
                System.out.println("Book issued successfully");
                return true;
            } else {
                System.out.println("Failed to issue the book. Insufficient quantity.");
                return false;
            }
        } catch (Exception e) {
            handleException(e);
            return false;
        }
    }

    /**
     * Retrieves a book by its serial number.
     *
     * @param sn The serial number of the book.
     * @return The Book object if found, or null if not found.
     */
    private Book getBookBySN(String sn) {
        try (Connection connection = connect(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM Books WHERE SN = ?")) {
            statement.setString(1, sn);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Book book = new Book();
                    book.setSn(resultSet.getString("SN"));
                    book.setTitle(resultSet.getString("Title"));
                    book.setAuthor(resultSet.getString("Author"));
                    book.setPublisher(resultSet.getString("Publisher"));
                    book.setPrice(resultSet.getDouble("Price"));
                    book.setQuantity(resultSet.getInt("Quantity"));
                    book.setIssued(resultSet.getInt("Issued"));
                    book.setAddedDate(resultSet.getDate("addedDate"));
                    return book;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Returns a book previously issued to a student.
     *
     * @param book The Book object representing the book to be returned.
     * @param student The Student object representing the student returning the
     * book.
     * @return true if the book is returned successfully, false otherwise.
     */
    public boolean returnBook(Book book, Student student) {
        try {
            if (databaseHandler.returnBook(book, student)) {
                System.out.println("Book returned successfully");
                return true;
            } else {
                System.out.println("Failed to return the book. No copies issued.");
                return false;
            }
        } catch (Exception e) {
            handleException(e);
            return false;
        }
    }

    /**
     * Retrieves a map of issued books.
     *
     * @return A map of issued books, where the key is the serial number and the
     * value is the corresponding Book object.
     */
    public Map<String, Book> viewIssuedBooks() {
        return databaseHandler.getIssuedBooks();
    }

    /**
     * Retrieves a map of all books in the catalog.
     *
     * @return A map of all books, where the key is the serial number and the
     * value is the corresponding Book object.
     */
    public Map<String, Book> viewCatalog() {
        // Using Java Stream API to simplify the process
        return databaseHandler.getAllBooks().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Retrieves a map of available books.
     *
     * @return A map of available books, where the key is the serial number and
     * the value is the corresponding Book object.
     */
    public Map<String, Book> getAvailableBooks() {
        // Using Java Stream API to filter available books
        return databaseHandler.getAllBooks().entrySet().stream()
                .filter(entry -> entry.getValue().getQuantity() > entry.getValue().getIssued())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    /**
     * Retrieves a map of issued books.
     *
     * @return A map of issued books, where the key is the serial number and the
     * value is the corresponding Book object.
     */
    public Map<String, Book> getIssuedBooks() {
        Map<String, Book> issuedBooks = new TreeMap<>();
       
            return databaseHandler.getIssuedBooks();
    }

    /**
     * Searches for books based on title, author, and publisher.
     *
     * @param title The title of the book.
     * @param author The author of the book.
     * @param publisher The publisher of the book.
     * @return A map of books matching the search criteria.
     */
    public Map<String, Book> searchBooks(String title, String author, String publisher) {
        // Using Java Stream API for filtering
        return databaseHandler.searchBooks(title, author, publisher).entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    /**
     * Handles exceptions by printing the stack trace.
     *
     * @param e The exception to be handled.
     */
    private void handleException(Exception e) {
        // Handle or log the exception 
        e.printStackTrace();
    }

    /**
     * Removes a book from the catalog.
     *
     * @param book The Book object representing the book to be removed.
     * @return true if the book is removed successfully, false otherwise.
     */
    public boolean removeBook(Book book) {
        try {
            if (databaseHandler.removeBook(book)) {
                System.out.println("Book removed successfully");
                return true;
            } else {
                System.out.println("Failed to remove the book. Book not found.");
                return false;
            }
        } catch (Exception e) {
            handleException(e);
            return false;
        }
    }
     public boolean removeAllBooks() {
        try {
            // Get a map of all books
            Map<String, Book> allBooks = databaseHandler.getAllBooks();

            // Using Java Stream API to remove each book
            allBooks.entrySet().forEach(entry -> {
                Book bookToRemove = entry.getValue();
                databaseHandler.removeBook(bookToRemove);
            });

            System.out.println("All books removed successfully");
            return true;
        } catch (Exception e) {
            handleException(e);
            return false;
        }
    }

}
