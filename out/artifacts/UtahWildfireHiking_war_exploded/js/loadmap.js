var map;
var infowindow = new google.maps.InfoWindow();
var place;
var autocomplete;
//variable for the marker used to create a new trail
var newMarker;

function initialization() {
    showAllReports();
    initAutocomplete();
    onPlaceChanged();
}

function showAllReports() {
    $.ajax({
        url: 'HttpServlet',
        type: 'POST',
        data: { "tab_id": "1"},
        success: function(reports) {
            mapInitialization(reports);
        },
        error: function(xhr, status, error) {
            alert("An AJAX error occured: " + status + "\nError: " + error);
        }
    });
}

function mapInitialization(reports) {
    var mapOptions = {
        mapTypeId : google.maps.MapTypeId.ROADMAP, // Set the type of Map
    };

    // Render the map within the empty div
    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

    var bounds = new google.maps.LatLngBounds ();

    $.each(reports, function(i, e) {
        var long = Number(e['longitude']);
        var lat = Number(e['latitude']);
        var latlng = new google.maps.LatLng(lat, long);

        bounds.extend(latlng);

        // Create the infoWindow content
        var contentStr = '<h4>Trailhead Information</h4><hr>';
        contentStr += '<p><b>' + 'Trail name' + ':</b>&nbsp' + e['name'] + '</p>';
        contentStr += '<p><b>' + 'Trailhead ID' + ':</b>&nbsp' + e['trailhead_ID'] +
            '</p>';

        // Create the marker
        var marker = new google.maps.Marker({ // Set the marker
            position : latlng, // Position marker to coordinates
            customInfo: contentStr,
            map : map, // assign the market to our map variable
        });

        // Add a Click Listener to the marker
        google.maps.event.addListener(marker, 'click', function() {
            // use 'customInfo' to customize infoWindow
            infowindow.setContent(marker['customInfo']);
            infowindow.open(map, marker); // Open InfoWindow
        });


    });

    //on click
    //  fill lat and long on form
    //  remove old marker (if exists) and place a new marker
    google.maps.event.addListener(map, "click", function(event) {
        // get lat/lon of click
        var clickLat = event.latLng.lat();
        var clickLon = event.latLng.lng();

        // show in input box
        document.getElementById("lat").value = clickLat.toFixed(5);
        document.getElementById("lon").value = clickLon.toFixed(5);

        //check for existing marker
        if (newMarker && newMarker.setMap){
            newMarker.setMap(null);
        }

        //places the new marker
        newMarker = new google.maps.Marker({
            position: new google.maps.LatLng(clickLat, clickLon),
            map: map
        });

    });

    //TODO: Create a function to remove a point if desired


    map.data.loadGeoJson('SLCOTrailheads.json');
    map.data.loadGeoJson('SLCoTrails.json');

    map.data.setStyle({
        strokeColor: 'red',
        icon: 'img/damage.png',
        scaledSize: (15, 15)
    });

    map.fitBounds (bounds);

}

function initAutocomplete() {
    // Create the autocomplete object
    autocomplete = new google.maps.places.Autocomplete(document
        .getElementById('autocomplete'));

    // When the user selects an address from the dropdown, show the place selected
    autocomplete.addListener('place_changed', onPlaceChanged);
}
function onPlaceChanged() {
    place = autocomplete.getPlace();
    // If the place has a geometry, then present it on a map.
    if (place.geometry.viewport) {
        map.fitBounds(place.geometry.viewport);
    } else {
        map.setCenter(place.geometry.location);
        map.setZoom(17);  // Why 17? Because it looks good.
    }

    // Removed the place a marker at the location when zoomed to avoid confusion1
    /*
    var marker = new google.maps.Marker({
        position: place.geometry.location,
        map: map
    });

    marker.setPosition(place.geometry.location);
    marker.setVisible(true);
    */
}


//Execute our 'initialization' function once the page has loaded.
google.maps.event.addDomListener(window, 'load', initialization);
