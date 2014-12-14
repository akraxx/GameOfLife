/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.gui;

import events.QuitGameEvent;
import events.TitleChangeEvent;
import gameoflife.gui.grid.GridGUI;
import gameoflife.gui.menubar.MenuBarGUI;
import gameoflife.gui.toolbar.ToolBarGUI;
import javax.swing.JPanel;

/**
 *
 * @author group9
 */
public class GameOfLifeAppletGUI extends GameOfLifeGUI {
    private MenuBarGUI menuBarGUI;
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * 3-args GameOfLifeAppletGUI constructor
     * @param toolbarGUI Toolbar view
     * @param gridGUI Grid view
     * @param menuBarGUI Menu bar view
     */
    public GameOfLifeAppletGUI(ToolBarGUI toolBarGUI, GridGUI gridGUI, MenuBarGUI menuBarGUI) {
        super(toolBarGUI, gridGUI);
        this.menuBarGUI = menuBarGUI;
    }
    
    /**
     * Quit the game 
     * @param e Quit game event
     */
    @Override
    public void quitGameOccured(QuitGameEvent e) {
        this.content.setVisible(false);
        this.fireQuitGameEvent();
    }
    
    /**
     * Change the title
     * @param e TitleChangeEvent
     */
    @Override
    public void titleChangeOccured(TitleChangeEvent e) {
        
    }
    
    /**
     * @return the content panel
     */
    public JPanel getContent() {
        return this.content;
    }
    
    /**
     * @return the menubar view
     */
    public MenuBarGUI getMenuBarGUI() {
        return this.menuBarGUI;
    }
}
