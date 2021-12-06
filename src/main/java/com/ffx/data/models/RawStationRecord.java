package com.ffx.data.models;

import java.util.Optional;

import lombok.Builder;
import lombok.Getter;

/**
 * An instance of this class represents a station record
 * parsed from the stations CSV data file
 * 
 * @author Louis Drotos
 */
@Getter
@Builder
public class RawStationRecord {
	private String stationId;
	private String facilityId;
	private String addressId;
	private String stationNumber;
	private String departmentName;
	private String battalion;
	private boolean battalionHq;
	private boolean emsHq;
	private boolean opsHq;
	private String latitude;
	private String longitude;
	private String stationName;
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
	private String phoneNumber;
	private String fireBoxes;
	private boolean volunteer;
	private String density;
	private String areaDescription;
	private String fireHazardDescription;
	private String nonFireHazardDescription;
	private String firstDueArea;
	
	/**
	 * Sets the station's first due area polygon coordinates
	 * 
	 * @param polygons The first due polygons
	 */
	public void setFirstDueArea(RawFirstDuePolygonCollection polygons) {
		Optional<RawFirstDuePolygon> polygon = polygons.getFeatures().stream()
				.filter(f -> stationNumber.equals(String.valueOf(f.getStation())))
				.findFirst();
		firstDueArea = polygon.get().getCoordinates();
	}
	
	/**
	 * Generates the insert SQL statement for the station 
	 * table for this instance
	 * 
	 * @return The insert statement
	 */
	public String getStationInsertSql() {
		return "INSERT INTO station("
				.concat("id, facility_id, station_number, battalion, is_battalion_hq, is_ems_hq, ops_hq, is_volunteer, fire_boxes, ")
				.concat("density, area_description, fire_hazard_description, non_fire_hazard_description, first_due_area) ")
				.concat("VALUES (")
				.concat("'" + stationId + "', ")
				.concat("'" + facilityId + "', ")
				.concat("'" + stationNumber + "', ")
				.concat("'" + battalion + "', ")
				.concat("'" + battalionHq + "', ")
				.concat("'" + emsHq + "', ")
				.concat("'" + opsHq + "', ")
				.concat("'" + volunteer + "', ")
				.concat("'" + fireBoxes + "', ")
				.concat("'" + density + "', ")
				.concat("'" + areaDescription + "', ")
				.concat("'" + fireHazardDescription + "', ")
				.concat("'" + nonFireHazardDescription + "', ")
				.concat("ST_GeomFromGeoJSON('{\"type\":\"Polygon\",\"coordinates\":[")
				.concat(firstDueArea)
				.concat("]}'))");
	}
	
	/**
	 * Generates the insert SQL statement for the facility 
	 * table for this instance
	 * 
	 * @return The insert statement
	 */
	public String getFaclityInsertSql() {
		return "INSERT INTO facility("
				.concat("id, facility_name, department, latitude, longitude, location, phone_number, address_id) ")
				.concat("VALUES (")
				.concat("'" + facilityId + "', ")
				.concat("'" + stationName + "', ")
				.concat("'" + departmentName + "', ")
				.concat("'" + latitude + "', ")
				.concat("'" + longitude + "', ")
				.concat("ST_SetSRID(ST_MakePoint(")
				.concat("'" + longitude + "', ")
				.concat("'" + latitude + "'), 4326), ")
				.concat("'" + phoneNumber + "', ")
				.concat("'" + addressId + "');");
	}

	/**
	 * Generates the insert SQL statement for the address 
	 * table for this instance
	 * 
	 * @return The insert statement
	 */
	public String getAddressInsertSql() {
		return "INSERT INTO address("
				.concat("id, street_address, city, state, zip_code) ")
				.concat("VALUES (")
				.concat("'" + addressId + "', ")
				.concat("'" + streetAddress + "', ")
				.concat("'" + city + "', ")
				.concat("'" + state + "', ")
				.concat("'" + zipCode + "');");
	}
}