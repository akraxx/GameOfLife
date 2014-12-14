/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapcontrollers;

import common.MapController;
import common.WorldModel;
import gameoflife.gui.grid.GridGUI;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
/**
 *
 * @author group9
 */
public class GUIMapController extends MapController {
    
    /****************************
    * PROTECTED FUNCTIONS
    ****************************/
    /**
     * Ask user to confirm action without change
     * @return true if user enter yes, else otherwise
     */
    @Override
    protected boolean confirmWithoutChange() {
        int n = JOptionPane.showConfirmDialog(
        null,
        "The world has been changed but not saved. Continue?",
        "New File",
        JOptionPane.YES_NO_OPTION);
        if(n == 0)
            return true;
        else
            return false;
    }

    /**
     * Open file
     * @return file
     */
    @Override
    protected File openFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("./" + MapController.MAPS_DIRECTORY));
        fileChooser.showOpenDialog(null);
        return fileChooser.getSelectedFile();
    }

    /**
     * Ask user to enter a file name
     * @return show an input dialog
     */
    @Override
    protected String askForFileName() {
        return JOptionPane.showInputDialog("Please enter file name:");
    }

    /**
     * Show a warning message
     * @param message Message to show
     */
    @Override
    protected void showWarningMessage(String message) {
        JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * The 2-args GUIMapController constructor
     * @param view View associated
     * @param m World model 
     */
    public GUIMapController(GridGUI view, WorldModel m) {
        super(view, m);
    }


    
}
