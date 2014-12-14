/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.gui.menubar.menus.checkboxitems.view;

import events.ComponentDisplayingEvent;
import exceptions.listeners.ComponentDisplayingListenerException;
import listeners.ComponentDisplayingListener;

/**
 *
 * @author group9
 */
public class ShowGenerationCheckBoxMenuItem extends ViewCheckBoxMenuItem {
        
    /****************************
    * PRIVATE FUNCTION
    ****************************/
    /**
     * Initialize menu item
     */
    private void initMenuItem() {
        this.menuItem.addItemListener(this);
    }
    
    /****************************
    * PROTECTED FUNCTION
    ****************************/
    /**
     * Fire a displaying component event
     * @param visible The visibilty of the component
     */
    protected void fireComponentDisplayingEvent(boolean visible) {
        ComponentDisplayingEvent e = new ComponentDisplayingEvent(ComponentDisplayingEvent.Component.GENERATION_NUMBER, visible);
        for(ComponentDisplayingListener l : this.listeners)
            l.componentDisplayingOccured(e);
        
        if(!e.isTreated())
            throw new ComponentDisplayingListenerException("Can't change the visibility of the generation number, item has no effects");
    }
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 2-args ShowGenerationCheckBoxMenuItem constructor
     * @param itemName Name of item
     * @param selected Default selected value of checkbox 
     */
    public ShowGenerationCheckBoxMenuItem(String itemName, boolean selected) {
        super(itemName, selected);
        this.initMenuItem();
    }
}
