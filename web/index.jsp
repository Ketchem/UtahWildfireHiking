<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UtahWildfireHiking</title>

    <!-- Custom styles -->
    <link rel="stylesheet" href="css/style.css">

    <!-- jQuery -->
    <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="//code.jquery.com/jquery-migrate-1.2.1.min.js"></script>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

    <!-- Google -->
    <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_KEY&libraries=geometry,places,visualization"></script>

</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <a class="navbar-brand">Utah Wildfire Hiking Trail Portal</a>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="sidebar col-xs-4">
            <ul class="nav nav-tabs">
                <li class="active"><a href="#add_review" data-toggle="tab">Add Review</a></li>
                <li><a href="#query_trail" data-toggle="tab">Query Trails</a></li>
                <li><a href="#query_wildfire" data-toggle="tab">Query Wildfires</a></li>
            </ul>

            <div class="tab-content">
                <!-- Add Review Panel -->
                <div class="tab-pane active" id="add_review">
                    <form id="add_review_form">
                        <h2>Add Review</h2>
                    </form>
                </div>

                <!-- Query Trail Panel -->
                <div class="tab-pane active" id="query_trail">
                    <form id="query_trail_form">
                        <h2>Query Trail</h2>
                    </form>
                </div>
                <!-- Query Wildfire Panel -->
                <div class="tab-pane active" id="query_wildfire">
                    <form id="query_wildfire_form">
                        <h2>Query Wildfire</h2>
                    </form>
                </div>
            </div>

        </div>

        <div id="map-canvas" class="col-xs-9"></div>
    </div>
</div>

<script src ="js/loadmap.js"></script>
</body>
</html>
