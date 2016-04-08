/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zirinna.himmunhedelmapeli.gamelogic;

import com.zirinna.himmunhedelmapeli.JavaFXThreadingRule;
import com.zirinna.himmunhedelmapeli.gameobjects.GameBoard;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

/**
 *
 * @author zirinna
 */
public class GameLogicTest {
    
    private GameLogic logic;
    
    @Rule 
    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        logic = new GameLogic();
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void newGameLogicHasBoardFullOfFruits() {
        for (int x=0; x < logic.getGameBoard().getBoardSize(); x++) {
            for (int y=0; y < logic.getGameBoard().getBoardSize(); y++) {
                assert(logic.getGameBoard().getTile(x, y).getFruit() != null);
            }
        }
            
    }
    
}
