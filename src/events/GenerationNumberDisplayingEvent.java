/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

/**
 *
 * @author group9
 * 
 * @param visible The visibility of the generation number
 */
public class GenerationNumberDisplayingEvent extends MyEvent {
    private boolean visible;
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 1-arg GenerationNumberDisplayingEvent constructor
     * @param visible The visibility of the generation number
     */
    public GenerationNumberDisplayingEvent(boolean visible) {
        this.visible = visible;
    }
    
    /****************************
    * GETTER
    ****************************/
    /**
     * @return The visibility of the generation number
     */
    public boolean isVisible() {
        return visible;
    }
}
