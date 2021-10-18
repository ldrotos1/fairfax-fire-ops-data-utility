package com.ffx.data.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ffx.data.utilities.DataFileAccessHelper;
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
	private final String CSV_DELIMITER = "|"; 

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
					.fireBoxes(station.get(11))
					.volunteer(station.get(12))
					.density(station.get(13))
					.areaDescription(station.get(14))
					.fireHazardDescription(station.get(15))
					.nonFireHazardDescription(station.get(16))
					.build());
		});
		return stationEntities;
	}
	
	/**
	 * Takes in a FileReader of a CSV file and parses the data into a list 
	 * of rows that each contain a list of the row's parsed column data
	 * 
	 * @param fileReader
	 * @return The parsed CSV file
	 */
	private List<List<String>> parseCsvLines(FileReader fileReader) {
		
		List<List<String>> parsedData = new ArrayList<List<String>>();
		try (BufferedReader br = new BufferedReader(fileReader)) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	String[] lineSegments = line.split(CSV_DELIMITER);
		    	parsedData.add(Arrays.asList(lineSegments));
		    }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return parsedData;
	}
}