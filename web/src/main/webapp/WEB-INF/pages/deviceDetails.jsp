<link rel="stylesheet" href="./resources/bower_components/bootstrap-switch/dist/css/bootstrap3/bootstrap-switch.css">

<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
  <div class="row">
    <div class="row">
      <div class="col-lg-12">
        <h1 class="page-header">{{component.componentType}}</h1>
      </div>
    </div><!--/.row-->
    <div class="row">





      <!-- -->
      <div class="row ng-scope">

        <!-- start -->
        <div class="panel panel-default">
          <div class="panel-heading" id="accordion"> Node {{component.addressDetails}}</div>
          <div class="panel-body">
                  <div ng-if="component.genericType == 'LIGHT'">
                    <form name="form" class="container" ng-controller="ComponentCtrl">
                      <input style="width:200px" ng-change="updateSwitch()" type="checkbox" ng-model="component.status" bootstrap-switch/>
                    </form>
                  </div>
          </div>
        </div>
      </div>
        <!-- end -->

      </div>
      <!-- -->
      <script src="./resources/bower_components/bootstrap-switch/dist/js/bootstrap-switch.js"></script>

    </div>
  </div>
</div>