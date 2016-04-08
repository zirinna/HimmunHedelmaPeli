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
            for (int y = this.board.getBoardSize()-1; y >= 0; y--) {
                if(this.board.getTile(x, y).getFruit() == null) {
                    return this.board.getTile(x, y);
                }
            }
        }
        return null;
    }
    
    /**
     * goes through the gameboard searching for empty tiles and drops the fruits
     * above the empty tile down
     */
    public void updateBoard() {
        while (findEmptyTile() != null) {
            System.out.println("empty tile found at:"+findEmptyTile().getXcoordinate() + "/"+findEmptyTile().getYcoordinate());
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
        for(int y = emptySpotY - 1; y >= 0; y --) {
            if (this.board.getTile(emptySpotX, y).getFruit() != null) {
                System.out.println("Found a fruit to drop down at "+emptySpotX+"/"+y);
                this.board.getTile(emptySpotX, emptySpotY).setFruit(this.board.getTile(emptySpotX, y).getFruit());
                this.board.getTile(emptySpotX, y).clearTile();
                break;
            }
        }
        if (this.board.getTile(emptySpotX, 0).getFruit() == null) {
            this.board.getTile(emptySpotX, 0).setFruit(generateRandomFruit());
        }
    }
    
    
}
