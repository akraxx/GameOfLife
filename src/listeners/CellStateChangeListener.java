/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import events.CellStateChangeEvent;

/**
 *
 * @author group9
 */
public interface CellStateChangeListener {
    
    /****************************
    * PUBLIC FUNCTION
    ****************************/
    public void cellStateChangeOccured(CellStateChangeEvent e);
}
