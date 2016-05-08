package com.zirinna.himmunhedelmapeli.gamelogic;

import com.zirinna.himmunhedelmapeli.gameobjects.GameBoard;
import com.zirinna.himmunhedelmapeli.gameobjects.Fruit;
import com.zirinna.himmunhedelmapeli.gameobjects.FruitType;
import com.zirinna.himmunhedelmapeli.gameobjects.Tile;
import java.util.Random;

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
     * @return true if the mouse click was accepted
     */
    public boolean mouseClickAtCoordinates(double xCoordinate, double yCoordinate) {
        if (this.board.hasHighlightTiles()) {
            return false;  //Don't do anything if fruits are dropping down
        }
        int xTile = (int) (xCoordinate / 64);
        int yTile = (int) (yCoordinate / 64);
        if (xTile >= 0 && xTile < board.getBoardSize() && yTile >= 0 && yTile < board.getBoardSize()) {
            highlightTile(xTile, yTile);
            moves--;
        }
        return true;
    }
    
    /**
     * Highlights the tile for removal
     * @param x Tile's x-coordinate
     * @param y Tile's y-coordinate
     */
    private void highlightTile(int x, int y) {
        board.getTile(x, y).highlightTile();
    }
    
    public GameBoard getGameBoard() {
        return board;
    }
    
    /**
     * Goes through the gameboard, searches for matching fruits, removes them and
     * populates the empty tiles.
     * @param time timer
     */
    public void updateBoard(double time) {
        this.removeMatchingFruits();
        while (board.findEmptyTile() != null) {
            dropFruitDown(board.findEmptyTile().getXcoordinate(), board.findEmptyTile().getYcoordinate());
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
            return;
        }
        for (int y = emptySpotY - 1; y >= 0; y--) {
            if (this.board.getTile(emptySpotX, y).getFruit() != null) {
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
     * Check through all the tiles, and remove horizontal and vertical matches.
     */
    public void removeMatchingFruits() {
        for (int x = 0; x < this.board.getBoardSize(); x++) {
            for (int y = 0; y < this.board.getBoardSize(); y++) {
                int horiMatch = GameRules.checkForHorizontalMatch(x, y, this.board);
                int vertiMatch = GameRules.checkForVerticalMatch(x, y, this.board);
                if (horiMatch >= 3) {
                    removeFruitsByHighlight(x, y, horiMatch, true);
                }
                if (vertiMatch >= 3) {
                    removeFruitsByHighlight(x, y, vertiMatch, false);
                }
            }
        }       
    }
    
    /**
     * Removes a number of fruits (either horizontal or vertical)
     * by triggering their Highlight. This results in "delayed removal"
     * in the game.
     * @param x starting point of the removal
     * @param y starting point of the removal
     * @param number how many fruits are removed
     * @param horizontal if true, removal is done horizontal, if false, removal is done vertical
     * @return 
     */
    private boolean removeFruitsByHighlight(int x, int y, int number, boolean horizontal) {
        if (horizontal) {
            for (int i = 0; i < number; i++) {
                if (!this.board.getTile(x + i, y).isHighlight()) {
                    this.highlightTile(x + i, y);
                    score++;
                }
            }
        } else {
            for (int i = 0; i < number; i++) {
                if (!this.board.getTile(x, y + i).isHighlight()) {
                    this.highlightTile(x, y + i);
                    score++;
                }
            }
        }
        return true;
    }
    
    /**
     * Ticks down the timer for highlighting a tile for removal.
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