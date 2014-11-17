# HandPoint Android Plugin for PhoneGap

Reference site: handpoint.com

This plugin is Cordova Plugin for https://www.handpoint.com android platform.

API Example:
1)Connect Device
-reference 
https://www.handpoint.com/docs/device/Android/#elem_useDevice
-corodva API
HandPointPlugin.connect("CardReader7", "08:00:69:02:01:FC", "1", "BLUETOOTH", connectSuccess, connectFail);

2)Pay
-reference 
https://www.handpoint.com/docs/device/Android/#elem_sale
-cordova API
HandPointPlugin.pay("1000","GBP",paySuccess, payFail);

