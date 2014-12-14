/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author group9
 */
public class MyException extends RuntimeException {

    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 2-args MyException constructor
     * @param string Text of exception
     * @param e Exception
     */
    public MyException(String string, Exception e) {
        super(string, e);
    }
    
    /**
     * The 1-arg MyException constructor
     * @param string Text of exception
     */
    public MyException(String string) {
        super(string);
    }
    
    /**
     * The 0-arg MyException constructor
     */
    public MyException() {
        
    }
    
    /****************************
    * GETTER
    ****************************/
    /**     
     * @return message exception
     */
    @Override
    public String getMessage() {
        Throwable currentCause = super.getCause();
        String r = "";
        r += "__________________________________________\n";
        r += "## An exception has been throwed. \n";
        r += "Details : " + super.getMessage() + "\n";
        r += "---- Causes ----\n";
        while(currentCause != null) {
            r += currentCause.getMessage() + "\n";
            currentCause = currentCause.getCause();
        }
        r += "---- End of causes ----\n";
        r += "__________________________________________\n";
        
        return r;
    }

}
