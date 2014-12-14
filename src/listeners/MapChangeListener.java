/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import events.MapChangeEvent;

/**
 *
 * @author group9
 */
public interface MapChangeListener {
    
    /****************************
    * PUBLIC FUNCTION
    ****************************/
    public void mapChangeOccured(MapChangeEvent e);
}
