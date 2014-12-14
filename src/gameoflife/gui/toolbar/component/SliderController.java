/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.gui.toolbar.component;

import gameoflife.gui.toolbar.ToolBarComponent;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

/**
 *
 * @author group9
 * 
 * @param slideControllerPanel Panel which contains the SliderController
 * @param slider Slider
 * @param textfield Text field
 * @param defaultValue Default value of slider controller
 */
public abstract class SliderController extends ToolBarComponent {
    private JPanel sliderControllerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
    protected JSlider slider = new JSlider();
    protected JTextField textfield = new JTextField();
    int defaultValue = 0;
    
    /****************************
    * PRIVATE FUNCTIONS
    ****************************/
    /**
     * Initializes the controllers
     * @param minimumValue Value minimum of slider
     * @param maximumValue Value maximum of slider
     * @param minorTickSpacing Value minimum of tick spacing
     * @param majorTickSpacing Value maximum of tick spacing
     */
    private void initControllers(int minimumValue, int maximumValue, int minorTickSpacing, int majorTickSpacing) {
        this.initTextField();
        this.initSlider(minimumValue, maximumValue, minorTickSpacing, majorTickSpacing);
    }
    
    /**
     * Initializes the slider
     * @param minimumValue Value minimum of slider
     * @param maximumValue Value maximum of slider
     * @param minorTickSpacing Value minimum of tick spacing
     * @param majorTickSpacing Value maximum of tick spacing
     */
    private void initSlider(int minimumValue, int maximumValue, int minorTickSpacing, int majorTickSpacing) {        
        slider.setMajorTickSpacing(majorTickSpacing);
        slider.setMinorTickSpacing(minorTickSpacing);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMaximum(maximumValue);
        slider.setMinimum(minimumValue);
        slider.setValue(this.defaultValue);
        this.sliderControllerPanel.add(slider);
    }
    
    /**
     * Initialized the TextFielf associted with the slider
     */
    private void initTextField() {
        textfield.setText(String.valueOf(this.defaultValue));
        this.textfield.setColumns(2);
        this.sliderControllerPanel.add(textfield);
    }
    
    
    /****************************
    * CONSTRUCTOR
    ****************************/    
    /**
     * The 2-args SliderController constructor
     * @param label SliderController label
     * @param defaultValue Default value of the slider
     * @param minimumValue Value minimum of slider
     * @param maximumValue Value maximum of slider
     * @param minorTickSpacing Value minimum of tick spacing
     * @param majorTickSpacing Value maximum of tick spacing
     */
    public SliderController(String label, int defaultValue, int minimumValue, int maximumValue, int minorTickSpacing, int majorTickSpacing) {
        super(label);
        this.defaultValue = defaultValue;
        this.initControllers(minimumValue, maximumValue, minorTickSpacing, majorTickSpacing);
        
        this.chargePanel(this.sliderControllerPanel);
    }
        
}
