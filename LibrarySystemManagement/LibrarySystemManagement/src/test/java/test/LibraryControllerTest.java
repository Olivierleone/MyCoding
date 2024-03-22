/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import com.mycompany.librarysystemmanagement.Book;
import com.mycompany.librarysystemmanagement.DatabaseHandler;
import com.mycompany.librarysystemmanagement.LibraryController;
import com.mycompany.librarysystemmanagement.Student;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Olivier
 */
public class LibraryControllerTest {

    @Mock
    // Mocking the DatabaseHandler
    private DatabaseHandler databaseHandler;

    @InjectMocks
    // Injecting the mock into the LibraryController
    //making the librarycontroller use the mock
    private LibraryController libraryController;

    @Before
    // Setup method to initialize mocks
    //way to createsimulated or fake version of the database for testing purposes
    public void setUp() {
        // Initialize mocks
        MockitoAnnotations.initMocks(this);
    }

    @Test
    // Test case for the addBook method
    public void testAddBook() {
        //Create a book
        Book book = new Book();
        libraryController.addBook(book);

        // Verify that the insertBook method of DatabaseHandler is called
        verify(databaseHandler, times(1)).insertBook(book);
    }

    @Test
    // Test case for a successful book issuance
    public void testIssueBookSuccess() {
        // Create a book and a student
        Book book = new Book();
        Student student = new Student();

        // Mocking behavior for successful book issuance
        when(databaseHandler.issueBook(book, student)).thenReturn(true);

        // Call the issueBook method and assert the result
        assertTrue(libraryController.issueBook(book, student));

        // Verify that the issueBook method of DatabaseHandler is called
        verify(databaseHandler, times(1)).issueBook(book, student);
    }

    @Test
    // Test case for an unsuccessful book issuance
    public void testIssueBookFailure() {
        //Create a book and a student
        Book book = new Book();
        Student student = new Student();

        // Mocking behavior for unsuccessful book issuance
        when(databaseHandler.issueBook(book, student)).thenReturn(false);

        //Call the issueBook method and assert the result
        assertFalse(libraryController.issueBook(book, student));

        // Verify that the issueBook method of DatabaseHandler is called
        verify(databaseHandler, times(1)).issueBook(book, student);
    }

    @Test
    // Test case for a successful book return
    public void testReturnBookSuccess() {
        //Create a book and a student
        Book book = new Book();
        Student student = new Student();

        // Mocking behavior for successful book return
        when(databaseHandler.returnBook(book, student)).thenReturn(true);

        //Call the returnBook method and assert the result
        assertTrue(libraryController.returnBook(book, student));

        // Verify that the returnBook method of DatabaseHandler is called
        verify(databaseHandler, times(1)).returnBook(book, student);
    }

    @Test
    // Test case for an unsuccessful book return
    public void testReturnBookFailure() {
        //Create a book and a student
        Book book = new Book();
        Student student = new Student();

        // Mocking behavior for unsuccessful book return
        when(databaseHandler.returnBook(book, student)).thenReturn(false);

        // Call the returnBook method and assert the result
        assertFalse(libraryController.returnBook(book, student));

        // Verify that the returnBook method of DatabaseHandler is called
        verify(databaseHandler, times(1)).returnBook(book, student);
    }

}
