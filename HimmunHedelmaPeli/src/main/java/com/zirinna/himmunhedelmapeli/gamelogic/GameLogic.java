/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zirinna.himmunhedelmapeli.gamelogic;

import com.zirinna.himmunhedelmapeli.gameobjects.GameBoard;
import com.zirinna.himmunhedelmapeli.gameobjects.Fruit;
import com.zirinna.himmunhedelmapeli.gameobjects.FruitType;
import com.zirinna.himmunhedelmapeli.gameobjects.Tile;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;

/**
 * This class contains several methods related to the actual logic of the game
 * @author zirinna
 */
public class GameLogic {
    private GameBoard board;
    
    public GameLogic() {
        this.board = new GameBoard(6);
        populateBoard(6);
    }
    
    private void populateBoard(int size) {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                this.board.getTile(x, y).setFruit(generateRandomFruit());
            }
        }
    
    }
    
    /**
     * generates a random fruit. Each fruit has roughly the same chance of being generated
     * @return returns the randomly generated fruit
     */
    
    public Fruit generateRandomFruit() {
        Random rnd = new Random();
        int fruitNumber = rnd.nextInt(5);
        switch (fruitNumber) {
            case 0: return new Fruit(FruitType.APPLE);
            case 1: return new Fruit(FruitType.BANANA);
            case 2: return new Fruit(FruitType.KIWI);
            case 3: return new Fruit(FruitType.ORANGE);
            case 4: return new Fruit(FruitType.PANDARIN);
            default: return new Fruit(FruitType.APPLE);
        }
    }
    

    /**
     * tells all the tiles on the board to draw themselves on the graphicscontext
     * @param gc graphicscontext to draw the tiles on
     */
    public void drawEverything(GraphicsContext gc) {
        for (int x = 0; x < this.board.getBoardSize(); x++) {
            for (int y = 0; y < this.board.getBoardSize(); y++) {
                this.board.getTile(x, y).drawTile(gc);
            }
        }
    }
    
    public void mouseClickAtCoordinates(double xCoordinate, double yCoordinate) {
        double fruitWidth = board.getTile(0, 0).getFruit().getFruitImage().getWidth();
        double fruitHeight = board.getTile(0, 0).getFruit().getFruitImage().getHeight();
        int xTile = (int) (xCoordinate / fruitWidth);
        int yTile = (int) (yCoordinate / fruitHeight);
        removeFruit(xTile, yTile);
    }
    
    /**
     * clears the tile
     * @param x Tile's x-coordinate
     * @param y Tile's y-coordinate
     */
    public void removeFruit(int x, int y) {
        board.getTile(x, y).clearTile();
    }
    
    public GameBoard getGameBoard() {
        return board;
    }
    
    /**
     * finds the first empty tile starting from lower left corner 
     * @return empty tile found, null if no empty tile was found
     */
    public Tile findEmptyTile() {
        for (int x = 0; x < this.board.getBoardSize(); x++) {
            for (int y = this.board.getBoardSize() - 1; y >= 0; y--) {
                if (this.board.getTile(x, y).getFruit() == null) {
                    return this.board.getTile(x, y);
                }
            }
        }
        return null;
    }
    
    /**
     * goes through the gameboard, searches for matching fruits, removes them and
     * populates the empty tiles
     */
    public void updateBoard() {
        this.removeMatchingFruits();
        while (findEmptyTile() != null) {
            System.out.println("empty tile found at:" + findEmptyTile().getXcoordinate() + "/" + findEmptyTile().getYcoordinate());
            dropFruitDown(findEmptyTile().getXcoordinate(), findEmptyTile().getYcoordinate());
        }
        
    }
    
    /**
     * Gets the fruit above the given spot, drops it down one tile and
     * clears the tile the fruit came from. If there is no empty fruit on the top
     * row, generates a random fruit.
     * @param emptySpotX the x-coordinate of the empty tile
     * @param emptySpotY the y-coordinate of the empty tile
     */
    
    public void dropFruitDown(int emptySpotX, int emptySpotY) {
        if (emptySpotX < 0 || emptySpotX >=  this.board.getBoardSize() || emptySpotY < 0 || emptySpotY >= this.board.getBoardSize()) {
            System.out.println("Tried to access a tile out of bounds: " + emptySpotY + "/" + emptySpotY);
            return;
        }
        for (int y = emptySpotY - 1; y >= 0; y--) {
            if (this.board.getTile(emptySpotX, y).getFruit() != null) {
                System.out.println("Found a fruit to drop down at " + emptySpotX + "/" + y);
                this.board.getTile(emptySpotX, emptySpotY).setFruit(this.board.getTile(emptySpotX, y).getFruit());
                this.board.getTile(emptySpotX, y).clearTile();
                break;
            }
        }
        if (this.board.getTile(emptySpotX, 0).getFruit() == null) {
            this.board.getTile(emptySpotX, 0).setFruit(generateRandomFruit());
        }
    }
    
    /**
     * goes through the gameboard and searches for matching fruits, removes the matching
     * fruits
     */
    public void removeMatchingFruits() {
        for (int x = 0; x < this.board.getBoardSize(); x++) {
            for (int y = 0; y < this.board.getBoardSize(); y++) {
                boolean horiMatch = checkForHorizontalMatch(x, y);
                boolean vertiMatch = checkForVerticalMatch(x, y);
                if (horiMatch) {
                    this.removeFruit(x - 1, y);
                    this.removeFruit(x, y);
                    this.removeFruit(x + 1, y);
                }
                if (vertiMatch) {
                    this.removeFruit(x, y - 1);
                    this.removeFruit(x, y);
                    this.removeFruit(x, y + 1);
                    
                }
            }
        }       
    }
    
    /**
     * checks if the fruit on a tile on given coordinates x,y has a fruit of the same type
     * on it's right and left side
     * @param x x-coordinate
     * @param y y-coordinate
     * @return true if a match was found, false otherwise
     */
    public boolean checkForHorizontalMatch(int x, int y) {
        if (x <= 0 || x >= this.board.getBoardSize() - 1) {
            return false;
        }
        if (this.board.getTile(x, y).getFruit() == null || this.board.getTile(x - 1, y).getFruit() == null || this.board.getTile(x + 1, y).getFruit() == null) {
            return false;
        }
        FruitType fruit = this.board.getTile(x, y).getFruit().getFruitType();
        
        if (this.board.getTile(x - 1, y).getFruit().getFruitType().equals(fruit) && this.board.getTile(x + 1, y).getFruit().getFruitType().equals(fruit)) {
            return true;
        }
        return false;   
    }
    
    /**
     * checks if the fruit on a tile on given coordinates x,y has a fruit of the same type
     * on top of it and under it
     * @param x x-coordinate
     * @param y y-coordinate
     * @return true if a match was found, false otherwise
     */
    public boolean checkForVerticalMatch(int x, int y) {
        if (y <= 0 || y >= this.board.getBoardSize() - 1) {
            return false;
        }
        if (this.board.getTile(x, y).getFruit() == null || this.board.getTile(x, y - 1).getFruit() == null || this.board.getTile(x, y + 1).getFruit() == null) {
            return false;
        }
        FruitType fruit = this.board.getTile(x, y).getFruit().getFruitType();
        if (this.board.getTile(x, y - 1).getFruit().getFruitType().equals(fruit) && this.board.getTile(x, y + 1).getFruit().getFruitType().equals(fruit)) {
            return true;
        }
        return false;
    }
    
    
}
