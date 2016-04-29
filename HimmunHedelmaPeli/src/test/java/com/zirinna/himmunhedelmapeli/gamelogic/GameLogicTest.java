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
import static org.junit.Assert.assertFalse;
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
        assert(this.logic.getGameBoard().findEmptyTile() != null);
    }
    
    @Test
    public void findEmptyTileDoesNotFindEmptyTilesIfThereAreNoEmptyTiles() {
        assert(this.logic.getGameBoard().findEmptyTile() == null);
    }
    
    
    @Test
    public void afterUpdatingTheBoardThereAreNoEmptyTiles() {
        this.logic.updateBoard(1);
        this.logic.getGameBoard().getTile(3, 2).clearTile();
        this.logic.updateBoard(1);
        assert(this.logic.getGameBoard().findEmptyTile() == null);
    }
    
    
    @Test
    public void emptySpotOnTheTopRowIsFilledWithANewlyGeneratedRandomFruit() {
        this.logic.updateBoard(1);
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
        assert(this.logic.getGameBoard().getTile(1, 1).isHighlight() || this.logic.getGameBoard().getTile(1, 1).getFruit() == null);
    }
    
    
    @Test
    public void removingFruitsOfTheSameTypeIncreasesScore() {
        Fruit fruit = new Fruit(FruitType.PANDARIN);
        this.logic.getGameBoard().getTile(2, 0).setFruit(fruit);
        this.logic.getGameBoard().getTile(2, 1).setFruit(fruit);
        this.logic.getGameBoard().getTile(2, 2).setFruit(fruit);
        this.logic.removeMatchingFruits();
        assert(this.logic.getScore() > 0);
    }
    
    
    @Test
    public void threeSameFruitsInARowAreRemoved() {
        logic.updateBoard(1);
        Fruit fruit = new Fruit(FruitType.PANDARIN);
        this.logic.getGameBoard().getTile(0, 3).setFruit(fruit);
        this.logic.getGameBoard().getTile(1, 3).setFruit(fruit);
        this.logic.getGameBoard().getTile(2, 3).setFruit(fruit);
        this.logic.removeMatchingFruits();
        assert(this.logic.getGameBoard().getTile(0, 3).isHighlight());
    }
    
    @Test
    public void horizontalFruitsAreRemovedAtCornersOfTheBoard() {
        Fruit fruit = new Fruit(FruitType.PANDARIN);
        this.logic.getGameBoard().getTile(3, 5).setFruit(fruit);
        this.logic.getGameBoard().getTile(4, 5).setFruit(fruit);
        this.logic.getGameBoard().getTile(5, 5).setFruit(fruit);
        this.logic.removeMatchingFruits();
        assert(this.logic.getGameBoard().getTile(5, 5).isHighlight());
    }
    
    @Test
    public void verticalFruitsAreRemovedAtCornersOfTheBoard() {
        Fruit fruit = new Fruit(FruitType.PANDARIN);
        this.logic.getGameBoard().getTile(5, 3).setFruit(fruit);
        this.logic.getGameBoard().getTile(5, 4).setFruit(fruit);
        this.logic.getGameBoard().getTile(5, 5).setFruit(fruit);
        this.logic.removeMatchingFruits();
        assert(this.logic.getGameBoard().getTile(5, 5).isHighlight());
    }
    
    @Test
    public void highlightTimerTicksDownOnUpdate() {
        logic.getGameBoard().getTile(0, 0).highlightTile();
        logic.updateBoard(0.1);
        assert(logic.getGameBoard().getTile(0, 0).isHighlight());
        logic.updateBoard(1);
        assertFalse(logic.getGameBoard().getTile(0, 0).isHighlight());
    }
    
    @Test
    public void scoreRisesWhenMatchingFruitsAreFound() {
        Fruit fruit = new Fruit(FruitType.PANDARIN);
        this.logic.getGameBoard().getTile(0, 3).setFruit(fruit);
        this.logic.getGameBoard().getTile(1, 3).setFruit(fruit);
        this.logic.getGameBoard().getTile(2, 3).setFruit(fruit);
        this.logic.removeMatchingFruits();
        assert(this.logic.getScore() > 0);
    }
    
    @Test
    public void mouseClickAtFruitRemovesOneMove() {
        Fruit fruit = new Fruit(FruitType.PANDARIN);
        int oldMoves = this.logic.getMoves();
        double fruitWidth = this.logic.getGameBoard().getTile(0, 0).getFruit().getFruitImage().getWidth();
        double fruitHeight = this.logic.getGameBoard().getTile(0, 0).getFruit().getFruitImage().getHeight();
        int xTile = (int) (300 / fruitWidth);
        int yTile = (int) (300 / fruitHeight);
        this.logic.mouseClickAtCoordinates(300, 300);
        
        this.logic.getGameBoard().getTile(xTile, yTile).setFruit(fruit);
        assert(this.logic.getMoves() != oldMoves);
    }
    
    @Test
    public void mouseClickRemovesCorrectTileFruit() {
        double fruitWidth = logic.getGameBoard().getTile(0, 0).getFruit().getFruitImage().getWidth();
        double fruitHeight = logic.getGameBoard().getTile(0, 0).getFruit().getFruitImage().getHeight();
        double xCoordinate = 200;
        double yCoordinate = 200;
        int xTile = (int) (xCoordinate / fruitWidth);
        int yTile = (int) (yCoordinate / fruitHeight);
        assertFalse(logic.getGameBoard().getTile(xTile, yTile).isHighlight());
        this.logic.mouseClickAtCoordinates(xCoordinate, yCoordinate);
        assert(logic.getGameBoard().getTile(xTile, yTile).isHighlight() || logic.getGameBoard().getTile(xTile, yTile).getFruit() == null);
    }
    
    @Test
    public void mouseClickingOutsideGameBoardDoesntReduceMoves() {
        double xCoordinate = 56200;
        double yCoordinate = 2300;
        int oldMoves = logic.getMoves();
        
        this.logic.mouseClickAtCoordinates(xCoordinate, yCoordinate);
        assert(logic.getMoves() == oldMoves);
    }
    
    @Test
    public void usingMovesUpdatesRemainingMoves() {
        logic.removeMatchingFruits();
        logic.updateBoard(1);
        int oldMoves = logic.getMoves();
        logic.mouseClickAtCoordinates(100, 100);
        int moves = logic.getMoves();
        assert(moves < oldMoves);
    }
    
    @Test
    public void mouseClicksAreNotAcceptedDuringHighlight() {
        logic.getGameBoard().getTile(0, 0).highlightTile();
        boolean shouldBeFalse = logic.mouseClickAtCoordinates(0, 0);
        assertFalse(shouldBeFalse);
    }
    
    @Test
    public void whenTileIsHighlightByMatchScoreIsUpdated() {
        int oldScore = logic.getScore();
        Fruit fruit = new Fruit(FruitType.PANDARIN);
        this.logic.getGameBoard().getTile(0, 3).setFruit(fruit);
        this.logic.getGameBoard().getTile(1, 3).setFruit(fruit);
        this.logic.getGameBoard().getTile(2, 3).setFruit(fruit);
        logic.removeMatchingFruits();
        assert(logic.getScore() > oldScore);
    }
    
    @Test 
    public void correctTilesAreHighLightedOnHorizontalRemoval() {
        Fruit fruit = new Fruit(FruitType.PANDARIN);
        this.logic.getGameBoard().getTile(0, 3).setFruit(fruit);
        this.logic.getGameBoard().getTile(1, 3).setFruit(fruit);
        this.logic.getGameBoard().getTile(2, 3).setFruit(fruit);
        logic.removeMatchingFruits();
        assert(logic.getGameBoard().getTile(0, 3).isHighlight() 
                && logic.getGameBoard().getTile(1, 3).isHighlight() 
                && logic.getGameBoard().getTile(2, 3).isHighlight()
        );
    }
    
    @Test 
    public void correctTilesAreHighLightedOnVerticalRemoval() {
        Fruit fruit = new Fruit(FruitType.PANDARIN);
        this.logic.getGameBoard().getTile(1, 2).setFruit(fruit);
        this.logic.getGameBoard().getTile(1, 3).setFruit(fruit);
        this.logic.getGameBoard().getTile(1, 4).setFruit(fruit);
        logic.removeMatchingFruits();
        assert(logic.getGameBoard().getTile(1, 2).isHighlight() 
                && logic.getGameBoard().getTile(1, 3).isHighlight() 
                && logic.getGameBoard().getTile(1, 4).isHighlight()
        );
    }
}