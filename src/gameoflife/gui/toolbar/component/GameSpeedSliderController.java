/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.gui.toolbar.component;

import events.GameSpeedChangeEvent;
import exceptions.listeners.GameSpeedChangeListenerException;
import exceptions.listeners.ListenerException;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import listeners.GameSpeedChangeListener;

/**
 *
 * @author group9
 * 
 * @param GAME_SPEED_MIN Default value of miniminum game speed
 * @param GAME_SPEED_MAX Default value of maximum game speed
 * @param CRITIC_SPEED_PERCENT Percentage representing the critical speed
 * @param CELL_SIZE_MAJOR_TICK_SPACING Default value of major tick spacing
 * @param CELL_SIZE_MINOR_TICK_SPACING Default value of minor tick spacing
 * @param listeners List of listeners
 */
public class GameSpeedSliderController extends SliderController implements ChangeListener, ActionListener {
    public static final int GAME_SPEED_MIN = 0;
    public static final int GAME_SPEED_MAX = 10;
    public static final int CRITIC_SPEED_PERCENT = 80;
    public static final int CELL_SIZE_MAJOR_TICK_SPACING = 10;
    public static final int CELL_SIZE_MINOR_TICK_SPACING = 5;
    private ArrayList<GameSpeedChangeListener> listeners = new ArrayList<GameSpeedChangeListener>();
    
        
    /****************************
    * PRIVATE FUNCTIONS
    ****************************/
    /**
     * Fire GameSpeedSliderListener
     * @param speed Value for gameSpeedChangeOccured
     */
    private void fireGameSpeedChangeEvent(int speed) {
        GameSpeedChangeEvent e = new GameSpeedChangeEvent(speed);
        for(GameSpeedChangeListener l : this.listeners)
                l.gameSpeedChangeOccured(e);
        
        if(!e.isTreated())
            throw new GameSpeedChangeListenerException("This control has no effects, it does not change the speed of the game");
    }
    
    /**
     * Initializes GameSpeedSliderController
     */
    private void initListeners() {
        this.slider.addChangeListener(this);
        this.textfield.addActionListener(this);
    }    
    
    /**
     * 
     */
    private void colorTextField() {
        try {
            float fps = Float.valueOf(this.textfield.getText());
            Color color;
            if((fps / (float)GameSpeedSliderController.GAME_SPEED_MAX) >=  ((float)GameSpeedSliderController.CRITIC_SPEED_PERCENT / 100)) {
                float[] hsbvals = new float[3];
                Color.RGBtoHSB(255, (255 - ((int)fps * 255 / GameSpeedSliderController.GAME_SPEED_MAX)), 0, hsbvals);
                color = Color.getHSBColor(hsbvals[0], hsbvals[1], hsbvals[2]);
            }
            else
                color = Color.BLACK;
            
            this.textfield.setForeground(color);
        }
        catch(Exception e) {}
    }
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * 2-args GameSpeedSliderController constructor.
     * @param label Label of GameSpeedSliderController
     * @param defaultValue Default value of GameSpeedSliderController
     */
    public GameSpeedSliderController(String label, int defaultValue){
        super(label,defaultValue,GAME_SPEED_MIN, GAME_SPEED_MAX, CELL_SIZE_MAJOR_TICK_SPACING, CELL_SIZE_MINOR_TICK_SPACING);
        this.initListeners();
        this.colorTextField();
    }
    
    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * Recover change of GameSpeedSlider
     * @param e ChangeEvent
     */
    public void stateChanged(ChangeEvent e) {
        int fps = this.slider.getValue();
        this.fireGameSpeedChangeEvent(fps);
        this.textfield.setText(String.valueOf(fps));
        this.textfield.revalidate();
        this.colorTextField();

    }

    /**
     * Recover change of GameSpeedSlider textfield
     * @param e ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        try {
            int fps = Integer.valueOf(this.textfield.getText());
            this.slider.setValue(Integer.valueOf(fps));
        }
        catch(ListenerException exc) {
            throw exc;
        }
        catch(Exception exc) {

        }
    }
    
    /**
     * Add a GameSpeedSliderListener to the GameSpeedSlider
     * @param l GameSpeedSliderListener
     */
    public void addGameSpeedChangeListener(GameSpeedChangeListener l) {
        if(l != null)
            this.listeners.add(l);
        else
            throw new IllegalArgumentException("GameSpeedChangeListener can not be null");
    }
    
}
