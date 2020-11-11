/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yedpay;

import java.util.HashMap;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import yedpay.response.Response;
import yedpay.response.Error;

/**
 *
 * @author Terrie
 */
public class ApiClientTest {
    
    @InjectMocks
    private static ApiClient apiClient;
    @Mock
    private static HttpTask httpTaskMock;
    
    public ApiClientTest() {
    }
    
    @Before
    public void setUp() throws Exception {
         MockitoAnnotations.initMocks(this);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        apiClient = new ApiClient(httpTaskMock);
    }
    
    @AfterClass
    public static void tearDownClass() {
        httpTaskMock = null;
        apiClient = null;
    }
    
    @Test
    public void testGetApiKey() throws Exception {
        String sessionKey = "";
        
        when (httpTaskMock.execute(Mockito.any(String.class), Mockito.any(String.class), Mockito.any(HashMap.class))).thenReturn(new Error("0", ""));
        Response result = apiClient.getApiKey(sessionKey);
        
        assertTrue(result instanceof Error);
    }
    
    @Test
    public void testCreateEmvCode() throws Exception {
        String storeId = "";
        float amount = 0.0f;
        
        when (httpTaskMock.execute(Mockito.any(String.class), Mockito.any(String.class), Mockito.any(HashMap.class))).thenReturn(new Error("0", ""));
        Response result = apiClient.createEmvCode(storeId, amount);
        
        assertTrue(result instanceof Error);
    }
    
    @Test
    public void testShowEmvCode() throws Exception {
        String id = "";
        
        when (httpTaskMock.execute(Mockito.any(String.class), Mockito.any(String.class), Mockito.any(HashMap.class))).thenReturn(new Error("0", ""));
        Response result = apiClient.showEmvCode(id);
        
        assertTrue(result instanceof Error);
    }
    
    @Test
    public void testEmvCodeTransactionList() throws Exception {
        String id = "";
        String createdAt = "";
        
        when (httpTaskMock.execute(Mockito.any(String.class), Mockito.any(String.class), Mockito.any(HashMap.class))).thenReturn(new Error("0", ""));
        Response result = apiClient.emvCodeTransactionList(id, createdAt);
        
        assertTrue(result instanceof Error);
    }
}
