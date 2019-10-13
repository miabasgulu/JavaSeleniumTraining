package com.my_global_library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class DatabaseManager {

	final static Logger logger = Logger.getLogger(DatabaseManager.class);

	private String databaseServerName = "localhost";
	private String databasePort = "1521";
	private String databaseName = "xe";
	private String userName = "hr";
	private String userPassword = "hr";
	private String connectionURL_Oracle = "jdbc:oracle:thin:" + userName + "@//" + databaseServerName + ":"
			+ databasePort + "/" + databaseName;

	private Connection connaction = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	private void connectToOracleDB() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connaction = DriverManager.getConnection(connectionURL_Oracle, userName, userPassword);
			statement = connaction.createStatement();
		} catch (Exception e) {
			logger.error("Not able to connect to database...", e);
		}
	}

	public ResultSet runSQLQuery(String sqlQuery) {
		try {
			connectToOracleDB();
			ResultSet resultData = statement.executeQuery(sqlQuery);
			resultSet = resultData;
		} catch (Exception e) {
			logger.error("Not able to connect to database...", e);
		}
		return resultSet;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		try {
			DatabaseManager dbManager = new DatabaseManager();
			ResultSet data = dbManager.runSQLQuery(
					"Select country_id, country_name, region_id From Countries where country_name like '%United%'");
			System.out.println("result data: " + data);

			System.out.println("---------------------------");

			String tab = "\t";

			while (data.next()) {
				String countryId = data.getString("COUNTRY_ID");
				String countryName = data.getString("COUNTRY_NAME");
				String regionId = data.getString("REGION_ID");
				System.out.println(countryId + tab + countryName + tab + tab + tab + regionId);
			}
		} catch (Exception e) {
			logger.error("Database error...", e);
		}
	}

}
