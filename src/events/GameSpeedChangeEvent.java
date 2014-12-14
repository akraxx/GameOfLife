/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

/**
 *
 * @author group9
 * 
 * @param speed The game speed
 */
public class GameSpeedChangeEvent extends MyEvent {
    int speed;

    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 1-arg GameSpeedChangeEvent constructor
     * @param speed The game speed
     */
    public GameSpeedChangeEvent(int speed) {
        this.speed = speed;
    }

    /****************************
    * GETTERS
    ****************************/
    /**
     * @return the game speed
     */
    public int getSpeed() {
        return speed;
    }
}
