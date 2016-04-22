/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zirinna.himmunhedelmapeli.gameobjects;

import com.zirinna.himmunhedelmapeli.JavaFXThreadingRule;
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
public class GameBoardTest {
    
    private GameBoard gb;
    private int n;
    
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
        int n = 6;
        gb = new GameBoard(n);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void gameboardSizeMatchesInitialGenerationParameters() {
        assert(this.gb.getBoardSize() == 6);
    }
    
    @Test
    public void gameboardSizeIsBiggerThanZero() {
        assert(gb.getBoardSize() > 0);
    }
    
   
    
}
