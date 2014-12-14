/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions.listeners;

/**
 *
 * @author group9
 */
public class StartListenerException extends ListenerException {

    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 1-arg StartListenerException constructor
     * @param string Text of exception
     */
    public StartListenerException(String string) {
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
