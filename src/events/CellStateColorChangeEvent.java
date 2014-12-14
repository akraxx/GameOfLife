/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package events;

import java.awt.Color;

/**
 *
 * @author group9
 * 
 * @param color Cell color
 * @param label Cell label
 */
public class CellStateColorChangeEvent extends MyEvent {
    Color color;
    String label;
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 2-args CellStateColorChangeEvent constructor
     * @param color Cell color
     * @param label Cell label
     */
    public CellStateColorChangeEvent(Color color, String label) {
        this.color = color;
        this.label = label;
    }

    /****************************
    * GETTERS
    ****************************/
    /**
     * @return Color of cell
     */
    public Color getColor() {
        return color;
    }

    /**
     * @return Label of cell
     */
    public String getLabel() {
        return label;
    }
}
