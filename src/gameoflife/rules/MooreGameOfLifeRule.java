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
public class MooreGameOfLifeRule extends GameOfLifeRule {

    
    /****************************
    * PUBLIC FUNCTION
    ****************************/
    /**
     * Moore rules applied to next generation generation
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
        
        for(int yc = y-1; yc <= y + 1; yc++) {
            for(int xc = x - 1; xc <= x + 1; xc++) {
                if ((xc != x || yc != y) && xc >= 0 && yc >= 0 && xc < width && yc < height) {
                    if(m.getState(yc, xc) == this.getState(GameOfLifeCellState.ALIVE_STRING)) {
                        nb++;
                    }
                }
            }
        }
        
        return nb;
    }
    
}
