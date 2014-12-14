/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.rules;

import common.WorldModel;
import gameoflife.GameOfLifeCellState;
import gameoflife.GameOfLifeRule;

/**
 *
 * @author group9
 */
public class ManhattanGameOfLifeRule extends GameOfLifeRule {

    
    /****************************
    * PUBLIC FUNCTION
    ****************************/
    /**
     * Manhattan rules applied to next generation generation
     * @param m World Model
     * @param x x-coordinate of cell
     * @param y y-coordinate of cell
     * @return number of neighboors
     */
    @Override
    public int rulesToNextGen(WorldModel m, int x, int y) {
        int nb = 0;
        int height = m.getHeight();
        int width = m.getWidth();
        
        if(x - 1 >= 0 && m.getState(x-1, y) == this.getState(GameOfLifeCellState.ALIVE_STRING)) {
            nb++;
        }
        if(x + 1 < width && m.getState(x+1, y) == this.getState(GameOfLifeCellState.ALIVE_STRING)) {
            nb++;
        }
        
        if(y - 1 >= 0 && m.getState(x, y-1) == this.getState(GameOfLifeCellState.ALIVE_STRING)) {
            nb++;
        }
        if(y + 1 < height && m.getState(x, y+1) == this.getState(GameOfLifeCellState.ALIVE_STRING)) {
            nb++;
        }

        return nb;
    }
    
}
