package com.ffx.data.reader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ffx.data.utilities.DataFileAccessHelper;
import com.ffx.data.models.RawApparatusRecord;
import com.ffx.data.models.RawApparatusTypeRecord;
import com.ffx.data.models.RawPersonnelRecord;
import com.ffx.data.models.RawStationRecord;

/**
 * Provides methods for extracting application data from CSV resource files
 * 
 * @author Louis Drotos
 */
@Component
public class CsvDataReader {
	
	@Autowired
	private DataFileAccessHelper dataFileAccessHelp;
	private final String CSV_DELIMITER = "\\|"; 

	/**
	 * Returns a list of raw station entities that are extracted
	 * from the stations CSV file resource
	 * 
	 * @return The list of raw station entities
	 */
	public List<RawStationRecord> parseStations() {
		
		List<List<String>> stationData = parseCsvLines(dataFileAccessHelp.getStationFile());
		List<RawStationRecord> stationEntities = new ArrayList<RawStationRecord>();
		stationData.stream().forEach(station -> {
			stationEntities.add(
				RawStationRecord.builder()
					.stationId(UUID.randomUUID().toString())
					.facilityId(UUID.randomUUID().toString())
					.addressId(UUID.randomUUID().toString())
					.stationNumber(station.get(0))
					.departmentName(station.get(1))
					.battalion(station.get(2))
					.latitude(station.get(3))
					.longitude(station.get(4))
					.stationName(station.get(5))
					.streetAddress(station.get(6))
					.city(station.get(7))
					.state(station.get(8))
					.zipCode(station.get(9))
					.phoneNumber(station.get(10))
					.fireBoxes(station.get(11).toString())
					.volunteer(Boolean.parseBoolean(station.get(12).toString()))
					.density(station.get(13))
					.areaDescription(station.get(14))
					.fireHazardDescription(station.get(15))
					.nonFireHazardDescription(station.get(16))
					.build());
		});
		return stationEntities;
	}
	
	/**
	 * Returns a list of raw apparatus types entities that are extracted
	 * from the apparatus types CSV file resource
	 * 
	 * @return The list of raw apparatus types entities
	 */
	public List<RawApparatusTypeRecord> parseApparatusTypes() {
		
		List<List<String>> apparatusTypeData = parseCsvLines(dataFileAccessHelp.getApparatusTypesFile());
		List<RawApparatusTypeRecord> apparatusTypeEntities = new ArrayList<RawApparatusTypeRecord>();
		apparatusTypeData.stream().forEach(appType -> {
			apparatusTypeEntities.add(
					RawApparatusTypeRecord.builder()
					.apparatusTypeId(UUID.randomUUID().toString())
					.apparatusTypeName(appType.get(0))
					.apparatusTypeCategory(appType.get(1))
					.apparatusTypeImage(appType.get(2))
					.maxStaffCount(Integer.parseInt(appType.get(3)))
					.minStaffCount(Integer.parseInt(appType.get(4)))
					.minFireFighterStaffCount(Integer.parseInt(appType.get(5)))
					.minTechStaffCount(Integer.parseInt(appType.get(6)))
					.minOfficerStaffCount(Integer.parseInt(appType.get(7)))
					.minCommanderStaffCount(Integer.parseInt(appType.get(8)))
					.isParamedicRequired(Boolean.parseBoolean(appType.get(9)))
					.isCrossStaffed(Boolean.parseBoolean(appType.get(10)))
					.isVolunteerStaffed(Boolean.parseBoolean(appType.get(11)))
					.build());
		});
		return apparatusTypeEntities;
	}
	
	/**
	 * Returns a list of raw apparatus entities that are extracted
	 * from the apparatus CSV file resource
	 * 
	 * @return The list of raw apparatus types entities
	 */
	public List<RawApparatusRecord> parseApparatus() {
		
		List<List<String>> apparatusData = parseCsvLines(dataFileAccessHelp.getApparatusFile());
		List<RawApparatusRecord> apparatusEntities = new ArrayList<RawApparatusRecord>();
		apparatusData.stream().forEach(apparatus -> {
			apparatusEntities.add(		
					RawApparatusRecord.builder()
					.apparatusId(UUID.randomUUID().toString())
					.departmentName("Fairfax County Fire and Rescue Department")
					.unitDesignator(apparatus.get(2))
					.apparatusTypeName(apparatus.get(3))
					.stationNumber(apparatus.get(0))
					.truckType(apparatus.get(4).isEmpty() ? null : apparatus.get(4))
					.rescueType(apparatus.get(5).isEmpty() ? null : apparatus.get(5))
					.hasFoamCell(Boolean.parseBoolean(apparatus.get(15)))
					.isReserved(Boolean.parseBoolean(apparatus.get(6)))
					.inService(!Boolean.parseBoolean(apparatus.get(6)))
					.build());
		});
		return apparatusEntities;
	}
	
	/**
	 * Returns a list of raw person entities that are extracted
	 * from the personnel CSV file resource
	 * 
	 * @return The list of raw person entities
	 */
	public List<RawPersonnelRecord> parsePersonnel() {
		
		List<List<String>> personData = parseCsvLines(dataFileAccessHelp.getPersonnelFile());
		List<RawPersonnelRecord> personEntities = new ArrayList<RawPersonnelRecord>();
		personData.stream().forEach(person -> {
			personEntities.add(		
					RawPersonnelRecord.builder()
					.personId(UUID.randomUUID().toString())
					.forename(person.get(1))
					.surname(person.get(2))
					.rank(person.get(3))
					.medCert(person.get(4))
					.stationNumber(person.get(5))
					.shift(person.get(6))
					.commandPosition(person.get(7))
					.stationLeader(person.get(8))
					.shiftLeader(person.get(9))
					.build());
		});
		return personEntities;
	}
	
	/**
	 * Takes in a FileReader of a CSV file and parses the data into a list 
	 * of rows that each contain a list of the row's parsed column data
	 * 
	 * @param streamReader
	 * @return The parsed CSV file
	 */
	private List<List<String>> parseCsvLines(InputStreamReader streamReader) {
		
		List<List<String>> parsedData = new ArrayList<List<String>>();
		try (BufferedReader br = new BufferedReader(streamReader)) {
		    String line;
		    Boolean firstLine = true;
		    while ((line = br.readLine()) != null) {
		    	if (firstLine) {
		    		firstLine = false;
		    	} 
		    	else {
			    	String[] lineSegments = line.split(CSV_DELIMITER);
			    	parsedData.add(Arrays.asList(lineSegments));
		    	}
		    }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return parsedData;
	}
}