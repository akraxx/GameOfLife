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
 */
public class GameOfLifeBlock extends GameOfLifeMapCreator {
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * 2-args GameOfLifeGlider constructor
     * @param name name of map
     * @param view view associated with map
     */
    public GameOfLifeBlock(String name, WorldView view) {
        super(name, view);
        
        p.addRow("- - - - - -");
        p.addRow("- - * * - -");
        p.addRow("- - * * - -");
        p.addRow("- - - - - -");
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
