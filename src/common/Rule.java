/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.ArrayList;

/**
 *
 * @author group9
 * 
 * @param states Array with the state of each cell
 */
public abstract class Rule {
    protected ArrayList<CellState> states = new ArrayList<CellState>();
    
    /****************************
    * GETTER
    ****************************/
    public ArrayList<CellState> getStates() {
        return this.states;
    }
    
    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * Return the state of a cell
     * @param string Cell state string
     * @return the state of a cell
     */
    public CellState getState(String string) {
        int i = 0;
        int length = states.size();
        CellState c = null;
        while(c == null && i < length) {
            if(states.get(i).toString().equals(string)) {
                c = states.get(i);
            }
            else {
                i++;
            }
        }
        
        if(c != null) {
            return c;
        }
        else {
            throw new IllegalArgumentException("State not found");
        }
    }
    
    /**
     * Rules for next generation
     * @param m World model
     * @param x x-coordinate of a cell
     * @param y y-coordinate of a cell
     * @return number of neighbors in a specific rule
     */
    public abstract int rulesToNextGen(WorldModel m, int x, int y);
}
