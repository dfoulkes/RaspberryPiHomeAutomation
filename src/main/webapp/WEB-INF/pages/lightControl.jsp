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
</div>


<script type="text/javascript">
    $('#turnOff').click(function(){
        $.getJSON("turnOff?socket=1",
                function(data) {
                  //  alert(data);
                    $('#turnOn').prop('disabled', false);
                    $('#turnOff').prop('disabled', true);
                });
    });

    $('#turnOn').click(function(){
        $.getJSON("turnOn?socket=1",
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
