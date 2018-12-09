function createReview(event) {
    event.preventDefault(); // stop form from submitting normally


    var a = $("#add_review_form").serializeArray(),
        place = autocomplete.getPlace(),
        lati =  String(place.geometry.location.lat()),
        longi = String(place.geometry.location.lng());

    a.push({ name: "tab_id", value: "0"}, {name: "latitude", value: lati}, {name: "longitude", value: longi});
    //check array
    console.log(a);
    a = a.filter(function(item){return item.value != '';});

    $.ajax({
        url: 'HttpServlet',
        type: 'POST',
        data: a,

        success: function(reports) {
            alert("Report was successfully submitted!"),
                showAllReports();
            document.getElementById("add_review_form").reset();

        },
        error: function (xhr, status, error) {
            alert("Status: " + status + "\nError: " + error);
        }
    });

}

function queryReviews(e) {
    e.preventDefault(); // stop form from submitting normally

    var a = $("#query_review_form").serializeArray();
    a.push({ name: "tab_id", value: "1" });
    a = a.filter(function(item){return item.value != '';});
    $.ajax({
        url: 'HttpServlet',
        type: 'POST',
        data: a,
        success: function(reports) {
            mapInitialization(reports);
        },
        error: function(xhr, status, error) {
            alert("Status: " + status + "\nError: " + error);
        }
    });
}



$("#add_review_form").on("submit",createReview()); $("#report_submit_btn").on("click" ,createReview());
$("#query_trail_form").on("submit",queryReviews()); $("#query_submit_btn").on("click" ,queryReviews());