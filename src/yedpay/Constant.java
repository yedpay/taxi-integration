/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yedpay;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Terrie
 */
public final class Constant {
    
    public static final String DEV = "dev";
    public static final String PRODUCTION = "production";
    public static final String STAGING = "staging";
    
    public static final int INDEX_GATEWAY_ALIPAY = 1;
    public static final int INDEX_GATEWAY_UNIONPAY = 2;
    public static final int INDEX_GATEWAY_ALIPAY_ONLINE = 4;
    
    public static final String GATEWAY_CODE_ALIPAY_ONLINE = "4_1";
    public static final String GATEWAY_CODE_ALIPAY_ONLINE_PC2MOBILE = "4_2";
    public static final String GATEWAY_CODE_WECHATPAY_ONLINE = "8_2";
    public static final List<String> AVAILABLE_GATEWAY_CODE_LIST = Arrays.asList(
            GATEWAY_CODE_ALIPAY_ONLINE, 
            GATEWAY_CODE_ALIPAY_ONLINE_PC2MOBILE,
            GATEWAY_CODE_WECHATPAY_ONLINE
    );
    
    public static final int INDEX_WALLET_HK = 1;
    public static final int INDEX_WALLET_CN = 2;
    public static final String WALLET_HK = "HK";
    public static final String WALLET_CN = "CN";
    
    public static final int INDEX_CURRENCY_HKD = 1;
    public static final int INDEX_CURRENCY_RMB = 2;
    public static final String CURRENCY_HKD = "HKD";
    public static final String CURRENCY_RMB = "RMB";
    
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    
    public static final String PATH_GET_API_KEY = "/session-key/%s";
    public static final String PATH_CREATE_EMVCODE = "/stores/%s/emv-codes";
    public static final String PATH_SHOW_EMVCODE = "/emv-codes/%s";
    public static final String PATH_EMVCODE_TRANSACTION_LIST = "/emv-codes/%s/transactions";
    
    public static final String PATH_PRECREATE = "/precreate/%s";
    public static final String PATH_REFUND = "/transactions/%s/refund";
    public static final String PATH_ONLINE_PAYMENT_CREATE = "/online-payment";
    public static final String PATH_ONLINE_PAYMENT_REFUND = "/online-payment/%s/refund";
 
    public static final String URL_DEV = "https://dev2.yedpay.com";
    public static final String URL_PRODUCTION = "https://api.yedpay.com";
    public static final String URL_STAGING = "https://api-staging.yedpay.com";
    public static final String API_VERSION = "v1";
    public static final String DEV_VERSION = "api";
    
    public static final int INDEX_AUTHENTICATION_ACCESS_TOKEN = 1;
    public static final int INDEX_AUTHENTICATION_API_KEY = 2;
    public static final String AUTHENTICATION_ACCESS_TOKEN = "access_token";
    public static final String AUTHENTICATION_API_KEY = "api_key";

}