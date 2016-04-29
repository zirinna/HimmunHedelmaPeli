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
    private char c;
    
    /**
     * Constructor.
     * @param type Fruittype.
     */
    public Fruit(FruitType type) {
        this.type = type;
        setImageByType();
    }
    
    private void setImageByType() {
        switch (this.type) {
            case BANANA: setImage("/images/banana.png");
                this.c = 'B';
                break;
            case APPLE: setImage("/images/apple.png");
                this.c = 'A';
                break;
            case KIWI: setImage("/images/kiwi.png");
                this.c = 'K';
                break;
            case ORANGE: setImage("/images/orange.png");
                this.c = 'O';
                break;
            case PANDARIN: setImage("/images/pandarin.png");
                this.c = 'P';
                break;
            default: break;
        }
    }

    public String getFruitLetter() {
        return "" + c;
    }
    
    
    private void setImage(String imagePath) {
        try {
            this.image = new Image(imagePath);
        } catch (Exception e) {
            
        }
    }
    
    public FruitType getFruitType() {
        return this.type;
    }
    
    /**
     * Getter for getting the image of the fruit defined by fruit type.
     * @return the image of the fruit
     */
    public Image getFruitImage() {
        return this.image;
    }
    
/**
 * String representation of the fruit.
 * @return returns the string
 */
    public String toString() {
        return this.type.toString();
    }
}
