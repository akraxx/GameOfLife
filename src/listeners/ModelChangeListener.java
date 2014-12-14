/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import events.ModelChangeEvent;

/**
 *
 * @author group9
 */
public interface ModelChangeListener {
    
    /****************************
    * PUBLIC FUNCTION
    ****************************/
    public void modelChangeOccured(ModelChangeEvent e);
}
