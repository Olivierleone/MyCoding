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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 * Dialog for returning a book to the library system. Extends JDialog for
 * creating a custom dialog window. Allows users to select a book from the
 * catalog and enter a student ID for return. Communicates with the
 * LibraryController to perform the return operation. Provides a graphical user
 * interface with combo boxes, text fields, and buttons.
 *
 * @author Olivier
 */
public class ReturnBookDialog extends javax.swing.JDialog {

    private LibraryController libraryController;
    private JComboBox<String> bookComboBox;
    private JTextField studentIdField;
    //  private ResourceBundle bundle;

    /**
     * Creates a new instance of the ReturnBookDialog.
     *
     * @param parent The parent JFrame that owns this dialog.
     * @param libraryController The controller for the library system.
     */
    public ReturnBookDialog(JFrame parent, LibraryController libraryController) {
        super(parent, "Return Book", true);
        this.libraryController = libraryController;

       // bundle = ResourceBundle.getBundle("ReturnBookDialog", new Locale("en"));

        setSize(400, 200);
        setLocationRelativeTo(parent);

        createGUI();
    }

    /**
     * Creates the graphical user interface for the ReturnBookDialog. Configures
     * layout, labels, combo boxes, text fields, buttons, and action listeners.
     */
    private void createGUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2));

        // Get the catalog of books from the library controller
        Map<String, Book> catalog = libraryController.viewCatalog();

        // Convert the book serial numbers to an array for the combo box
        String[] bookSnArray = catalog.keySet().toArray(new String[0]);

        // Initialize combo box for selecting books
        bookComboBox = new JComboBox<>(bookSnArray);
        studentIdField = new JTextField();

        mainPanel.add(new JLabel("Select Book:"));
        mainPanel.add(bookComboBox);
        mainPanel.add(new JLabel("Enter Student ID:"));
        mainPanel.add(studentIdField);

        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                returnBook();
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(returnButton);
        buttonPanel.add(cancelButton);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Attempts to return the selected book based on user input. Displays
     * success or error messages using JOptionPane dialog.
     */
    private void returnBook() {
        // Get the selected book serial number from the combo box
        String selectedBookSn = (String) bookComboBox.getSelectedItem();
        if (selectedBookSn == null) {
            JOptionPane.showMessageDialog(this, "No book selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get the Book object corresponding to the selected serial number
        Book selectedBook = libraryController.viewCatalog().get(selectedBookSn);

        // Check if the selected book has copies issued
        if (selectedBook.getIssued() > 0) {
            try {
                // Parse the student ID from the text field
                int studentId = Integer.parseInt(studentIdField.getText());
                Student student = new Student();
                student.setStudentId(studentId);

                // Attempt to return the book using the library controller
                if (libraryController.returnBook(selectedBook, student)) {
                    JOptionPane.showMessageDialog(this, "Book returned successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to return the book. No copies issued.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid input for student ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "The selected book has no copies issued.", "Error", JOptionPane.ERROR_MESSAGE);
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
            java.util.logging.Logger.getLogger(ReturnBookDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReturnBookDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReturnBookDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReturnBookDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                DatabaseHandler.createTables();
                LibraryController controller = new LibraryController(new DatabaseHandler());
                ReturnBookDialog returnBookDialog = new ReturnBookDialog(null, controller);
                returnBookDialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
