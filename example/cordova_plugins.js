cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
    {
        "file": "plugins/org.apache.cordova.device/www/device.js",
        "id": "org.apache.cordova.device.device",
        "clobbers": [
            "device"
        ]
    },
    {
        "file": "plugins/com.cordova.handpointplugin/www/handpoint.js",
        "id": "com.cordova.handpointplugin.HandPointPlugin",
        "clobbers": [
            "HandPointPlugin"
        ]
    }
];
module.exports.metadata = 
// TOP OF METADATA
{
    "org.apache.cordova.device": "0.2.12",
    "com.cordova.handpointplugin": "0.1.0"
}
// BOTTOM OF METADATA
});