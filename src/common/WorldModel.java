/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package common;

import java.io.Serializable;
import listeners.CellStateChangeListener;

/**
 *
 * @author group9
 */
public interface WorldModel extends Serializable, CellStateChangeListener {
    /****************************
    * GETTER
    ****************************/
    /**
     * Return the state of a cell located with its coordinates
     * @param i The cell row-coordinate
     * @param j The cell height-coordinate
     * @return The state of a cell
     */
    public CellState getState(int i, int j);
    
    /**
     * @return the height of the model
     */
    public int getHeight();
    
    /**
     * @return the width of the model
     */
    public int getWidth();
    
    /**
     * Return the generation number
     * @return the generation number
     */
    public int getNumberOfGenerations();
     
    /**
     * Return the actually state of game
     * @return True if the game is finished
     */
    public boolean isFinished();
    
    /**
     * @return True if the game has changed
     */
    public boolean hasChanged();
    
    /****************************
    * SETTER
    ****************************/
    /**
     * Set the state of a cell located with its coordinates
     * @param i The cell row-coordinate
     * @param j The cell height-coordinate
     * @param state The state will be assigned to the cell
     */
    public void setState(int i, int j, CellState state);
    
    /**
     * @param changed Model changed or not
     */
    public void setChanged(boolean changed);
    
    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * Add a world view to the model
     * @param v View to be added
     */
    public void addView(WorldView v);
    
    /**
     * Propage to the next generation
     */
    public void propageGeneration();
       
}
