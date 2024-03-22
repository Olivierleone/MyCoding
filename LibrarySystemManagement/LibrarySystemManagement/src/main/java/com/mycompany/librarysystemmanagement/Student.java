/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.librarysystemmanagement;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a student in the library system. Implements the Observable
 * interface to allow observing changes in student details. Contains methods to
 * get and set student information, and to manage observers.
 *
 * @author Olivier
 */
public class Student implements Observable {

    private int studentId;
    private String name;
    private String contact;
    private List<Observer> status = new ArrayList<Observer>();

    /**
     * Default constructor for the Student class.
     */
    public Student() {
    }

    /**
     * Gets the student ID.
     *
     * @return The student ID.
     */
    public int getStudentId() {
        return studentId;
    }

    /**
     * Sets the student ID.
     *
     * @param studentId The new student ID.
     */
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    /**
     * Gets the student's name.
     *
     * @return The student's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the student's name and notifies observers of the change.
     *
     * @param name The new name.
     */
    public void setName(String name) {
        this.name = name;
        notifyObservers();
    }

    /**
     * Gets the student's contact information.
     *
     * @return The student's contact information.
     */
    public String getContact() {
        return contact;
    }

    /**
     * Sets the student's contact information and notifies observers of the
     * change.
     *
     * @param contact The new contact information.
     */
    public void setContact(String contact) {
        this.contact = contact;
        notifyObservers();
    }

    /**
     * Overrides the toString method to provide a string representation of the
     * student.
     *
     * @return A string representation of the student.
     */
    @Override
    public String toString() {
        return "Student:"
                + "studentId=" + studentId
                + ", name='" + name + '\''
                + ", contact='" + contact + '\'';
    }

    /**
     * Adds an observer to the list of status observers.
     *
     * @param o The observer to be added.
     */
    @Override
    public void addObserver(java.util.Observer o) {
        status.add((Observer) o);
    }

    /**
     * Removes an observer from the list of status observers.
     *
     * @param o The observer to be removed.
     */
    @Override
    public void removeObserver(java.util.Observer o) {
        status.remove(o);
    }

    /**
     * Notifies all registered observers of a change in the student's details.
     */
    @Override
    public void notifyObservers() {
        for (Observer statuss : status) {
            statuss.update();

        }
    }
}
