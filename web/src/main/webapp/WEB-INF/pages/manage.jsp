<%--
  Created by IntelliJ IDEA.
  User: danfoulkes
  Date: 14/11/15
  Time: 17:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="row">
  <ol class="breadcrumb">
    <li><a href="#"><svg class="glyph stroked home"><use xlink:href="#stroked-home"></use></svg></a></li>
    <li class="active">Icons</li>
  </ol>
</div><!--/.row-->

<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">Dashboard</h1>
  </div>
</div><!--/.row-->

<!-- LivingRoom -->
<div class="row">
  <div class="col-xs-12 col-md-6 col-lg-3">
    <div class="panel panel-blue panel-widget ">
      <div class="row no-padding">
        <div class="col-sm-3 col-lg-5 widget-left">
          <svg class="glyph stroked bag">
            <%--<use xlink:href="#stroked-bag"></use>--%>
            <image x="0" y="0" height="50" width="50"  xlink:href="./resources/icons/house131.svg" />
          </svg>
        </div>
        <div class="col-sm-9 col-lg-7 widget-right">
          <div class="large">Living Rooms</div>
          <div class="text-muted"></div>
        </div>
      </div>
    </div>
  </div>
  <div class="col-xs-12 col-md-6 col-lg-3">
    <div class="panel panel-orange panel-widget">
      <div class="row no-padding">
        <div class="col-sm-3 col-lg-5 widget-left">
          <svg class="glyph stroked glyphicons glyphicons-bed"><use xlink:href="#stroked-empty-message"></use></svg>
        </div>
        <div class="col-sm-9 col-lg-7 widget-right">
          <div class="large">Bedroom</div>
          <div class="text-muted"></div>
        </div>
      </div>
    </div>
  </div>
  <div class="col-xs-12 col-md-6 col-lg-3">
    <div class="panel panel-teal panel-widget">
      <div class="row no-padding">
        <div class="col-sm-3 col-lg-5 widget-left">
          <svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg>
        </div>
        <div class="col-sm-9 col-lg-7 widget-right">
          <div class="large">Kitchen</div>
          <div class="text-muted"></div>
        </div>
      </div>
    </div>
  </div>
  <div class="col-xs-12 col-md-6 col-lg-3">
    <div class="panel panel-red panel-widget">
      <div class="row no-padding">
        <div class="col-sm-3 col-lg-5 widget-left">
          <svg class="glyph stroked app-window-with-content"><use xlink:href="#stroked-app-window-with-content"></use></svg>
        </div>
        <div class="col-sm-9 col-lg-7 widget-right">
          <div class="large">Hallway</div>
          <div class="text-muted"></div>
        </div>
      </div>
    </div>
  </div>
</div><!--/.row-->

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">
      <div class="panel-heading">Electricity Usage</div>
      <div class="panel-body">
        <div class="canvas-wrapper">
          <canvas class="main-chart" id="line-chart" height="200" width="600"></canvas>
        </div>
      </div>
    </div>
  </div>
</div><!--/.row-->

<div class="row">
  <div class="col-xs-6 col-md-3">
    <div class="panel panel-default">
      <div class="panel-body easypiechart-panel">
        <h4>Livingroom</h4>
        <div class="easypiechart" id="easypiechart-blue" data-percent="92" ><span class="percent">92%</span>
        </div>
      </div>
    </div>
  </div>
  <div class="col-xs-6 col-md-3">
    <div class="panel panel-default">
      <div class="panel-body easypiechart-panel">
        <h4>Bedroom</h4>
        <div class="easypiechart" id="easypiechart-orange" data-percent="65" ><span class="percent">65%</span>
        </div>
      </div>
    </div>
  </div>
  <div class="col-xs-6 col-md-3">
    <div class="panel panel-default">
      <div class="panel-body easypiechart-panel">
        <h4>Kitchen</h4>
        <div class="easypiechart" id="easypiechart-teal" data-percent="56" ><span class="percent">56%</span>
        </div>
      </div>
    </div>
  </div>
  <div class="col-xs-6 col-md-3">
    <div class="panel panel-default">
      <div class="panel-body easypiechart-panel">
        <h4>Hallway</h4>
        <div class="easypiechart" id="easypiechart-red" data-percent="27" ><span class="percent">27%</span>
        </div>
      </div>
    </div>
  </div>
