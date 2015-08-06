package com.youtube.dao;

import javax.naming.*;
import javax.sql.*;

public class Oracle308tube {
	
	private static DataSource Oracle308tube = null;
	private static Context context = null;
	
	public static DataSource Oracle308tubeConn() throws Exception {
		
		if (Oracle308tube != null) {
			return Oracle308tube;
		}
		
		try {
			if (context == null) {
				context = new InitialContext();
			}
			
			// "308tubeOracle" is we defined in our WebLogic server under "JNDI Name"
			Oracle308tube = (DataSource) context.lookup("308tubeOracle");	
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return Oracle308tube;
	}
}
