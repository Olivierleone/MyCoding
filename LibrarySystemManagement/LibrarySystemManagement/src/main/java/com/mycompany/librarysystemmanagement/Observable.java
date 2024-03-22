/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.librarysystemmanagement;

import java.util.Observer;

/**
 *
 * @author Olivier
 */
public interface Observable {

    /**
     * Adds an observer to the list of observers.
     *
     * @param o The observer to be added.
     */
    public void addObserver(Observer o);

    /**
     * Removes an observer from the list of observers.
     *
     * @param o The observer to be removed.
     */
    public void removeObserver(Observer o);

    /**
     * Notifies all registered observers of a change.
     */
    public void notifyObservers();
}
