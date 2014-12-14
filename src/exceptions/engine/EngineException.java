/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions.engine;

import exceptions.MyException;

/**
 *
 * @author group9
 */
public class EngineException extends MyException {
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 2-args EngineException constructor
     * @param string Text of exception
     * @param e Exception
     */
    public EngineException(String string, Exception e) {
        super(string, e);
    }
    
    /**
     * The 1-arg EngineException constructor
     * @param string Text of exception
     */
    public EngineException(String string) {
        super(string);
    }
    
    /**
     * The 0-arg EngineException constructor
     */
    public EngineException() {
        
    }
    
    /****************************
    * GETTER
    ****************************/
    /**     
     * @return message exception
     */
    @Override
    public String getMessage() {
        String r = "";
        
        r += "__________________________________________\n";
        r += "## An Engine exception has been throwed. \n";
        
        r += super.getMessage();
        return r;
    }
}
