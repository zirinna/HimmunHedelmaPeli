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
public class TileTest {
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
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void tileWithFruitIsNotEmpty() {
            Tile tile = new Tile(0,0);
            tile.setFruit(new Fruit(FruitType.APPLE));
            assert(tile.getFruit() != null);
    }
    
    @Test
    public void clearingTileRemovesTheFruitInIt() {
            Tile tile = new Tile(0,0);
            tile.setFruit(new Fruit(FruitType.KIWI));
            tile.clearTile();
            assert(tile.getFruit() == null);
    }
    

    
}
