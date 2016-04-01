/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zirinna.himmunhedelmapeli;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
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
        gc.drawImage(image, x*image.getWidth(), y*image.getHeight());
    }
    /**
     * clears the tile by setting the fruit to null
     */
    public void clearTile() {
        this.fruit = null;
    }

    public Object getFuirt() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
