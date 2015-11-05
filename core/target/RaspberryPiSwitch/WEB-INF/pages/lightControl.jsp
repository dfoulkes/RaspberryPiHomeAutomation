<%--
  Created by IntelliJ IDEA.
  User: danfoulkes
  Date: 14/04/15
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <script type="text/javascript" src="resources/Scripts/jquery-1.11.2.js"></script>
    <script type="text/javascript" src="resources/bootstrap-3.3.4/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resources/bootstrap-3.3.4/js/bootstrap-switch.min.js"></script>

    <link rel="stylesheet" href="resources/bootstrap-3.3.4/css/bootstrap.min.css" >
    <link rel="stylesheet" href="resources/bootstrap-3.3.4/css/bootstrap-theme.css">
    <link rel="stylesheet" href="resources/bootstrap-3.3.4/css/bootstrap-switch.css">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<script type="text/javascript">

    $( document ).ready(function() {


        $("[name='switch1']").bootstrapSwitch();
        $("[name='switch2']").bootstrapSwitch();

        $('input[name="switch1"]').on('switchChange.bootstrapSwitch', function(event, state) {
            var url;
            if(state){
                url = "turnOn?socket=1";
            }
            else{
                url = "turnOff?socket=1";
            }

            $.getJSON(url,
                    function(data) {
                        if(data.status == true){
                            //turn on
                            $('input[name="switch1"]').bootstrapSwitch('state', true, true);
                        }
                        else{
                            //turn off
                            $('input[name="switch1"]').bootstrapSwitch('state', false, true);
                        }
                    });
        });

        $('input[name="switch2"]').on('switchChange.bootstrapSwitch', function(event, state) {

            var url;
            if(state){
                url = "turnOn?socket=2";
            }
            else{
                url = "turnOff?socket=2";
            }

            $.getJSON(url,
                    function(data) {
                        if(data.status == true){
                            //turn on
                            $('input[name="switch2"]').bootstrapSwitch('state', true, true);
                        }
                        else{
                            //turn off
                            $('input[name="switch2"]').bootstrapSwitch('state', false, true);
                        }
                    });
        });

        $.getJSON("getStatus?socket=1",
                function(data) {
                    isOn = data.status;
                    if(isOn == true){
                            //turn on
                        $('input[name="switch1"]').bootstrapSwitch('state', true, true);
                    }
                    else{
                            // turn off
                        $('input[name="switch1"]').bootstrapSwitch('state', false, true);
                    }

                });
        $.getJSON("getStatus?socket=2",
                function(data) {
                    isOn = data.status;
                    if(isOn == true){
                       //TURN ON
                        $('input[name="switch2"]').bootstrapSwitch('state', true, true);
                    }
                    else{
                        //TURN OFF
                        $('input[name="switch2"]').bootstrapSwitch('state', false, true);
                    }

                });
    });
</script>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="page-header">
                <h1>
                    Home Automation <small>Making Life Simpler</small>
                </h1>
            </div>
            <div class="media">
                <iframe src="http://www.ustream.tv/embed/18610915?wmode=direct&volume=0&showtitle=false" style="border: 0 none transparent;"  webkitallowfullscreen allowfullscreen frameborder="no" width="100%" height="20%"></iframe>
            </div>
        </div>
        <%--<ul class="nav nav-pills">--%>
        <%--<li class="active">--%>
        <%--<a href="#"> <span class="badge pull-right">42</span> Home</a>--%>
        <%--</li>--%>
        <%--<li>--%>
        <%--<a href="#"> <span class="badge pull-right">16</span> More</a>--%>
        <%--</li>--%>
        <%--</ul>--%>
        <div class="row ">
            <div class="col-sm-6 col-lg-4">
                <h2>Switch 1 (Walter)</h2>
                <p><input type="checkbox" name="switch1" id="switch1"/></p>
            </div>
        </div>
        <div class="row ">
            <div class="col-sm-6 col-lg-4">
                <h2>Switch 2 (Lights)</h2>
                <p><input type="checkbox" name="switch2" id="switch2"/></p>
            </div>
        </div>

    </div>
</div>

</body>
</html>
