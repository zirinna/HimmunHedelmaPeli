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
    public void cantCheckForHorizontalMatchOutsideGameBoard() {
        int shouldBeZero = GameRules.checkForHorizontalMatch(4658, 999, logic.getGameBoard());
        assert(shouldBeZero == 0);
    }
    
    @Test
    public void cantCheckForVerticalMatchOutsideGameBoard() {
        int shouldBeZero = GameRules.checkForHorizontalMatch(15, 347, logic.getGameBoard());
        assert(shouldBeZero == 0);
    }

    @Test
    public void cantCheckForHorizontalMatchInNegativeCoordinates() {
        int shouldBeZero = GameRules.checkForHorizontalMatch(-1, -17, logic.getGameBoard());
        assert(shouldBeZero == 0);
    }
    

    @Test
    public void cantCheckForVerticalMatchInNegativeCoordinates() {
        int shouldBeZero = GameRules.checkForHorizontalMatch(-31, -12, logic.getGameBoard());
        assert(shouldBeZero == 0);
    }
    
    
    @Test
    public void horizontalMatchRemovesMatchingTiles() {
        Fruit fruit = new Fruit(FruitType.ORANGE);
        this.logic.getGameBoard().getTile(0, 3).setFruit(fruit);
        this.logic.getGameBoard().getTile(1, 3).setFruit(fruit);
        this.logic.getGameBoard().getTile(2, 3).setFruit(fruit);
        this.logic.removeMatchingFruits();
        assert(logic.getGameBoard().getTile(0, 3).isHighlight());
        assert(logic.getGameBoard().getTile(1, 3).isHighlight());
        assert(logic.getGameBoard().getTile(2, 3).isHighlight());
    }
    
    @Test
    public void verticalMatchRemovesMatchingTiles() {
        Fruit fruit = new Fruit(FruitType.APPLE);
        this.logic.getGameBoard().getTile(1, 2).setFruit(fruit);
        this.logic.getGameBoard().getTile(1, 3).setFruit(fruit);
        this.logic.getGameBoard().getTile(1, 4).setFruit(fruit);
        this.logic.removeMatchingFruits();
        assert(logic.getGameBoard().getTile(1, 2).isHighlight());
        assert(logic.getGameBoard().getTile(1, 3).isHighlight());
        assert(logic.getGameBoard().getTile(1, 4).isHighlight());
    }
    
    @Test
    public void horizontalMatchDoesntMatchEmptyTiles() {
        this.logic.getGameBoard().getTile(1, 2).setFruit(null);
        assert(GameRules.checkForHorizontalMatch(1, 2, this.logic.getGameBoard()) < 3);
    }
    
    @Test
    public void verticalMatchDoesntMatchEmptyTiles() {
        this.logic.getGameBoard().getTile(3, 3).setFruit(null);
        assert(GameRules.checkForVerticalMatch(3, 3, this.logic.getGameBoard()) < 3);
    }
    
    @Test
    public void horizontalMatchReturnsTrueOnMatch() {
        Fruit fruit = new Fruit(FruitType.ORANGE);
        this.logic.getGameBoard().getTile(1, 1).setFruit(fruit);
        this.logic.getGameBoard().getTile(2, 1).setFruit(fruit);
        this.logic.getGameBoard().getTile(3, 1).setFruit(fruit);
        assert(GameRules.checkForHorizontalMatch(1, 1, this.logic.getGameBoard()) >= 3);
    }
    
    @Test
    public void verticalMatchReturnsTrueOnMatch() {
        Fruit fruit = new Fruit(FruitType.KIWI);
        this.logic.getGameBoard().getTile(1, 2).setFruit(fruit);
        this.logic.getGameBoard().getTile(1, 3).setFruit(fruit);
        this.logic.getGameBoard().getTile(1, 4).setFruit(fruit);
        assert(GameRules.checkForVerticalMatch(1, 2, this.logic.getGameBoard()) >= 3);
    }
    
}