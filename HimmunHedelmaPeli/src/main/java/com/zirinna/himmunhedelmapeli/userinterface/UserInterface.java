/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zirinna.himmunhedelmapeli.userinterface;

import com.zirinna.himmunhedelmapeli.gamelogic.GameLogic;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author zirinna
 */
public class UserInterface {
 
    
    
    public void drawUI(GraphicsContext gc, GameLogic gl) {
        gc.save();
        double screenWidth = gc.getCanvas().getWidth();
        double screenHeight = gc.getCanvas().getHeight();
        
        gc.setFill(Color.DARKRED);
        gc.setFont(Font.font("Comic Sans", 24));
        gc.fillText("Pisteet: " + gl.score, screenWidth-150, 20);
        gc.fillText("Siirtoja: " + gl.moves, screenWidth-150, 50);
        
        if (gl.moves <= 0) {
            drawGameOver(gc, gl);
        }
        
        gc.restore();
        
    }
    
    public void drawGameOver(GraphicsContext gc, GameLogic gl) {
        gc.setFill(Color.DARKCYAN);
        gc.fillRect(50, 50, 200, 125);
        gc.setFill(Color.WHITE);
        gc.setFont(Font.font("Comic Sans", 24));
        gc.fillText("Peli loppui, sait ", 75, 85);
        gc.fillText("" + gl.score, 115, 110);
        gc.fillText("pistettä.", 90, 135);
        gc.fillText("You died.", 80, 160);
    }
}
