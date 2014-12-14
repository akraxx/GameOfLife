/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import events.CellStateColorChangeEvent;

/**
 *
 * @author group9
 */
public interface CellStateColorChangeListener {
    
    /****************************
    * PUBLIC FUNCTION
    ****************************/
    public void cellStateColorChangeOccured(CellStateColorChangeEvent e);
}
