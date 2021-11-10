package com.ffx.data.utilities;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Provides a set of methods for generating SQL that can
 * be used to 
 * 
 * @author Louis Drotos
 */
@Component
public class DatabaseTablesSqlHelper {
	
	private List<String> tables = Arrays.asList(
			"address", 
			"facility", 
			"station", 
			"apparatus_type",
			"apparatus",
			"personnel");
	
	/**
	 * Provides a list of all database table names
	 * 
	 * @return The list of tables
	 */
	public List<String> getTables() {
		return tables;
	}

	/**
	 * Returns the SQL statement for creating the specified 
	 * database table
	 * 
	 * @param tableName The table name
	 * @return The SQL statement
	 */
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
		else if (tableName.equals("apparatus_type")) {
			return getApparatusTypeTableSql();
		}
		else if (tableName.equals("apparatus")) {
			return getApparatusTableSql();
		}
		else if (tableName.equals("personnel")) {
			return getPersonnelTableSql();
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
				.concat("first_due_area geometry(Polygon, 4326), ")
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
				.concat("CREATE INDEX idx_location ON facility USING GIST (location);");
	}
	
	private String getAddressTableSql() {
		return "CREATE TABLE address("
				.concat("id character varying(36) NOT NULL, ")
				.concat("street_address character varying(150), ")
				.concat("city character varying(150), ")
				.concat("state character varying(15), ")
				.concat("zip_code character varying(5), ")
				.concat("CONSTRAINT address_pkey PRIMARY KEY (id)); ")
				.concat("CREATE INDEX idx_address_id ON address(id);");
	}
	
	private String getApparatusTypeTableSql() {
		return "CREATE TABLE apparatus_type("
				.concat("id character varying(36) NOT NULL, ")
				.concat("type_name character varying(50), ")
				.concat("type_category character varying(50), ")
				.concat("type_image character varying(50), ")
				.concat("max_staff_count int , ")
				.concat("min_ff_staff_count int, ")
				.concat("min_tech_staff_count int, ")
				.concat("min_officer_staff_count int, ")
				.concat("min_command_staff_count int, ")
				.concat("is_paramedic_required boolean NOT NULL, ")
				.concat("is_cross_staffed boolean NOT NULL, ")
				.concat("is_volunteer_staffed boolean NOT NULL, ")
				.concat("CONSTRAINT app_type_pkey PRIMARY KEY (id)); ")
				.concat("CREATE INDEX idx_app_type_id ON apparatus_type(id);");
	}
	
	private String getApparatusTableSql() {
		return "CREATE TABLE apparatus("
				.concat("id character varying(36) NOT NULL, ")
				.concat("unit_designator character varying(8), ")
				.concat("department character varying(50), ")
				.concat("facility_id character varying(36) NOT NULL, ")
				.concat("apparatus_type_id character varying(36) NOT NULL, ")
				.concat("truck_type character varying(15), ")
				.concat("rescue_type character varying(15), ")
				.concat("has_foam_cell boolean NOT NULL, ")
				.concat("is_reserve boolean NOT NULL, ")
				.concat("in_service boolean NOT NULL, ")
				.concat("CONSTRAINT app_pkey PRIMARY KEY (id), ")
				.concat("CONSTRAINT app_type_fk FOREIGN KEY (apparatus_type_id) ")
				.concat("REFERENCES apparatus_type(id) ")
				.concat("ON DELETE NO ACTION ")
				.concat("ON UPDATE NO ACTION, ")
				.concat("CONSTRAINT fac_fk FOREIGN KEY (facility_id) ")
				.concat("REFERENCES facility(id) ")
				.concat("ON DELETE NO ACTION ")
				.concat("ON UPDATE NO ACTION); ")
				.concat("CREATE INDEX idx_app_id ON apparatus(id); ")
				.concat("CREATE INDEX idx_fac_id ON apparatus(facility_id); ")
				.concat("CREATE INDEX idx_app_app_type_id ON apparatus(apparatus_type_id); ");
	}
	
	private String getPersonnelTableSql() {
		return "CREATE TABLE personnel("
				.concat("id character varying(36) NOT NULL, ")
				.concat("forename character varying(50), ")
				.concat("surname character varying(50), ")
				.concat("rank character varying(20), ")
				.concat("medical_certification character varying(20), ")
				.concat("shift character varying(1), ")
				.concat("facility_id character varying(36) NOT NULL, ")
				.concat("command_postion character varying(6), ")
				.concat("station_leader character varying(4), ")
				.concat("shift_leader character varying(4), ")
				.concat("CONSTRAINT person_pkey PRIMARY KEY (id), ")
				.concat("CONSTRAINT per_fac_fk FOREIGN KEY (facility_id) ")
				.concat("REFERENCES facility(id) ")
				.concat("ON DELETE NO ACTION ")
				.concat("ON UPDATE NO ACTION); ")
				.concat("CREATE INDEX idx_person_id ON personnel(id);");
	}
}