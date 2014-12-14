/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.gui.toolbar;

import events.ComponentDisplayingEvent;
import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JToolBar;
import listeners.ComponentDisplayingListener;

/**
 *
 * @author group9
 * 
 * @param toolBar Toolbar
 */
public class ToolBarGUI implements ComponentDisplayingListener {
    private JToolBar toolBar = new JToolBar();
    
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 0-arg ToolBarGUI constructor. Set toolbar attributes.
     */
    public ToolBarGUI() {
        this.toolBar.setLayout(new BoxLayout(this.toolBar, BoxLayout.Y_AXIS));
        this.toolBar.setFloatable(false);
    }
    
    /****************************
    * GETTER
    ****************************/
    /**
     * @return the toolBar
     */
    public JToolBar getToolBar() {
        return toolBar;
    }
    
    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * Add and initializes component in the toolbar
     * @param component Component to be added and initialized in toolbar
     */
    public void addComponent(ToolBarComponent component) {
        component.getPanel().setAlignmentX(Component.LEFT_ALIGNMENT);
        this.getToolBar().add(component.getPanel());
    }

    public void componentDisplayingOccured(ComponentDisplayingEvent e) {
        switch(e.getComponent()) {
            case TOOLBAR:
                if(e.isVisible())
                    this.toolBar.setVisible(true);
                else
                    this.toolBar.setVisible(false);

                e.setTreated(true);
                break;
        }
    }
    
}
