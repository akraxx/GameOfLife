/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import events.ActionAnimationEvent;
import events.ActionAnimationEvent.Actions;
import events.ActionMapEvent;
import events.MapChangeEvent;
import events.QuitGameEvent;
import events.TitleChangeEvent;
import exceptions.listeners.MapChangeListenerException;
import exceptions.listeners.QuitGameListenerException;
import gameoflife.GameOfLifeDefaultValues;
import gameoflife.gui.grid.GridGUI;
import gameoflife.models.ArrayModel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import listeners.ActionAnimationListener;
import listeners.ActionMapListener;
import listeners.MapChangeListener;
import listeners.QuitGameListener;
import listeners.TitleChangeListener;

/**
 *
 * @author group9
 */
public abstract class MapController implements ActionMapListener, MapChangeListener {
    public final static String MAPS_DIRECTORY = "maps";
    public final static String MAP_EXTENSION = "map";
    public final static String DEFAULT_MAP_NAME = "untitled";
    private String currentFileName = null;
    private ArrayList<MapChangeListener> mapChangeListeners = new ArrayList<MapChangeListener>();
    private ArrayList<TitleChangeListener> titleChangeListeners = new ArrayList<TitleChangeListener>();
    private ArrayList<QuitGameListener> quitGameListeners = new ArrayList<QuitGameListener>();
    private ArrayList<ActionAnimationListener> actionAnimationListeners = new ArrayList<ActionAnimationListener>();
    
    private GridGUI view = null;
    private WorldModel model = null;
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 2-args MapController constructor
     * @param view View associated with the map controller
     * @param m World Model
     */
    public MapController(GridGUI view, WorldModel m) {
        this.view = view;
        this.model = m;

        File dir = new File(MapController.MAPS_DIRECTORY);
        
        if(!dir.exists() && !(new File(MapController.MAPS_DIRECTORY)).mkdir())
            throw new RuntimeException("Can't create the folder : " + MapController.MAPS_DIRECTORY);
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
    
    /**
     * Fire an event to animation controller
     */
    private void fireMapChangeEvent(WorldModel m) {
        MapChangeEvent e = new MapChangeEvent(m);
        for(MapChangeListener l : this.mapChangeListeners)
            l.mapChangeOccured(e);
        
        if(!e.isTreated())
            throw new MapChangeListenerException("Can't change the map because the chooser has no effects");
    }
    
    /**
     * Fire an event to animation controller
     */
    private void fireTitleChangeEvent() {
        String title;
        if(this.currentFileName == null)
            title = MapController.DEFAULT_MAP_NAME + "." + MapController.MAP_EXTENSION;
        else
            title = this.currentFileName;
        
        TitleChangeEvent e = new TitleChangeEvent(title);
        for(TitleChangeListener l : this.titleChangeListeners)
            l.titleChangeOccured(e);
    }
    
    /**
     * Fire an event to animation controller
     */
    private void fireQuitGameEvent() {
        QuitGameEvent e = new QuitGameEvent();
        for(QuitGameListener l : this.quitGameListeners)
            l.quitGameOccured(e);
        
        if(!e.isTreated())
            throw new QuitGameListenerException("Can't quit the game.");
    }

    /**
     * Determine whether a card can be changed or not
     * @return true if map can be changed, false otherwise
     */
    private boolean mapCanBeChanged() {
        boolean r = false;
        this.fireActionAnimationEvent(Actions.STOP);
        if(this.model.hasChanged()) {
            if(this.confirmWithoutChange())
                r = true;
        }
        else
            r = true;
        
        return r;
    }
    
    /**
     * Change model
     * @param m The model
     */
    private void changeModel(WorldModel m) {
        this.model = m;
        /* CHANGE CELL STATE */
        this.view.addCellStateChangeListener(m); 
    }
    
    /****************************
    * PROTECTED FUNCTIONS
    ****************************/
    protected abstract boolean confirmWithoutChange();
    
    protected abstract File openFile();
    
    protected abstract String askForFileName();
    
    protected abstract void showWarningMessage(String message);
    
    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * Save the map
     */
    public void saveMap() {
        boolean canSave = false;
        if(getCurrentFileName() == null) {
            String name = null;
            if((name = this.askForFileName()) != null) {
                this.setCurrentFileName(name + "." + MapController.MAP_EXTENSION);
                canSave = true;
            }
        }
        else
            canSave = true;
        
        if(canSave) {
            try{
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream("./" + MapController.MAPS_DIRECTORY + "/" + this.getCurrentFileName()));
                objectOutputStream.writeObject(this.model);
                this.model.setChanged(false);
            }
            catch(IOException ioException){
                this.showWarningMessage("The program was unable to save your map.");
            }
        }
    }
    
