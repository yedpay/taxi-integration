/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yedpay;

import java.util.HashMap;
import java.util.Map;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;

/**
 *
 * @author Terrie
 */
public class HttpTaskTest {
    @InjectMocks
    private static HttpTask httpTask;
    
    public HttpTaskTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        httpTask = new HttpTask("", "", Constant.INDEX_AUTHENTICATION_ACCESS_TOKEN);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    /**
     * Test of setAuthentication method, of class HttpTask
     * @throws Exception 
     */
    @Test
    public void testSetAuthenticationAccessToken() throws Exception {
        httpTask.setAuthentication(Constant.INDEX_AUTHENTICATION_ACCESS_TOKEN);
        
        assertEquals(Constant.AUTHENTICATION_ACCESS_TOKEN, httpTask.getAuthentication());
    }
    
    /**
     * Test of setAuthentication method, of class HttpTask
     * @throws Exception 
     */
    @Test
    public void testSetAuthenticationApiKey() throws Exception {
        httpTask.setAuthentication(Constant.INDEX_AUTHENTICATION_API_KEY);
        
        assertEquals(Constant.AUTHENTICATION_API_KEY, httpTask.getAuthentication());
    }
    
    
    @Test(expected = Exception.class)
    public void testSetUnknownAuthentication() throws Exception {
        httpTask.setAuthentication(0);
    }
    
    /**
     * Test of getEndpoint method, of class HttpTask.
     */
    @Test
    public void testGetEndpoint() {
        assertNotNull(httpTask.getEndpoint());
    }

    /**
     * Test of mapToString method, of class HttpTask.
     */
    @Test
    public void testMapToString() {
        Map<String, String> map = new HashMap<>();
        map.put("testkey", "testvalue");
        
        String expResult = "testkey=testvalue";
        String result = HttpTask.mapToString(map);
        
        assertEquals(expResult, result);
    }

    /**
     * Test of mapToStringException method, of class HttpTask.
     */
    @Test(expected = Exception.class)
    public void testMapToStringException() {
        String result = HttpTask.mapToString(null);
    }
}