/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zirinna.himmunhedelmapeli.gamelogic;

import com.zirinna.himmunhedelmapeli.JavaFXThreadingRule;
import com.zirinna.himmunhedelmapeli.gameobjects.Fruit;
import com.zirinna.himmunhedelmapeli.gameobjects.FruitType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;

/**
 *
 * @author zirinna
 */
public class GameRulesTest {
    private GameLogic logic;
    
    @Rule 
    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
    
    public GameRulesTest() {
    }
    
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
    public void horizontalMatchRemovesMatchingTiles() {
        Fruit fruit = new Fruit(FruitType.ORANGE);
        this.logic.getGameBoard().getTile(0, 3).setFruit(fruit);
        this.logic.getGameBoard().getTile(1, 3).setFruit(fruit);
        this.logic.getGameBoard().getTile(2, 3).setFruit(fruit);
        this.logic.removeMatchingFruits();
        assert(logic.getGameBoard().getTile(0, 3).getFruit() == null);
        assert(logic.getGameBoard().getTile(1, 3).getFruit() == null);
        assert(logic.getGameBoard().getTile(2, 3).getFruit() == null);
    }
    
    @Test
    public void verticalMatchRemovesMatchingTiles() {
        Fruit fruit = new Fruit(FruitType.APPLE);
        this.logic.getGameBoard().getTile(1, 2).setFruit(fruit);
        this.logic.getGameBoard().getTile(1, 3).setFruit(fruit);
        this.logic.getGameBoard().getTile(1, 4).setFruit(fruit);
        this.logic.removeMatchingFruits();
        assert(logic.getGameBoard().getTile(1, 2).getFruit() == null);
        assert(logic.getGameBoard().getTile(1, 3).getFruit() == null);
        assert(logic.getGameBoard().getTile(1, 4).getFruit() == null);
    }
}
