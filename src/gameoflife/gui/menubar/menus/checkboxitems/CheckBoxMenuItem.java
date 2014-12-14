/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.gui.menubar.menus.checkboxitems;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;

/**
 *
 * @author group9
 * 
 * @param menuItem Checkbox menu item
 */
public class CheckBoxMenuItem {
    protected JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem();    
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 2-args CheckBoxMenuItem constructor
     * @param itemName Name of item
     * @param selected Default selected value of checkbox
     */
    public CheckBoxMenuItem(String itemName, boolean selected) {
        this.menuItem.setText(itemName);
        this.menuItem.setSelected(true);
    }

    /****************************
    * GETTER
    ****************************/
    /**
     * @return the menuItem
     */
    public JMenuItem getCheckBoxMenuItem() {
        return menuItem;
    }
}
