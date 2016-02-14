"use strict";
var homeAutomationApp = angular.module('homeAutomationApp', ['ngRoute']);

homeAutomationApp.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: './dashboard',
        controller: 'MainCtrl'
    })
        .when('/m', {
            templateUrl: './manage',
            controller: 'MaintenanceCtrl'
        }).when('/add', {
            templateUrl: './add',
            controller: 'AddCtrl'
        }).
        when('/update', {
            templateUrl: './update',
            controller: 'UpdateCtrl'
        }).
        when('/updateDevice/:componentId', {
            templateUrl: './updateDevice',
            controller: 'UpdateDeviceCtrl'
        })
        .when('/d', {
            templateUrl: './dashboard',
            controller: 'MainCtrl'
        }).
        when('/device/:componentId/:subcommand', {
            templateUrl: './deviceDetails',
            controller: 'ComponentCtrl'
        }).
        when('/device', {
            templateUrl: './deviceDetails',
            controller: 'ComponentCtrl'
        }).
        when('/room', {
            templateUrl: './rooms',
            controller: 'RoomCtrl'
        })
        .otherwise({
            redirectTo: '/d'
        });
});

homeAutomationApp.controller('MainCtrl', function ($scope) {
    $scope.project = {
        name: "Home Automation (Dashboard)"
    }

}
);

homeAutomationApp.controller('MaintenanceCtrl', function ($scope, $routeParams, ComponentService) {
    $scope.project = {
        name: "Home Automation (Management)"
    }

});


homeAutomationApp.controller('UpdateCtrl', function ($scope, $routeParams, ComponentService) {
    $scope.project = {
        name: "Home Automation (Management)"
    }

});

homeAutomationApp.controller('AddCtrl', function ($scope, $routeParams, ComponentService, $location) {
    $scope.project = {
        name: "Home Automation (Add Component)"
    }
    $scope.comType = '';
    $scope.ipAddress = '';
    $scope.port ='';


    $scope.submit = function() {
        ComponentService.add($scope.comType,$scope.ipAddress,$scope.port);
        $location.path("./#/m/");

    };


});

//UpdateDeviceCtrl
homeAutomationApp.controller('UpdateDeviceCtrl', function ($scope , $routeParams, ComponentService) {
    $scope.project = {
        name: "Home Automation (Management)"
    }
    $scope.oldComponent = $routeParams.componentId;
    $scope.componentId = $routeParams.componentId;
    $scope.component = ComponentService.getByIdComponent($routeParams.componentId, $routeParams.subcommand);
    $scope.updateComponent= function(){
        $.ajax({
            url: "/core/updateComponent.json?oldComponent="+$scope.oldComponent+"&componentId="+$scope.component.uniquieId+"&ip="+$scope.component.ip+"&address="+$scope.component.addressDetails,
            async: true
        }).then(function(data) {

        });
    }

});

homeAutomationApp.controller('ComponentCtrl', function ($scope , $routeParams, ComponentService) {
    $scope.project = {
        name: "Home Automation (Management)"
    }
    $scope.componentId = $routeParams.componentId;
    $scope.component = ComponentService.getComponentById($routeParams.componentId, $routeParams.subcommand);

    $scope.updateSwitch= function(){
        //update switch
        //alert($scope.component.status);
        var state;

        if($scope.component.status == true){
            state = "ON";
        }
        else if($scope.component.status == false){
            state = "OFF";
        }
        $.ajax({
            url: "/core/setComponent.json?status="+state+"&componentId="+$scope.component.uniquieId+"&subcommand="+$scope.component.addressDetails,
            async: true
        }).then(function(data) {

            if(data == "ON"){
                $scope.component.status = true;
            }
            else if(data == "OFF"){
                $scope.component.status = false;
            }
        });
    }
});

homeAutomationApp.controller('RoomCtrl', function ($scope , $routeParams, RoomService) {
    $scope.project = {
        name: "Home Automation (Management)"
    }
    $scope.componentId = $routeParams.componentId;
    $scope.rooms = RoomService.getAll();
});



homeAutomationApp.directive('bootstrapSwitch', [
    function() {
        return {
            restrict: 'A',
            require: '?ngModel',
            link: function(scope, element, attrs, ngModel) {
                element.bootstrapSwitch();

                element.on('switchChange.bootstrapSwitch', function(event, state) {
                    if (ngModel) {
                        scope.$apply(function() {
                            ngModel.$setViewValue(state);
                        });
                    }
                });

                scope.$watch(attrs.ngModel, function(newValue, oldValue) {
                    if (newValue) {
                        element.bootstrapSwitch('state', true, true);
                    } else {
                        element.bootstrapSwitch('state', false, true);
                    }
                });
            }
        };
    }
]);