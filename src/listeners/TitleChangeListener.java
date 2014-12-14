/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import events.TitleChangeEvent;

/**
 *
 * @author group9
 */
public interface TitleChangeListener {
    
    /****************************
    * PUBLIC FUNCTION
    ****************************/
    public void titleChangeOccured(TitleChangeEvent e);
}
