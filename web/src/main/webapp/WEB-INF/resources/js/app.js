"use strict";
var homeAutomationApp = angular.module('homeAutomationApp', ['ngRoute']);

homeAutomationApp.config(function ($routeProvider) {
    $routeProvider.when('/', {
        //templateUrl: 'templates/book_details_with_expressions.html',
        templateUrl: './dashboard',
        controller: 'MainCtrl'
    })
        .when('/manage', {
            templateUrl: 'templates/book_list.html',
            controller: 'MainCtrl'
        })
        .when('/dashboard', {
            templateUrl: './dashboard',
            controller: 'MainCtrl'
        })
        .otherwise({
            redirectTo: '/'
        });
});

homeAutomationApp.controller('MainCtrl', function ($scope) {
    $scope.project = {
        name: "Home Automation"
    };

});