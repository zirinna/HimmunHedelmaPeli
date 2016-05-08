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
* on its right side and how many similar fruits there are.
* @param x x-coordinate of a given tile
* @param y y-coordinate of a given tile
* @param board gameboard to check for matches on
* @return the number of similar fruits
*/   
    public static int checkForHorizontalMatch(int x, int y, GameBoard board) {
        //checks that x is on board (inside bounds)
        if (x < 0 || x >= board.getBoardSize()) {
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
        return i;
    }

    /**
     * Checks if the fruit on a tile on given coordinates x,y has a fruit of the same type.
     * on top of it and how many similar fruits there are.
     * @param x x-coordinate of a given tile
     * @param y y-coordinate of a given tile
     * @param board gameboard to check for matches on
     * @return the numbers of similar fruits
     */
    public static int checkForVerticalMatch(int x, int y, GameBoard board) {
        if (y < 0 || y >= board.getBoardSize()) { //checks that y is on board (inside bounds)
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
        return i;
    }
    
}
