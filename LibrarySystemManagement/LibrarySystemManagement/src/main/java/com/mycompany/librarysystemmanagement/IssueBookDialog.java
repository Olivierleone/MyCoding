/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.librarysystemmanagement;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Represents the dialog for issuing books in the library system GUI. Allows
 * librarians to issue books to students. Handles the creation of the issue book
 * dialog GUI and issuing books.
 *
 * @author Olivier
 */
public class IssueBookDialog extends javax.swing.JDialog {

    private LibraryController libraryController;
    //private ResourceBundle bundle;
    private JComboBox<String> bookComboBox;
    private JTextField studentIdField;

    /**
     * Creates a new instance of IssueBookDialog.
     *
     * @param parent The parent JFrame.
     * @param libraryController The LibraryController for managing library
     * operations.
     */
    public IssueBookDialog(JFrame parent, LibraryController libraryController) {
        super(parent, "Issue Book", true);
        this.libraryController = libraryController;

        //this.bundle = ResourceBundle.getBundle("IssueBookDialog", currentLocale);
        setSize(400, 200);
        setLocationRelativeTo(parent);

        createGUI();
    }

    /**
     * Initializes the graphical user interface components.
     */
    private void createGUI() {

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2));

        // Retrieve the catalog of books from the library controller
        Map<String, Book> catalog = libraryController.viewCatalog();
        String[] bookSnArray = catalog.keySet().toArray(new String[0]);

        // Create the book selection combo box and student ID field
        bookComboBox = new JComboBox<>(bookSnArray);
        studentIdField = new JTextField();

        // Add labels and components to the main panel
        //mainPanel.add(new JLabel(bundle.getString("selectBookLabel")));
        
        mainPanel.add(new JLabel("Select Book:"));
        mainPanel.add(bookComboBox);

       // mainPanel.add(new JLabel(bundle.getString("enterStudentIdLabel")));

        mainPanel.add(new JLabel("Enter Student ID:"));
        mainPanel.add(studentIdField);

        // Create buttons for issuing and canceling
        JButton issueButton = new JButton("Issue");
        issueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                issueBook();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Create a button panel and add buttons to it
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(issueButton);
        buttonPanel.add(cancelButton);

        // Add main panel and button panel to the dialog
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Handles the issuance of a book.
     */
    private void issueBook() {
        // Get the selected book from the combo box
        String selectedBookSn = (String) bookComboBox.getSelectedItem();
        if (selectedBookSn == null) {
            JOptionPane.showMessageDialog(this, "No book selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Retrieve the selected book from the library controller
        Book selectedBook = libraryController.viewCatalog().get(selectedBookSn);

        // Check if the book is available for issuance
        if (selectedBook.getQuantity() > selectedBook.getIssued()) {
            try {
                // Parse the student ID from the text field
                int studentId = Integer.parseInt(studentIdField.getText());
                Student student = new Student();
                student.setStudentId(studentId);

                // Attempt to issue the book and display appropriate messages
                if (libraryController.issueBook(selectedBook, student)) {
                    JOptionPane.showMessageDialog(this, "Book issued successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to issue the book. Insufficient quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input for student ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "The selected book is not available for issuing.", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
            java.util.logging.Logger.getLogger(IssueBookDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueBookDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueBookDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueBookDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new IssueBookDialog().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
