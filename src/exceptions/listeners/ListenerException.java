/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions.listeners;

/**
 *
 * @author group9
 */
public class ListenerException extends RuntimeException {
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 1-arg ListenerException constructor
     * @param string Text of exception
     */
    public ListenerException(String string) {
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
