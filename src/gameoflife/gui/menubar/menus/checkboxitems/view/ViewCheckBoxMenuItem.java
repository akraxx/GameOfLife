/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.gui.menubar.menus.checkboxitems.view;

import gameoflife.gui.menubar.menus.checkboxitems.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import listeners.ComponentDisplayingListener;

/**
 *
 * @author group9
 * 
 * @param listeners ArrayList contains the displaying components
 */
public abstract class ViewCheckBoxMenuItem extends CheckBoxMenuItem implements ItemListener {
    protected ArrayList<ComponentDisplayingListener> listeners = new ArrayList<ComponentDisplayingListener>();
    
    /****************************
    * PROTECTED FUNCTION
    ****************************/
    /**
     * Fire a displaying component event
     * @param visible The visibilty of the component
     */
    protected abstract void fireComponentDisplayingEvent(boolean visible);
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 2-args ViewCheckBoxMenuItem constructor
     * @param itemName Name of item
     * @param selected Default selected value of checkbox 
     */
    public ViewCheckBoxMenuItem(String itemName, boolean selected) {
        super(itemName, selected);
    }
    
    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * Add a component listener
     * @param l ActionAnimationListener
     */
    public void addComponentDisplayingListener(ComponentDisplayingListener l) {
        if(l != null)
            this.listeners.add(l);
        else
            throw new IllegalArgumentException("ComponentDisplayingListener can not be null");
    }

    /**
     * Change state of item
     * @param e ItemEvent
     */
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED)
            this.fireComponentDisplayingEvent(true);
        else
            this.fireComponentDisplayingEvent(false);
    }
}
