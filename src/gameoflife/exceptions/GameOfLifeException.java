/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.exceptions;

import exceptions.MyException;

/**
 *
 * @author group9
 */
public class GameOfLifeException extends MyException {
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 2-args GameOfLifeException constructor
     * @param string Text of exception
     * @param e Exception
     */
    public GameOfLifeException(String string, Exception e) {
        super(string, e);
    }
    
    /**
     * The 1-arg GameOfLifeException constructor
     * @param string Text of exception
     */
    public GameOfLifeException(String string) {
        super(string);
    }
    
    /**
     * The 0-arg GameOfLifeException constructor
     */
    public GameOfLifeException() {
        
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
        r += "## A GameOfLife exception has been throwed. \n";
        
        r += super.getMessage();
        return r;
    }
}
