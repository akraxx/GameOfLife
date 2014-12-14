/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import common.CellState;

/**
 * 
 * @author group9
 * 
 * @param ALIVE_STRING Default alive string 
 * @param DEAD_STRING Default dead string
 * @param value String value of the enum
 */
public enum GameOfLifeCellState implements CellState {
    ALIVE(GameOfLifeCellState.ALIVE_STRING),
    DEAD(GameOfLifeCellState.DEAD_STRING);
    
    public static final String ALIVE_STRING = "Alive";
    public static final String DEAD_STRING = "Dead";
    
    private final String value;
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * Set the value of the enum
     * @param value Value of the enum
     */
    private GameOfLifeCellState(String value) {
            this.value = value;
    }
    
    /****************************
    * PUBLIC FUNCTION
    ****************************/
    /**
     * Return the string value
     * @return The value of the Enum
     */
    @Override
    public String toString() {
            return this.value;
    }
}
