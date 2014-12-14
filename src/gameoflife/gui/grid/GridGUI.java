/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.gui.grid;

import common.CellState;
import common.WorldModel;
import common.WorldView;
import events.CellSizeChangeEvent;
import events.CellStateChangeEvent;
import events.CellStateColorChangeEvent;
import events.CellStateColorSwitchEvent;
import events.ComponentDisplayingEvent;
import events.MapChangeEvent;
import exceptions.listeners.CellStateChangeListenerException;
import exceptions.listeners.CellStateColorChangeListenerException;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import listeners.CellSizeChangeListener;
import listeners.CellStateChangeListener;
import listeners.CellStateColorChangeListener;
import listeners.CellStateColorSwitchListener;
import listeners.ComponentDisplayingListener;
import listeners.MapChangeListener;


/**
 * 
 * @author group9
 * 
 * 
 * @param gl GridLayout representing the grid
 * @param loaded Boolean indicating wheter the grif was loaded
 * @param gridPanel Panel contains the GridLayout g1
 * @param nbGenerations Number of generation
 * @param size Size of the GridGUI
 * @param colors HashMap contains Cell state and color associated
 * @param cells HashMap contains Cells view 
 * @param currentModelHeight Height of the current model
 * @param currentModelWidth Width of the current model
 * @param DEFAULT_HGAP_PERCENT Default value of horizontal gap
 * @param DEFAULT_VGAP_PERCENT Default value of vertical gap
 * @param listeners Table of listeners 
 */
public class GridGUI implements WorldView, ComponentDisplayingListener, CellSizeChangeListener, CellStateColorChangeListener, MapChangeListener, CellStateColorSwitchListener {
    private GridLayout gl = new GridLayout();
    private boolean loaded = false;
    private JPanel gridPanel = new JPanel(this.gl);
    private JLabel nbGenerations = new JLabel();
    private int size;
    private HashMap<CellState, Color> colors = new HashMap<CellState, Color>();
    private HashMap<CellGUI, int[]> cells = new HashMap<CellGUI, int[]>();
    
    private int currentModelHeight;
    private int currentModelWidth;
    
    //Defaults values of horizontal and vertical gap
    public static final int DEFAULT_HGAP_PERCENT = 2;
    public static final int DEFAULT_VGAP_PERCENT = 2;
    
    private int currentHGap = GridGUI.DEFAULT_HGAP_PERCENT;
    private int currentVGap = GridGUI.DEFAULT_VGAP_PERCENT;
    private ArrayList<CellStateChangeListener> listeners = new ArrayList<CellStateChangeListener>();
     
    /****************************
    * PRIVATE FUNCTIONS
    ****************************/
    /**
     * Fire a CellStateColorSwitchEvent
     */
    private boolean fireCellStateChangeEvent(int i, int j, CellState state) {
        CellStateChangeEvent e = new CellStateChangeEvent(i, j, state);
        
        for(CellStateChangeListener l : this.listeners)
            l.cellStateChangeOccured(e);
        
        if(!e.isTreated())
            throw new CellStateChangeListenerException("Can't change the state");
        else
            return true;
    }
    
