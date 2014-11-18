cordova.define("com.cordova.handpointplugin.HandPointPlugin", function(require, exports, module) { /*global cordova*/
module.exports = {

    connect: function (name, address, port, method, success, failure) {
        cordova.exec(success, failure, "HandPointPlugin", "connect", [{"name": name, "address": address, "port": port, "method": method}]);
    },
    connectWithCurrentDevice: function (success, failure) {
        cordova.exec(success, failure, "HandPointPlugin", "connectWithCurrentDevice", []);
    },
	pay: function (price, currency, success, failure) {
        cordova.exec(success, failure, "HandPointPlugin", "pay", [{"price": price, "currency": currency}]);
    },
    SetMerchantKey: function (key, success, failure) {
        cordova.exec(success, failure, "HandPointPlugin", "SetMerchantKey", [{"key":key}]);
    },
    SetDeviceName: function (name, success, failure) {
        cordova.exec(success, failure, "HandPointPlugin", "SetDeviceName", [{"name":name}]);
    },
    ListDevices: function (success, failure) {
        cordova.exec(success, failure, "HandPointPlugin", "ListDevices", []);
    },
    init: function (success, failure) {
        cordova.exec(success, failure, "HandPointPlugin", "init", []);
    }
	
  };
});
