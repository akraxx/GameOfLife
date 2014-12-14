/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import events.CellSizeChangeEvent;

/**
 *
 * @author group9
 */
public interface CellSizeChangeListener {
    
    /****************************
    * PUBLIC FUNCTION
    ****************************/
    public void cellSizeChangeOccured(CellSizeChangeEvent e);
}
