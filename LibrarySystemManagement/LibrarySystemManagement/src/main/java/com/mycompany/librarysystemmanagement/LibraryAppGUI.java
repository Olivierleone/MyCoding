/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.librarysystemmanagement;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

/**
 * Represents the main graphical user interface for the Library Management
 * System. Allows users to interact with the library, view the catalog, add
 * books, issue and return books. Handles the creation of the main GUI and
 * opening dialog for various operations.
 *
 * @author Olivier
 */
public class LibraryAppGUI extends javax.swing.JFrame {

    private LibraryController libraryController;
    // private ResourceBundle bundle;
    private JTextArea outputArea;

    /**
     * Creates a new instance of LibraryAppGUI.
     *
     * @param libraryController The LibraryController for managing library
     * operations.
     */
    public LibraryAppGUI(LibraryController libraryController) {
        this.libraryController = libraryController;

       // this.bundle = ResourceBundle.getBundle("LibraryAppGUI", currentLocale);

        setTitle("Library Management System");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createGUI();
    }

    /**
     * Initializes the graphical user interface components.
     */
    private void createGUI() {
        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create the output area for displaying information
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Create buttons for various library operations
        JButton viewCatalogButton = new JButton("View Catalog");
        viewCatalogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openViewCatalogDialog();
            }
        });

        JButton addBookButton = new JButton("Add Book");
        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openAddBookDialog();
            }
        });

        JButton issueBookButton = new JButton("Issue Book");
        issueBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openIssueBookDialog();
            }
        });

        JButton returnBookButton = new JButton("Return Book");
        returnBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openReturnBookDialog();
            }
        });

        JButton removeBookButton = new JButton("Remove Book");
        removeBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRemoveBook();
            }
        });

        // Add a "Back" button in your createGUI method
        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose the current frame and navigate back to the login page
                dispose();
                openInitialPage();
            }
        });

        // Create a button panel and add buttons to it
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewCatalogButton);
        buttonPanel.add(addBookButton);
        buttonPanel.add(issueBookButton);
        buttonPanel.add(returnBookButton);
        buttonPanel.add(removeBookButton);
        buttonPanel.add(backButton);

        // Add the main panel and button panel to the JFrame
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    /**
     * Opens the dialog for viewing the catalog.
     */
    private void openViewCatalogDialog() {
        // Clear the output area before displaying the catalog
    outputArea.setText("");

    // Show all books directly in the main JTextArea
    showAllBooks();
    }

        
    

    /**
     * Displays all books in the catalog.
     */
    private void viewCatalog() {
        outputArea.setText("");
        Map<String, Book> catalog = libraryController.viewCatalog();
        for (Book book : catalog.values()) {
            outputArea.append(book.toString() + "\n");
        }
    }

    /**
     * Opens the dialog for adding a new book.
     */
    private void openAddBookDialog() {
        AddBookDialog addBookDialog = new AddBookDialog(this, libraryController);
        addBookDialog.setVisible(true);
    }

    /**
     * Opens the dialog for issuing a book.
     */
    private void openIssueBookDialog() {
        IssueBookDialog issueBookDialog = new IssueBookDialog(this, libraryController);
        issueBookDialog.setVisible(true);
    }

    /**
     * Opens the dialog for returning a book.
     */
    private void openReturnBookDialog() {
        ReturnBookDialog returnBookDialog = new ReturnBookDialog(this, libraryController);
        returnBookDialog.setVisible(true);
    }

    /**
     * Opens the dialog for removing a book.
     */
    private void openRemoveBook() {
        RemoveBook removeBookDialog = new RemoveBook(this, libraryController);
        removeBookDialog.setVisible(true);
    }

    /**
     * Opens the dialog for showing all books
     */
    private void showAllBooks() {
        outputArea.setText("");
        Map<String, Book> catalog = libraryController.viewCatalog();
        for (Book book : catalog.values()) {
            outputArea.append(book.toString() + "\n");
        }
    }

    // Add this method to open the login form
    private void openInitialPage() {
        // Assuming userType is a variable or constant holding the user type
        String userType = "Librarian"; // Replace this with your actual userType value

        InitialPage initialPage = new InitialPage(libraryController);
        initialPage.setVisible(true);
    }

    public LibraryAppGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Main method to launch the application.
     *
     * @param args The command line arguments.
     */
    public static void main(String args[]) {
        // Launch the GUI on the event dispatch thread
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LibraryAppGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LibraryAppGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LibraryAppGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LibraryAppGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
 /* Create and display the form */
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DatabaseHandler.createTables();
                LibraryController controller = new LibraryController(new DatabaseHandler());
                LibraryAppGUI libraryAppGUI = new LibraryAppGUI(controller);
                libraryAppGUI.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
