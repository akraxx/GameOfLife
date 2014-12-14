/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.maps;

import common.MapCreator;
import common.WorldModel;
import common.WorldView;
import gameoflife.GameOfLifePattern;
import gameoflife.models.ArrayModel;

/**
 * 
 * @author group9
 * 
 * @param name Name of man
 * @param view View of world
 * @param p Game of Life pattern
 */
public abstract class GameOfLifeMapCreator implements MapCreator {
    private String name;
    private WorldView view;
    protected GameOfLifePattern p = new GameOfLifePattern();
    
        
    /****************************
    * PRIVATE FUNCTIONS
    ****************************/
    /**
     * Converts a pattern to map
     * @return model of world
     */
    protected WorldModel patternToMap() {
        p.convertToPattern();
        
        ArrayModel model = new ArrayModel(p);
        model.addView(this.view);
        
        return model;
    }
        
    /****************************
    * CONSTRUCTORS
    ****************************/
    /**
     * 0-arg GameOfLifeMapCreator constructor
     */
    private GameOfLifeMapCreator() {
        
    }
    
    /**
     * 2-args GameOfLifeMapCreator constructor
     * @param name Name of new map
     * @param view View of the new map
     */
    public GameOfLifeMapCreator(String name, WorldView view) {
        this.name = name;
        this.view = view;
    }
    
    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * Create a new map
     * @return Model of world
     */
    public abstract WorldModel createMap();
    
    /**
     * @return the name of map
     */
    public String getName() {
        return this.name;
    }
}
