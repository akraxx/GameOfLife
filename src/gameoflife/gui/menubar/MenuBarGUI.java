/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.gui.menubar;

import gameoflife.gui.menubar.menus.Menu;
import javax.swing.JMenuBar;

/**
 *
 * @author group9
 * 
 * @param menuBar Menu bar
 */
public class MenuBarGUI {
    private JMenuBar menuBar = new JMenuBar();
        
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 0-arg MenuBarGUI constructor.
     */
    public MenuBarGUI() { 
        
        
    }
    
    /****************************
    * GETTER
    ****************************/
    /**
     * @return the toolBar
     */
    public JMenuBar getMenuBar() {
        return menuBar;
    }

    /****************************
    * PUBLIC FUNCTION
    ****************************/
    /**
     * Add a menubar component
     * @param menu Menu 
     */
    public void addMenuBarComponent(Menu menu) {
        this.menuBar.add(menu.getMenu());
    }
}
