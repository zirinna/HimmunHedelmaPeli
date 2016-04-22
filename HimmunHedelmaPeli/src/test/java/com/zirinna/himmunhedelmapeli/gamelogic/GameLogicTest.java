/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zirinna.himmunhedelmapeli.gamelogic;

import com.zirinna.himmunhedelmapeli.JavaFXThreadingRule;
import com.zirinna.himmunhedelmapeli.gameobjects.Fruit;
import com.zirinna.himmunhedelmapeli.gameobjects.FruitType;
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
    
    @Test
    public void findEmptyTileFindsEmptyTile() {
        this.logic.getGameBoard().getTile(2, 2).clearTile();
        assert(this.logic.findEmptyTile() != null);
    }
    
    @Test
    public void findEmptyTileDoesNotFindEmptyTilesIfThereAreNoEmptyTiles() {
        assert(this.logic.findEmptyTile() == null);
    }
    
    @Test
    public void afterUpdatingTheBoardThereAreNoEmptyTiles() {
        this.logic.getGameBoard().getTile(3, 2).clearTile();
        this.logic.updateBoard(1);
        assert(this.logic.findEmptyTile() == null);
    }
    
    /*
    / this test is not applicaple anymore since the removing of matching fruits in in game, 
    / I will look at this later
    @Test
    public void theRightFruitDropsDown() {
        this.logic.getGameBoard().getTile(3, 3).clearTile();
        Fruit f = this.logic.getGameBoard().getTile(3, 2).getFruit();
        this.logic.updateBoard();
        assert(this.logic.getGameBoard().getTile(3, 3).getFruit().equals(f));
    }
    */
    
    @Test
    public void emptySpotOnTheTopRowIsFilledWithANewlyGeneratedRandomFruit() {
        this.logic.getGameBoard().getTile(3, 0).clearTile();
        this.logic.updateBoard(1);
        assert(this.logic.getGameBoard().getTile(3, 0).getFruit() != null);
    }
    
    
    @Test
    public void newGameLogicHas6x6Board() {
        assert(this.logic.getGameBoard().getBoardSize() == 6);
    }
    
    @Test
    public void mouseCoordinatesAreSetToCorrectTile() {
        double xCoor = 100;
        double yCoor = 100;
        this.logic.mouseClickAtCoordinates(xCoor, yCoor);
        assert(this.logic.getGameBoard().getTile(1, 1).getFruit() == null);
    }
    
    
    @Test
    public void removingFruitsOfTheSameTypeIncreasesScore() {
        Fruit fruit = new Fruit(FruitType.PANDARIN);
        this.logic.getGameBoard().getTile(2, 0).setFruit(fruit);
        this.logic.getGameBoard().getTile(2, 1).setFruit(fruit);
        this.logic.getGameBoard().getTile(2, 2).setFruit(fruit);
        this.logic.removeMatchingFruits();
        assert(this.logic.score > 0);
    }
    
    
    @Test
    public void threeSameFruitsInARowAreRemoved() {
        Fruit fruit = new Fruit(FruitType.PANDARIN);
        this.logic.getGameBoard().getTile(0, 3).setFruit(fruit);
        this.logic.getGameBoard().getTile(1, 3).setFruit(fruit);
        this.logic.getGameBoard().getTile(2, 3).setFruit(fruit);
        this.logic.removeMatchingFruits();
        assert(this.logic.getGameBoard().getTile(0, 3).getFruit() == null);
    }
    
    @Test
    public void scoreRisesWhenMatchingFruitsAreFound() {
        Fruit fruit = new Fruit(FruitType.PANDARIN);
        this.logic.getGameBoard().getTile(0, 3).setFruit(fruit);
        this.logic.getGameBoard().getTile(1, 3).setFruit(fruit);
        this.logic.getGameBoard().getTile(2, 3).setFruit(fruit);
        this.logic.removeMatchingFruits();
        assert(this.logic.score > 0);
    }
    
    @Test
    public void mouseClickAtFruitRemovesOneMove() {
        Fruit fruit = new Fruit(FruitType.PANDARIN);
        int oldMoves = this.logic.moves;
        double fruitWidth = this.logic.getGameBoard().getTile(0, 0).getFruit().getFruitImage().getWidth();
        double fruitHeight = this.logic.getGameBoard().getTile(0, 0).getFruit().getFruitImage().getHeight();
        int xTile = (int) (300 / fruitWidth);
        int yTile = (int) (300 / fruitHeight);
        this.logic.mouseClickAtCoordinates(300, 300);
        
        this.logic.getGameBoard().getTile(xTile, yTile).setFruit(fruit);
        assert(this.logic.moves != oldMoves);
    }
    
    @Test
    public void mouseClickRemovesCorrectTileFruit() {
        double fruitWidth = logic.getGameBoard().getTile(0, 0).getFruit().getFruitImage().getWidth();
        double fruitHeight = logic.getGameBoard().getTile(0, 0).getFruit().getFruitImage().getHeight();
        double xCoordinate = 200;
        double yCoordinate = 200;
        int xTile = (int) (xCoordinate / fruitWidth);
        int yTile = (int) (yCoordinate / fruitHeight);
        assert(logic.getGameBoard().getTile(xTile, yTile).getFruit() != null);
        this.logic.mouseClickAtCoordinates(xCoordinate, yCoordinate);
        assert(logic.getGameBoard().getTile(xTile, yTile).getFruit() == null);
    }
    
    @Test
    public void mouseClickingOutsideGameBoardDoesntReduceMoves() {
        double xCoordinate = 56200;
        double yCoordinate = 2300;
        int oldMoves = logic.moves;
        
        this.logic.mouseClickAtCoordinates(xCoordinate, yCoordinate);
        assert(logic.moves == oldMoves);
    }
    
    
}
