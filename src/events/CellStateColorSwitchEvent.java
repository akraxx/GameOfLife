/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import gameoflife.gui.grid.CellGUI;

/**
 *
 * @author group9
 * 
 * @param cell Cell view
 */
public class CellStateColorSwitchEvent extends MyEvent {
    CellGUI cell;
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 1-arg CellStateColorSwitchEvent constructor
     * @param cell Cell view
     */
    public CellStateColorSwitchEvent(CellGUI cell) {
        this.cell = cell;
    }

    /****************************
    * GETTERS
    ****************************/
    /**
     * @return Cell view
     */
    public CellGUI getCellGUI() {
        return cell;
    }
}
