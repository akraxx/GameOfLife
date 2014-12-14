/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

/**
 *
 * @author group9
 * 
 * @param treated The state of event treatment
 */
public class MyEvent {
    private boolean treated = false;

    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 0-arg MyEvent constructor
     */
    public MyEvent() {
    }

    /****************************
    * GETTER
    ****************************/
    /**
     * @return The state of event treatment
     */
    public boolean isTreated() {
        return treated;
    }
    
    /****************************
    * SETTER
    ****************************/
    /**
     * Set the state of event treatment
     * @param treated The state of event treatment
     */
    public void setTreated(boolean treated) {
        this.treated = treated;
    }
}
