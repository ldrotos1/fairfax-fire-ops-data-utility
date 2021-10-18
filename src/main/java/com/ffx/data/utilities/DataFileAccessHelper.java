package com.ffx.data.utilities;

import java.io.FileReader;
import java.io.IOException;

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
	
	/**
	 * Gets a file reader for the stations CSV file
	 * 
	 * @return The stations file reader
	 */
	public FileReader getStationFile() {
		return getFileReader(new ClassPathResource(stationCsvFile));
	}
	
	/**
	 * Creates a file reader for the provided file resource 
	 * 
	 * @param fileResource
	 * @return The file reader
	 */
	private FileReader getFileReader(Resource fileResource) {
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(fileResource.getFile().getAbsolutePath());
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		return fileReader;
	}
}
