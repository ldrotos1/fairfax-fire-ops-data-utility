package com.ffx.data.models;

import java.util.List;
import java.util.Optional;

import lombok.Builder;
import lombok.Getter;

/**
 * An instance of this class represents a apparatus record
 * parsed from the apparatus CSV data file
 * 
 * @author Louis Drotos
 */
@Getter
@Builder
public class RawApparatusRecord {
	private String apparatusId;
	private String facilityId;
	private String apparatusTypeId;
	private String unitDesignator;
	private String departmentName;
	private String apparatusTypeName;
	private String stationNumber;
	private String truckType;
	private String rescueType;
	private boolean hasFoamCell;
	private boolean isReserved;
	private boolean inService;
	
	/**
	 * Generates the insert SQL statement for the apparatus 
	 * table for this instance
	 * 
	 * @return The insert statement
	 */
	public String getApparatusInsertSql() {
		return "INSERT INTO apparatus("
				.concat("id, facility_id, apparatus_type_id, unit_designator, department, truck_type, rescue_type, ")
				.concat("has_foam_cell, is_reserve, in_service) ")
				.concat("VALUES (")
				.concat("'" + apparatusId + "', ")
				.concat("'" + facilityId + "', ")
				.concat("'" + apparatusTypeId + "', ")
				.concat("'" + unitDesignator + "', ")
				.concat("'" + departmentName + "', ")
				.concat("'" + truckType + "', ")
				.concat("'" + rescueType + "', ")
				.concat("'" + hasFoamCell + "', ")
				.concat("'" + isReserved + "', ")
				.concat("'" + inService + "') ");
	}
	
	/**
	 * Takes in a list of stations and extracts the facility ID
	 * that this apparatus belongs and set it in it's own state
	 * 
	 * @param stations The list of stations
	 */
	public void setFacilityId(List<RawStationRecord> stations) {
		Optional<RawStationRecord> station = stations.stream()
			.filter(s -> s.getStationNumber().equals(stationNumber))
			.findFirst();
		facilityId = station.get().getFacilityId();
	}
	
	/**
	 * Takes in a list of apparatus types and extracts the apparatus 
	 * type ID that this apparatus is associated with and set it in 
	 * it's own state
	 * 
	 * @param stations The list of stations
	 */
	public void setApparatusTypeId(List<RawApparatusTypeRecord> apparatusTypes) {
		Optional<RawApparatusTypeRecord> apparatusType = apparatusTypes.stream()
				.filter(a -> a.getApparatusTypeName().equals(apparatusTypeName))
				.findFirst();
		apparatusTypeId = apparatusType.get().getApparatusTypeId();
	}
}