/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

/**
 *
 * @author group9
 */
public interface MapCreator {
    
    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * @return name of the new map
     */
    public String getName();
    
    /**
     * @return create a new map
     */
    public WorldModel createMap();
}
