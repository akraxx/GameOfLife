/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package common;

import events.ActionAnimationEvent;
import events.ActionAnimationEvent.Actions;
import events.GameSpeedChangeEvent;
import events.MapChangeEvent;
import events.StepEvent;
import exceptions.listeners.MapChangeListenerException;
import gameoflife.exceptions.engine.GameOfLifeEngineException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import listeners.GameSpeedChangeListener;
import listeners.MapChangeListener;
import listeners.ActionAnimationListener;
import listeners.StepListener;

/**
 *
 * @author group9
 * @param model attached model
 * @param timeToWait Time to wait between 2 generations
 * @param timer Timer
 * @param rule Rule of the game
 * @param startListeners List of start listeners
 * @param stopListeners List of stop listeners
 * @param stepListeners List of step listeners
 */
public abstract class Engine implements GameSpeedChangeListener, ActionAnimationListener, StepListener, MapChangeListener {
    private WorldModel model;
    private long timeToWait;
    private Timer timer;
    private Rule rule;
    private boolean running = false;
    private ArrayList<ActionAnimationListener> actionAnimationListeners = new ArrayList<ActionAnimationListener>();
    
    /****************************
    * PRIVATE CLASS
    ****************************/
    /**
     * Animation of the engine
     */
    class Tick extends TimerTask {
        private boolean step;
        
        /****************************
        * CONSTRUCTOR
        ****************************/
        /**
         * The 1-arg Tick constructor 
         * @param step If the tick needs to be done only one time
         */
        public Tick(boolean step) {
            this.step = step;
        }
        
        /**
         * The 0-arg Tick constructor
         */
        public Tick() {
            this(false);
        }
        
        /****************************
        * PUBLIC FUNCTIONS
        ****************************/
        /**
         * Run the animation
         */
        public void run() {
            if(!model.isFinished())
                toNextGen(model);
            
            if(!this.step && running && model != null)
                timer.schedule(new Tick(), timeToWait);
            else
                stopAnimation();
        }
    }
    
    /****************************
    * PRIVATE FUNCTIONS
    ****************************/
    /**
     * Fire a start event to animation controller
     */
    private void fireActionAnimationEvent(Actions action) {
        ActionAnimationEvent e = new ActionAnimationEvent(action);
        for(ActionAnimationListener l : this.actionAnimationListeners)
                l.actionAnimationOccured(e);
    }
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 2-args Engine constructor
     * @param timeToWait Time to wait between 2 generations
     * @param rule Rule associate with engine
     */
    public Engine(long timeToWait, Rule rule) {
        try {
            this.rule = rule;
            this.setTimeToWait(timeToWait);
            
            timer = new Timer();
        }
        catch(Exception e) {}
    }
    
    /****************************
    * GETTER
    ****************************/
    /**
     * @return the rule
     */
    public Rule getRule() {
        return rule;
    }
    
    /**
     * @return the running
     */
    public boolean isRunning() {
        return running;
    }
    
    /****************************
    * SETTER
    ****************************/
    /**
     * @param rule the rule to set
     * @throws GameOfLifeEngineException
     */
    public void setRule(Rule rule) throws GameOfLifeEngineException {
        if(rule != null)
            this.rule = rule;
        else
            throw new GameOfLifeEngineException("Rule can not be null");
    }
    
    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * Add a world model
     * @param m World model to be added
     * @throws GameOfLifeEngineException 
     */
    public void setModel(WorldModel m) throws GameOfLifeEngineException {
        if(m != null) {
            this.model = m;
        }
        else
            throw new GameOfLifeEngineException("Engine can't add this model", new NullPointerException());
    }
    
    /**
     * Upgrade the generation of the game
     * @param m World model to be modified
     */
    public abstract void toNextGen(WorldModel m);
    
    /**
     * Add a start listener to animation controller
     * @param l StartListener
     */
    public void addActionAnimationListener(ActionAnimationListener l) {
        if(l != null)
            this.actionAnimationListeners.add(l);
        else
            throw new IllegalArgumentException("StartListener can not be null");
    }
    
    /**
     * Stop the animation
     */
    public void stopAnimation() {
        this.running = false;
        this.timer.cancel();
        this.fireActionAnimationEvent(Actions.STOP);
    }
    
    /**
     * Start the animation
     */
    public void startAnimation() {
        this.timer.cancel();
        this.timer = new Timer();
        timer.schedule(new Tick(), timeToWait);
        this.running = true;
        this.fireActionAnimationEvent(Actions.START);
    }
    
    /**
     * Step the animation
     */
    public void stepAnimation() {
        this.timer.cancel();
        this.timer = new Timer();
        timer.schedule(new Tick(true), timeToWait);
        this.running = true;
        this.fireActionAnimationEvent(Actions.STEP);
    }
    
    /**
     * @return the tick
     */
    public long getTimeToWait() {
        return timeToWait;
    }

    /**
     * Set the tick
     * @param tick the tick to set
     */
    public void setTimeToWait(long tick) {
        this.timeToWait = tick;
    }
    
    /**
     * Change the speed of game
     * @param e Event
     */
    public void gameSpeedChangeOccured(GameSpeedChangeEvent e) {
        int fps = e.getSpeed();
        if (fps == 0) {
            stopAnimation();
        } else {
            this.setTimeToWait(1000 / fps);
        }
        e.setTreated(true);
    }
    
    public void actionAnimationOccured(ActionAnimationEvent e) {
        switch(e.getAction()) {
            case START:
                this.startAnimation();
                e.setTreated(true);
                break;
            case STOP:
                this.stopAnimation();
                e.setTreated(true);
                break;
            case STEP:
                this.stepAnimation();
                e.setTreated(true);
                break;
        }
    }
    
    public void stepOccured(StepEvent e) {
        this.stepAnimation();
        e.setTreated(true);
    }
    
    /**
     * Change the map
     * @param e Event
     */
    public void mapChangeOccured(MapChangeEvent e) {
        WorldModel m = e.getModel();
        if(m != null) {
            this.setModel(m);
            e.setTreated(true);
        }
        else
            throw new MapChangeListenerException("Can't change the map because it can not be null");
            
        
    }
}
