/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yedpay.integration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import yedpay.Constant;
import yedpay.response.Response;
import yedpay.response.Error;
import yedpay.response.Success;

/**
 *
 * @author Terrie
 */
public class TaxiTest {
    private static String DEVICE_ID;
    private static String SECRET_KEY;
    
    @InjectMocks 
    private static Taxi taxi;
   
    public TaxiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        DEVICE_ID = "VXPKRVJR84MZ";
        SECRET_KEY = "4fddf45f81e1b276c4fd21f6147ca3ddcd945b0d72165ed8ef906b64c51d100e";
        
        taxi = new Taxi(Constant.STAGING, DEVICE_ID, SECRET_KEY);
    }
    
    @AfterClass
    public static void tearDownClass() {
        DEVICE_ID = "";
        SECRET_KEY = "";
    }
    
    /**
     * Test of generate QR String  method, of class Taxi.
     */
    @Test
    public void testGenerateQRString() {
        String result = taxi.getQRString();
        assertTrue(!result.isEmpty());
    }
    
    @Test
    public void testGetSessionKey() {
        String sessionKey = taxi.getSessionKey();
        assertTrue(!sessionKey.isEmpty());
    }
    
    @Test
    public void testGetApiKey() throws Exception {
        Response result = taxi.getApiKey();
        
        assertTrue(result instanceof Success);
    }
    
    @Test
    public void testCreatePaymentQRCode() throws Exception {
        String apiKey = "";
        String storeId = "";
        float amount = 0.0f;
        
        Response result = taxi.createPaymentQRCode(apiKey, storeId, amount);
        
        assertTrue(result instanceof Success);
    }
    
    @Test
    public void testQueryTransactionList() throws Exception {
        String apiKey = "";
        String emvCodeId = "";
        String date = "";
        
        Response result = taxi.queryTransactionList(apiKey, emvCodeId, date);
        
        assertTrue(result instanceof Success);
    }
}