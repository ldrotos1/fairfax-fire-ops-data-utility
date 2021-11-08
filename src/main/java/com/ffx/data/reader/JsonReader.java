package com.ffx.data.reader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ffx.data.models.RawFirstDuePolygonCollection;
import com.ffx.data.utilities.DataFileAccessHelper;

/**
 * Provides methods for accessing data stored in JSON files
 * 
 * @author Louis Drotos
 */
@Component
public class JsonReader {

	@Autowired
	private DataFileAccessHelper dataFileAccessHelp;
	
	/**
	 * Extracts a collection of polygon coordinates defining
	 * the first due areas of stations.
	 * 
	 * @return The collection of polygons
	 */
	public RawFirstDuePolygonCollection getStationFirstDuePolygons() {
		ObjectMapper mapper = new ObjectMapper();
		RawFirstDuePolygonCollection polygons = null;

		try {
			polygons = mapper.readValue(dataFileAccessHelp.getStationPolygonFile(), RawFirstDuePolygonCollection.class);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return polygons;
	}
}
