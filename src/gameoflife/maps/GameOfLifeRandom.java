/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.maps;

import common.WorldModel;
import common.WorldView;

/**
 *
 * @author group9
 * 
 * @param height Height of random map
 * @param width Width of random map
 */
public class GameOfLifeRandom extends GameOfLifeMapCreator {
    private int height;
    private int width;
    

    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * 4-args GameOfLifeGlider constructor
     * @param name name of map
     * @param view view associated with map
     * @param height Height of random map
     * @param width Width of random map
     */
    public GameOfLifeRandom(String name, WorldView view, int height, int width) {
        super(name, view);
        this.height = height;
        this.width = width;
        
        p.randomPattern(this.width, this.height);
    }
    
    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * Create a map
     * @return pattern of map created
     */
    public WorldModel createMap() {        
        return this.patternToMap();
    }
    
}
