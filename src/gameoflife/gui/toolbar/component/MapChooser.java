/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.gui.toolbar.component;

import common.MapCreator;
import common.WorldModel;
import events.MapChangeEvent;
import exceptions.listeners.MapChangeListenerException;
import gameoflife.gui.toolbar.ToolBarComponent;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import listeners.MapChangeListener;

/**
 *
 * @author group9
 * 
 * @param mapChooserPanel Panel which contains the map chooser
 * @param listeners List of listeners
 * @param maps List of map
 */
public class MapChooser extends ToolBarComponent implements ActionListener {
    private JPanel mapChooserPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
    private ArrayList<MapChangeListener> listeners = new ArrayList<MapChangeListener>();
    private ArrayList<MapCreator> maps;
    
    /****************************
    * PRIVATE FUNCTIONS
    ****************************/
    /**
     * Fire a MapChangeEvent
     * @param m Model of world
     */
    private void fireMapChangeEvent(WorldModel m) {
        MapChangeEvent e = new MapChangeEvent(m);
        for(MapChangeListener l : this.listeners)
            l.mapChangeOccured(e);
        
        if(!e.isTreated())
            throw new MapChangeListenerException("Can't change the map because the chooser has no effects");
    }
    
    /**
     * Initializes and add the chooser (ComboBox) in the mapChooserPanel
     */
    private void initChooser() {
        JComboBox cb = new JComboBox();
        for(MapCreator map : this.maps) {
            cb.addItem(map.getName());
        }
        cb.addActionListener(this);
        this.mapChooserPanel.add(cb);
    } 
    
    /****************************
    * CONSTRUCTOR
    ****************************/   
    /**
     * The 2-arg MapChooser constructor.
     * @param label MapChooser label
     * @param maps Array of map
     */
    public MapChooser(String label, ArrayList<MapCreator> maps) {
        super(label);
        this.maps = maps;
        this.initChooser();
        this.chargePanel(this.mapChooserPanel);
    }
    

    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * Add a MapChangeListener to MapChooser
     * @param l MapChangeListener
     */
    public void addMapChangeListener(MapChangeListener l) {
        if(l != null)
            this.listeners.add(l);
        else
            throw new IllegalArgumentException("MapChangeListener can not be null");
        
    }
    
    /**
     * Recover change of MapChooser
     * @param e ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        JComboBox cb = (JComboBox)e.getSource();
        String mapName = (String)cb.getSelectedItem();
        boolean found = false;
        int length = this.maps.size();
        int i = 0;
        
        while(i < length && !found) {
            if(this.maps.get(i).getName().equals(mapName))
                found = true;
            else
                i++;
        }

        if(found)
            this.fireMapChangeEvent(this.maps.get(i).createMap());
        else
            throw new MapChangeListenerException("Can't change the map because it can not be found");
            
    }
    
}
