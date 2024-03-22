/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.librarysystemmanagement;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Dialog for removing a book from the library system. Extends JDialog for
 * creating a custom dialog window. Allows users to enter the Serial Number and
 * Title of the book to be removed. Communicates with the LibraryController to
 * perform the removal operation. Provides a graphical user interface with text
 * fields and buttons.
 *
 * @author Olivier
 */
public class RemoveBook extends JDialog {

    private LibraryController libraryController;
    //private ResourceBundle bundle;
    private JTextField snField;
    private JTextField titleField;

    /**
     * Creates a new instance of the RemoveBook dialog.
     *
     * @param parent The parent JFrame that owns this dialog.
     * @param libraryController The controller for the library system.
     */
    public RemoveBook(JFrame parent, LibraryController libraryController) {
        super(parent, "Remove Book", true);
        this.libraryController = libraryController;

        //   bundle = ResourceBundle.getBundle("RemoveBook", new Locale("en"));
        setSize(400, 200);
        setLocationRelativeTo(parent);

        createGUI();
    }

    /**
     * Creates the graphical user interface for the RemoveBook dialog.
     * Configures layout, labels, text fields, buttons, and action listeners.
     */
    private void createGUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 2));

        snField = new JTextField();
        titleField = new JTextField();

        mainPanel.add(new JLabel("Enter Serial Number:"));
        mainPanel.add(snField);
        mainPanel.add(new JLabel("Enter Title:"));
        mainPanel.add(titleField);

        // Add the "Remove" button and attach the action listener
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeBook();
            }
        });
        
          // Add the "Remove All" button and attach the action listener
        JButton removeAllButton = new JButton("Remove All");
        removeAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAllBooks();
            }
        });

        // Add the "Cancel" button and attach the action listener
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(removeButton);
        buttonPanel.add(removeAllButton); // Add the "Remove All" button
        buttonPanel.add(cancelButton);

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Attempts to remove the book from the library system based on user input.
     * Displays success or error messages using JOptionPane dialogs.
     */
    private void removeBook() {
        String sn = snField.getText();
        String title = titleField.getText();

        if (sn.isEmpty() || title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both Serial Number and Title.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Book bookToRemove = new Book();
        bookToRemove.setSn(sn);
        bookToRemove.setTitle(title);

        if (libraryController.removeBook(bookToRemove)) {
            JOptionPane.showMessageDialog(this, "Book removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to remove the book. Book not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Add the method to remove all books
    private void removeAllBooks() {
        // Remove all books from the library
        if (libraryController.removeAllBooks()) {
            JOptionPane.showMessageDialog(this, "All books removed successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Failed to remove all books.", "Error", JOptionPane.ERROR_MESSAGE);
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
            java.util.logging.Logger.getLogger(RemoveBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RemoveBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RemoveBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RemoveBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new RemoveBook().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
