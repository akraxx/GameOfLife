/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

/**
 *
 * @author group9
 * 
 * @param title Title changed
 */
public class TitleChangeEvent extends MyEvent {
    private String title;
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 1-arg TitleChangeEvent constructor
     * @param title Title 
     */
    public TitleChangeEvent(String title) {
        this.title = title;
    }

    /**
     * @return Title changed
     */
    public String getTitle() {
        return this.title;
    }
}
