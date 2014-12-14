/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

/**
 *
 * @author group9
 * 
 * @param size Size of cell
 */
public class CellSizeChangeEvent extends MyEvent {
    int size;

    /****************************
    * GETTER
    ****************************/
    public int getSize() {
        return size;
    }
    
    /****************************
    * SETTER
    ****************************/
    public CellSizeChangeEvent(int size) {
        this.size = size;
    }

}
