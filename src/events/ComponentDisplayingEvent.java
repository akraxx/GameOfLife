/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

/**
 *
 * @author group9
 * 
 * @param visible The visibility of the component
 */
public class ComponentDisplayingEvent extends MyEvent {
    /**
     * Contains the possible values of displaying component
     */
    public static enum Component {
        TOOLBAR,
        GENERATION_NUMBER,
        GRID
    };
    
    private boolean visible;
    private Component component;
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 2-args ComponentDisplayingEvent constructor
     * @param component Displaying component
     * @param visible Defines if the component is visible or not
     */
    public ComponentDisplayingEvent(Component component, boolean visible) {
        this.component = component;
        this.visible = visible;
    }
    
    /****************************
    * GETTERS
    ****************************/
    /**
     * @return the visibility of the component
     */
    public boolean isVisible() {
        return visible;
    }
    
    /**
     * @return the component
     */
    public Component getComponent() {
        return component;
    }
}
