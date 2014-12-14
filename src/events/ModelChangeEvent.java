/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

/**
 *
 * @author group9
 * 
 * @param changed The state of the model change
 */
public class ModelChangeEvent extends MyEvent {
    boolean changed = false;;

    /****************************
    * GETTER
    ****************************/
    /**
     * @return The state of the model change 
     */
    public boolean isChanged() {
        return changed;
    }
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * Set the state of the model change 
     * @param changed The state of the model change 
     */
    public void setChanged(boolean changed) {
        this.changed = changed;
    }
}
