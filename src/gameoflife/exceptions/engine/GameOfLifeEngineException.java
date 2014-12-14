/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.exceptions.engine;

import exceptions.engine.EngineException;

/**
 *
 * @author group9
 */
public class GameOfLifeEngineException extends EngineException {
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 2-args GameOfLifeEngineException constructor
     * @param string Text of exception
     * @param e Exception
     */
   public GameOfLifeEngineException(String string, Exception e) {
        super(string, e);
    }
   
   /**
     * The 1-arg GameOfLifeEngineException constructor
     * @param string Text of exception
     */
    public GameOfLifeEngineException(String string) {
        super(string);
    }
   
   /**
     * The 0-arg GameOfLifeEngineException constructor
     */
    public GameOfLifeEngineException() {
        
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
        r += "## A GameOfLifeEngine exception has been throwed. \n";
        
        r += super.getMessage();
        return r;
    }
}
