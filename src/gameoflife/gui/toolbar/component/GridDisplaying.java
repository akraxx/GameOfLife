/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.gui.toolbar.component;

import events.ComponentDisplayingEvent;
import exceptions.listeners.ComponentDisplayingListenerException;
import gameoflife.gui.toolbar.ToolBarComponent;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import listeners.ComponentDisplayingListener;

/**
 *
 * @author group9
 * 
 * @param ComponentDisplayingPanel Panel which contains the grid displaying
 * @param listeners List of listener
 * @param checkbox Checkbox
 */
public class GridDisplaying extends ToolBarComponent implements ItemListener {
    private JPanel ComponentDisplayingPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
    private ArrayList<ComponentDisplayingListener> listeners = new ArrayList<ComponentDisplayingListener>();
    private JCheckBox checkbox = new JCheckBox();
    
    /****************************
    * PRIVATE FUNCTIONS
    ****************************/
    /**
     * Initializes the checkbox of the ComponentDisplayingPanel
     */
    private void initCheckBox() {
        checkbox.setSelected(true);
        checkbox.addItemListener(this);
        this.ComponentDisplayingPanel.add(checkbox);
        this.ComponentDisplayingPanel.add(new JLabel("Show grid"));
    }
    
    /**
     * Fire a ComponentDisplayingListener to the ComponentDisplaying.
     * @param visible This value set the ComponentDisplayingOccure visible or not
     */
    private void fireComponentDisplayingEvent(boolean visible) {
        ComponentDisplayingEvent e = new ComponentDisplayingEvent(ComponentDisplayingEvent.Component.GRID, visible);
        for(ComponentDisplayingListener l : this.listeners)
                l.componentDisplayingOccured(e);

        if(!e.isTreated())
            throw new ComponentDisplayingListenerException("This control has no effects, it does not show or hide the grid");
    }
    
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * 1-arg ComponentDisplaying cconstructor 
     * @param label ComponentDisplaying label
     */
    public GridDisplaying(String label) {
        super(label);
        this.initCheckBox();
        this.chargePanel(this.ComponentDisplayingPanel);
    }
    
    

    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * Add a addComponentDisplayingListener to the ComponentDisplaying
     * @param l ComponentDisplayingListener
     */
    public void addComponentDisplayingListener(ComponentDisplayingListener l) {
        if(l != null)
            this.listeners.add(l);
        else
            throw new IllegalArgumentException("GameSpeedChangeListener can not be null");
    }

    /**
     * Change the state of the checkbox ComponentDisplaying
     * @param e ItemEvent
     */
    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED)
            this.fireComponentDisplayingEvent(true);
        else
            this.fireComponentDisplayingEvent(false);
    }

}
