/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import yedpay.integration.Taxi;

/**
 *
 * @author Terrie
 */
public class ExampleGenerateQR {
    private static final String DEVICE_ID = "VXPKRVJR84MZ";
    private static final String SECRET_KEY = "4fddf45f81e1b276c4fd21f6147ca3ddcd945b0d72165ed8ef906b64c51d100e";

    public static void main(String[] args) {
        Taxi taxi = new Taxi("dev", DEVICE_ID, SECRET_KEY);
        
        String qrString = taxi.getQRString();
        System.out.println(qrString);
    }
}
