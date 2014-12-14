/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.gui.toolbar.component;

import events.CellSizeChangeEvent;
import exceptions.listeners.CellSizeChangeListenerException;
import exceptions.listeners.ListenerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import listeners.CellSizeChangeListener;

/**
 * 
 * @author group9
 * 
 * @param CELL_SIZE_MIN Default minimum size of cell
 * @param CELL_SIZE_MAX Default maximum size of cell
 * @param CELL_SIZE_MAJOR_TICK_SPACING Default value of major tick spacing
 * @param CELL_SIZE_MINOR_TICK_SPACING Default value of minor tick spacing
 * @param listeners List of listeners
 */
public class CellSizeSliderController extends SliderController implements ChangeListener, ActionListener {
    public static final int CELL_SIZE_MIN = 0;
    public static final int CELL_SIZE_MAX = 50;
    public static final int CELL_SIZE_MAJOR_TICK_SPACING = 10;
    public static final int CELL_SIZE_MINOR_TICK_SPACING = 2;
    private ArrayList<CellSizeChangeListener> listeners = new ArrayList<CellSizeChangeListener>();
     
    /****************************
    * PRIVATE FUNCTIONS
    ****************************/
    /**
     * Initializes CellSizeSliderController
     */
    private void initListeners() {
        this.slider.addChangeListener(this);
        this.textfield.addActionListener(this);
    }
     
    /**
     * Fire CellSizeChangeListener
     * @param speed Value for cellSizeChangeOccured
     */
    private void fireCellSizeChangeEvent(int speed) {
        CellSizeChangeEvent e = new CellSizeChangeEvent(speed);
        for(CellSizeChangeListener l : this.listeners)
                l.cellSizeChangeOccured(e);
        
        if(!e.isTreated())
            throw new CellSizeChangeListenerException("This control has no effects, it does not change the size of the cells");
    }
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * 2-args CellSizeSliderController constructor.
     * @param label Label of CellSizeSlider
     * @param defaultValue Default value of CellSizeSlider
     */
    public CellSizeSliderController(String label, int defaultValue){
        super(label,defaultValue,CELL_SIZE_MIN, CELL_SIZE_MAX, CELL_SIZE_MINOR_TICK_SPACING, CELL_SIZE_MAJOR_TICK_SPACING);
        this.initListeners();
    }
    
    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * Recover change of CellSizeSlider
     * @param e ChangeEvent
     */
    public void stateChanged(ChangeEvent e) {
        int size = this.slider.getValue();
        this.fireCellSizeChangeEvent(size);
        this.textfield.setText(String.valueOf(size));
    }

    /**
     * Recover change of CellSizeSlider textfield
     * @param e ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        try {
            int size = Integer.valueOf(this.textfield.getText());
            this.slider.setValue(Integer.valueOf(size));
        }
        catch(ListenerException exc) {
            throw exc;
        }
        catch(Exception exc) {

        }
    }
    
    /**
     * Add a CellSizeChangeListener to the CellSliderController
     * @param l CellSizeChangeListener
     */
    public void addCellSizeChangeListener(CellSizeChangeListener l) {
        if(l != null)
            this.listeners.add(l);
        else
            throw new IllegalArgumentException("CellSizeChangeListener can not be null");
    }
    
}