</div><!--/.row-->

<div class="row">
  <div class="col-md-12Icons">

    <div class="panel panel-default chat">
      <div class="panel-heading" id="accordion"><svg class="glyph stroked two-messages"><use xlink:href="#stroked-two-messages"></use></svg> Activity Log</div>
      <div class="panel-body">
        <ul>
          <li class="left clearfix">
								<span class="chat-img pull-left">
									<img src="http://placehold.it/80/30a5ff/fff" alt="User Avatar" class="img-circle" />
								</span>
            <div class="chat-body clearfix">
              <div class="header">
                <strong class="primary-font">Living Room Light</strong> <small class="text-muted">32 mins ago</small>
              </div>
              <p>
                Light switched on.
              </p>
            </div>
          </li>
          <li class="right clearfix">
								<span class="chat-img pull-right">
									<img src="http://placehold.it/80/dde0e6/5f6468" alt="User Avatar" class="img-circle" />
								</span>
            <div class="chat-body clearfix">
              <div class="header">
                <strong class="pull-left primary-font">Activity - Going to bed</strong> <small class="text-muted">12 hours ago</small>
              </div>
              <p>
                Going to bed schedule called.
              </p>
            </div>
          </li>
          <li class="left clearfix">
								<span class="chat-img pull-left">
									<img src="http://placehold.it/80/30a5ff/fff" alt="User Avatar" class="img-circle" />
								</span>
            <div class="chat-body clearfix">
              <div class="header">
                <strong class="primary-font">John Doe</strong> <small class="text-muted">32 mins ago</small>
              </div>
              <p>
                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla ante turpis, rutrum ut ullamcorper sed, dapibus ac nunc. Vivamus luctus convallis mauris, eu gravida tortor aliquam ultricies.
              </p>
            </div>
          </li>
        </ul>
      </div>

      <div class="panel-footer">
        <%--<div class="input-group">--%>
        <%--<input id="btn-input" type="text" class="form-control input-md" placeholder="Type your message here..." />--%>
        <%--<span class="input-group-btn">--%>
        <%--<button class="btn btn-success btn-md" id="btn-chat">Dashboard</button>--%>
        <%--</span>--%>
        <%--</div>--%>
      </div>
    </div>

  </div><!--/.col-->


  <%--<div class="col-md-4">--%>

  <%--<div class="panel panel-blue">--%>
  <%--<div class="panel-heading dark-overlay"><svg class="glyph stroked clipboard-with-paper"><use xlink:href="#stroked-clipboard-with-paper"></use></svg>To-do List</div>--%>
  <%--<div class="panel-body">--%>
  <%--<ul class="todo-list">--%>
  <%--<li class="todo-list-item">--%>
  <%--<div class="checkbox">--%>
  <%--<input type="checkbox" id="checkbox" />--%>
  <%--<label for="checkbox">Make a plan for today</label>--%>
  <%--</div>--%>
  <%--<div class="pull-right action-buttons">--%>
  <%--<a href="#"><svg class="glyph stroked pencil"><use xlink:href="#stroked-pencil"></use></svg></a>--%>
  <%--<a href="#" class="flag"><svg class="glyph stroked flag"><use xlink:href="#stroked-flag"></use></svg></a>--%>
  <%--<a href="#" class="trash"><svg class="glyph stroked trash"><use xlink:href="#stroked-trash"></use></svg></a>--%>
  <%--</div>--%>
  <%--</li>--%>
  <%--<li class="todo-list-item">--%>
  <%--<div class="checkbox">--%>
  <%--<input type="checkbox" id="checkbox" />--%>
  <%--<label for="checkbox">Update Basecamp</label>--%>
  <%--</div>--%>
  <%--<div class="pull-right action-buttons">--%>
  <%--<a href="#"><svg class="glyph stroked pencil"><use xlink:href="#stroked-pencil"></use></svg></a>--%>
  <%--<a href="#" class="flag"><svg class="glyph stroked flag"><use xlink:href="#stroked-flag"></use></svg></a>--%>
  <%--<a href="#" class="trash"><svg class="glyph stroked trash"><use xlink:href="#stroked-trash"></use></svg></a>--%>
  <%--</div>--%>
  <%--</li>--%>
  <%--<li class="todo-list-item">--%>
  <%--<div class="checkbox">--%>
  <%--<input type="checkbox" id="checkbox" />--%>
  <%--<label for="checkbox">Send email to Jane</label>--%>
  <%--</div>--%>
  <%--<div class="pull-right action-buttons">--%>
  <%--<a href="#"><svg class="glyph stroked pencil"><use xlink:href="#stroked-pencil"></use></svg></a>--%>
  <%--<a href="#" class="flag"><svg class="glyph stroked flag"><use xlink:href="#stroked-flag"></use></svg></a>--%>
  <%--<a href="#" class="trash"><svg class="glyph stroked trash"><use xlink:href="#stroked-trash"></use></svg></a>--%>
  <%--</div>--%>
  <%--</li>--%>
  <%--<li class="todo-list-item">--%>
  <%--<div class="checkbox">--%>
  <%--<input type="checkbox" id="checkbox" />--%>
  <%--<label for="checkbox">Drink coffee</label>--%>
  <%--</div>--%>
  <%--<div class="pull-right action-buttons">--%>
  <%--<a href="#"><svg class="glyph stroked pencil"><use xlink:href="#stroked-pencil"></use></svg></a>--%>
  <%--<a href="#" class="flag"><svg class="glyph stroked flag"><use xlink:href="#stroked-flag"></use></svg></a>--%>
  <%--<a href="#" class="trash"><svg class="glyph stroked trash"><use xlink:href="#stroked-trash"></use></svg></a>--%>
  <%--</div>--%>
  <%--</li>--%>
  <%--<li class="todo-list-item">--%>
  <%--<div class="checkbox">--%>
  <%--<input type="checkbox" id="checkbox" />--%>
  <%--<label for="checkbox">Do some work</label>--%>
  <%--</div>--%>
  <%--<div class="pull-right action-buttons">--%>
  <%--<a href="#"><svg class="glyph stroked pencil"><use xlink:href="#stroked-pencil"></use></svg></a>--%>
  <%--<a href="#" class="flag"><svg class="glyph stroked flag"><use xlink:href="#stroked-flag"></use></svg></a>--%>
  <%--<a href="#" class="trash"><svg class="glyph stroked trash"><use xlink:href="#stroked-trash"></use></svg></a>--%>
  <%--</div>--%>
  <%--</li>--%>
  <%--<li class="todo-list-item">--%>
  <%--<div class="checkbox">--%>
  <%--<input type="checkbox" id="checkbox" />--%>
  <%--<label for="checkbox">Tidy up workspace</label>--%>
  <%--</div>--%>
  <%--<div class="pull-right action-buttons">--%>
  <%--<a href="#"><svg class="glyph stroked pencil"><use xlink:href="#stroked-pencil"></use></svg></a>--%>
  <%--<a href="#" class="flag"><svg class="glyph stroked flag"><use xlink:href="#stroked-flag"></use></svg></a>--%>
  <%--<a href="#" class="trash"><svg class="glyph stroked trash"><use xlink:href="#stroked-trash"></use></svg></a>--%>
  <%--</div>--%>
  <%--</li>--%>
  <%--</ul>--%>
  <%--</div>--%>
  <%--<div class="panel-footer">--%>
  <%--<div class="input-group">--%>
  <%--<input id="btn-input" type="text" class="form-control input-md" placeholder="Add new task" />--%>
  <%--<span class="input-group-btn">--%>
  <%--<button class="btn btn-primary btn-md" id="btn-todo">Add</button>--%>
  <%--</span>--%>
  <%--</div>--%>
  <%--</div>--%>
  <%--</div>--%>

  <%--</div><!--/.col-->--%>
<%--</div><!--/.row-->--%>

  <script src="./resources/js/theme/easypiechart.js"></script>
  <script src="./resources/js/theme/easypiechart-data.js"></script>
  <script src="./resources/js/theme/chart.min.js"></script>
  <script src="./resources/js/theme/chart-data.js"></script>

<script>
  loadChart();
</script>