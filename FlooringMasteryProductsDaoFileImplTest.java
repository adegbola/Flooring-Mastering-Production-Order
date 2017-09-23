/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FlooringMastery.dao;

import FlooringMastery.dto.Products;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kelsey
 */
public class FlooringMasteryProductsDaoFileImplTest {

    public FlooringMasteryProductsDaoFileImplTest() {
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

    /**
     * Test of getProductinfo method, of class
     * FlooringMasteryProductsDaoFileImpl.
     */
    @Test
    public void testGetProductinfo() {
        System.out.println("getProductinfo");
        String type = "Wood";
        FlooringMasteryProductsDaoFileImpl instance = new FlooringMasteryProductsDaoFileImpl();
        // String expResult = "Wood";
        Products result = instance.getProductinfo(type);
        assertNotNull(result);
    }

    /**
     * Test of getProductlist method, of class
     * FlooringMasteryProductsDaoFileImpl.
     */
    @Test
    public void testGetProductlist() {
        System.out.println("getProductlist");
        FlooringMasteryProductsDaoFileImpl instance = new FlooringMasteryProductsDaoFileImpl();
        List<Products> result = instance.getProductlist();
        assertTrue(result.size() == 5 );

    }

}
