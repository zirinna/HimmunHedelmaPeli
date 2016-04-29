/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zirinna.himmunhedelmapeli.gameobjects;

/**
 * This class is used to represent the gameboard, which holds the tiles.
 * @author zirinna
 */
public class GameBoard {
    
    private Tile[][] gameboard;
    
    /**
     * Constructor.
     * @param boardSize n in n x n sized board
     */
    public GameBoard(int boardSize) {
        this.gameboard = new Tile[boardSize][boardSize];
        generateBoard();
    }
    
    private void generateBoard() {
        for (int x = 0; x < this.gameboard.length; x++) {
            for (int y = 0; y < this.gameboard.length; y++) {
                gameboard[x][y] = new Tile(x, y);
            }
        }
    }
    
    /**
     * Check through all the tiles on the board,
     * and return true if any of them are highlighted.
     * @return True if at least one tile is Highlight. False otherwise.
     */
    public boolean hasHighlightTiles() {
        for (int x = 0; x < this.gameboard.length; x++) {
            for (int y = 0; y < this.gameboard[0].length; y++) {
                if (gameboard[x][y].isHighlight()) {
                    return true;
                }
            }
        }
        return false;
    }
            
    /**
     * Finds the first empty tile starting from lower left corner.
     * @return empty tile found, null if no empty tile was found.
     */
    public Tile findEmptyTile() {
        for (int x = 0; x < gameboard.length; x++) {
            for (int y = gameboard.length - 1; y >= 0; y--) {
                if (gameboard[x][y].getFruit() == null) {
                    return gameboard[x][y];
                }
            }
        }
        return null;
    }
    
    /**
     * When given coordinates that are on the gameboard, returns the tile on those coordinates.
     * @param x x-coordinate
     * @param y y-coordinate
     * @return Returns the tile on the board on given coordinates.
     */
    public Tile getTile(int x, int y) {
        return gameboard[x][y];
    }
    
    /**
     * Sets the tile on given coordinates to given tile.
     * @param x x-coordinate
     * @param y y-coordinate
     * @param tile Tile
     */
    public void setTile(int x, int y, Tile tile) {
        this.gameboard[x][y] = tile;
    }
    
    public int getBoardSize() {
        return this.gameboard.length;
    }
}