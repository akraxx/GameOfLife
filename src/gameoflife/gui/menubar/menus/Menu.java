/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.gui.menubar.menus;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author group9
 * 
 * @param menu Menu
 */
public class Menu {
    protected JMenu menu = new JMenu();
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
    * The 1-arg ToolBarComponent constructor
    * @param menuName Name of the menu
    */
    public Menu(String menuName) {
        this.menu.setText(menuName);
    }
    
    /****************************
    * GETTER
    ****************************/
     /**
     * @return menu
     */
    public JMenu getMenu() {
        return this.menu;
    }
    
    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * Add an item in a menu
     * @param item Item to be added
     */
    public void addItem(JMenuItem item) {
        this.menu.add(item);
    }
    
    /**
     * Add a separator in a menu
     */
    public void addSeparator() {
        this.menu.addSeparator();
    }
}
