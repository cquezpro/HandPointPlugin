package com.cordova.handpointplugin;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.cordova.*; // Cordova 3.x
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.apache.cordova.LOG;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;

import android.content.Context;

import com.handpoint.api.*;


public class HandPointPlugin extends CordovaPlugin implements Events.Required {
    
	Hapi api;
    Device device;
    @SuppressWarnings("unused")
	private CallbackContext callbackContext;
    
    // Debugging
    private static final String TAG = "HandPoint";
    private static final boolean D = true;
    
    private String[] ConnectioMethod = {"USB", "SERIAL", "BLUETOOTH", "HTTPS", "WIFI", "ETHERNET", "SIMULATOR"};
    

    /*public HandPointPlugin(Context context){
        initApi(context);
    }*/
    
    public HandPointPlugin() {
    	super();
        //Context context = this.cordova.getActivity().getApplicationContext();
        //initApi(context);        
    }
    
    public HandPointPlugin(Context context){
        initApi(context);
    }

    
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    	 LOG.d(TAG, "action = " + action);
    	 
    	 Context context = this.cordova.getActivity().getApplicationContext();
         initApi(context); 
         
    	 this.callbackContext = callbackContext;
         
         boolean retValue = true;
         if (action.equals("init")) {
             //Initialize  API
        	 
        	 retValue = true;
         } else if (action.equals("pay")) {
        	 //Pay
        	 pay(args, callbackContext);
        	 retValue = true;
         } else if (action.equals("connect")) {
             //Connect Device
        	 connect(args, callbackContext);
        	 retValue = true;
         } else {
             retValue = false;
         }

         return retValue;
    }

    public void initApi(Context context){
        String sharedSecret = "0102030405060708091011121314151617181920212223242526272829303132";
        this.api = HapiFactory.getAsyncInterface(this, context).defaultSharedSecret(sharedSecret);

        //The api is now initialized. Yay! we've even set a default shared secret!
        //But we need to connect to a device to start taking payments.
        //Let's search for them:
        this.api.listDevices(ConnectionMethod.BLUETOOTH);
        //This triggers the search, you should expect the results in the listener defined above
    }
    
    public void connect(JSONArray args, CallbackContext callbackContext) throws JSONException {
    	JSONObject obj = args.optJSONObject(0);
    	String name = obj.optString("name");
        String address = obj.optString("address");
    	String port = obj.optString("port");
    	String connectionMethod = obj.optString("method");
    	ConnectionMethod method;
    	
    	method = ConnectionMethod.BLUETOOTH;
    	if(connectionMethod == "USB") {
    		method = ConnectionMethod.USB;
    	}
    	if(connectionMethod == "SERIAL") {
    		method = ConnectionMethod.SERIAL;
    	}
    	if(connectionMethod == "BLUETOOTH") {
    		method = ConnectionMethod.BLUETOOTH;
    	}
    	if(connectionMethod == "HTTPS") {
    		method = ConnectionMethod.HTTPS;
    	}
    	if(connectionMethod == "WIFI") {
    		method = ConnectionMethod.WIFI;
    	}
    	if(connectionMethod == "ETHERNET") {
    		method = ConnectionMethod.ETHERNET;
    	}
    	if(connectionMethod == "SIMULATOR") {
    		method = ConnectionMethod.SIMULATOR;
    	}
    	
    	Device device = new Device(name, address, port, method);

    	api.useDevice(device);
    	
    	callbackContext.success();
    }
    
    public void disconnect() throws JSONException {
    	//Disconnect from current device
    	api.disconnect();
    }
    
    
    @Override
    public void deviceDiscoveryFinished(List<Device> devices){
        for(Device device : devices){
            if(device.getName().equals("MyDeviceName")){
                //We'll remember the device for this session, but is cool that you do too
                this.device = device;
                this.api.useDevice(this.device);
            }
        }
    }

    public boolean pay(JSONArray args, CallbackContext callbackContext) throws JSONException{
    	String price;
    	String currency;
    	JSONObject obj = args.optJSONObject(0);
    	
    	price = obj.optString("price");
        currency = obj.optString("currency");
    	
    	Currency _currency;
    	_currency = Currency.GBP;
    	if(currency == "GBP") {
    		_currency = Currency.GBP;
    	}
    	if(currency == "ZAR") {
    		_currency = Currency.ZAR;
    	}
    	if(currency == "USD") {
    		_currency = Currency.USD;
    	}
    	if(currency == "EUR") {
    		_currency = Currency.EUR;
    	}
    	if(currency == "CNY") {
    		_currency = Currency.CNY;
    	}
    	if(currency == "EGP") {
    		_currency = Currency.EGP;
    	}
    	if(currency == "INR") {
    		_currency = Currency.INR;
    	}
    	if(currency == "UAH") {
    		_currency = Currency.UAH;
    	}
    	if(currency == "TWD") {
    		_currency = Currency.TWD;
    	}
    	if(currency == "AUD") {
    		_currency = Currency.AUD;
    	}
    	if(currency == "CAD") {
    		_currency = Currency.CAD;
    	}
    	if(currency == "SGD") {
    		_currency = Currency.SGD;
    	}
    	if(currency == "CHF") {
    		_currency = Currency.CHF;
    	}
    	if(currency == "MYR") {
    		_currency = Currency.MYR;
    	}
    	if(currency == "JPY") {
    		_currency = Currency.JPY;
    	}
    	
    	boolean bReturn =  this.api.sale(new BigInteger(price), _currency);
    	if(bReturn == true ){
    		callbackContext.success("success");
    	}else{
    		callbackContext.error("fail");
    	}
    	
        return this.api.sale(new BigInteger(price), _currency);
    }

    @Override
    public void signatureRequired(SignatureRequest signatureRequest, Device device){
        //You'll be notified here if a sale process needs signature verification
        //See documentation
    }

    @Override
    public void endOfTransaction(TransactionResult transactionResult, Device device){
	        if(transactionResult.getFinStatus() == FinancialStatus.AUTHORISED){
	            //...
	        }
    }

}
