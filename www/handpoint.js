/*global cordova*/
module.exports = {

    connect: function (name, address, port, method, success, failure) {
        cordova.exec(success, failure, "HandPointPlugin", "connect", [name], [address], [port], [method]);
    }
};
