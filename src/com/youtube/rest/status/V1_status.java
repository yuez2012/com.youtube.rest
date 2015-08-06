package com.youtube.rest.status;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.sql.*;

import com.youtube.dao.*;

/**
 * This is the root path for our restful api service.
 * In the web.xml file, we specified that /api/* need to be in the URL to get to this class.
 * 
 * We are versioning the class in the URL path. This is the first version v1.
 * Example how to get the root of this api resource:
 * http://localhost:7001/com.youtube.rest/api/v1/status
 * 
 * @author 308tube
 *
 */
@Path("/v1/status/*")
public class V1_status {
	
	private static final String api_version = "00.02.00";
	
	/**
	 * This method sits at the root of the api. It will return the name of this api.
	 * 
	 * @return String - Title of the api
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnTitle() {
		return "<p>Java Web Service</p>";
	}
	
	/**
	 * This method will return the version number of the api
	 * Note: this is nested one down from the root.  You will need to add version into the URL path.
	 * 
	 * Example:
	 * http://localhost:7001/com.youtube.rest/api/v1/status/version
	 * 
	 * @return String - version number of the api
	 */
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<pVersion:</p>" + api_version;
	}
	
	@Path("/database")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnDatabaseStatus() throws Exception {
		
		PreparedStatement query = null;
		String myString = null;
		String returnString = null;
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			conn =  Oracle308tube.Oracle308tubeConn().getConnection();
			query = conn.prepareStatement("select to_char(sysdate, 'YYYY-MM-DD HH24:MI:SS') DATETIME " +
					"from sys.dual");
			rs = query.executeQuery();
			
			while (rs.next()) {
				// /*Debug*/ System.out.println(rs.getString("DATETIME"));
				myString = rs.getString("DATETIME");
			}
			
			query.close();	// close connection
			
			returnString = "<p>Database Status</p>" +
					"<p>Database Date/Time return: " + myString + "</p>";
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (rs != null) {
				rs.close();
			}
			if (conn != null) { 
				conn.close();
			}
		}
		
		return returnString;
	}
	
}
