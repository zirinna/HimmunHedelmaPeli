/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zirinna.himmunhedelmapeli.gamelogic;

import com.zirinna.himmunhedelmapeli.JavaFXThreadingRule;
import com.zirinna.himmunhedelmapeli.gameobjects.GameBoard;
import com.zirinna.himmunhedelmapeli.gameobjects.Tile;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;

/**
 *
 * @author nikok
 */
public class GameBoardTest {
    GameLogic logic;
    GameBoard board;
    
    @Rule 
    public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();
    
    public GameBoardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.logic = new GameLogic();
        this.board = logic.getGameBoard();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void highlightingTileOnBoardMakesItLit() {
        board.getTile(0, 0).highlightTile();
        assert(board.getTile(0, 0).isHighlight());
    }
    
    @Test
    public void emptyTilesAreNotFoundIfThereAreNone() {
        assert(board.findEmptyTile() == null);
    }
    
    @Test
    public void findEmptyTileFindsEmptyTiles() {
        board.getTile(0, 0).clearTile();
        assertFalse(board.findEmptyTile() == null);
    }
    
    @Test
    public void findEmptyTileFindsCorrectTile() {
        Tile t = board.getTile(1, 1);
        board.getTile(1, 1).clearTile();
        assert(board.findEmptyTile().equals(t));
    }
    
    
}
