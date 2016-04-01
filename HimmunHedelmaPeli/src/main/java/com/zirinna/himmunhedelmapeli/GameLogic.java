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
    
    public void drawEverything(GraphicsContext gc, GameLogic game) {
        for(int x = 0; x < this.board.getBoardSize(); x++) {
            for(int y = 0; y < this.board.getBoardSize(); y++) {
                this.board.getTile(x, y).drawTile(gc);
            }
        }
    }
    
    // "removes" the fruit, which means that the fruittype is set to "REM"...
    public void removeFruit(int x, int y) {
        Fruit fruit = new Fruit(FruitType.REM);
        board.getTile(x, y).clearTile();
    }
    
    
    
}
