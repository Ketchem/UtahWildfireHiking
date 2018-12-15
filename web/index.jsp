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
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyB96h7i2VBoDzWPsDIF2QvvakaN6qxWFEE&libraries=geometry,places,visualization"></script>

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
                <li><a href="#query_trail" data-toggle="tab">Query Trail Reviews</a></li>
                <!--<li><a href="#query_wildfire" data-toggle="tab">Query Wildfires</a></li>-->
            </ul>

            <div class="tab-content">

                <!-- Add Review Panel -->
                <div class="tab-pane active" id="add_review">
                    <form id="add_review_form">
                        <h2>Add Review</h2>
                        <!--<div><label>Zoom to Location:</label>
                            <input id="autocomplete" placeholder="Location"></div> -->
                        <div><label>Name:&nbsp</label><input placeholder="Trail Name" name="trail_name"></div>
                        <div><label>Date Hiked:&nbsp</label><input placeholder="Date mm/dd/yyyy" name="date_added"></div>
                        <!--<div><label>Trail ID(Required):</label><input placeholder="Trail ID" name="trail_id"></div>-->
                        <div>
                            <label><input type="radio" name="active" value="t">&nbspActive</label>
                            <label><input type="radio" name="active" value="f">&nbspInactive</label>
                        </div>
                        <div><label>Rating:</label>
                            <label><input type="radio" name="rating" value="1">&nbsp1</label>
                            <label><input type="radio" name="rating" value="2">&nbsp2</label>
                            <label><input type="radio" name="rating" value="3">&nbsp3</label>
                            <label><input type="radio" name="rating" value="4">&nbsp4</label>
                            <label><input type="radio" name="rating" value="5">&nbsp5</label>
                        </div>
                        <div>
                        <label>Click Map or enter</label><div><label> Latitude:</label>
                        <input type="text" id='lat' name="latitude"></div>
                        <div><label>Longitude:</label><input type="text" id='lon' name="longitude"></div>
                        </div>
                        <div><label>Comment:&nbsp</label><input placeholder="Comments" name="comments"></div>
                        <button type="submit" class="btn btn-default" id="report_submit_btn">
                            <span class="glyphicon glyphicon-star"></span> Submit
                        </button>
                    </form>
                </div>


                <!-- Query Trail Panel -->
                <div class="tab-pane" id="query_trail">
                    <form id="query_review_form">
                        <h2>Query Reviews</h2>
                        <div><label>Trail Rating:</label>
                            <select name="q_rating">
                                <option value="">Choose Trail Rating</option>
                                <option value="1+">1+</option>
                                <option value="2+">2+</option>
                                <option value="3+">3+</option>
                                <option value="4+">4+</option>
                                <option value="5">5</option>
                            </select>
                        </div>
                        <!--<div class="additional_msg_div" style="visibility: hidden"><label class="additional_msg"></label>
                            <select class="additional_msg_select" name="BIKE"></select>
                        </div>-->
                        <div><label>Comment Key Word: </label>
                            <input type="text" name="q_keyword">
                        </div>
                        <!--<div class="additional_msg_div" style="visibility: hidden"><label class="additional_msg"></label>
                            <select class="additional_msg_select" name="HIKE"></select>
                        </div>-->
                        <div><label>Trail Name</label>
                            <input type="text" name="q_trail_name">
                        </div>
                        <button type="submit" class="btn btn-default" id="query_submit_btn">
                            <span class="glyphicon glyphicon-star"></span> Submit the query
                        </button>
                    </form>
                </div>

                <!-- Query Wildfire Panel -->
                <!--
                <div class="tab-pane" id="query_wildfire">
                    <form id="query_wildfire_form">
                        <h2>Query Wildfire</h2>
                    </form>
                </div> -->
            </div>

        </div>

        <div id="map-canvas" class="col-xs-8"></div>
    </div>
</div>

<script src ="js/loadform.js"></script>
<script src ="js/loadmap.js"></script>

</body>
</html>
