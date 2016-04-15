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
     * draws the tile and the fruit on the tile, if the tile has a fruit
     * @param gc the graphicscontext to draw the tile on
     */
    public void drawTile(GraphicsContext gc) {
        if (this.fruit == null) {
            return;
        }
        Image image = this.fruit.getFruitImage();
        gc.drawImage(image, x * image.getWidth(), y * image.getHeight());
        gc.fillText(this.x + "/" + this.y, x * image.getWidth() + 20, y * image.getHeight() + 20);
    }
    
    /**
     * clears the tile by setting the fruit to null
     */
    public void clearTile() {
        this.fruit = null;
    }


}
