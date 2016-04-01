/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GameTests;

import com.zirinna.himmunhedelmapeli.Fruit;
import com.zirinna.himmunhedelmapeli.FruitType;
import com.zirinna.himmunhedelmapeli.GameBoard;
import com.zirinna.himmunhedelmapeli.Tile;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zirinna
 */
public class GameTest {
    
    public GameTest() {
    }
    
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


@Test
public void newlyCreatedFruitHasImage() {
	Fruit f = new Fruit(FruitType.APPLE);
	assert(f.getFruitImage() != null);
}

@Test
public void fruitToStringMatchesFruitType() {
	Fruit f = new Fruit(FruitType.KIWI);
	assert(f.toString().equals("KIWI"));
}


@Test
public void tilesCantBePlacedOutsideGameBoard() {
	int n = 6;
	GameBoard gb = new GameBoard(n);
	Tile t = new Tile(n+1, n+1);
	gb.setTile(n+1, n+1, t); //This might crash result in fail (array out of bounds)
}

@Test
public void gameboardSizeMatchesInitialGenerationParameters() {
	int n = 6;
	GameBoard gb = new GameBoard(n);
	assert(gb.getBoardSize() == n);
}
}

