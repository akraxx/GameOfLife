/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions.model;

import exceptions.MyException;

/**
 *
 * @author group9
 */
public class ModelException extends MyException {
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 1-arg ModelException constructor
     * @param string Text of exception
     * @param e Exception
     */
    public ModelException(String string, Exception e) {
        super(string, e);
    }
    
    /**
     * The 1-arg ModelException constructor
     * @param string Text of exception
     */
    public ModelException(String string) {
        super(string);
    }
    
    /**
     * The 0-arg ModelException constructor
     */
    public ModelException() {
        
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
        r += "## A GameOfLifeModel exception has been throwed. \n";
        
        r += super.getMessage();
        return r;
    }
}
