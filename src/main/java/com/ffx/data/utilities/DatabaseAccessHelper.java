package com.ffx.data.utilities;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ffx.data.models.DatabaseConnectionProps;
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
	 * @param connProps The database connection properties
	 * @return The JDBC template for the connection
	 */
	public JdbcTemplate getJdbcTemplate(DatabaseConnectionProps connProps) {
		
		HikariConfig config = new HikariConfig();
        config.setJdbcUrl(connProps.getJdbcUrl());
        config.setUsername(connProps.getUser());
        config.setPassword(connProps.getPassword());
		return new JdbcTemplate(new HikariDataSource(config));
	}
}
