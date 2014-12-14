/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import common.MapCreator;
import common.Rule;
import common.WorldModel;
import events.QuitGameEvent;
import exceptions.listeners.ListenerException;
import gameoflife.engines.GameOfLifeEngine;
import gameoflife.gui.GameOfLifeFramedGUI;
import gameoflife.maps.GameOfLifeBlinker;
import gameoflife.maps.GameOfLifeBlock;
import gameoflife.maps.GameOfLifeRandom;
import gameoflife.rules.MooreGameOfLifeRule;
import gameoflife.gui.grid.GridGUI;
import gameoflife.gui.menubar.menus.Menu;
import gameoflife.gui.menubar.MenuBarGUI;
import gameoflife.gui.menubar.menus.checkboxitems.view.ShowGenerationCheckBoxMenuItem;
import gameoflife.gui.menubar.menus.checkboxitems.view.ShowToolBarCheckBoxMenuItem;
import gameoflife.gui.menubar.menus.items.map.NewMapMenuItem;
import gameoflife.gui.menubar.menus.items.map.OpenMapMenuItem;
import gameoflife.gui.menubar.menus.items.map.QuitMapMenuItem;
import gameoflife.gui.menubar.menus.items.map.SaveMapMenuItem;
import gameoflife.gui.toolbar.ToolBarGUI;
import gameoflife.gui.toolbar.component.AnimationController;
import gameoflife.gui.toolbar.component.CellSizeSliderController;
import gameoflife.gui.toolbar.component.CellStateColorChooser;
import gameoflife.gui.toolbar.component.GameSpeedSliderController;
import gameoflife.gui.toolbar.component.GridDisplaying;
import gameoflife.maps.GameOfLifeGlider;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import listeners.QuitGameListener;
import mapcontrollers.GUIMapController;

/**
 *
 * @author group9
 * 
 * @param DEFAULT_TIMETOWAIT_VALUE Default time to wait
 * @param DEFAULT_FPS_VALUE Default fps (frame per seconds) value
 * @param DEFAULT_CELL_SIZE_VALUE Default cell size value
 * @param DEFAULT_MAP_HEIGHT_VALUE Default map height value
 * @param DEFAULT_MAP_WIDTH_VALUE Default map width value
 */
public class GameOfLifeFrameLauncher implements QuitGameListener {
    
