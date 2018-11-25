package org.finalproject.servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.finalproject.servlet.DBUtility;

/** Servlet implementation class HttpServlet*/


@WebServlet("/HttpServlet")
public class HttpServlet extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see javax.servlet.http.HttpServlet#javax.servlet.http.HttpServlet()
     */
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
		
		// submit a new trailhead
		if (tab_id.equals("0")) {
			System.out.println("Your Trailhead has been submitted for review!");
			try {
				createNewLandmark(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		// query reports
		if (tab_id.equals("1")) {
			try {
					queryTrails(request, response);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void createNewTrailhead(HttpServletRequest request, HttpServletResponse 
			response) throws SQLException, IOException {
		DBUtility dbutil = new DBUtility();		
		String sql;
		
		// create new trail review
		//a lot of this is just placeholders for now
		int trail_id = 0;
		String trailName = request.getParameter("Trail Name"); 
		String trailID = request.getParameter("trailID");
		String lon = request.getParameter("longitude");
		String lat = request.getParameter("latitude");
		
		String comments = request.getParameter("comments");
		String date = request.getParameter("date");
		String source = request.getParameter("source");
		String active = request.getParameter("active");
		String rating = request.getParameter("rating");
		
		
		if (trailName != null) {trailName = "'" + trailName + "'";}
		if (trailID != null) {trailID = "'" + trailID + "'";}
		
		if (comments != null) {comments = "'" + comments + "'";}
		if (date != null) {date = "'" + date + "'";}
		if (source != null) {source = "'" + source + "'";}
		if (active != null) {active = "'" + active + "'";}
		if (rating != null) {rating = "'" + rating + "'";}

		//everything else in this function is from the summer example anwar gave us
		//i haven't touched it yet
		
		
		
		//not needed for our DB
//		sql = "insert into landmarks (name, type, geom," +
//				" message) values (" + landmarkName + "," + landmarkType
//				+ ", ST_GeomFromText('POINT(" + lon + " " + lat + ")', 4326)" + "," + 
//				message + ")";

		
		//id | name | type | lat | long | user_created | user_saved | notes
		sql = "insert into trailheads ( , type, lat,long,notes)" 
		+ "values (" + landmarkName + "," + landmarkType + "," + lat  + "," + lon   + "," + message + ")";

		
		dbutil.modifyDB(sql);
		
		// record report_id
		ResultSet res_1 = dbutil. queryDB("select last_value from landmarks_id_seq");
		res_1.next();
		report_id = res_1.getInt(1);
		
		System.out.println("Success! Landmark add created.");
		
		System.out.println(sql);
		
		
		
		
		// response that the report submission is successful
		JSONObject data = new JSONObject();
		try {
			data.put("status", "success");
		} catch (JSONException e) {
			e.printStackTrace();
		}	
		response.getWriter().write(data.toString());
		

	} //end of createReport	
	
	

	private void queryTrailhead(HttpServletRequest request, HttpServletResponse 
			response) throws JSONException, SQLException, IOException {
			JSONArray list = new JSONArray();
			DBUtility dbutil = new DBUtility();
			String sql = "";
			//get the difficulty
			String difficulty = request.getParameter("difficulty");
			//When opening the webpage, no landmarks will be shown.
			if (!type.equals("none")){
				//if request is to show all then query all of them 
				if (type.equals("all")) {
					sql = "select * from trailheads";
				} 
				else { //otherwise select where type equals the clicked button
					sql = "select * from trailheads where difficulty ='" + difficulty+"'";
				}
	
				ResultSet res = dbutil.queryDB(sql);
				while (res.next()) {
					// this is where we list the data we want to get back
					HashMap<String, String> m = new HashMap<String,String>();
					m.put("trailName", res.getString("trailName"));
					m.put("trailID", res.getString("trailID"));
					m.put("longitude", res.getString("long"));
					m.put("latitude", res.getString("lat"));
					
					m.put("comments", res.getString("comments"));
					m.put("date", res.getString("date"));
					m.put("source", res.getString("source"));
					m.put("active", res.getString("active"));
					m.put("rating", res.getString("rating"));
					list.put(m);
				}
				
				response.getWriter().write(list.toString());
		}
	} // end of queryLandmarks
	
	public void main() throws JSONException {
	}
}