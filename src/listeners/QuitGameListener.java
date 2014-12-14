/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import events.QuitGameEvent;

/**
 *
 * @author group9
 */
public interface QuitGameListener {
    
    /****************************
    * PUBLIC FUNCTION
    ****************************/
    public void quitGameOccured(QuitGameEvent e);
}
