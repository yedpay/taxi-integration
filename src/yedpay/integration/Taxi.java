/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yedpay.integration;

import com.google.gson.JsonObject;
import java.security.SecureRandom;
import yedpay.ApiClient;
import yedpay.Constant;
import yedpay.Helper;
import yedpay.HttpTask;
import yedpay.response.Response;
import yedpay.response.Success;
import yedpay.response.Error;

/**
 *
 * @author Terrie
 */
public class Taxi {
    
    private String deviceId;
    private String secretKey;
    
    private String environment;
    private byte[] randomBytes;
    private String sessionKey = "";
    private String qrCode = "";
    
    public Taxi(String environment, String deviceId, String secretKey) {
        this.environment = environment;
        this.deviceId = deviceId;
        this.secretKey = secretKey;
        
        generateQRString();
    }
    
    public void generateQRString() {
        SecureRandom random = new SecureRandom();
        randomBytes = new byte[32];
        random.nextBytes(randomBytes);
        randomBytes = random.generateSeed(32);
        
        try {
            long unixTime = System.currentTimeMillis() / 1000L;
            String prk = Helper.hmacSha256(randomBytes, deviceId);
            String timestamp = Integer.toHexString((int) unixTime);

            sessionKey = timestamp + "" + prk;
//            System.out.println("timestamp: " + timestamp);
//            System.out.println("prk: " + prk);
//            System.out.println("sessionKey: " + sessionKey);
            String message = "device_id=" + deviceId + "&session_key=" + sessionKey;

            String sign = Helper.hmacSha256(message, secretKey);

            JsonObject qrJson = new JsonObject();
            qrJson.addProperty("device_id", deviceId);
            qrJson.addProperty("session_key", sessionKey);
            qrJson.addProperty("sign", sign);

            qrCode =  qrJson.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String getQRString() {
        return qrCode;
    }
  
    public String getSessionKey() {
        return sessionKey;
    }
    
    public Response getApiKey() throws Exception {
        if (!sessionKey.equals("")) {
            HttpTask httpTask = new HttpTask(environment);
            
            ApiClient apiClient = new ApiClient(httpTask);
            Response response = apiClient.getApiKey(sessionKey);
            return response;
        } else {
            return new Error("0", "Session key empty");
        }
    }
    
    public Response createPaymentQRCode(String apiKey, String storeId, float amount) throws Exception {
        HttpTask httpTask = new HttpTask(environment, apiKey, Constant.INDEX_AUTHENTICATION_API_KEY);
        ApiClient apiClient = new ApiClient(httpTask);
            
        Response response = apiClient.createEmvCode(storeId, amount);
        if (response instanceof Success) {
            JsonObject result = ((Success) response).getData();
//            System.out.println(result.toString());
            if (!result.get("success").getAsBoolean()) {
                String message = result.has("message")? result.get("message").getAsString():"";
                if (message.equalsIgnoreCase("EMV Code already exists.")) {
                    String emvCodeId = result.has("emv_code_id")? result.get("emv_code_id").getAsString():"";
                    
//                    System.out.println("emvCodeId: " + emvCodeId);
                    Response response2 = apiClient.showEmvCode(emvCodeId);
                    return response2;
                }
            }
        }
        return response;
    }
    
    public Response queryTransactionList(String apiKey, String emvCodeId, String date) throws Exception {
        HttpTask httpTask = new HttpTask(environment, apiKey, Constant.INDEX_AUTHENTICATION_API_KEY);
        ApiClient apiClient = new ApiClient(httpTask);
            
        Response response = apiClient.emvCodeTransactionList(emvCodeId, date);
        return response;
    }
}
