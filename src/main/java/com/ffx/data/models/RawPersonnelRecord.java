package com.ffx.data.models;

import lombok.Builder;
import lombok.Getter;

/**
 * An instance of this class represents a personnel record
 * parsed from the personnel CSV data file
 * 
 * @author Louis Drotos
 */
@Getter
@Builder
public class RawPersonnelRecord {
	private String personId;
	private String forename;
	private String surname;
	private String rank;
	private String medCert;
	private String station;
	private String facilityId;
	private String shift;
	private String commandPosition;
	private String stationLeader;
	private String shiftLeader;
	
	/**
	 * Generates the insert SQL statement for the personnel 
	 * table for this instance
	 * 
	 * @return The insert statement
	 */
	public String getPersonInsertSql() {
		return "INSERT INTO personnel("
				.concat("id, forename, surname, rank, medical_certification, shift, command_postion, station_leader shift_leader)")
				.concat("VALUES (")
				.concat("'" + personId + "', ")
				.concat("'" + forename + "', ")
				.concat("'" + surname + "', ")
				.concat("'" + rank + "', ")
				.concat("'" + medCert + "', ")
				.concat("'" + station + "', ")
				.concat("'" + facilityId + "', ")
				.concat("'" + shift + "', ")
				.concat("'" + commandPosition + "', ")
				.concat("'" + stationLeader + "', ")
				.concat("'" + shiftLeader + "') ");
	}
}