/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.librarysystemmanagement;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Represents the GUI for the student's library application. Provides
 * functionality for searching, borrowing, and returning books. Extends JFrame
 * for the graphical interface.
 *
 * @author Olivier
 */
public class StudentAppGUI extends javax.swing.JFrame {

    private LibraryController libraryController;

    private JTextArea outputArea;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField publisherField;
    //private ResourceBundle bundle;

    /**
     * Constructs a new instance of the StudentAppGUI class. Initializes the GUI
     * components and sets up the necessary event listeners.
     *
     * @param libraryController The library controller for handling library
     * operations.
     */
    public StudentAppGUI(LibraryController libraryController) {
        this.libraryController = libraryController;

        setTitle("Student Library App");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //bundle = ResourceBundle.getBundle("StudentAppGUI", new Locale("en"));

        createGUI();
    }

    /**
     * Creates and sets up the graphical user interface components. Configures
     * layout, buttons, text fields, and event listeners.
     */
    private void createGUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        titleField = new JTextField();
        authorField = new JTextField();
        publisherField = new JTextField();

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                search();
            }
        });

        JButton borrowButton = new JButton("Borrow Book");
        borrowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openBorrowBookDialog();
            }
        });

        JButton returnButton = new JButton("Return Book");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openReturnBookDialog();
            }
        });

        JButton backButton = new JButton("Back to Main Menu");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Dispose the current frame and navigate back to the login page
                dispose();
                openInitialPage();
            }
        });

        JPanel searchPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

        buttonPanel.add(searchButton);
        buttonPanel.add(borrowButton);
        buttonPanel.add(returnButton);
        System.out.println("Button added");
        buttonPanel.add(backButton);
        searchPanel.add(buttonPanel, BorderLayout.WEST);

        // Add labels and text fields to the center
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 1));
        inputPanel.add(new JLabel("Name of Book:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Author's Name:"));
        inputPanel.add(authorField);
        inputPanel.add(new JLabel("Publisher:"));
        inputPanel.add(publisherField);
        searchPanel.add(inputPanel, BorderLayout.CENTER);

        mainPanel.add(searchPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    /**
     * Executes a search based on the entered criteria and displays the results
     * in the JTextArea.
     */
    private void search() {
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        String publisher = publisherField.getText().trim();

        // Implement the search functionality based on the entered criteria
        Map<String, Book> searchResult = libraryController.searchBooks(title, author, publisher);

        outputArea.setText("");
        if (searchResult.isEmpty()) {
            outputArea.append("No books found matching the criteria.");
        } else {
            for (Book book : searchResult.values()) {
                outputArea.append(book.toString() + "\n");
            }
        }
    }

    /**
     * Opens the BorrowBookDialog for the student to borrow a book.
     */
    private void openBorrowBookDialog() {
        IssueBookDialog issueBookDialog = new IssueBookDialog(this, libraryController);
        issueBookDialog.setModal(true);
        issueBookDialog.setVisible(true);
    }

    /**
     * Opens the ReturnBookDialog for the student to return a book.
     */
    private void openReturnBookDialog() {
        ReturnBookDialog returnBookDialog = new ReturnBookDialog(this, libraryController);
        returnBookDialog.setModal(true);
        returnBookDialog.setVisible(true);
    }

    // Add this method to open the login page
   
     
    private void openInitialPage() {
        // Assuming userType is a variable or constant holding the user type
    String userType = "Student"; // Replace this with your actual userType value
    
    InitialPage initialPage = new InitialPage(libraryController);
    initialPage.setVisible(true);
}

    /**
     * Creates new form StudentAppGUI
     */
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
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(StudentAppGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentAppGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentAppGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentAppGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DatabaseHandler.createTables();
                LibraryController controller = new LibraryController(new DatabaseHandler());

                StudentAppGUI studentAppGUI = new StudentAppGUI(controller);
                studentAppGUI.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
