package com.ffx.data.utilities;

import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/**
 * Class is used for accessing file readers of CSV data files resources
 * 
 * @author Louis Drotos
 */
@Component
public class DataFileAccessHelper {

	private static final String stationCsvFile = "data/ffx-station-data.csv";
	private static final String apparatusTypeCsvFile = "data/ffx-apparatus_type_data.csv";
	private static final String apparatusCsvFile = "data/ffx-apparatus-data.csv";
	
	/**
	 * Gets a input stream reader for the stations CSV file
	 * 
	 * @return The stations input stream reader
	 */
	public InputStreamReader getStationFile() {
		return getFileReader(new ClassPathResource(stationCsvFile));
	}
	
	/**
	 * Gets a input stream reader for the apparatus types CSV file
	 * 
	 * @return The apparatus types input stream reader
	 */
	public InputStreamReader getApparatusTypesFile() {
		return getFileReader(new ClassPathResource(apparatusTypeCsvFile));
	}
	
	/**
	 * Gets a input stream reader for the apparatus CSV file
	 * 
	 * @return The apparatus input stream reader
	 */
	public InputStreamReader getApparatusFile() {
		return getFileReader(new ClassPathResource(apparatusCsvFile));
	} 
	
	/**
	 * Creates a input stream reader for the provided file resource 
	 * 
	 * @param fileResource
	 * @return The input stream reader
	 */
	private InputStreamReader getFileReader(Resource fileResource) {
		InputStreamReader fileReader = null;
		try {
			fileReader = new InputStreamReader(fileResource.getInputStream());
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return fileReader;
	}
}
