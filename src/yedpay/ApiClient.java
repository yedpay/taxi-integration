/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yedpay;

import java.util.HashMap;
import yedpay.response.Response;
import yedpay.response.Error;

/**
 *
 * @author Terrie
 */
public class ApiClient {
    private HttpTask httpTask;
    
    public ApiClient(HttpTask httpTask) throws Exception {
        this.httpTask = httpTask;
    }
    
    public ApiClient(String environment) throws Exception {
        if (environment != null) {
            try {
                httpTask = new HttpTask(environment);
            } catch (Exception e) {
                throw new Exception("Init HttpTask fail");
            }
        }
    }
    
    public ApiClient(String environment, String credential) throws Exception {
        if (environment != null && credential != null) {
            try {
                httpTask = new HttpTask(environment, credential, Constant.INDEX_AUTHENTICATION_API_KEY);
            } catch (Exception e) {
                throw new Exception("Init HttpTask fail");
            }
        }
    }
    
    public Response getApiKey(String sessionKey) throws Exception {
        if (httpTask == null) {
            throw new Exception("HttpTask should not be null");
        }
        String path = String.format(Constant.PATH_GET_API_KEY, sessionKey);
        
        try {
            HashMap<String, String> parameter = new HashMap<>();
            
            Response response = httpTask.execute(Constant.GET, path, parameter);
            return response;
        } catch (Exception e) {
            return new Error("0", e.getMessage());
        }
    }
    
    public Response createEmvCode(String storeId, float amount) throws Exception {
        if (httpTask == null) {
            throw new Exception("HttpTask should not be null");
        }
        String path = String.format(Constant.PATH_CREATE_EMVCODE, storeId);
        
        try {
            HashMap<String, String> parameter = new HashMap<>();
            parameter.put("amount", amount + "");
            
            Response response = httpTask.execute(Constant.POST, path, parameter);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new Error("0", e.getMessage());
        }
    }
    
    public Response showEmvCode(String id) throws Exception {
        if (httpTask == null) {
            throw new Exception("httpTask should not be null");
        }
        String path = String.format(Constant.PATH_SHOW_EMVCODE, id);
        
        try {
            HashMap<String, String> parameter = new HashMap<>();
            
            Response response = httpTask.execute(Constant.GET, path, parameter);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return new Error("0", e.getMessage());
        }
    }
    
    public Response emvCodeTransactionList(String id, String created_at) throws Exception {
        if (httpTask == null) {
            throw new Exception("httpTask should not be null");
        }
        String path = String.format(Constant.PATH_EMVCODE_TRANSACTION_LIST, id);
//        System.out.println("path: " + path);
        
        try {
            HashMap<String, String> parameter = new HashMap<>();
            parameter.put("include", "store");
//            created_at = created_at == null? "":created_at.trim().replace(" ", "%20");
            parameter.put("created_at>", created_at);
            parameter.put("status", "paid");
            
            Response response = httpTask.execute(Constant.GET, path, parameter);
            return response;
        } catch (Exception e) {
            return new Error("0", e.getMessage());
        }
    }
}