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
import com.zirinna.himmunhedelmapeli.userinterface.UserInterface;
import java.util.Random;
import javafx.scene.canvas.GraphicsContext;

/**
 * This class contains several methods related to the actual logic of the game.
 * @author zirinna
 */
public class GameLogic {
    private GameBoard board;
    private int moves = 20;
    private int score = 0;
    
    /**
     * Constructor for GameLogic.
     */
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
     * Generates a random fruit. Each fruit has roughly the same chance of being generated.
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
     * Handles the mouse click on given coordinates.
     * @param xCoordinate x-coordinate of the mouse click.
     * @param yCoordinate y-coordinate of the mouse click.
     */
    
    public void mouseClickAtCoordinates(double xCoordinate, double yCoordinate) {
        double fruitWidth = board.getTile(0, 0).getFruit().getFruitImage().getWidth();
        double fruitHeight = board.getTile(0, 0).getFruit().getFruitImage().getHeight();
        int xTile = (int) (xCoordinate / fruitWidth);
        int yTile = (int) (yCoordinate / fruitHeight);
        if (xTile >= 0 && xTile < board.getBoardSize() && yTile >= 0 && yTile < board.getBoardSize()) {
            //highlightTile(xTile, yTile);
            removeFruit(xTile, yTile);
            moves--;
        }
    }
    
    /**
     * Clears the tile.
     * @param x Tile's x-coordinate
     * @param y Tile's y-coordinate
     */
    public void removeFruit(int x, int y) {
        board.getTile(x, y).clearTile();        
    }
    
    private void highlightTile(int x, int y) {
        board.getTile(x, y).highlightTile();
    }
    
    public GameBoard getGameBoard() {
        return board;
    }
    
    /**
     * Finds the first empty tile starting from lower left corner.
     * @return empty tile found, null if no empty tile was found.
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
     * Goes through the gameboard, searches for matching fruits, removes them and
     * populates the empty tiles.
     * @param time timer
     */
    public void updateBoard(double time) {
        this.removeMatchingFruits();
        while (findEmptyTile() != null) {
            //System.out.println("empty tile found at:" + findEmptyTile().getXcoordinate() + "/" + findEmptyTile().getYcoordinate());
            dropFruitDown(findEmptyTile().getXcoordinate(), findEmptyTile().getYcoordinate());
        }
        tickDownHighlighttimer(time);
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
            //System.out.println("Tried to access a tile out of bounds: " + emptySpotY + "/" + emptySpotY);
            return;
        }
        for (int y = emptySpotY - 1; y >= 0; y--) {
            if (this.board.getTile(emptySpotX, y).getFruit() != null) {
                //System.out.println("Found a fruit to drop down at " + emptySpotX + "/" + y);
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
     * Goes through the gameboard and searches for matching fruits, removes the matching
     * fruits.
     */
    public void removeMatchingFruits() {
        for (int x = 0; x < this.board.getBoardSize(); x++) {
            for (int y = 0; y < this.board.getBoardSize(); y++) {
                boolean horiMatch = GameRules.checkForHorizontalMatch(x, y, this.board);
                boolean vertiMatch = GameRules.checkForVerticalMatch(x, y, this.board);
                if (horiMatch) {
                    this.removeFruit(x - 1, y);
                    this.removeFruit(x, y);
                    this.removeFruit(x + 1, y);
                    score++;
                }
                if (vertiMatch) {
                    this.removeFruit(x, y - 1);
                    this.removeFruit(x, y);
                    this.removeFruit(x, y + 1);
                    score++;
                    
                }
            }
        }       
    }    
    /**
     * Ticks down the timer for highlighting a tile.
     * @param time 
     */
    private void tickDownHighlighttimer(double time) {
       for (int x = 0; x < this.board.getBoardSize(); x++) {
           for (int y = 0; y < this.board.getBoardSize(); y++) {
               this.board.getTile(x, y).tickDownHighlighttimer(time);
           }
       }
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
}