/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yedpay.response;

import com.google.gson.JsonObject;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Terrie
 */
public class SuccessTest {
    
    public SuccessTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getData method, of class Success.
     */
    @Test
    public void testGetData() {
        Success instance = new Success("200", new JsonObject());
        
        JsonObject result = instance.getData();
        assertTrue(result instanceof JsonObject);
    }
}
