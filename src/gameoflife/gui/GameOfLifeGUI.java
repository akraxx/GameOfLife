/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.gui;

import common.GUI;
import gameoflife.gui.grid.GridGUI;
import gameoflife.gui.toolbar.ToolBarGUI;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


/**
 *
 * @author group9
 * 
 */
public abstract class GameOfLifeGUI extends GUI {
    /**
     * Add a new toolbar in GameOfLifeGUI
     * @param toolBarGUI Toolbar
     */
    private void addToolBar(ToolBarGUI toolBarGUI) {
        JPanel pane = new JPanel(new BorderLayout(0,0));
        
        pane.add(toolBarGUI.getToolBar());
        this.content.add(pane, BorderLayout.WEST);
        this.content.setMinimumSize(pane.getPreferredSize());
    }
    
    /**
     * The GUI grid is initialized with an Hashmap wich contains the colors of each state of cell and the size of grid. Next the GUI grid is added to a scrollpane
     * @param gridGUI Grid to be added
     */
    private void addGrid(GridGUI gridGUI) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.add(gridGUI.getGridPanel());
        this.content.add(new JScrollPane(panel), BorderLayout.CENTER);
        this.content.add(gridGUI.getNumberOfGenerations(), BorderLayout.SOUTH);
    }
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * 2-args GameOfLifeGUI constructor
     * @param toolBarGUI Toolbar view
     * @param gridGUI Grid view
     */
    public GameOfLifeGUI(ToolBarGUI toolBarGUI, GridGUI gridGUI) {
        this.addGrid(gridGUI);
        this.addToolBar(toolBarGUI);
    }
}
