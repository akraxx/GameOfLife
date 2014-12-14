/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gameoflife.models;

import gameoflife.GameOfLifeCellState;
import java.io.Serializable;

/**
 *
 * @author group9
 * @param currentState Current state of the cell
 * @param nextState Next state of the cell
 */
public class Cell implements Serializable {
    private GameOfLifeCellState currentState;
    private GameOfLifeCellState nextState;
  
    /****************************
    * CONSTRUCTORS
    ****************************/
    /**
     * The 2-args Cell constructor
     * @param currentState Current state of the cell
     * @param nextState Next state of the cell
     */
    public Cell(GameOfLifeCellState currentState, GameOfLifeCellState nextState) {
        this.setCurrentState(currentState);
        this.setNextState(nextState);
    }

    /**
     * The 1-arg Cell constructor
     * @param currentState Current state of the cell
     */
    public Cell(GameOfLifeCellState currentState) {
        this(currentState, GameOfLifeCellState.DEAD);
    }
    
    /**
     * The 0-arg Cell constructor
     */
    public Cell() {
        this(GameOfLifeCellState.DEAD);
    }
    
    /****************************
    * GETTER
    ****************************/
    /**
     * @return the currentState
     */
    public GameOfLifeCellState getCurrentState() {
        return currentState;
    }

    /**
     * @return the nextState
     */
    public GameOfLifeCellState getNextState() {
        return nextState;
    }

    /****************************
    * SETTER
    ****************************/

    /**
     * Set the current state
     * @param currentState the currentState to set
     */
    public void setCurrentState(GameOfLifeCellState currentState) {
        this.currentState = currentState;
    }

    /**
     * Set the next state
     * @param nextState the nextState to set
     */
    public void setNextState(GameOfLifeCellState nextState) {
        this.nextState = nextState;
    }
    
    
    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * Clone the cell
     * @return a clone of the cell
     */
    @Override
    public Cell clone() {
        return new Cell(this.getCurrentState(),this.getNextState());
    }
    
    /**
     * A cell to string
     * @return a string of the cell
     */
    @Override
    public String toString() {
        return "Cell : " + super.toString();
    }
}
