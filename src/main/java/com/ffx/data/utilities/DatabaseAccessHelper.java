package com.ffx.data.utilities;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Provides a method for connecting to a PostgresSQL database
 * 
 * @author Louis Drotos
 */
@Component
public class DatabaseAccessHelper {
	
	/**
	 * Makes a connection to a database and creates a JDBC template
	 * 
	 * @param host The database connection host
	 * @param port The database connection port
	 * @param dbName The database name
	 * @param user The database user
	 * @param password The database password
	 * @return The JDBC template for the connection
	 */
	public JdbcTemplate getJdbcTemplate(String host, String port, String dbName, String user, String password) {
		
		String jdbcUrl = buildJdbcUrl(host, port, dbName);
		HikariDataSource datasource = getDatasource(jdbcUrl, user, password);
		return new JdbcTemplate(datasource);
	}
	
	/**
	 * Creates a data source to the specified database
	 * 
	 * @param jdbcUrl The URL to the database
	 * @param user The database user
	 * @param password The database password
	 * @return The data source object
	 */
	private HikariDataSource getDatasource(String jdbcUrl, String user, String password) {
		
		HikariConfig config = new HikariConfig();
        config.setJdbcUrl(jdbcUrl);
        config.setUsername(user);
        config.setPassword(password);
        return new HikariDataSource(config);
	}
	
	/**
	 * Constructs a JDBC URL
	 * 
	 * @param host The database host
	 * @param port the database port
	 * @param dbName The database name
	 * @return The JDBC URL
	 */
	private String buildJdbcUrl(String host, String port, String dbName) {
		
		StringBuilder url = new StringBuilder();
		url.append("jdbc:postgresql://");
		url.append(host);
		url.append(":");
		url.append(port);
		url.append("/");
		url.append(dbName);
		return url.toString();
	}
}
