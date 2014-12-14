/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

/**
 *
 * @author group9
 */
public class ActionMapEvent extends MyEvent {
    /**
     * Contains the possible values of map action
     */
    public static enum Actions {
        NEW,
        OPEN,
        SAVE,
        QUIT
    };
    
    private Actions action;
    
    /****************************
    * GETTER
    ****************************/
    /**
     * @return action
     */
    public Actions getAction() {
        return action;
    }
    
    /****************************
    * SETTER
    ****************************/
    public ActionMapEvent(Actions action) {
        this.action = action;
    }
    
}
