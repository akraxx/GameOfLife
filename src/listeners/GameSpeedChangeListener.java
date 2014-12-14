/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import events.GameSpeedChangeEvent;

/**
 *
 * @author group9
 */
public interface GameSpeedChangeListener {
    
    /****************************
    * PUBLIC FUNCTION
    ****************************/
    public void gameSpeedChangeOccured(GameSpeedChangeEvent e);
}
