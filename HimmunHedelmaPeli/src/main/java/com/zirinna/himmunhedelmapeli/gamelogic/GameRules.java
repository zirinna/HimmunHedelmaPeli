/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zirinna.himmunhedelmapeli.gamelogic;

import com.zirinna.himmunhedelmapeli.gameobjects.FruitType;
import com.zirinna.himmunhedelmapeli.gameobjects.GameBoard;

/**
 * Contains the methods for checking matches of fruits.
 * @author zirinna
 */
public class GameRules {
    
    /**
     * Checks if the fruit on a tile on given coordinates x,y has a fruit of the same type
     * on it's right and left side.
     * @param x x-coordinate
     * @param y y-coordinate
     * @param board gameboard to check for matches on
     * @return True if a match was found, false otherwise.
     */   
    public static int checkForHorizontalMatch(int x, int y, GameBoard board) {
        //checks that x is on board (inside bounds)
        if (x < 0 || x >= board.getBoardSize()) {
            System.out.println("Tried to access a tile out of bounds: x=" + x);
            return 0;
        }
        if (board.getTile(x, y).getFruit() == null) {
            return 0;
        }
        
        FruitType fruit = board.getTile(x, y).getFruit().getFruitType();
        
        int i = 1;
        while (i < board.getBoardSize() - x) {
            if (board.getTile(x + i, y).getFruit() == null) {
                break;
            }
            if (!board.getTile(x + i, y).getFruit().getFruitType().equals(fruit)) {
                break;
            } else if (board.getTile(x + i, y).getFruit().getFruitType().equals(fruit)) {
                i++;
            }
        }
        if (i >= 3) {
            System.out.println("Found a row of " + i + " - Y:" + y + " X, from:" + x + " to " + (x + i));
        }
        return i;
    }

    /**
     * Checks if the fruit on a tile on given coordinates x,y has a fruit of the same type.
     * on top of it and under it.
     * @param x x-coordinate
     * @param y y-coordinate
     * @param board gameboard to check for matches on
     * @return true if a match was found, false otherwise
     */
    public static int checkForVerticalMatch(int x, int y, GameBoard board) {
        if (y < 0 || y >= board.getBoardSize()) { //checks that y is on board (inside bounds)
            //System.out.println("Tried to access a tile out of bounds: y=" + y);
            return 0;
        }
        if (board.getTile(x, y).getFruit() == null) {
            return 0;
        }
        
        FruitType fruit = board.getTile(x, y).getFruit().getFruitType();
        int i = 1;
        while (i < board.getBoardSize() - y) {
            if (board.getTile(x, y + i).getFruit() == null) {
                break;
            }
            if (!board.getTile(x, y + i).getFruit().getFruitType().equals(fruit)) {
                break;
            } else if (board.getTile(x, y + i).getFruit().getFruitType().equals(fruit)) {
                i++;
            }
        }
        //if (i>=3)System.out.println("Found a column of "+i+" - Y:"+y+" X, from:"+x+" to "+(x+i));
        return i;
    }
    
}
