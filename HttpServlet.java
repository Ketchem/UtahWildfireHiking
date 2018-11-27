package org.finalproject.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//import org.finalproject.servlet.DBUtility;

/** Servlet implementation class HttpServlet*/


@WebServlet("/HttpServlet")
public class HttpServlet extends javax.servlet.http.HttpServlet {
    private static final long serialVersionUID = 1L;


    public HttpServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse
            response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String tab_id = request.getParameter("tab_id");

        // submit a new trail review
        if (tab_id.equals("0")) {
            System.out.println("Your Trail review has been submitted!");
            try {
                createNewReview(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        // query reports
        if (tab_id.equals("1")) {
            try {
                queryReviews(request, response);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void createNewReview(HttpServletRequest request, HttpServletResponse
            response) throws SQLException, IOException {
        DBUtility dbutil = new DBUtility();
        String sql;

        // create new trail review

       // int review_id = 0;
       // String trailName = request.getParameter("Trail Name");

        String trailID = request.getParameter("trailID");
       // String lon = request.getParameter("longitude");
        //String lat = request.getParameter("latitude");

        String comments = request.getParameter("comments");
        String date_added = request.getParameter("date_added"); //Date?
        String active = request.getParameter("active"); //Boolean?
        String rating = request.getParameter("rating");
        String user = request.getParameter("user");



        //if (trailName != null) {trailName = "'" + trailName + "'";}
        if (trailID != null) {trailID = "'" + trailID + "'";}
        if (comments != null) {comments = "'" + comments + "'";}
        if (date_added != null) {date_added = "'" + date_added + "'";}
        if (active != null) {active = "'" + active + "'";}
        if (rating != null) {rating = "'" + rating + "'";}
        if (user != null) {user = "'" + user + "'";}






        //sql statement to add to db
        sql = "insert into trail_review ( trail_id, date_added, active, rating, comments, user)"
                + "values (" + trailID + "," + date_added + "," + active  + "," + rating   + "," + user + ")";


        dbutil.modifyDB(sql);

        // record report_id
        ResultSet res_1 = dbutil. queryDB("select last_value from trail_review_id_seq");
        res_1.next();
       // review_id = res_1.getInt(1);

        System.out.println("Success! Review created.");

        System.out.println(sql);

        // response that the report submission is successful
        JSONObject data = new JSONObject();
        try {
            data.put("status", "success");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        response.getWriter().write(data.toString());


    } //end of create



    private void queryReviews(HttpServletRequest request, HttpServletResponse
            response) throws JSONException, SQLException, IOException {
        JSONArray list = new JSONArray();
        DBUtility dbutil = new DBUtility();
        String sql = "select * from trail_review";


            ResultSet res = dbutil.queryDB(sql);
            while (res.next()) {
                // this is where we list the data we want to get back
                HashMap<String, String> m = new HashMap<String,String>();
                m.put("trailID", res.getString("trailID"));

                //m.put("trailName", res.getString("trailName"));

                //m.put("longitude", res.getString("long"));
                //m.put("latitude", res.getString("lat"));

                m.put("comments", res.getString("comments"));
                m.put("date", res.getString("date"));
                m.put("source", res.getString("source"));
                m.put("active", res.getString("active"));
                m.put("rating", res.getString("rating"));
                list.put(m);
            }

            response.getWriter().write(list.toString());

    }
    // end of queryLandmarks

    public void main() throws JSONException {
    }
}
