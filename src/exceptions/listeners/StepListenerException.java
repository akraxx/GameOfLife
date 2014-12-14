/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions.listeners;

/**
 *
 * @author group9
 */
public class StepListenerException extends ListenerException {

    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 1-arg StepListenerException constructor
     * @param string Text of exception
     */
    public StepListenerException(String string) {
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
