/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import common.WorldModel;

/**
 *
 * @author group9
 * 
 * @param model World model
 */
public class MapChangeEvent extends MyEvent {
    WorldModel model;

    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 1-arg MapChangeEvent constructor
     * @param model World model
     */
    public MapChangeEvent(WorldModel model) {
        this.model = model;
    }

    /**
     * @return the world model
     */
    public WorldModel getModel() {
        return model;
    }
}
