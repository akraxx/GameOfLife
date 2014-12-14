/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import common.Rule;

/**
 *
 * @author group9
 */
public abstract class GameOfLifeRule extends Rule {
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 0-arg GameOfLife constructor. Add the 2 cell states in the variable states.
     */
    public GameOfLifeRule() {
        this.states.add(GameOfLifeCellState.ALIVE);
        this.states.add(GameOfLifeCellState.DEAD);
    }
    
}
