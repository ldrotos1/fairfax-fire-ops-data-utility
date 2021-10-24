package com.ffx.data.utilities;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class DatabaseTablesSqlHelper {
	
	private List<String> tables = Arrays.asList("address", "facility", "station");
	
	public List<String> getTables() {
		return tables;
	}

	public String getTableCreateSql(String tableName) {
		String sql = null;
		
		if (tableName.equals("station")) {
			return getStationTableSql();
		}
		else if (tableName.equals("facility")) {
			return getFacilityTableSql();
		}
		else if (tableName.equals("address")) {
			return getAddressTableSql();
		}
		return sql;
	}
	
	private String getStationTableSql() {
		return "CREATE TABLE station("
				.concat("id character varying(36) NOT NULL, ")
				.concat("facility_id character varying(36) NOT NULL, ")
				.concat("station_number character varying(3) NOT NULL, ")
				.concat("battalion character varying(3), ")
				.concat("is_volunteer boolean NOT NULL, ")
				.concat("fire_boxes character varying(3) NOT NULL, ")
				.concat("density character varying(15) NOT NULL, ")
				.concat("area_description text NOT NULL, ")
				.concat("fire_hazard_description text NOT NULL, ")
				.concat("non_fire_hazard_description text NOT NULL, ")
				.concat("CONSTRAINT station_pkey PRIMARY KEY (id), ")
				.concat("CONSTRAINT fk_facility_id FOREIGN KEY (facility_id) ")
				.concat("REFERENCES facility (id) MATCH SIMPLE ")
				.concat("ON UPDATE CASCADE ")
				.concat("ON DELETE CASCADE); ")
				.concat("CREATE INDEX idx_station_number ON station(station_number);");
	}
	
	private String getFacilityTableSql() {
		return "CREATE TABLE facility("
				.concat("id character varying(36) NOT NULL, ")
				.concat("facility_name character varying(75), ")
				.concat("department character varying(75), ")
				.concat("latitude double precision NOT NULL, ")
				.concat("longitude double precision NOT NULL, ")
				.concat("location geometry(Point, 4326), ")
				.concat("phone_number character varying(12) NOT NULL, ")
				.concat("address_id character varying(36) NOT NULL, ")
				.concat("CONSTRAINT facility_pkey PRIMARY KEY (id), ")
				.concat("CONSTRAINT fk_addres_id FOREIGN KEY (address_id) ")
				.concat("REFERENCES address (id) MATCH SIMPLE ")
				.concat("ON UPDATE CASCADE ")
				.concat("ON DELETE CASCADE); ")
				.concat("CREATE INDEX idx_facility_id ON facility(id);")
				.concat("CREATE INDEX location_index ON facility USING GIST (location);");
	}
	
	private String getAddressTableSql() {
		return "CREATE TABLE address("
				.concat("id character varying(36) NOT NULL, ")
				.concat("street_address character varying(150), ")
				.concat("city character varying(150), ")
				.concat("state character varying(15), ")
				.concat("zip_code character varying(5), ")
				.concat("CONSTRAINT address_pkey PRIMARY KEY (id)); "
				.concat("CREATE INDEX idx_address_id ON address(id);"));
	}
}
