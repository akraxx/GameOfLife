/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gameoflife.models;

import common.CellState;
import events.CellStateChangeEvent;
import gameoflife.GameOfLifeCellState;
import gameoflife.GameOfLifePattern;
import common.WorldModel;
import common.WorldView;
import events.ModelChangeEvent;
import gameoflife.exceptions.model.GameOfLifeModelException;
import java.io.Serializable;
import java.util.ArrayList;
import listeners.ModelChangeListener;





/**
 * 
 * @author group9
 * 
 * @param numberOfGenerations The geration number
 * @param changed Status of array
 * @param maxGenerations The maximum number of generation
 * @param map Game of life map
 * @param height Height of array
 * @param width Width of array
 * @param wiews List of views
 */
public class ArrayModel implements WorldModel, Serializable {
    private int numberOfGenerations = 0;
    private boolean changed = false;
    private int maxGenerations = Integer.MAX_VALUE;
    private Cell map[][];
    private int height, width;
    private ArrayList<WorldView> views = new ArrayList<WorldView>();
    private ArrayList<ModelChangeListener> listeners = new ArrayList<ModelChangeListener>();
            
    /****************************
    * PRIVATE FUNCTIONS
    ****************************/
     /**
     * Create and init a grid of the model
     */
    private void createGrid() {
        this.map = new Cell[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
           for (int j = 0; j < this.width; j++)
               map[i][j] = new Cell(GameOfLifeCellState.DEAD);
        }
    }
    
    /**
     * Fire an event to animation controller
     */
    private void fireModelChangeEvent() {
        for(ModelChangeListener l : this.listeners)
            l.modelChangeOccured(new ModelChangeEvent());
    }
    
    /****************************
    * CONSTRUCTORS
    ****************************/
    /**
     * The 3-args ArrayModel constructor
     * @param height Height of array
     * @param width Width of array
     * @param maxGenerations The maximum number of generation
     * @throws GameOfLifeModelException 
     */
    public ArrayModel(int height, int width, int maxGenerations) throws GameOfLifeModelException {
        try {
            this.setHeight(height);
            this.setWidth(width);
            this.createGrid();
            this.maxGenerations = maxGenerations;
        }
        catch(Exception e) {
            throw new GameOfLifeModelException("Can't initiate the game", e);
        }
    }
    
    /**
     * The 2-args ArrayModel constructor
     * @param height Height of array
     * @param width Width of array
     * @throws GameOfLifeModelException
     */
    public ArrayModel(int height, int width) throws GameOfLifeModelException {
        this(height, width, Integer.MAX_VALUE);
    }
    
    /**
     * The 2-args ArrayModel constructor
     * @param pattern Game of life pattern
     * @param maxGenerations The maximum number of generation
     * @throws GameOfLifeModelException
     */
    public ArrayModel(GameOfLifePattern pattern, int maxGenerations) throws GameOfLifeModelException {
        try {
            this.setHeight(pattern.getHeight());
            this.setWidth(pattern.getWidth());
            this.map = pattern.getPattern().clone();
            this.maxGenerations = maxGenerations;
        }
        catch(Exception e) {
            throw new GameOfLifeModelException("Can't initiate the game", e);
        }
    }
    
    /**
     * The 1-arg ArrayModel constructor
     * @param pattern Game of life pattern
     * @throws GameOfLifeModelException
     */
    public ArrayModel(GameOfLifePattern pattern) throws GameOfLifeModelException {
            this(pattern, Integer.MAX_VALUE);
    }
    
    /****************************
    * GETTER
    ****************************/
    /**
     * @return the map
     */
    public Cell[][] getMap() {
        return map;
    }
    
    /**
     * @return the state of cell
     */
    public GameOfLifeCellState getState(int i, int j) {
        try {
            return this.map[i][j].getCurrentState();
        }
        catch(Exception e) {
            throw new GameOfLifeModelException("Model can't get a state, coords ["+i+","+j+"]", e);
        }
    }
   
    /**
     * @return the height of the map
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * @return the width of the map
     */
    public int getWidth() {
        return this.width;
    }
        
    /**
     * @return True if the game is finished
     */
    public boolean isFinished() {
        if(this.numberOfGenerations >= this.maxGenerations) 
            return true;
        else
            return false;
    }
    
    /**
     * 
     * @return True is the game has changed
     */
    public boolean hasChanged() {
        return changed;
    }
    /**
     * @return generation number
     */
    public int getNumberOfGenerations() {
        return this.numberOfGenerations;
    }
    
    /****************************
    * SETTER
    ****************************/
    /**
     * Set the state of cell
     * @param state the state to set
     */
    public void setState(int i, int j, CellState state) {
        try {
            if(state instanceof GameOfLifeCellState)
                this.map[i][j].setNextState((GameOfLifeCellState)state);
            else
                throw new GameOfLifeModelException("CellState is not a GameOfLifeCellState");
            this.changed = true;
        }
        catch(Exception e) {
            throw new GameOfLifeModelException("Model can't set a state, coords ["+i+","+j+"]", e);
        }
    }
    
    /**
     * Set the status of the array model
     * @param changed The status of the array model
     */
    public void setChanged(boolean changed) {
        this.changed = changed;
    }
    
    /**
     * Set the height array model
     * @param height the height to set
     */
    public void setHeight(int height) {
        if (height > 0)
            this.height = height;
        else
            throw new IllegalArgumentException("Height of the grid can't be negative or null");
    }

    /**
     * Set the width array model
     * @param width the width to set
     */
    public void setWidth(int width) {
        if (width > 0)
            this.width = width;
        else
            throw new IllegalArgumentException("Width of the grid can't be negative or null");
    }
    

    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * refresh views
     */
    public void update() {
        for(int i = 0; i < this.views.size(); i++)
            this.views.get(i).refresh(this);
    }
    
        /**
     * Add view to world
     * @param v Add a view to the list
     */
    @Override
    public void addView(WorldView v) {
        if(v != null)
            this.views.add(v);
        
        this.update();
    }

    /**
     * Swap the next generation state to the current
     */
    @Override
    public void propageGeneration() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++)
                this.map[i][j].setCurrentState(this.map[i][j].getNextState());
        }
        this.numberOfGenerations++;
        this.update();
    }
    
    /**
     * Add a listener
     * @param l ModelChangeListener
     */
    public void addMapChangeListener(ModelChangeListener l) {
        if(l != null) {
            this.listeners.add(l);
        }
        else
            throw new IllegalArgumentException("ModelChangeListener can not be null");
    }

    public void cellStateChangeOccured(CellStateChangeEvent e) {
        this.map[e.getI()][e.getJ()].setCurrentState((GameOfLifeCellState)e.getState());
        e.setTreated(true);
    }

}
