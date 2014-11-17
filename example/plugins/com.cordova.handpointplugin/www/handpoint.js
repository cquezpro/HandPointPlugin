cordova.define("com.cordova.handpointplugin.HandPointPlugin", function(require, exports, module) { /*global cordova*/
module.exports = {

    connect: function (name, address, port, method, success, failure) {
        cordova.exec(success, failure, "HandPointPlugin", "connect", [{"name": name, "address": address, "port": port, "method": method}]);
    },
	pay: function (price, currency, success, failure) {
        cordova.exec(success, failure, "HandPointPlugin", "pay", [{"price": price, "currency": currency}]);
    }
};
});
