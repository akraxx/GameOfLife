/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.engines;

import common.CellState;
import common.Engine;
import common.Rule;
import common.WorldModel;
import gameoflife.GameOfLifeCellState;
import gameoflife.exceptions.engine.GameOfLifeEngineException;

/**
 *
 * @author group9
 */
public class GameOfLifeEngine extends Engine {
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 2-arg GameOfLifeEngine constructor
     * @param timeToWait Time to wait between 2 generations
     * @param rule Rule Associated rule
     */
    public GameOfLifeEngine(long timeToWait, Rule rule) {
        super(timeToWait, rule);
    }
    
    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * Upgrade the generation of the game
     * @param m World model to be modified
     * @throws GameOfLifeEngineException 
     */
    public void toNextGen(WorldModel m) throws GameOfLifeEngineException {
        if(m == null) throw new GameOfLifeEngineException("Engine can't set the next generation", new NullPointerException());
        
        int height = m.getHeight();
        int width = m.getWidth();
        
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                int numberOfAliveNeighbors = this.getRule().rulesToNextGen(m, x, y);
                CellState currentState = m.getState(y, x);
                if(currentState == null) System.out.println("NULL");
                if (currentState.equals(this.getRule().getState(GameOfLifeCellState.ALIVE_STRING))) {
                    //A cell with 2 or 3 alive neighbors survives for the next generation.
                    if(numberOfAliveNeighbors == 2 || numberOfAliveNeighbors == 3)
                        m.setState(y, x, this.getRule().getState(GameOfLifeCellState.ALIVE_STRING));

                    //A cell with 4 or more alive neighbors dies from overpopulation.
                    else if(numberOfAliveNeighbors >= 4 || numberOfAliveNeighbors <= 1)
                        m.setState(y, x, this.getRule().getState(GameOfLifeCellState.DEAD_STRING));
                    
                }
                //A dead cell with exactly 3 alive neighbors –no more, no fewer– comes to life.
                else if (currentState.equals(this.getRule().getState(GameOfLifeCellState.DEAD_STRING)) && numberOfAliveNeighbors == 3) {
                    m.setState(y, x, this.getRule().getState(GameOfLifeCellState.ALIVE_STRING));
                }
            }
        }
        
        m.propageGeneration();
    }
}
