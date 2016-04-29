/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zirinna.himmunhedelmapeli.gameobjects;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * This class represents a single tile on the board, which can be empty or
 * holding a fruit.
 * @author zirinna
 */
public class Tile {
    private Fruit fruit;
    private int x;
    private int y;
    private double highlighttime;
    
    /**
     * Constructor.
     * @param x x-coordinate
     * @param y y-coordinate
     */
    
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getXcoordinate() {
        return this.x;
    }
    
    public int getYcoordinate() {
        return this.y;
    }
    
    public void setFruit(Fruit fruit) {
        this.fruit = fruit;    
    }
    
    public Fruit getFruit() {
        return this.fruit;
    }
    /**
     * Draws the tile and the fruit on the tile, if the tile has a fruit.
     * @param gc the graphicscontext to draw the tile on
     */
    public void drawTile(GraphicsContext gc) {
        if (this.fruit == null) {
            return;
        }
        if (this.highlighttime > 0) {
            return;
        }
        gc.drawImage(this.fruit.getFruitImage(), x * this.fruit.getFruitImage().getWidth(), y * this.fruit.getFruitImage().getHeight());
    }
    
    /**
     * Clears the tile by setting the fruit to null.
     */
    public void clearTile() {
        this.fruit = null;
    }
    
    /**
     * Highlights the tile.
     */
    public void highlightTile() {
        this.highlighttime = 0.4;
    }
    
    private void highlightClear() {
        this.fruit = null;
    }
    
    /**
     * Tick down the highlight timer on this tile, signalling
     * it to clear the tile if the timer gets to zero.
     * @param time Passed time in seconds
     */
    public void tickDownHighlighttimer(double time) {
        if (this.highlighttime <= 0) {
            return;
        }
        this.highlighttime = highlighttime - time;
        if (highlighttime < 0)  {
            highlighttime = 0;
            this.highlightClear();
        }
    }

    /**
     * Tells if the highlighttimer is on.
     * @return true if the highlighttime is bigger than zero.
     */
    public boolean isHighlight() {
        return (this.highlighttime > 0);
    }
    
}
