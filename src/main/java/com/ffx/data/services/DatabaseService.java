package com.ffx.data.services;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ffx.data.models.DatabaseConnectionProps;
import com.ffx.data.utilities.DatabaseAccessHelper;
import com.ffx.data.utilities.DatabaseTablesSqlHelper;


@Service
public class DatabaseService {

	@Autowired
	private DatabaseAccessHelper databaseAccessHelper;
	
	@Autowired
	private DatabaseTablesSqlHelper databaseTableSqlHelper;
	
	/**
	 * Builds the PostgresSQL database
	 * 
	 * @param connProps The database connection properties
	 */
	public void buildDatabase(DatabaseConnectionProps connProps) {
		try {
			// Gets the database connection
			JdbcTemplate jdbcTemplate = databaseAccessHelper.getJdbcTemplate(connProps);
			
			// Creates the tables
			System.out.println("Building tables");
			databaseTableSqlHelper.getTables().stream().forEach(table -> {
				jdbcTemplate.execute("DROP TABLE IF EXISTS " + table + " cascade");
				jdbcTemplate.execute(databaseTableSqlHelper.getTableCreateSql(table));
				System.out.println("Table created - " + table);
			});
			
			// Closes the database connection
			jdbcTemplate.getDataSource().getConnection().close();
			System.out.println("Database built completed");
		} 
		catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
