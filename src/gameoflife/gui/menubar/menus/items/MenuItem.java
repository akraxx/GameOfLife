/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.gui.menubar.menus.items;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author group9
 * 
 * @param menuItem MenuItem
 */
public class MenuItem {
    protected JMenuItem menuItem = new JMenuItem();    
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 2-args MenuItem constructor
     * @param itemName Name of item
     * @param key Mnemonic of item
     */
    public MenuItem(String itemName, Character key) {
        this.menuItem.setText(itemName);
        this.menuItem.setMnemonic(key);
        this.menuItem.setAccelerator(KeyStroke.getKeyStroke("control " + key));
    }

    /****************************
    * GETTER
    ****************************/
    /**
     * @return the menuItem
     */
    public JMenuItem getMenuItem() {
        return menuItem;
    }
}
