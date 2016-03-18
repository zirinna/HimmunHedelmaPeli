/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zirinna.himmunhedelmapeli;

/**
 *
 * @author zirinna
 */
public class GameBoard {
    
    private Fruit[][] gameboard;
    
    public GameBoard(int boardSize) {
        this.gameboard = new Fruit[boardSize][boardSize];
    }
    
    public Fruit getFruit(int x, int y) {
        return gameboard[x][y];
    }
    
    public void setFruit(int x, int y, Fruit fruit) {
        this.gameboard[x][y] = fruit;
    }
    
    public int getBoardSize() {
        return this.gameboard.length;
    }
}
