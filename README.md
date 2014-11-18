# HandPoint Android Plugin for PhoneGap

Reference site: handpoint.com

This plugin is Cordova Plugin for https://www.handpoint.com android platform.

API Example:

1)SetMerchantKey

parameter: key

2)Init

Initialze the API

3)SetDeviceName

parameter: device name

4)ListDevices

show the connected device list

return value:name, address, port

5)connectwithCurrentDevice

connect with device set by SetDeviceName

6)Connect Device

-reference 
https://www.handpoint.com/docs/device/Android/#elem_useDevice

-corodva API

HandPointPlugin.connect("CardReader7", "08:00:69:02:01:FC", "1", "BLUETOOTH", connectSuccess, connectFail);

7)Pay

-reference 

https://www.handpoint.com/docs/device/Android/#elem_sale

-cordova API

HandPointPlugin.pay("1000","GBP",paySuccess, payFail);

