/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife.gui.toolbar.component;

import events.ActionAnimationEvent;
import events.ActionAnimationEvent.Actions;
import events.StepEvent;
import exceptions.listeners.StartListenerException;
import exceptions.listeners.StepListenerException;
import gameoflife.gui.toolbar.ToolBarComponent;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JPanel;
import listeners.ActionAnimationListener;
import listeners.StepListener;

/**
 *
 * @author group9
 * 
 * @param fl FlowLayout which contains the animation controller
 * @param animationControllerPanel Panel which contains fl
 * @param actionAnimationListeners List of action animation listeners
 * @param stepListeners List of step listeners
 * @param startButton Start button of the game
 * @param stopButton Stop button of the game
 */
public class AnimationController extends ToolBarComponent implements MouseListener, ActionAnimationListener {
    private FlowLayout fl = new FlowLayout(FlowLayout.LEFT, 5, 5);
    private JPanel animationControllerPanel = new JPanel(fl);
    private ArrayList<ActionAnimationListener> actionAnimationListeners = new ArrayList<ActionAnimationListener>();
    private ArrayList<StepListener> stepListeners = new ArrayList<StepListener>();
    
    private JButton startButton = new JButton("Start");
    private JButton stepButton = new JButton("Step");
    
    /****************************
    * PRIVATE FUNCTIONS
    ****************************/
    /**
     * Initializes the StepButton
     */
    private void initStepButton() {
        this.stepButton.setName("step");
        this.stepButton.addMouseListener(this);
        animationControllerPanel.add(this.stepButton);
    }
    
    /**
     * Initializes the StartButton
     */
    private void initStartButton() {
        this.startButton.setName("start");
        this.startButton.addMouseListener(this);
        animationControllerPanel.add(this.startButton);
    }
    
    /**
     * Fire a start event to animation controller
     */
    private void fireActionAnimationEvent(Actions action) {
        ActionAnimationEvent e = new ActionAnimationEvent(action);
        for(ActionAnimationListener l : this.actionAnimationListeners)
            l.actionAnimationOccured(e);
        
        if(!e.isTreated())
            throw new StartListenerException("Can't start/stop because the game is aleady lunched or button has no effects");
    }
    
    
    /**
     * Fire a step listener to animation controller
     * @param l StepListener
     */
    private void fireStepEvent() {
        StepEvent e = new StepEvent();
        for(StepListener l : this.stepListeners)
                l.stepOccured(e);
        
        if(!e.isTreated())
            throw new StepListenerException("Can't step because the button has no effects");
    }
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 1-arg AnimationController constructor
     * @param label Label of the AnimationController
     */
    public AnimationController(String label) {
        super(label);
        this.initStepButton();
        this.initStartButton();
        
        this.chargePanel(this.animationControllerPanel);
    }
  
    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * Change text of the animation controller button
     * @param e MouseEvent
     */
    public void mouseClicked(MouseEvent e) {
        JButton b = (JButton)e.getSource();
        if(b.getName().equals("start")) {
            if(b.getText().equals("Start")) {
                this.fireActionAnimationEvent(Actions.START);
            }
            else if(b.getText().equals("Stop")) {
                this.fireActionAnimationEvent(Actions.STOP);
            }
        }
        else if(b.getName().equals("step")) {
            this.fireActionAnimationEvent(Actions.STEP);
        }
    }

    public void mousePressed(MouseEvent e) {
        
    }

    public void mouseReleased(MouseEvent e) {
        
    }

    public void mouseEntered(MouseEvent e) {
        
    }

    public void mouseExited(MouseEvent e) {
        
    }
    
    /**
     * Add a start listener to animation controller
     * @param l ActionAnimationListener
     */
    public void addActionAnimationListener(ActionAnimationListener l) {
        if(l != null)
            this.actionAnimationListeners.add(l);
        else
            throw new IllegalArgumentException("ActionAnimationListener can not be null");
    }
    
    /**
     * Change the string of the button
     */
    public void actionAnimationOccured(ActionAnimationEvent e) {
        switch(e.getAction()) {
            case START:
                this.startButton.setText("Stop");
                break;
            case STOP:
                this.startButton.setText("Start");
                break;
            case STEP:
                this.startButton.setText("Start");
                break;
        }
    }
}
