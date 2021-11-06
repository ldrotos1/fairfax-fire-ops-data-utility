package com.ffx.data.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ffx.data.models.DatabaseConnectionProps;
import com.ffx.data.models.RawApparatusRecord;
import com.ffx.data.models.RawApparatusTypeRecord;
import com.ffx.data.models.RawPersonnelRecord;
import com.ffx.data.models.RawStationRecord;
import com.ffx.data.reader.CsvDataReader;
import com.ffx.data.utilities.DatabaseAccessHelper;
import com.ffx.data.utilities.DatabaseTablesSqlHelper;


@Service
public class DatabaseService {
	
	@Autowired
	private CsvDataReader csvDataReader;

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
			
			// Parses CSV files
			List<RawStationRecord> stations = csvDataReader.parseStations();
			List<RawApparatusTypeRecord> apparatusTypes = csvDataReader.parseApparatusTypes();
			List<RawApparatusRecord> apparatus = csvDataReader.parseApparatus();
			List<RawPersonnelRecord> personnel = csvDataReader.parsePersonnel();
			
			// Creates the tables
			System.out.println("Building tables");
			databaseTableSqlHelper.getTables().stream().forEach(table -> {
				jdbcTemplate.execute("DROP TABLE IF EXISTS " + table + " cascade");
				jdbcTemplate.execute(databaseTableSqlHelper.getTableCreateSql(table));
				System.out.println("Table created - " + table);
			});
			
			// Populates the station tables
			System.out.println("Populating station table");
			stations.stream().forEach(station -> {
				jdbcTemplate.execute(station.getAddressInsertSql());
				jdbcTemplate.execute(station.getFaclityInsertSql());
				jdbcTemplate.execute(station.getStationInsertSql());
			});
			
			// Populates the apparatus type table
			System.out.println("Populating apparatus type table");
			apparatusTypes.stream().forEach(appType -> {
				jdbcTemplate.execute(appType.getAppTypeInsertSql());
			});
			
			// Populates the apparatus table
			System.out.println("Populating apparatus table");
			apparatus.stream().forEach(app -> {
				app.setFacilityId(stations);
				app.setApparatusTypeId(apparatusTypes);
				jdbcTemplate.execute(app.getApparatusInsertSql());
			});
			
			// Closes the database connection
			jdbcTemplate.getDataSource().getConnection().close();
			System.out.println("Database build completed");
		} 
		catch (SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}
