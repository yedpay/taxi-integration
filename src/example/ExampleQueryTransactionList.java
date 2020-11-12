/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import yedpay.integration.Taxi;
import yedpay.response.Response;
import yedpay.response.Success;

/**
 *
 * @author Terrie
 */
public class ExampleQueryTransactionList {
    private static final String DEVICE_ID = "VXPKRVJR84MZ";
    private static final String SECRET_KEY = "4fddf45f81e1b276c4fd21f6147ca3ddcd945b0d72165ed8ef906b64c51d100e";

    private static String apiKey = "HiFlBxJOMGULFFCKLKCGCGV7f0WHz1ZFRGT1VC/iQjo=";
    private static String emvCodeId = "VG7ER1JJLZ9W";
    private static String paymentCreateTime = "2020-09-10 00:00:00";
    
    public static void main(String[] args) {
        Taxi taxi = new Taxi("dev", DEVICE_ID, SECRET_KEY);
        
        try {
            taxi.generateQRString();
            
            Response response = taxi.queryTransactionList(apiKey, emvCodeId, paymentCreateTime);
            if (response instanceof Success) {
                System.out.println("size: " + ((Success) response).getData().get("data").getAsJsonArray().size() + " response: [" + ((Success) response).getData().toString() + "]");
            } else {
                System.out.println("response: " + ((yedpay.response.Error) response).getErrorMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
