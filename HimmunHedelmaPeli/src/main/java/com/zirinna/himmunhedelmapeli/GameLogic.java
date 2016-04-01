/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zirinna.himmunhedelmapeli;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author zirinna
 */
public class GameLogic {
    private GameBoard board;
    
    public GameLogic() {
        this.board = new GameBoard(6);
        populateBoard(6);
    }
    
    private void populateBoard(int size) {
        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {
                this.board.getTile(x, y).setFruit(generateRandomFruit());
            }
        }
    
    }
    
    /**
     * generates a random fruit. Each fruit has the same chance of being generated
     * @return returns the randomly generated fruit
     */
    
    private Fruit generateRandomFruit() {
        Random rnd = new Random();
        int fruitNumber = rnd.nextInt(3);
        switch(fruitNumber) {
            case 0: return new Fruit(FruitType.APPLE);
            case 1: return new Fruit(FruitType.BANANA);
            case 2: return new Fruit(FruitType.KIWI);
            default: return new Fruit(FruitType.APPLE);
        }
    }
    
    /**
     * printing the gameboard to console
     */
    public void printBoard() {
        for(int i = 0; i < this.board.getBoardSize(); i++) {
            String rivi = "";
            for(int j = 0; j < this.board.getBoardSize(); j++) {
                rivi = rivi+this.board.getTile(i, j).toString()+",";
            }
            System.out.println(rivi);
        }
        
    }
    /**
     * tells all the tiles on the board to draw themselves on the graphicscontext
     * @param gc graphicscontext to draw the tiles on
     */
    public void drawEverything(GraphicsContext gc) {
        for(int x = 0; x < this.board.getBoardSize(); x++) {
            for(int y = 0; y < this.board.getBoardSize(); y++) {
                this.board.getTile(x, y).drawTile(gc);
            }
        }
    }
    
    /**
     * clears the tile
     * @param x Tile's x-coordinate
     * @param y Tile's y-coordinate
     */
    public void removeFruit(int x, int y) {
        board.getTile(x, y).clearTile();
    }
    
    
    
}
