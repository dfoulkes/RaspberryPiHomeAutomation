

homeAutomationApp.factory('ComponentService', function() {
    var srv = {};
    srv._books = null;
    //srv._books = [
    //    {
    //        id       : '1',
    //        addressType : '1',
    //        type  : 'Light',
    //        status  : 'ON'
    //    },
    //    {
    //        id       : '1',
    //        addressType : '2',
    //        type  : 'Light',
    //        status  : 'OFF'
    //    }
    //];

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

    // Public API
    return {
        getComponentById: function(id, subId) {
            return srv.getById(id,subId);
        },
        getAll: function() {
            return srv.getAll();
        }
    };
});