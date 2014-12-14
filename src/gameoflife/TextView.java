/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gameoflife;

import common.WorldModel;
import common.WorldView;

/**
 *
 * @author group9
 */
public class TextView implements WorldView {

    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * Refresh and print the text view of the model
     * @param m World model of the game
     */
    public void refresh(WorldModel m) {
        for (int i = 0; i < m.getHeight(); i++) {
            for (int j = 0; j < m.getWidth(); j++) {
                System.out.print(m.getState(i, j).toString() + " ");
            }//end for j
            System.out.println("");
        }//end for i
        System.out.println("");
    }

}
