/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.librarysystemmanagement;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.ResourceBundle;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Dialog for adding a new book to the library. Extends javax.swing.JDialog to
 * create a custom dialog window.
 *
 * @author Olivier
 */
public class AddBookDialog extends javax.swing.JDialog {

    // Reference to the LibraryController for interacting with the library
    private LibraryController libraryController;
    
   // private ResourceBundle resourceBundle;

    // Text fields for user input
    private JTextField snField;
    private JTextField titleField;
    private JTextField authorField;
    private JTextField publisherField;
    private JTextField priceField;
    private JTextField quantityField;

    /**
     * Creates a new instance of the AddBookDialog.
     *
     * @param parent The parent JFrame.
     * @param libraryController The LibraryController associated with the
     * dialog.
     */
    public AddBookDialog(JFrame parent, LibraryController libraryController) {

        // Call the constructor of the superclass (JDialog)
        super(parent, "Add Book", true);
        this.libraryController = libraryController;

        // Set the size and location of the dialog window
        setSize(400, 300);
        setLocationRelativeTo(parent);

        // Initialize and set up the graphical user interface
        createGUI();
    }

    /**
     * Creates the graphical user interface for the AddBookDialog.
     */
    private void createGUI() {
        // Create the main panel with a grid layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 2));

        // Initialize text fields for user input
        snField = new JTextField();
        titleField = new JTextField();
        authorField = new JTextField();
        publisherField = new JTextField();
        priceField = new JTextField();
        quantityField = new JTextField();

        // Add labels and corresponding text fields to the main panel
        mainPanel.add(new JLabel("Serial Number:"));
        mainPanel.add(snField);
        mainPanel.add(new JLabel("Title:"));
        mainPanel.add(titleField);
        mainPanel.add(new JLabel("Author:"));
        mainPanel.add(authorField);
        mainPanel.add(new JLabel("Publisher:"));
        mainPanel.add(publisherField);
        mainPanel.add(new JLabel("Price:"));
        mainPanel.add(priceField);
        mainPanel.add(new JLabel("Quantity:"));
        mainPanel.add(quantityField);

        // Create buttons and add action listeners
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
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
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);

        // Add the main panel and button panel to the dialog
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    /**
     * Adds a new book to the library using the information entered in the
     * dialog. Displays a success message upon successful addition.
     */
    private void addBook() {
        
        // Check if any of the fields is empty
    if (snField.getText().isEmpty() || titleField.getText().isEmpty() || authorField.getText().isEmpty()
            || publisherField.getText().isEmpty() || priceField.getText().isEmpty() || quantityField.getText().isEmpty()) {
        // Display an error message for empty fields
        JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
        // Create a new Book object
        Book newBook = new Book();
        newBook.setSn(snField.getText());
        newBook.setTitle(titleField.getText());
        newBook.setAuthor(authorField.getText());
        newBook.setPublisher(publisherField.getText());
        try {
            // Parse and set the price and quantity from user input
            newBook.setPrice(Double.parseDouble(priceField.getText()));
            newBook.setQuantity(Integer.parseInt(quantityField.getText()));
        } catch (NumberFormatException e) {

            // Display an error message for invalid input(error handling)
            JOptionPane.showMessageDialog(this, "Invalid input for price or quantity.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Set a default addedDate for the new book
        newBook.setAddedDate(new Date());

        // Add the new book to the library
        libraryController.addBook(newBook);

        // Display a success message
        JOptionPane.showMessageDialog(this, "Book added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Clear the text fields and close the dialog(close old window)
        clearFields();
        dispose();
    }

    /**
     * Clears all the text fields in the dialog.
     */
    private void clearFields() {
        snField.setText("");
        titleField.setText("");
        authorField.setText("");
        publisherField.setText("");
        priceField.setText("");
        quantityField.setText("");
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
            java.util.logging.Logger.getLogger(AddBookDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddBookDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddBookDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddBookDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AddBookDialog().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
