/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zirinna.himmunhedelmapeli.gameobjects;

/**
 *
 * @author zirinna
 */
public class GameBoard {
    
    private Tile[][] gameboard;
    
    public GameBoard(int boardSize) {
        this.gameboard = new Tile[boardSize][boardSize];
        generateBoard();
    }
    
    private void generateBoard() {
        for(int x = 0; x < this.gameboard.length; x++) {
            for(int y = 0; y < this.gameboard.length; y++) {
                gameboard[x][y] = new Tile(x, y);
            }
        }
    }
    
    public Tile getTile(int x, int y) {
        return gameboard[x][y];
    }
    
    public void setTile(int x, int y, Tile tile) {
        this.gameboard[x][y] = tile;
    }
    
    public int getBoardSize() {
        return this.gameboard.length;
    }
}
