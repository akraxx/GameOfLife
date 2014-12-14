/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.gui.grid;

import common.CellState;
import events.CellStateColorSwitchEvent;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JPanel;
import listeners.CellStateColorSwitchListener;

/**
 *
 * @author group9
 * 
 * @param p Panel represented cellGUI
 * @param size Size of cellGUI
 * @param currentState State of the cell
 * @param listeners Table of listeners 
 * @param isPressed Mouse status
 */
public class CellGUI implements Serializable, MouseListener {
    private JPanel p;
    private int size;
    CellState currentState;
    private ArrayList<CellStateColorSwitchListener> listeners = new ArrayList<CellStateColorSwitchListener>();
    private static boolean isPressed = false;
    
    /****************************
    * PRIVATE FUNCTIONS
    ****************************/
    /**
     * Fire a CellStateColorSwitchEvent
     */
    private void fireCellStateColorSwitchEvent() {
        CellStateColorSwitchEvent e = new CellStateColorSwitchEvent(this);
        
        for(CellStateColorSwitchListener l : this.listeners)
            l.cellStateColorSwitchOccured(e);
    }
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * the 1-arg CellGUI constructor
     * @param size Size of cellGUI
     */
    public CellGUI(int size) {
        this.size = size;
        this.p = new JPanel();
        this.setSize();
        this.p.addMouseListener(this);
    }
    
    
    /****************************
    * GETTER
    ****************************/
    /**
     * @return the background color of cell
     */
    public Color getColor() {
        return this.p.getBackground();
    }
    
    /**
     * @return the panl p
     */
    public JPanel getP() {
        return p;
    }
    
    /****************************
    * SETTER
    ****************************/
    /**
     * Set size of a cell
     */
    private void setSize() {
        if(p.getHeight() != this.size || p.getWidth() != this.size) {
            this.p.setSize(this.size, this.size);
            this.p.setPreferredSize(this.p.getSize());
            this.p.setMaximumSize(this.p.getSize());
            this.p.setMinimumSize(this.p.getSize());
        }
    }
    
    /**
     * Set the background color of a cell
     * @param color Color to be setted in cell background
     */
    private void setBackground(Color color) {
        if(color != this.p.getBackground()) {
            this.p.setBackground(color);
        }
    }

    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
   /**
     * Refreshes and actualizes the state of cell with the appropriate color
     * @param color 
     * @param size 
     */
    public void refresh(Color color, int size){
        this.size = size;
        this.setBackground(color);
        this.setSize();
    }
    
    /**
     * Refresh the background color of the cell
     * @param color Background color to set
     */
    public void refresh(Color color){
        this.setBackground(color);
    }
    
    /**
     * Refresh the size of cell
     * @param size Size of cell
     */
    public void refresh(int size){
        this.size = size;
        this.setSize();
    }
    
    /**
     * Add a CellStateColorSwitchListener
     * @param l CellStateColorSwitchListener
     */
    public void addCellStateColorSwitchListener(CellStateColorSwitchListener l) {
        if(l != null)
            this.listeners.add(l);
        else
            throw new IllegalArgumentException("CellStateColorSwitchListener can not be null");
    }
    
    public void mouseClicked(MouseEvent e) {
        
    }

    public void mousePressed(MouseEvent e) {
        this.isPressed = true;
        this.fireCellStateColorSwitchEvent();
    }

    public void mouseReleased(MouseEvent e) {
        this.isPressed = false;
    }

    public void mouseEntered(MouseEvent e) {
        if(this.isPressed)
            this.fireCellStateColorSwitchEvent();
    }

    public void mouseExited(MouseEvent e) {
        
    }
        
}
