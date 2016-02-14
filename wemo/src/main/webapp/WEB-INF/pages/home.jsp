<%--
  Created by IntelliJ IDEA.
  User: danfoulkes
  Date: 12/11/15
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html ng-app="homeAutomationApp">
<head>
    <title></title>


    <link href="./resources/css/bootstrap.css" rel="stylesheet">
    <link href="./resources/css/datepicker3.css" rel="stylesheet">
    <link href="./resources/css/styles.css" rel="stylesheet">

    <!--Icons-->
    <script src="./resources/js/theme/lumino.glyphs.js"></script>

</head>
    <body ng-controller="MainCtrl">
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"><span>{{project.name}}</span></a>
                <ul class="user-menu">
                    <li class="dropdown pull-right">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown"><svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg> User <span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                            <li><a href="#"><svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg> Profile</a></li>
                            <li><a href="#"><svg class="glyph stroked gear"><use xlink:href="#stroked-gear"></use></svg> Settings</a></li>
                            <li><a href="#"><svg class="glyph stroked cancel"><use xlink:href="#stroked-cancel"></use></svg> Logout</a></li>
                        </ul>
                    </li>
                </ul>
            </div>

        </div><!-- /.container-fluid -->
    </nav>

    <div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
        <%--<form role="search">--%>
            <%--<div class="form-group">--%>
                <%--<input type="text" class="form-control" placeholder="Search">--%>
            <%--</div>--%>
        <%--</form>--%>
        <ul class="nav menu">
            <li id="dashboardLink" class="active"><a ng-href="#/d/" ><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-dashboard-dial"></use></svg>Activity Watch</a></li>
            <li id="manageLink"><a ng-href="#/m/"><svg class="glyph stroked table"><use xlink:href="#stroked-table"></use></svg> Manage</a></li>
            <li id="alertsLink" ><a href="panels.html"><svg class="glyph stroked app-window"><use xlink:href="#stroked-app-window"></use></svg> Alerts</a></li>
            <li class="parent ">
                <a href="#">
                    <span data-toggle="collapse" href="#sub-item-1"><svg class="glyph stroked chevron-down"><use xlink:href="#stroked-chevron-down"></use></svg></span> Dropdown
                </a>
                <ul class="children collapse" id="sub-item-1">
                    <li>
                        <a class="" href="#">
                            <svg class="glyph stroked chevron-right"><use xlink:href="#stroked-chevron-right"></use></svg> Sub Item 1
                        </a>
                    </li>
                    <li>
                        <a class="" href="#">
                            <svg class="glyph stroked chevron-right"><use xlink:href="#stroked-chevron-right"></use></svg> Sub Item 2
                        </a>
                    </li>
                    <li>
                        <a class="" href="#">
                            <svg class="glyph stroked chevron-right"><use xlink:href="#stroked-chevron-right"></use></svg> Sub Item 3
                        </a>
                    </li>
                </ul>
            </li>
            <li role="presentation" class="divider"></li>
            <li><a href="login.html"><svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg> Login Page</a></li>
        </ul>
    </div><!--/.sidebar-->

    <div ng-view class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">

    </div>	<!--/.main-->

        <script src="./resources/js/theme/jquery-1.11.1.min.js"></script>
        <script src="./resources/bower_components/bootstrap/dist/js/bootstrap.js"></script>
        <script src="./resources/js/theme/bootstrap-datepicker.js"></script>

        <script type="application/javascript" src="./resources/bower_components/angular/angular.js"></script>
        <script type="application/javascript" src="./resources/bower_components/angular-route/angular-route.js"></script>
        <script type="application/javascript" src="./resources/js/app.js"></script>
        <script type="application/javascript" src="./resources/js/service.js"></script>

    <script>
        !function ($) {
            $(document).on("click","ul.nav li.parent > a > span.icon", function(){
                $(this).find('em:first').toggleClass("glyphicon-minus");
            });
            $(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
        }(window.jQuery);

        $(window).on('resize', function () {
            if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
        })
        $(window).on('resize', function () {
            if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
        })

        $( "#dashboardLink" ).click(function() {
            removeAllSelected();
            $("#dashboardLink" ).addClass( "active");
        });
        $( "#manageLink" ).click(function() {
            removeAllSelected();
            $("#manageLink" ).addClass( "active");
        });
        $( "#alertsLink" ).click(function() {
            removeAllSelected();
            $("#alertsLink" ).addClass( "active");
        });

        function removeAllSelected(){
            $( "#dashboardLink" ).removeClass( "active");
            $( "#manageLink" ).removeClass( "active");
            $( "#alertsLink" ).removeClass( "active");
        }

    </script>


    </body>
</html>
