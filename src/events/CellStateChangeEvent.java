/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import common.CellState;

/**
 *
 * @author group9
 * 
 * @param i x-coordinate of cell
 * @param j y-coordinate of cell
 * @param state Cell state
 */
public class CellStateChangeEvent extends MyEvent {
    int i;
    int j;
    CellState state;

    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 3-args CellStateChangeEvent constructor
     * @param i x-coordinate of cell
     * @param j y-coordinate of cell
     * @param state Cell state
     */
    public CellStateChangeEvent(int i, int j, CellState state) {
        this.i = i;
        this.j = j;
        this.state = state;
    }

    /****************************
    * GETTERS
    ****************************/
    /**    
     * @return x-coordinate of cell
     */
    public int getI() {
        return i;
    }

    /**
     * @return y-coordinate of cell
     */
    public int getJ() {
        return j;
    }

    /**
     * @return Cell state
     */
    public CellState getState() {
        return state;
    }
    
    
}
