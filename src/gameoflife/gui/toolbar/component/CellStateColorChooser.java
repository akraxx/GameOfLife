/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.gui.toolbar.component;

import common.CellState;
import events.CellStateColorChangeEvent;
import exceptions.listeners.CellStateColorChangeListenerException;
import exceptions.listeners.CellStateColorChooserListenerException;
import gameoflife.gui.toolbar.ToolBarComponent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import listeners.CellStateColorChangeListener;

/**
 *
 * @author group9
 * 
 * @param cellStateColorChooserPanel Panel of the color chooser
 * @param listeners List of listeners
 * @param boxes List of panel 
 */
public class CellStateColorChooser extends ToolBarComponent implements MouseListener, CellStateColorChangeListener {
    private JPanel cellStateColorChooserPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
    private ArrayList<CellStateColorChangeListener> listeners = new ArrayList<CellStateColorChangeListener>();
    private HashMap<String, JPanel> boxes = new HashMap<String, JPanel>();
    
    /****************************
    * PRIVATE FUNCTIONS
    ****************************/
    /**
     * Initializes the state of the color chooser
     * @param label Color chooser label
     * @param color Background color
     */
    private void initState(String label, Color color) {
        
        JPanel box = new JPanel();
        box.setBackground(color);
        box.setSize(10, 10);
        box.setName(label);
        box.addMouseListener(this);
        box.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.boxes.put(label, box);
        this.cellStateColorChooserPanel.add(box);
        this.cellStateColorChooserPanel.add(new JLabel(label));
    }
    
    /**
     * Fire a CellStateColorChooserEvent
     * @param color Color of the CellStateColorChooser
     * @param label Label of the CellStateColorChooser
     */
    private void fireCellStateColorChangeEvent(Color color, String label) {
        CellStateColorChangeEvent e = new CellStateColorChangeEvent(color, label);
        
        for(CellStateColorChangeListener l : this.listeners)
            l.cellStateColorChangeOccured(e);
        
        if(!e.isTreated())
            throw new CellStateColorChooserListenerException("Can't open the color chooser because the button has no effects");
    }
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * 2-args CellStateColorChooser constructor. 
     * @param label CellStateColorChooser label
     * @param states HashMap which contains string and color of states
     */
    public CellStateColorChooser(String label, HashMap<CellState, Color> states) {
        super(label);
        for(Entry<CellState, Color> state : states.entrySet()) {
            this.initState(state.getKey().toString(), state.getValue());
        }
        
        this.chargePanel(this.cellStateColorChooserPanel);
    }
    

    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * Add a CellStateColorChooserListener to CellStateColorChooser
     * @param l CellStateColorChooser
     */
    public void addCellStateColorChangeListener(CellStateColorChangeListener l) {
        if(l != null)
            this.listeners.add(l);
        else
            throw new IllegalArgumentException("CellStateColorChooserListener can not be null");
    }

    /**
     * Event linked to the mouse clicked
     * @param e MouseEvent
     */
    public void mouseClicked(MouseEvent e) {
        JPanel p = (JPanel)e.getSource();
        this.fireCellStateColorChangeEvent(JColorChooser.showDialog(null, "Choose " + p.getName() + " color", p.getBackground()), p.getName());
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    /**
     * Set the color of cell with the recovered color by the ColorChooser
     * @param color Recovered color by the ColorChooser
     * @param label Label of the CellStateColorChooser
     * @return true if the change is made
     */
    public void cellStateColorChangeOccured(CellStateColorChangeEvent e) {
        String label = e.getLabel();
        Color color = e.getColor();
        if(this.boxes.containsKey(label)) {
            this.boxes.get(label).setBackground(color);
            e.setTreated(true);
        }
        else
            throw new CellStateColorChangeListenerException("CellStateColorChooser has no state with the label : " + label);
    }
}
