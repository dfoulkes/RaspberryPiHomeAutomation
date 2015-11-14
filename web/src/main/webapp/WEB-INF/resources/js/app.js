"use strict";
var homeAutomationApp = angular.module('homeAutomationApp', []);

homeAutomationApp.controller('MainCtrl', function ($scope) {
    $scope.project = {
        name: "Home Automation"
    };

});