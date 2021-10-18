package com.ffx.data.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RawStationRecord {
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
	private String volunteer;
	private String density;
	private String areaDescription;
	private String fireHazardDescription;
	private String nonFireHazardDescription;
}
