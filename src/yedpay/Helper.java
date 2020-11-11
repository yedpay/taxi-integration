/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yedpay;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author kobeleung
 */
public class Helper {
    private static final char[] HEX_ARRAY = "0123456789abcdef".toCharArray();
    private static final String hashingAlgorithm = "HmacSHA256"; //or "HmacSHA1", "HmacSHA512"
    
    /**
     * 
     * @param message
     * @param key
     * @return String
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException 
     */
    public static String hmacSha256(String message, String key) throws InvalidKeyException, NoSuchAlgorithmException, Exception {
        byte[] bytes = hmac(hashingAlgorithm, key.getBytes(), message.getBytes());
        return bytesToHex(bytes);
    }
    
    /**
     * 
     * @param message
     * @param key
     * @return String
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException 
     */
    public static String hmacSha256(byte[] message, String key) throws InvalidKeyException, NoSuchAlgorithmException, Exception {
        byte[] bytes = hmac(hashingAlgorithm, key.getBytes(), message);
        return bytesToHex(bytes);
    }
    
    /**
     * 
     * @param algorithm
     * @param key
     * @param message
     * @return String
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException 
     */
    public static byte[] hmac(String algorithm, byte[] key, byte[] message) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance(algorithm);
        mac.init(new SecretKeySpec(key, algorithm));
        return mac.doFinal(message);
    }
    /**
     * 
     * @param bytes
     * @return String
     */
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
