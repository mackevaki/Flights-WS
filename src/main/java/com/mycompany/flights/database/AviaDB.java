package com.mycompany.flights.database;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AviaDB {
	private static Connection conn;
	public static final String JDBC_JNDI = "jdbc/Avia";

	private static AviaDB instance;

	private AviaDB() {
	}

	public static AviaDB getInstance() {
		if (instance == null) {
			instance = new AviaDB();
		}
		return instance;
	}

	public Connection getConnection() {
		try {
			if (conn == null || conn.isClosed()) {
				InitialContext ic = new InitialContext();
				DataSource ds = (DataSource) ic.lookup(JDBC_JNDI);
				conn = ds.getConnection();
			}

		} catch (SQLException | NamingException ex) {
			Logger.getLogger(AviaDB.class.getName()).log(Level.SEVERE, null, ex);
		}

		return conn;
	}

	public void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException ex) {
				Logger.getLogger(AviaDB.class
						.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}
}
