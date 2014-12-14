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
import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author group9
 */
public class GameOfLifeFramedGUI extends GameOfLifeGUI {
    protected JFrame frame = new JFrame();
    
    /****************************
    * PRIVATE FUNCTIONS
    ****************************/
    /**
     * Configure the frame
     * @param title Title to be attributed to the frame
     */
    private void configureFrame(String title) {
        this.frame.setTitle(title);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLayout(new BorderLayout(0,0));
    } 
    
    /**
     * Add a new menubar in GameOfLifeGUI
     * @param menuBarGUI Menubar
     */
    private void addMenuBar(MenuBarGUI menuBarGUI) {
        this.frame.setJMenuBar(menuBarGUI.getMenuBar());
    }
    
    /**
     * Set the default size of GUI and makes it visible
     */
    private void run() {
        this.frame.setSize(700,450);
        this.frame.setVisible(true);
    }
    
    /**
     * Quit the frame
     */
    private void quit() {
        this.frame.dispose();
    }
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * 4-args GameOfLifeFramedGUI contructor
     * @param toolBarGUI Toolbar view
     * @param gridGUI Grid view
     * @param menuBarGUI Menu bar view
     * @param title Frame title
     */
    public GameOfLifeFramedGUI(ToolBarGUI toolBarGUI, GridGUI gridGUI, MenuBarGUI menuBarGUI, String title) {
        super(toolBarGUI, gridGUI);
        this.configureFrame(title);
        this.addMenuBar(menuBarGUI);
        this.frame.add(this.content);
        this.run();
    }
    
    /**
     * Quit the game 
     * @param e Quit game event
     */
    @Override
    public void quitGameOccured(QuitGameEvent e) {
        this.quit();
        e.setTreated(true);
        this.fireQuitGameEvent();
    }
    /**
     * Change the frame title
     * @param e TitleChangeEvent
     */
    @Override
    public void titleChangeOccured(TitleChangeEvent e) {
        this.frame.setTitle(this.title + " - " + e.getTitle());
    }
}