    /**
     * Opens a map
     */
    public void openMap() {
        if(this.mapCanBeChanged()) {
            File map = this.openFile();
            if(map != null) {
                try{
                    ObjectInputStream objectInputStream = new ObjectInputStream(
                    new FileInputStream(map));
                    WorldModel mod = (ArrayModel) objectInputStream.readObject();
                    mod.addView(view);
                    mod.setChanged(false);
                    this.fireMapChangeEvent(mod);
                    this.setCurrentFileName(map.getName());
                }
                catch(IOException ioException){
                    this.showWarningMessage("The program was unable to open your map.");
                }
                catch(ClassNotFoundException classNotFoundException){
                    this.showWarningMessage("The program was unable to load your map.");
                }
            }
        }
    }
    
    /**
     * Creates a map
     */
    public void newMap() { 
        if(this.mapCanBeChanged()) {
            WorldModel m = new ArrayModel(GameOfLifeDefaultValues.DEFAULT_MAP_HEIGHT_VALUE, GameOfLifeDefaultValues.DEFAULT_MAP_WIDTH_VALUE);
            m.addView(this.view);
            this.fireMapChangeEvent(m);
            this.setCurrentFileName(null);
        }
    }
    
    /**
     * Quit the game 
     */
    public void quitGame() {
        if(this.mapCanBeChanged())
            this.fireQuitGameEvent();
    }
    
    /**
     * Add a listener
     * @param l MapChangeListener
     */
    public void addMapChangeListener(MapChangeListener l) {
        if(l != null)
            this.mapChangeListeners.add(l);
        else
            throw new IllegalArgumentException("MapChangeListener can not be null");
    }
    
    /**
     * Add a listener
     * @param l TitleChangeListener
     */
    public void addTitleChangeListener(TitleChangeListener l) {
        if(l != null) {
            this.titleChangeListeners.add(l);
            this.fireTitleChangeEvent();
        }
        else
            throw new IllegalArgumentException("TitleChangeListener can not be null");
    }
    
    /**
     * Add a listener
     * @param l QuitGameListener
     */
    public void addQuitGameListener(QuitGameListener l) {
        if(l != null)
            this.quitGameListeners.add(l);
        else
            throw new IllegalArgumentException("QuitGameListener can not be null");
    }
    
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
     * Add a listener
     * @param e ActionMapEvent
     */
    public void ActionMapOccured(ActionMapEvent e) {
        switch(e.getAction()) {
            case NEW:
                this.newMap();
                e.setTreated(true);
                break;
            case SAVE:
                this.saveMap();
                e.setTreated(true);
                break;
            case OPEN:
                this.openMap();
                e.setTreated(true);
                break;
            case QUIT:
                this.quitGame();
                e.setTreated(true);
                break;
        }
    }
    
    /**
     * Add a listener
     * @param e MapChangeEvent
     */
    public void mapChangeOccured(MapChangeEvent e) {
        WorldModel m = e.getModel();
        if(m != null) {
            this.changeModel(m);
            this.setCurrentFileName(null);
        }
        e.setTreated(true);
    }

    /**
     * @return the currentFileName
     */
    public String getCurrentFileName() {
        return currentFileName;
    }

    /**
     * @param currentFileName the currentFileName to set
     */
    public void setCurrentFileName(String currentFileName) {
        this.currentFileName = currentFileName;
        this.fireTitleChangeEvent();
    }
}
