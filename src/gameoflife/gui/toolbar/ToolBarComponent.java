/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.gui.toolbar;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.metal.MetalBorders;

/**
 *
 * @author group9
 * 
 * @param componentPanel Panel which contains the toolbar component
 */
public class ToolBarComponent { 
    protected JPanel componentPanel = new JPanel(new BorderLayout(0, 0));
        
    /****************************
    * PRIVATE FUNCTIONS
    ****************************/
    /**
     * Initializes a label 
     * @param label Text to incorporate the label
     */
    private void initLabel(String label) {
        JLabel l = new JLabel(label);
        l.setBorder(MetalBorders.getTextBorder());
        Font f = new Font("Courrier", 0, 15);
        l.setFont(f);
        this.componentPanel.add(l, BorderLayout.NORTH);
    }
    
    /****************************
    * GETTER
    ****************************/
     /**
     * @return component panel
     */
    public JPanel getPanel() {
        return this.componentPanel;
    }

    /****************************
    * PROTECTED FUNCTIONS
    ****************************/
    /**
     * Charge a panel in the component panel 
     * @param panel Panel to be charged
     */
    protected void chargePanel(JPanel panel) {
        this.componentPanel.add(panel, BorderLayout.CENTER);
    }
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 1-arg ToolBarComponent constructor
     * @param label Label which initializes the label of the toolbar component
     */
    public ToolBarComponent(String label) {
        this.initLabel(label);
    }
    
}
