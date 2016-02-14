<%--
  Created by IntelliJ IDEA.
  User: danfoulkes
  Date: 14/11/15
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
  <div class="row">
    <ol class="breadcrumb">
      <li><a href="#"><svg class="glyph stroked home"><use xlink:href="#stroked-home"></use></svg></a></li>
      <li class="active">Icons</li>
    </ol>
  </div><!--/.row-->

  <div class="row">
    <div class="col-lg-12">
      <h1 class="page-header">Tables</h1>
    </div>
  </div><!--/.row-->


  <div class="row">
    <div class="col-lg-12">
      <div class="panel panel-default">
        <div class="panel-heading">Manage Rooms</div>
        <div class="panel-body">

            <table
                   id="allComponents" data-toggle="table" data-url="/core/getAllComponents.json" data-pagination="true" data-sort-order="desc">
            <thead>
            <tr>
              <th data-field="uniquieId" ng-href="componentId" data-sortable="true">Component Handler</th>
              <th data-field="componentType" data-sortable="true">Component Type</th>
              <th data-field="assigned">Assigned</th>
              <th data-field="addressDetails" data-sortable="true">Component Number</th>
              <th data-field="ip" data-sortable="true">IP</th>
            </tr>
            </thead>
          </table>

        </div>
      </div>
    </div>
  </div><!--/.row-->
  <div class="row">
    <div class="col-md-6">
      <div class="panel panel-default">
        <div class="panel-heading">Basic Table</div>
        <div class="panel-body">
        </div>
      </div>
    </div>
    <div class="col-md-6">
      <div class="panel panel-default">
        <div class="panel-heading">Styled Table</div>
        <div class="panel-body">
          <script>


//                $('#allComponents').on('click-row.bs.table', function (e, row, $element) {
//                    console.log("id is:"+row.uniquieId);
//                    window.location.href = '#/device/'+row.uniquieId+"/"+row.addressDetails;
//                });


            $(function () {
              $('#hover, #striped, #condensed').click(function () {
                var classes = 'table';

                if ($('#hover').prop('checked')) {
                  classes += ' table-hover';
                }
                if ($('#condensed').prop('checked')) {
                  classes += ' table-condensed';
                }
                $('#table-style').bootstrapTable('destroy')
                        .bootstrapTable({
                          classes: classes,
                          striped: $('#striped').prop('checked')
                        });
              });
            });

            function rowStyle(row, index) {
              var classes = ['active', 'success', 'info', 'warning', 'danger'];

              if (index % 2 === 0 && index / 2 < classes.length) {
                return {
                  classes: classes[index / 2]
                };
              }
              return {};
            }
          </script>
        </div>
      </div>
    </div>
  </div><!--/.row-->


</div><!--/.main-->




<script src="./resources/js/theme/chart.min.js"></script>
<script src="./resources/js/theme/chart-data.js"></script>
<script src="./resources/js/theme/easypiechart.js"></script>
<script src="./resources/js/theme/easypiechart-data.js"></script>
<script src="./resources/js/theme/bootstrap-datepicker.js"></script>
<script src="./resources/js/theme/bootstrap-table.js"></script>