    /**
     * Set a color for each state of a cell
     * @param states Table of cell states
     */
    private void randomColor(ArrayList<CellState> states) {
        Random r = new Random();
        for(CellState state : states) {
            this.colors.put(state, Color.getHSBColor(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
        }
    }
    
    /**
     * Get a cell state by its label
     * @param label Label of cell state
     * @return Cell state
     */
    private CellState getCellStateByLabel(String label) {
        CellState rstate = null;
        for(Entry<CellState, Color> state : this.colors.entrySet()) {
            if(state.getKey().toString().equals(label))
                rstate = state.getKey();
        }
        return rstate;
    }
    
    /**
     * Get a cell state by its color
     * @param color Color of cell state
     * @return Cell color
     */
    private CellState getCellStateByColor(Color color) {
        CellState rstate = null;
        for(Entry<CellState, Color> state : this.colors.entrySet()) {
            if(state.getValue().equals(color))
                rstate = state.getKey();
        }
        return rstate;
    }
    
    /**
     * Refresh the collor of cell
     * @param currentColor The current color of cell
     * @param newColor The next/new color of cell
     */
    private void refreshColor(Color currentColor, Color newColor) {
        for(Entry<CellGUI, int[]> cell : this.cells.entrySet()) {
            if(cell.getKey().getColor().equals(currentColor))
                    cell.getKey().refresh(newColor, this.size);
        }
    }
    
    /**
     * Refresh the size of cells
     */
    private void refreshSize() {
        for(Entry<CellGUI, int[]> cell : this.cells.entrySet()) {
            cell.getKey().refresh(this.size);
        }
        this.refreshGaps();
    }
    
    /**
     * Refresh the size of gaps
     */
    private void refreshGaps() {
        this.gl.setHgap(this.currentHGap);
        this.gl.setVgap(this.currentVGap);
    }
    
    /**
     * Initializes the grid of game
     */
    private void initGrid() {
        this.gl = new GridLayout();
        this.gridPanel.removeAll();
        this.gridPanel.setLayout(gl);
        
        this.gl.setRows(this.currentModelHeight);
        this.gl.setColumns(this.currentModelWidth);

        this.refreshGaps();

        this.gridPanel.setBackground(Color.WHITE);
        this.gridPanel.setVisible(true);
        this.cells.clear();
        for (int i = 0; i < this.currentModelHeight; i++) {
            for (int j = 0; j < this.currentModelWidth; j++) {
                int coords[] = new int[2];
                coords[0] = i;
                coords[1] = j;
                CellGUI cell = new CellGUI(this.size);
                this.cells.put(cell, coords);
                cell.addCellStateColorSwitchListener(this);
                this.getGridPanel().add(cell.getP());
            }
        }
        
        this.gridPanel.revalidate();
        
        this.loaded = true;
    }
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 2-args GridGUI constructor
     * @param states Hashmap wich contains states of the game
     * @param size Size of the GridGUI
     */
    public GridGUI(ArrayList<CellState> states, int size){
        this.randomColor(states);
        this.size = size;
    }
    
    /**
     * The 0-arg gridGUI constructor
     */
    private GridGUI(){
        
    }
    
     /****************************
    * GETTER
    ****************************/
    /**
     * @return the colors of the states
     */
    public HashMap<CellState, Color> getColors() {
        return this.colors;
    }
    
    /**
     * @return the gridPanel
     */
    public JPanel getGridPanel() {
        return gridPanel;
    }

    /**
     * @return the number of generations
     */
    public JPanel getNumberOfGenerations() {
        JPanel panel = new JPanel();
        panel.add(this.nbGenerations);
        
        return panel;
    }
    

    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * Refresh and print the text view of the model
     * @param m World model of the game
     */
    public void refresh(WorldModel m) {
        if (!this.loaded || this.currentModelWidth != m.getWidth() || this.currentModelHeight != m.getHeight()) {
            this.currentModelWidth = m.getWidth();
            this.currentModelHeight = m.getHeight();
            
            this.initGrid();
        }
        this.nbGenerations.setText("Generations : " + m.getNumberOfGenerations());
        for(Entry<CellGUI, int[]> cell : this.cells.entrySet()) {
            cell.getKey().refresh(this.colors.get(this.getCellStateByLabel(m.getState(cell.getValue()[0], cell.getValue()[1]).toString())), this.size);
        }
    }

    /**
     * Add a listener
     * @param l MapChangeListener
     */
    public void addCellStateChangeListener(CellStateChangeListener l) {
        if(l != null)
            this.listeners.add(l);
        else
            throw new IllegalArgumentException("CellStateChangeListener can not be null");
    }
    
    /**
     * Actualizes the size of cell
     * @param size Size of cell
     */
    public void cellSizeChangeOccured(CellSizeChangeEvent e) {
        int newSize = e.getSize();
        if(this.size != newSize) {
            this.size = newSize;
            this.refreshSize();
            this.gridPanel.revalidate();
        }
        e.setTreated(true);
    }

    /**
     * Actualizes color and label of cell
     * @param color Color of cell
     * @param label Label of cell
     * @throw CellStateColorChangeListenerException
     */
    public void cellStateColorChangeOccured(CellStateColorChangeEvent e) {
        String label = e.getLabel();
        Color color = e.getColor();
        CellState state = this.getCellStateByLabel(label);
        if(state != null) {
            if(this.colors.containsValue(color) && this.colors.get(state) != color)
                throw new CellStateColorChangeListenerException("The color choosed can not be set because it's already used by an other state");
            
            this.refreshColor(this.colors.get(state), color);
            this.colors.remove(state);
            this.colors.put(state, color);
            e.setTreated(true);
        }
        else
            throw new CellStateColorChangeListenerException("GridGUI has no state with the label : " + label);
    }

    /**
     * Change the map 
     * @param e MapChangeEvent
     */
    public void mapChangeOccured(MapChangeEvent e) {
        this.loaded = false;
        this.refresh(e.getModel());
        e.setTreated(true);
    }

    /**
     * Change the displaying component associted by ComponentDisplayingEvent 
     * @param e ComponentDisplayingEvent 
     */
    public void componentDisplayingOccured(ComponentDisplayingEvent e) {
        switch(e.getComponent()) {
            case GRID:
                if(e.isVisible()) {
                    this.currentHGap = GridGUI.DEFAULT_HGAP_PERCENT;
                    this.currentVGap = GridGUI.DEFAULT_VGAP_PERCENT;
                }
                else {
                    this.currentHGap = 0;
                    this.currentVGap = 0;
                }
                
                this.refreshGaps();
                this.gridPanel.revalidate();
                e.setTreated(true);
                break;
            case GENERATION_NUMBER:
                if(e.isVisible())
                    this.nbGenerations.setVisible(true);
                else
                    this.nbGenerations.setVisible(false);
                this.nbGenerations.revalidate();
                e.setTreated(true);
                break;
        }
    }

    /**
     * Change the color of a cell state
     * @param e CellStateColorSwitchEvent
     */
    public void cellStateColorSwitchOccured(CellStateColorSwitchEvent e) {
        e.getCellGUI().getColor();
        ArrayList<Color> colorsArray = new ArrayList<Color>();
        int position = -1;
        for(Entry<CellState, Color> state : this.colors.entrySet()) {
            if(e.getCellGUI().getColor().equals(state.getValue()))
                position = colorsArray.size();
            colorsArray.add(state.getValue());
        }

        if(position > -1) {
            int[] coords = this.cells.get(e.getCellGUI());
            Color color = colorsArray.get((position + 1) % this.colors.size());
            if(this.fireCellStateChangeEvent(coords[0], coords[1], this.getCellStateByColor(color)))
                e.getCellGUI().refresh(color);
        }
    }

}
