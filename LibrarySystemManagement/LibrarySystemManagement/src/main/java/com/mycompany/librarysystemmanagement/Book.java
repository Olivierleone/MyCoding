/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarysystemmanagement;

import java.util.Date;

/**
 * Represents a book in the library. Each book has a unique serial number,
 * title, author, publisher, price, quantity, information about how many copies
 * are issued, the date it was added to the library, and information about the
 * student to whom it is issued.
 *
 * @author Olivier
 */
public class Book {

    // Serial Number
    private String sn;

    // Title of the book
    private String title;

    // Author of the book
    private String author;

    // Publisher of the book
    private String publisher;

    // Price of the book
    private double price;

    // Number of copies available in the library
    private int quantity;

    // Number of copies issued to students
    private int issued;

    // Date the book was added to the library
    private Date addedDate;

    // Student to whom the book is issued
    private Student issuedTo;

    /**
     * Gets the serial number of the book.
     *
     * @return The serial number of the book.
     */
    public String getSn() {
        return sn;
    }

    /**
     * Sets the serial number of the book.
     *
     * @param sn The serial number to set.
     */
    public void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * Gets the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the author of the book.
     *
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     *
     * @param author The author to set.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the publisher of the book.
     *
     * @return The publisher of the book.
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets the publisher of the book.
     *
     * @param publisher The publisher to set.
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Gets the price of the book.
     *
     * @return The price of the book.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the book.
     *
     * @param price The price to set.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the quantity of the book available in the library.
     *
     * @return The quantity of the book.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the book available in the library.
     *
     * @param quantity The quantity to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the number of copies of the book that are issued to students.
     *
     * @return The number of issued copies.
     */
    public int getIssued() {
        return issued;
    }

    /**
     * Sets the number of copies of the book that are issued to students.
     *
     * @param issued The number of issued copies to set.
     */
    public void setIssued(int issued) {
        this.issued = issued;
    }

    /**
     * Gets the date the book was added to the library.
     *
     * @return The date the book was added.
     */
    public Date getAddedDate() {
        return addedDate;
    }

    /**
     * Sets the date the book was added to the library.
     *
     * @param addedDate The date to set.
     */
    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    /**
     * Gets the student to whom the book is issued.
     *
     * @return The student to whom the book is issued.
     */
    public Student getIssuedTo() {
        return issuedTo;
    }

    /**
     * Sets the student to whom the book is issued.
     *
     * @param issuedTo The student to set.
     */
    public void setIssuedTo(Student issuedTo) {
        this.issuedTo = issuedTo;
    }

    /**
     * Returns a formatted string representation of the book.
     *
     * @return A formatted string containing information about the book.
     */
    @Override
    public String toString() {
        return String.format("""
                             Book: Sn: %s, title: %s, author: %s, publisher: %s, price: %s, quantity: %s, issued: %s, addedDate: %s
                             """,
                sn, title, author, publisher, price, quantity, issued, addedDate);
    }
}
