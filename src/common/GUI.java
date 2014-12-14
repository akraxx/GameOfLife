/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import events.QuitGameEvent;
import events.TitleChangeEvent;
import exceptions.listeners.QuitGameListenerException;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JPanel;
import listeners.QuitGameListener;
import listeners.TitleChangeListener;

/**
 * 
 * @author group9
 * 
 * @param frame JFrame
 */
public abstract class GUI implements QuitGameListener, TitleChangeListener {
    protected JPanel content = new JPanel(new BorderLayout());
    protected String title = "GUI";
    private ArrayList<QuitGameListener> quitGameListeners = new ArrayList<QuitGameListener>();
    
    /**
     * Fire an event to animation controller
     */
    protected void fireQuitGameEvent() {
        QuitGameEvent e = new QuitGameEvent();
        for(QuitGameListener l : this.quitGameListeners)
            l.quitGameOccured(e);
        
        if(!e.isTreated())
            throw new QuitGameListenerException("Can't quit the game.");
    }
    
    /**
     * The 0-arg GUI constructor
     */
    public GUI() {
        
    }
    
    /**
     * Quit the game 
     * @param e Quit game event
     */
    public abstract void quitGameOccured(QuitGameEvent e);
    
    /**
     * Add a listener
     * @param l MapChangeListener
     */
    public void addQuitGameListener(QuitGameListener l) {
        if(l != null)
            this.quitGameListeners.add(l);
        else
            throw new IllegalArgumentException("QuitGameListener can not be null");
    }
    
    public abstract void titleChangeOccured(TitleChangeEvent e);
   
}
