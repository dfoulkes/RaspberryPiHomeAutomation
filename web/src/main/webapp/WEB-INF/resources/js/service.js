

homeAutomationApp.factory('ComponentService', function() {
    var srv = {};
    srv._books = null;

    // Service implementation
    srv.getById = function(id, subId) {
        //
        $.ajax({
            url: "/core/getAllComponents.json",
            async: false
        }).then(function(data) {
            srv._books = data;
        });


        for (var i = 0, n = srv._books.length; i < n; i++) {
            if (id === srv._books[i].uniquieId && subId == srv._books[i].addressDetails) {
                var com =  angular.copy(srv._books[i]);
                //get status
                $.ajax({
                    url: "/core/setComponent.json?status=STATUS&componentId="+id+"&subcommand="+subId,
                    async: false
                }).then(function(data) {
                   var rtnCom = data;

                    if(rtnCom =="ON"){
                        com.status = true;
                    }
                    else{
                        com.status = false;
                    }
                });

                return com;
            }
        }

        return null;
    };

    srv.getAll = function() {
        // Copy the array in order not to expose internal data structures
        return angular.copy(srv._books);
    };

    srv.add = function(com,ip){
      alert("added component");
        $.ajax({
            url: "/core//addCom.html?ip="+ip+"&type="+com,
            async: false
        }).then(function(data) {
            srv._books = data;
        });
        //redirect to route
    };

    // Public API
    return {
        getComponentById: function(id, subId) {
            return srv.getById(id,subId);
        },
        getAll: function() {
            return srv.getAll();
        },
        add: function(com, ip) {
            return srv.add(com,ip);
        }
    };
});


homeAutomationApp.factory('RoomService', function() {
    var srv = {};
    srv._devices = null;

    // Service implementation
    srv.getByRoom = function(room) {
        //
        $.ajax({
            url: "/core/getAllDevices.json",
            async: false
        }).then(function(data) {
            srv._devices = data;
        });

       var roomCollection = [];
        for (var i = 0, n = srv._devices.length; i < n; i++) {
            if (room === srv._devices[i].room) {
                roomCollection.push(angular.copy(srv._devices[i]));
            }
        }
        return roomCollection;
    };

    srv.getAll = function() {
        // Copy the array in _devices not to expose internal data structures
        return angular.copy(srv._books);
    };

    // Public API
    return {
        getByRoom: function(room) {
            return srv.getByRoom(room);
        },
        getAll: function() {
            return srv.getAll();
        }
    };
});