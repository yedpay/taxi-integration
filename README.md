[![Build Status](https://travis-ci.org/yedpay/taxi-integration.svg?branch=mater)](https://travis-ci.org/yedpay/taxi-integration)

# Yedpay Taxi Integration Library

## Description

A Java library for Taxi to integrate Yedpay

## Prerequisites
* [Yedpay](https://www.yedpay.com/)

## Integration

#### Initialize taxi integration class
| Parameter | Type | Description |
| --- | --- | --- |
| environment | String | Environment ( 'staging' or 'production' )|
| device id | String | Contact Yedpay to get Device ID|
| secret key | String | Contact Yedpay to get Secret Key|

Input parameters

    String DEVICE_ID = "";		
    String SECRET_KEY = "";

Create instance of Taxi

	Taxi taxi = new Taxi(Constant.STAGING, DEVICE_ID, SECRET_KEY);
	
* Example Get QR String

get QR string

	String result = taxi.getQRString();
	
* Example Get API Key

Get api key

	Response response = taxi.getApiKey();
	
* Example Create Payment QR Code

Input parameters

	// Get from getApiKey()
	String apiKey = "";
	// Get from getApiKey()
	String storeId = "";
	float amount = 0.2f;
	
	
Create payment QR code

	Response response = taxi.createPaymentQRCode(apiKey, storeId, amount);


* Example Query Transaction List

Input parameters

	// Get from getApiKey()
	String apiKey = "";
	// Get from createPaymentQRCode()	
	String emvCodeId = "";
	// Create from createPaymentQRCode(), format "yyyy-MM-dd HH:mm:ss"
	String paymentCreateTime = "";	
	
	
Query transaction list

	Response response = taxi.queryTransactionList(apiKey, emvCodeId, paymentCreateTime);
	