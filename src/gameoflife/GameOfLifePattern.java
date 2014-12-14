/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import gameoflife.models.Cell;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author group9
 * 
 * @param pattern Pattern
 * @param height Height of the grid
 * @param width Width of the grid
 * @param rows Rows to convert
 * @param aliveSymbol Symbol of the alive state
 * @param deadSymbol Symbol of the dead state
 */
public class GameOfLifePattern {
    private Cell[][] pattern = null;
    private int height, width;
    private ArrayList<String> rows = new ArrayList<String>();
    private final String aliveSymbol = "*";
    private final String deadSymbol = "-";
    
    /****************************
    * CONSTRUCTOR
    ****************************/
    /**
     * Construct the object
     */
    public GameOfLifePattern() {
        
    }
    
    /****************************
    * GETTER
    ****************************/
    /**
     * @return the pattern
     */
    public Cell[][] getPattern() {
        return pattern;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the aliveSymbol
     */
    public String getAliveSymbol() {
        return aliveSymbol;
    }

    /**
     * @return the deadSymbol
     */
    public String getDeadSymbol() {
        return deadSymbol;
    }
    
    /****************************
    * PUBLIC FUNCTIONS
    ****************************/
    /**
     * Add row to the list
     * @param row Row to be added
     */
    public void addRow(String row) {
        if(row != null)
            this.rows.add(row);
    }
    
    /**
     * Create a random pattern
     * @param width Width of future pattern
     * @param height Height of future pattern 
     */
    public void randomPattern(int width, int height) {
        Random r = new Random();
        
        for (int i = 0; i < height; i++) {
            String s = new String();
            for (int j = 0; j < width; j++) {
                if (j != 0){
                    s += " ";
                }
                
                if(r.nextInt(2) == 0)
                    s += this.deadSymbol;
                else 
                    s += this.aliveSymbol;
            }
            this.addRow(s);
        }        
    }
    
    /**
     * Parse string rows and put the datas into an array
     */
    public void convertToPattern() {
        if(this.rows != null && this.rows.size() > 0) {
            String[] states = this.rows.get(0).split(" ");
            this.width = states.length;
            this.height = this.rows.size();

            this.pattern = new Cell[this.getHeight()][this.getWidth()];
            for (int i = 0; i < this.getHeight(); i++) {
                states = this.rows.get(i).split(" ");
                for (int j = 0; j < this.getWidth(); j++) {
                    if(states[j] != null && !states[j].equals(this.aliveSymbol))
                        pattern[i][j] = new Cell(GameOfLifeCellState.DEAD);
                    else
                        pattern[i][j] = new Cell(GameOfLifeCellState.ALIVE);
                }
            }
        }
    }

}
