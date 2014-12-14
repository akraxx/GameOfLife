/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import events.CellStateColorSwitchEvent;

/**
 *
 * @author group9
 */
public interface CellStateColorSwitchListener {
    
    /****************************
    * PUBLIC FUNCTION
    ****************************/
    public void cellStateColorSwitchOccured(CellStateColorSwitchEvent e);
}
