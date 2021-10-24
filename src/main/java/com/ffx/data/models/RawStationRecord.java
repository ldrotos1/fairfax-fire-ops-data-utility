package com.ffx.data.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RawStationRecord {
	private String stationId;
	private String facilityId;
	private String addressId;
	private String stationNumber;
	private String departmentName;
	private String battalion;
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
	
	public String getStationInsertSql() {
		return "INSERT INTO station("
				.concat("id, facility_id, station_number, battalion, is_volunteer, fire_boxes, density, ")
				.concat("area_description, fire_hazard_description, non_fire_hazard_description) ")
				.concat("VALUES (")
				.concat("'" + stationId + "', ")
				.concat("'" + facilityId + "', ")
				.concat("'" + stationNumber + "', ")
				.concat("'" + battalion + "', ")
				.concat("'" + volunteer + "', ")
				.concat("'" + fireBoxes + "', ")
				.concat("'" + density + "', ")
				.concat("'" + areaDescription + "', ")
				.concat("'" + fireHazardDescription + "', ")
				.concat("'" + nonFireHazardDescription + "')");
	}
	
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