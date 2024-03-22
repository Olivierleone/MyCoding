/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarysystemmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Handles interactions with the database for the library system. Manages
 * connections, table creation, and CRUD operations for books and students. Also
 * provides methods for searching and retrieving book information from the
 * database.
 *
 * @author Olivier
 */
public class DatabaseHandler {

    // URL for the SQLite database file
    private static final String JDBC_URL = "jdbc:sqlite:library.db";

    /**
     * Establishes a connection to the SQLite database.
     *
     * @return The connection to the database.
     * @throws SQLException If a database access error occurs.
     */
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(JDBC_URL);
    }

    /**
     * Creates necessary tables in the database if they do not exist.
     */
    public static void createTables() {

        try (Connection connection = connect(); Statement statement = connection.createStatement()) {
            // Create Books, Students, IssuedBooks, and Users tables
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Books (SN TEXT PRIMARY KEY, Title TEXT NOT NULL, Author TEXT NOT NULL, Publisher TEXT NOT NULL, Price DOUBLE NOT NULL, Quantity INT, Issued INT, addedDate DATE);");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Students (StudentId INT PRIMARY KEY, Name TEXT, Contact TEXT);");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS IssuedBooks (id INT PRIMARY KEY, SN TEXT, StId INT, StName TEXT, StudentContact TEXT, IssueDate DATE, FOREIGN KEY (SN) REFERENCES Books (SN), FOREIGN KEY (StId) REFERENCES Students (StudentId));");
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS Users (Username TEXT PRIMARY KEY, Password TEXT NOT NULL);");

            // Handle or log the exception
        } catch (SQLException e) {

        }
    }

    /**
     * Inserts a new book into the database.
     *
     * @param book The Book object to insert.
     */
    public void insertBook(Book book) {
        try (Connection connection = connect(); PreparedStatement statement = connection.prepareStatement("INSERT INTO Books (SN, Title, Author, Publisher, Price, Quantity, Issued, addedDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            // Set parameters for the prepared statement
            statement.setString(1, book.getSn());
            statement.setString(2, book.getTitle());
            statement.setString(3, book.getAuthor());
            statement.setString(4, book.getPublisher());
            statement.setDouble(5, book.getPrice());
            statement.setInt(6, book.getQuantity());
            statement.setInt(7, book.getIssued());
            statement.setDate(8, new java.sql.Date(book.getAddedDate().getTime()));

            // Execute the update
            statement.executeUpdate();
        } catch (SQLException e) {
            // Handle or log the exception 
            handleSQLException(e);
        }
    }

    /**
     * Issues a book to a student and updates the database accordingly.
     *
     * @param book The Book object to issue.
     * @param student The Student object to whom the book is issued.
     * @return True if the book is successfully issued, false otherwise.
     */
    public boolean issueBook(Book book, Student student) {
        if (book.getQuantity() > book.getIssued()) {
            try (Connection connection = connect()) {
                // Start a transaction to ensure atomicity of the database updates
                connection.setAutoCommit(false);

                // Update the Books table to increment the Issued count
                try (PreparedStatement updateBookStatement = connection.prepareStatement("UPDATE Books SET Issued = Issued + 1 WHERE SN = ?")) {
                    updateBookStatement.setString(1, book.getSn());
                    updateBookStatement.executeUpdate();
                }

                // Insert into the IssuedBooks table
                try (PreparedStatement insertIssuedBookStatement = connection.prepareStatement("INSERT INTO IssuedBooks (SN, StId, StName, StudentContact, IssueDate) VALUES (?, ?, ?, ?, CURRENT_DATE)")) {
                    insertIssuedBookStatement.setString(1, book.getSn());
                    insertIssuedBookStatement.setInt(2, student.getStudentId());
                    insertIssuedBookStatement.setString(3, student.getName());
                    insertIssuedBookStatement.setString(4, student.getContact());
                    insertIssuedBookStatement.executeUpdate();
                }

                // Commit the transaction
                connection.commit();

                return true;
            } catch (SQLException e) {
                // Rollback the transaction in case of an exception
                handleSQLException(e);
                return false;
            }
        }
        return false;
    }

    /**
     * Returns a book from a student and updates the database accordingly.
     *
     * @param book The Book object to return.
     * @param student The Student object from whom the book is returned.
     * @return True if the book is successfully returned, false otherwise.
     */
    public boolean returnBook(Book book, Student student) {
        if (book.getIssued() > 0) {
            try (Connection connection = connect()) {
                // Start a transaction to ensure atomicity of the database updates
                connection.setAutoCommit(false);

                // Update the Books table to decrement the Issued count
                try (PreparedStatement updateBookStatement = connection.prepareStatement("UPDATE Books SET Issued = Issued - 1 WHERE SN = ?")) {
                    updateBookStatement.setString(1, book.getSn());
                    updateBookStatement.executeUpdate();
                }

                // Delete from IssuedBooks table
                try (PreparedStatement deleteIssuedBookStatement = connection.prepareStatement("DELETE FROM IssuedBooks WHERE SN = ? AND StId = ?")) {
                    deleteIssuedBookStatement.setString(1, book.getSn());
                    deleteIssuedBookStatement.setInt(2, student.getStudentId());
                    int rowsAffected = deleteIssuedBookStatement.executeUpdate();

                    // Commit the transaction only if the deletion was successful
                    if (rowsAffected > 0) {
                        connection.commit();
                        return true;
                    } else {
                        // Rollback the transaction if the deletion failed
                        connection.rollback();
                        return false;
                    }
                }
            } catch (SQLException e) {
                // Rollback the transaction in case of an exception
                handleSQLException(e);
                return false;
            }
        }
        return false;
    }

    /**
     * Removes a book from the database.
     *
     * @param book The Book object to remove.
     * @return True if the book is successfully removed, false otherwise.
     */
    public boolean removeBook(Book book) {
        try (Connection connection = connect(); PreparedStatement statement = connection.prepareStatement("DELETE FROM Books WHERE SN = ?")) {
            // Set parameters for the prepared statement
            statement.setString(1, book.getSn());

            // Execute the update
            int rowsAffected = statement.executeUpdate();

            return rowsAffected > 0;

            // Handle or log the exception 
        } catch (SQLException e) {
            handleSQLException(e);
            return false;
        }
    }

    /**
     * Retrieves all books from the database.
     *
     * @return A Map containing book serial numbers as keys and corresponding
     * Book objects as values.
     */
    public Map<String, Book> getAllBooks() {
        Map<String, Book> books = new TreeMap<>();
        try (Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT * FROM Books")) {
            // Iterate through the result set and populate the Map
            while (resultSet.next()) {
                Book book = new Book();
                book.setSn(resultSet.getString("SN"));
                book.setTitle(resultSet.getString("Title"));
                book.setAuthor(resultSet.getString("Author"));
                book.setPublisher(resultSet.getString("Publisher"));
                book.setPrice(resultSet.getDouble("Price"));
                book.setQuantity(resultSet.getInt("Quantity"));
                book.setIssued(resultSet.getInt("Issued"));
                book.setAddedDate(resultSet.getDate("addedDate"));

                books.put(book.getSn(), book);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return books;
    }

    public Map<String, Book> searchBooks(String title, String author, String publisher) {
        Map<String, Book> books = new HashMap<>();
        try (Connection connection = connect(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM Books WHERE Title LIKE ? AND Author LIKE ? AND Publisher LIKE ?")) {
            statement.setString(1, "%" + title + "%");
            statement.setString(2, "%" + author + "%");
            statement.setString(3, "%" + publisher + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Book book = new Book();
                    book.setSn(resultSet.getString("SN"));
                    book.setTitle(resultSet.getString("Title"));
                    book.setAuthor(resultSet.getString("Author"));
                    book.setPublisher(resultSet.getString("Publisher"));
                    book.setPrice(resultSet.getDouble("Price"));
                    book.setQuantity(resultSet.getInt("Quantity"));
                    book.setIssued(resultSet.getInt("Issued"));
                    book.setAddedDate(resultSet.getDate("addedDate"));

                    books.put(book.getSn(), book);
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return books;
    }

    public Map<String, Book> getIssuedBooks() {
        Map<String, Book> issuedBooks = new TreeMap<>();
        try (Connection connection = connect(); Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT b.*, i.StId, i.StName, i.StudentContact, i.IssueDate FROM Books b, IssuedBooks i WHERE b.SN = i.SN")) {
            while (resultSet.next()) {
                Book book = new Book();
                book.setSn(resultSet.getString("SN"));
                book.setTitle(resultSet.getString("Title"));
                book.setAuthor(resultSet.getString("Author"));
                book.setPublisher(resultSet.getString("Publisher"));
                book.setPrice(resultSet.getDouble("Price"));
                book.setQuantity(resultSet.getInt("Quantity"));
                book.setIssued(resultSet.getInt("Issued"));
                book.setAddedDate(resultSet.getDate("addedDate"));

                // Additional information from IssuedBooks table
                Student student = new Student();
                student.setStudentId(resultSet.getInt("StId"));
                student.setName(resultSet.getString("StName"));
                student.setContact(resultSet.getString("StudentContact"));

                // Set student information in the book object
                book.setIssuedTo(student);

                issuedBooks.put(book.getSn(), book);
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }
        return issuedBooks;
    }

    private void handleSQLException(SQLException e) {
        // Handle or log the exception according to your application's requirements
        e.printStackTrace();
    }
}
