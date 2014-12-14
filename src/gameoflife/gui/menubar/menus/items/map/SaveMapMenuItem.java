/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.gui.menubar.menus.items.map;

import events.ActionMapEvent;
import exceptions.listeners.ActionMapListenerException;
import listeners.ActionMapListener;

/**
 *
 * @author group9
 */
public class SaveMapMenuItem extends MapMenuItem {
        
    /****************************
    * PRIVATE FUNCTION
    ****************************/
    /**
     * Initialize menu item
     */
    private void initMenuItem() {
        this.menuItem.addActionListener(this);
    }
    
    /****************************
    * PROTECTED FUNCTION
    ****************************/
    /**
     * Fire a map action event
     */
    protected void fireActionMapEvent() {
        ActionMapEvent e = new ActionMapEvent(ActionMapEvent.Actions.SAVE);
        for(ActionMapListener l : this.listeners)
            l.ActionMapOccured(e);
        
        if(!e.isTreated())
            throw new ActionMapListenerException("Can't open a new game, item has no effects");
    }

    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 2-args MenuItem constructor
     * @param itemName Name of item
     * @param key Mnemonic of item
     */
    public SaveMapMenuItem(String itemName, Character key) {
        super(itemName, key);
        this.initMenuItem();
    }

}