    private void run() {
        try {
            /****************************
             * INIT RULES OF THE GAME
             ****************************/
            Rule r = new MooreGameOfLifeRule();

            /****************************
             * INIT GRID
             ****************************/
            GridGUI gridGUI = new GridGUI(r.getStates(), GameOfLifeDefaultValues.DEFAULT_CELL_SIZE_VALUE);
            
            /****************************
             * INIT MAPS
             ****************************/
            ArrayList<MapCreator> maps = new  ArrayList<MapCreator>();
            maps.add(new GameOfLifeRandom("Random", gridGUI, GameOfLifeDefaultValues.DEFAULT_MAP_HEIGHT_VALUE, GameOfLifeDefaultValues.DEFAULT_MAP_WIDTH_VALUE));
            maps.add(new GameOfLifeBlinker("Blinker", gridGUI));
            maps.add(new GameOfLifeGlider("Glider", gridGUI));
            maps.add(new GameOfLifeBlock("Block", gridGUI));
            

            /****************************
             * INIT MENUBAR AND ITS COMPONENTS
             ****************************/
            MenuBarGUI menuBarGUI = new MenuBarGUI();
            Menu fileMenu = new Menu("File");
            NewMapMenuItem newMenuItem = new NewMapMenuItem("New", 'N');
            OpenMapMenuItem openMenuItem = new OpenMapMenuItem("Open", 'O');
            SaveMapMenuItem saveMenuItem = new SaveMapMenuItem("Save", 'S');
            QuitMapMenuItem quitMenuItem = new QuitMapMenuItem("Quit", 'Q');
            
            Menu viewMenu = new Menu("View");
            ShowToolBarCheckBoxMenuItem showToolBarCheckBoxMenuItem = new ShowToolBarCheckBoxMenuItem("Show toolbar", true);
            ShowGenerationCheckBoxMenuItem showGenerationCheckBoxMenuItem = new ShowGenerationCheckBoxMenuItem("Show generation number", true);
            
            menuBarGUI.addMenuBarComponent(fileMenu);
            fileMenu.addItem(newMenuItem.getMenuItem());
            fileMenu.addItem(openMenuItem.getMenuItem());
            fileMenu.addItem(saveMenuItem.getMenuItem());
            fileMenu.addSeparator();
            fileMenu.addItem(quitMenuItem.getMenuItem());
            
            menuBarGUI.addMenuBarComponent(viewMenu);
            viewMenu.addItem(showToolBarCheckBoxMenuItem.getCheckBoxMenuItem());
            viewMenu.addItem(showGenerationCheckBoxMenuItem.getCheckBoxMenuItem());
            
            
            
            
            
            /****************************
             * INIT TOOLBAR AND ITS COMPONENTS
             ****************************/
            ToolBarGUI toolBarGUI = new ToolBarGUI();

            /* ANIMATION CONTROLLER */
            AnimationController animationController = new AnimationController("Animation Controller");
            toolBarGUI.addComponent(animationController);

            /* GAME SPEED SLIDER CONTROLLER */
            GameSpeedSliderController gameSpeedSliderController = new GameSpeedSliderController("Game speed (gen. per sec.)", GameOfLifeDefaultValues.DEFAULT_FPS_VALUE);
            toolBarGUI.addComponent(gameSpeedSliderController);

            /* CELL SIZE SLIDER CONTROLLER */
            CellSizeSliderController cellSizeSliderController = new CellSizeSliderController("Cell size (pixels)", GameOfLifeDefaultValues.DEFAULT_CELL_SIZE_VALUE); 
            toolBarGUI.addComponent(cellSizeSliderController);

            /* CELL STATE COLOR CHOOSER */
            CellStateColorChooser cellStateColorChooser = new CellStateColorChooser("Cell state", gridGUI.getColors()); 
            toolBarGUI.addComponent(cellStateColorChooser);

            /* GRID DISPLAYING */
            GridDisplaying gridDisplaying = new GridDisplaying("Grid displaying");
            toolBarGUI.addComponent(gridDisplaying);
            
            /****************************
             * INIT MODEL
             ****************************/
            WorldModel model = maps.get(0).createMap();

            model.addView(gridGUI);

            /****************************
             * INIT ENGINE
             ****************************/
            GameOfLifeEngine e = new GameOfLifeEngine(GameOfLifeDefaultValues.DEFAULT_TIMETOWAIT_VALUE, r);

            e.setModel(model);

            /****************************
             * INIT FRAME
             ****************************/
            GameOfLifeFramedGUI GUIframe = new GameOfLifeFramedGUI(toolBarGUI, gridGUI, menuBarGUI, "Game Of Life");
            
            /****************************
             * INIT MAP CONTROLLER
             ****************************/
            GUIMapController mapController = new GUIMapController(gridGUI, model);
            
            /****************************
             * INIT LISTENERS
             ****************************/
            /* GAME SPEED CONTROLLER */
            gameSpeedSliderController.addGameSpeedChangeListener(e);

            /* ACTION ANIMATION */
            animationController.addActionAnimationListener(e);
            e.addActionAnimationListener(animationController); 

            /* CELL SIZE CONTROLLER */
            cellSizeSliderController.addCellSizeChangeListener(gridGUI);

            /* COLOR CHOOSER */
            cellStateColorChooser.addCellStateColorChangeListener(gridGUI);
            cellStateColorChooser.addCellStateColorChangeListener(cellStateColorChooser);
            
            /* GRID DISPLAYING */
            gridDisplaying.addComponentDisplayingListener(gridGUI); 
            
            /* CHANGE CELL STATE */
            gridGUI.addCellStateChangeListener(model); 
            
            
            /* MAP ACTIONS */
            mapController.addMapChangeListener(mapController);
            mapController.addMapChangeListener(e);
            mapController.addMapChangeListener(gridGUI);
            mapController.addTitleChangeListener(GUIframe);
            mapController.addQuitGameListener(GUIframe);
            mapController.addActionAnimationListener(e);

                /* NEW */
                newMenuItem.addActionMapListener(mapController);

                /* OPEN */
                openMenuItem.addActionMapListener(mapController);

                /* SAVE */
                saveMenuItem.addActionMapListener(mapController);

                /* QUIT */
                quitMenuItem.addActionMapListener(mapController);
                
            /* VIEW ACTION */ 
            showGenerationCheckBoxMenuItem.addComponentDisplayingListener(gridGUI);
            showToolBarCheckBoxMenuItem.addComponentDisplayingListener(toolBarGUI);
            
            /* QUIT PROGRAM */
            GUIframe.addQuitGameListener(this);
        
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
        }
        
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
          public void uncaughtException(Thread t, Throwable ex) {
            if(ex instanceof ListenerException) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
            else {
                  System.err.println("Err :" + ex.getMessage());
              }
          }
        });
    }
    /****************************
    * CONSTRUCTOR
    ****************************/
    public GameOfLifeFrameLauncher() {
        this.run();
    }
    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * Initialize the program
     * @param args Arguments of the program
     */
    public static void main(String[] args) {
        GameOfLifeFrameLauncher gameOfLifeFrameLauncher = new GameOfLifeFrameLauncher();
    }

    public void quitGameOccured(QuitGameEvent e) {
            System.exit(0);
    }
}
