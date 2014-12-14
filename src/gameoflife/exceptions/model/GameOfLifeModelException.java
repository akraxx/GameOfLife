/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.exceptions.model;

import exceptions.model.ModelException;

/**
 *
 * @author group9
 */
public class GameOfLifeModelException extends ModelException {
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 2-args GameOfLifeModelException constructor
     * @param string Text of exception
     * @param e Exception
     */
    public GameOfLifeModelException(String string, Exception e) {
        super(string, e);
    }
    
    /**
     * The 1-arg GameOfLifeModelException constructor
     * @param string Text of exception
     */
    public GameOfLifeModelException(String string) {
        super(string);
    }
    
    /**
     * The 0-arg GameOfLifeModelException constructor
     */
    public GameOfLifeModelException() {
        
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
