/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zirinna.himmunhedelmapeli.gamelogic;

import com.zirinna.himmunhedelmapeli.gameobjects.FruitType;
import com.zirinna.himmunhedelmapeli.gameobjects.GameBoard;

/**
 *
 * @author zirinna
 */
public class GameRules {
    
    /**
     * checks if the fruit on a tile on given coordinates x,y has a fruit of the same type
     * on it's right and left side
     * @param x x-coordinate
     * @param y y-coordinate
     * @param board gameboard to check for matches on
     * @return true if a match was found, false otherwise
     */
    
    public static boolean checkForHorizontalMatch(int x, int y, GameBoard board) {
        if (x <= 0 || x >= board.getBoardSize() - 1) {
            return false;
        }
        if (board.getTile(x, y).getFruit() == null || board.getTile(x - 1, y).getFruit() == null || board.getTile(x + 1, y).getFruit() == null) {
            return false;
        }
        FruitType fruit = board.getTile(x, y).getFruit().getFruitType();
        
        if (board.getTile(x - 1, y).getFruit().getFruitType().equals(fruit) && board.getTile(x + 1, y).getFruit().getFruitType().equals(fruit)) {
            return true;
        }
        return false;   
    }
    /**
     * checks if the fruit on a tile on given coordinates x,y has a fruit of the same type
     * on top of it and under it
     * @param x x-coordinate
     * @param y y-coordinate
     * @param board gameboard to check for matches on
     * @return true if a match was found, false otherwise
     */
    public static boolean checkForVerticalMatch(int x, int y, GameBoard board) {
        if (y <= 0 || y >= board.getBoardSize() - 1) {
            return false;
        }
        if (board.getTile(x, y).getFruit() == null || board.getTile(x, y - 1).getFruit() == null || board.getTile(x, y + 1).getFruit() == null) {
            return false;
        }
        FruitType fruit = board.getTile(x, y).getFruit().getFruitType();
        if (board.getTile(x, y - 1).getFruit().getFruitType().equals(fruit) && board.getTile(x, y + 1).getFruit().getFruitType().equals(fruit)) {
            return true;
        }
        return false;
    }
    
}
