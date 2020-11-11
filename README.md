
# Yedpay Taxi Integration Library

## Description

A Java library for Taxi to integrate Yedpay

## Prerequisites
* [Yedpay](https://www.yedpay.com/)

## Integration

#### Initialize taxi integration class
| Parameter | Type | Description |
| --- | --- | --- |
| environment | String | Environment ( 'dev' or 'staging' or 'production' )|
| device id | String | |
| secret key | String | |

Input parameters

    String DEVICE_ID = "";		
    String SECRET_KEY = "";

Create instance of Taxi

	Taxi taxi = new Taxi(Constant.DEV, DEVICE_ID, SECRET_KEY);
	
* Example Get QR String

	String result = taxi.getQRString();
	
* Example Get API Key

	Response response = taxi.getApiKey();
	
* Example Create Payment QR Code

Input parameters

	String apiKey = "";		// Get from getApiKey()
	String storeId = ""; 	// Get from getApiKey()
	float amount = 0.2f;
	
	Response response = taxi.createPaymentQRCode(apiKey, storeId, amount);

* Example Query Transaction List

	String apiKey = "";				// Get from getApiKey()
	String emvCodeId = "";			// Get from createPaymentQRCode()
	String paymentCreateTime = "";	// Create from createPaymentQRCode(), format "yyyy-MM-dd HH:mm:ss"
	
	Response response = taxi.queryTransactionList(apiKey, emvCodeId, paymentCreateTime);
	