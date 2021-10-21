package com.ffx.data.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ffx.data.models.DatabaseConnectionProps;
import com.ffx.data.utilities.DatabaseAccessHelper;


@Service
public class DatabaseService {

	@Autowired
	private DatabaseAccessHelper databaseAccessHelper;
	
	/**
	 * Builds the PostgresSQL database
	 * 
	 * @param connProps The database connection properties
	 */
	public void buildDatabase(DatabaseConnectionProps connProps) {
		JdbcTemplate jdbcTemplate = databaseAccessHelper.getJdbcTemplate(connProps);
	}
}
