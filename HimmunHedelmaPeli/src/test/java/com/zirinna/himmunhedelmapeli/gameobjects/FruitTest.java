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
public class FruitTest {
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
    public void newlyCreatedFruitHasImage() {
            Fruit f = new Fruit(FruitType.APPLE);
            assert(f.getFruitImage() != null);
    }
    
    @Test
    public void fruitToStringMatchesFruitType() {
            Fruit f = new Fruit(FruitType.KIWI);
            assert(f.toString().equals("KIWI"));
    }
    
    
    
}
