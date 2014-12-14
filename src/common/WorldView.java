/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package common;

import java.io.Serializable;

/**
 *
 * @author group9
 */
public interface WorldView extends Serializable {
    
    /**
     * Refresh and print a text view of a model
     * @param m World model of the game
     */
    public void refresh(WorldModel m);
}
