package com.ffx.data.models;

import lombok.Builder;
import lombok.Getter;

/**
 * An instance of this class represents a apparatus type record
 * parsed from the apparatus type CSV data file
 * 
 * @author Louis Drotos
 */
@Getter
@Builder
public class RawApparatusTypeRecord {
	private String apparatusTypeId;
	private String apparatusTypeName;
	private String apparatusTypeCategory;
	private String apparatusTypeImage;
	private int maxStaffCount;
	private int minFireFighterStaffCount;
	private int minTechStaffCount;
	private int minOfficerStaffCount;
	private int minCommanderStaffCount;
	private boolean isParamedicRequired;
	private boolean isCrossStaffed;
	private boolean isVolunteerStaffed;
	
	/**
	 * Generates the insert SQL statement for the apparatus 
	 * type table for this instance
	 * 
	 * @return The insert statement
	 */
	public String getAppTypeInsertSql() {
		return "INSERT INTO apparatus_type("
				.concat("id, type_name, type_category, type_image, max_staff_count, min_staff_count, min_ff_staff_count, ")
				.concat("min_tech_staff_count, min_officer_staff_count, min_command_staff_count, is_paramedic_required, ")
				.concat("is_cross_staffed, is_volunteer_staffed)")
				.concat("VALUES (")
				.concat("'" + apparatusTypeId + "', ")
				.concat("'" + apparatusTypeName + "', ")
				.concat("'" + apparatusTypeCategory + "', ")
				.concat("'" + apparatusTypeImage + "', ")
				.concat("'" + maxStaffCount + "', ")
				.concat("'" + minFireFighterStaffCount + "', ")
				.concat("'" + minTechStaffCount + "', ")
				.concat("'" + minOfficerStaffCount + "', ")
				.concat("'" + minCommanderStaffCount + "', ")
				.concat("'" + isParamedicRequired + "', ")
				.concat("'" + isCrossStaffed + "', ")
				.concat("'" + isVolunteerStaffed + "')");
	}
}