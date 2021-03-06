package org.zerock.web;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class MySQLConnectionTest {

	private static final String DRIVER =
			"org.mariadb.jdbc.Driver";
	private static final String URL =
			"jdbc:mariadb://localhost:3306/demo";
	private static final String USER =
			"tester";
	private static final String PW =
			"1541";
	
	@Test
	public void testConnection() throws Exception{
		
		Class.forName(DRIVER);
		
		try (Connection con = DriverManager.getConnection(URL, USER, PW)){

			System.out.println(con);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
