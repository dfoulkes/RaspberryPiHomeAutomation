<%--
  Created by IntelliJ IDEA.
  User: danfoulkes
  Date: 14/04/15
  Time: 21:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="resources/Scripts/jquery-1.11.2.js"></script>
    <script type="text/javascript" src="resources/bootstrap-3.3.4/js/bootstrap.min.js"></script>

    <link rel="stylesheet" href="resources/bootstrap-3.3.4/css/bootstrap.min.css" >
    <link rel="stylesheet" href="resources/bootstrap-3.3.4/css/bootstrap-theme.css">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div class="container">
    <h1>The Walter Cam</h1>
    <div class="row">
        <div class="col-xs-6"><button class="btn" id="turnOff">Turn Off</button></div>
        <div class="col-xs-6"><button class="btn" id="turnOn">Turn On</button></div>
    </div>
    <div class="row">
        <iframe src="https://www.ustream.tv/embed/18610915?wmode=direct&volume=0&showtitle=false" style="border: 0 none transparent;"  webkitallowfullscreen allowfullscreen frameborder="no" width="360" height="235"></iframe><br /><a href="https://www.ustream.tv/" style="padding: 2px 0px 4px; width: 400px; background: #ffffff; display: block; color: #000000; font-weight: normal; font-size: 10px; text-decoration: underline; text-align: center;" target="_blank">Live streaming video by Ustream</a>
    </div>
    <div class="row">
        <div class="col-xs-6"><button class="btn" id="turnOff2">Turn Off</button></div>
        <div class="col-xs-6"><button class="btn" id="turnOn2">Turn On</button></div>
    </div>
    <div class="row">
       <h1>Light Switch</h1>

    </div>
</div>


<script type="text/javascript">
    $('#turnOff2').click(function(){
        $.getJSON("turnOff?socket=2",
                function(data) {
                  //  alert(data);
                    $('#turnOn').prop('disabled', false);
                    $('#turnOff').prop('disabled', true);
                });
    });

    $('#turnOn2').click(function(){
        $.getJSON("turnOn?socket=2",
                function(data) {
                  //  alert(data);
                    $('#turnOn').prop('disabled', true);
                    $('#turnOff').prop('disabled', false);
                });
    });


    $.getJSON("getStatus?socket=1",
            function(data) {
               isOn = data.status;
                if(isOn == true){
                    $('#turnOn').prop('disabled', true);
                    $('#turnOff').prop('disabled', false);
                }
                else{
                    $('#turnOn').prop('disabled', false);
                    $('#turnOff').prop('disabled', true);
                }

            });

</script>
</body>
</html>
