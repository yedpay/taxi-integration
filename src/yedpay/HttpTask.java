/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yedpay;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import yedpay.response.Response;
import yedpay.response.Success;
import yedpay.response.Error;

/**
 *
 * @author Terrie
 */
public class HttpTask {
    private HttpURLConnection connection;
    private URL url;
    private String credential;
    private String environment = "staging";;
    private String authentication;
    
    public HttpTask(String environment) throws Exception {
        this.environment = environment;
    }
    
    public HttpTask(String environment, String credential, int authentication) throws Exception {
        this.environment = environment;
        this.credential = credential;
        this.setAuthentication(authentication);
    }
    
    public boolean hasCrendential() {
        return credential != null;
    }
    
    public Response execute(String method, String url, HashMap parameters) {
        int responseCode = 0;
        String responseMessage = "";
        try {
            String urlParameters = "";
            if (parameters != null) {
                urlParameters = mapToString(parameters);
            }
            
            url = getEndpoint() + url;
            if (method.equals(Constant.GET) && urlParameters.length() > 0) {
                url += "?" + urlParameters;
            }
            this.url = new URL(url);
            connection = (HttpURLConnection) this.url.openConnection();
            
            if (credential != null) {
                connection.setRequestProperty("Authorization", this.getAuthenticationPrefix() + " " + credential);
            }
            connection.setRequestMethod(method);
            connection.setUseCaches(false);
            if (method.equals(Constant.POST)) {
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes(StandardCharsets.UTF_8).length));
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.getOutputStream().write(urlParameters.getBytes(StandardCharsets.UTF_8));
            } else {
                connection.setRequestProperty("Accept","*/*");
            }
            
//            System.out.println("url:" + url);
                     
            responseCode = connection.getResponseCode();
            responseMessage = connection.getResponseMessage();
            
            //Get Response
            InputStream is;
            if ((responseCode + "").startsWith("2")) {
                is = connection.getInputStream();
            } else {
                is = connection.getErrorStream();
            }
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer(); 
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            
            JsonObject json = new JsonParser().parse(response.toString()).getAsJsonObject();
//            System.out.println(json.toString());
            if (json.has("success")) {
                return new Success(responseCode + "", json);
            } else {
                if (json.has("message")) {
                    return new Error(responseCode + "", json.get("message").getAsString());
                } else {
                    return new Error(responseCode + "", responseMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Error(responseCode + "", e.getMessage());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    
    /**
     * 
     * @param auth
     * @throws Exception 
     */
    public void setAuthentication(int auth) throws Exception {
        switch (auth) {
            case Constant.INDEX_AUTHENTICATION_ACCESS_TOKEN: {
                this.authentication = Constant.AUTHENTICATION_ACCESS_TOKEN;
                break;
            }
            case Constant.INDEX_AUTHENTICATION_API_KEY: {
                this.authentication = Constant.AUTHENTICATION_API_KEY;
                break;
            }
            default: {
                throw new Exception("Not supported authentication");
            }
        }
    }
    
    /**
     * 
     * @return 
     */
    public String getAuthentication() {
        return this.authentication;
    }

    /**
        * Get the value of endpoint
        * @return endpoint
        */
    public String getEndpoint() {
        switch (this.environment) {
            case Constant.PRODUCTION:
                return Constant.URL_PRODUCTION + "/" + Constant.API_VERSION;
            case Constant.STAGING:
                return Constant.URL_STAGING + "/" + Constant.API_VERSION;
            case Constant.DEV:
                return Constant.URL_DEV + "/" + Constant.DEV_VERSION;
            default:
                return Constant.URL_DEV + "/" + Constant.DEV_VERSION;
        }
    }
    
    public static String mapToString(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String key : map.keySet()) {
            String value = map.get(key);
            appendStringBuilder(stringBuilder, key , value);
        }
        return stringBuilder.toString();
    }
    
    private static StringBuilder appendStringBuilder(StringBuilder stringBuilder, String key, String value) throws RuntimeException {
        if (stringBuilder.length() > 0) {
            stringBuilder.append("&");
        }
        try {
            stringBuilder.append((key != null ? URLEncoder.encode(key, "UTF-8") : ""));
            stringBuilder.append("=");
            stringBuilder.append(value != null ? URLEncoder.encode(value, "UTF-8") : "");
        } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("This method requires UTF-8 encoding support", e);
        }
        return stringBuilder;
    }

    /**
     * 
     * @return
     * @throws Exception 
     */
    private String getAuthenticationPrefix() {
        switch (authentication) {
            case Constant.AUTHENTICATION_ACCESS_TOKEN:
                return "Bearer";
            case Constant.AUTHENTICATION_API_KEY:
                return "API-KEY";
        }
        return "";
    }
}