/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

/**
 *
 * @author group9
 */
public class ActionAnimationEvent extends MyEvent {
    /**
     * Contains the possible values of animation action
     */
    public static enum Actions {
        START,
        STOP,
        STEP,
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
    public ActionAnimationEvent(Actions action) {
        this.action = action;
    }

}
