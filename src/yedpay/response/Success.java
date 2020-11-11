/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yedpay.response;

import com.google.gson.JsonObject;

/**
 *
 * @author Terrie
 */
public class Success extends Response {
    private JsonObject data;
    
    public Success(String responseCode, JsonObject response) {
        super(responseCode);
        data = response;
    }
    
    public JsonObject getData() {
        return data;
    }
}
