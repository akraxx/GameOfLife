/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.gui.menubar.menus.items.map;

import gameoflife.gui.menubar.menus.items.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import listeners.ActionMapListener;

/**
 *
 * @author group9
 */
public abstract class MapMenuItem extends MenuItem implements ActionListener {
    protected ArrayList<ActionMapListener> listeners = new ArrayList<ActionMapListener>();
    
    /****************************
    * PROTECTED FUNCTION
    ****************************/
    /**
     * Fire a map action event
     */
    protected abstract void fireActionMapEvent();
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 2-args MapMenuItem constructor
     * @param itemName Name of item
     * @param key Mnemonic of item
     */
    public MapMenuItem(String itemName, Character key) {
        super(itemName, key);
    }
    
    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * Add an action listener to listeners array
     * @param l ActionAnimationListener
     */
    public void addActionMapListener(ActionMapListener l) {
        if(l != null)
            this.listeners.add(l);
        else
            throw new IllegalArgumentException("ActionMapListener can not be null");
    }
    
    /**
     * Action performed
     * @param e ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        this.fireActionMapEvent();
    }
}
