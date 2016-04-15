/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zirinna.himmunhedelmapeli.gameobjects;

import javafx.scene.image.Image;

/**
 * Represents a fruit on the gameboard. A fruit has a type and can be removed from the 
 * board by clicking it or by matching it with similar fruits.
 * @author zirinna
 */
public class Fruit {
    private FruitType type;
    private Image image;
    
    public Fruit(FruitType type) {
        this.type = type;
        setImageByType();
    }
    
    private void setImageByType() {
        switch (this.type) {
            case BANANA: this.image = new Image("/images/banana.png");
                break;
            case APPLE: this.image = new Image("/images/apple.png");
                break;
            case KIWI: this.image = new Image("/images/kiwi.png");
                break;
            case ORANGE: this.image = new Image("/images/orange.png");
                break;
            case PANDARIN: this.image = new Image("/images/pandarin.png");
                break;
            default: break;
        }
    }
    
    public FruitType getFruitType() {
        return this.type;
    }
    
    public Image getFruitImage() {
        return this.image;
    }
    
    public String toString() {
        return this.type.toString();
    }
}
