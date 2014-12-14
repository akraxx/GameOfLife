/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions.listeners;

/**
 *
 * @author group9
 */
public class ComponentDisplayingListenerException extends ListenerException {
   
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 1-arg GameSpeedChangeListenerException constructor
     * @param string Text of exception
     */
    public ComponentDisplayingListenerException(String string) {
        super(string);
    }
    
    /****************************
    * GETTER
    ****************************/
    /**     
     * @return message exception
     */
    @Override
    public String getMessage() {
        return super.getMessage();
    }
    
}
