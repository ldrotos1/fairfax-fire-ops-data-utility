package com.ffx.data.services;

import com.ffx.data.models.RawStationRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ffx.data.reader.CsvDataReader;

import java.util.List;

@Service
public class DatabaseService {

	@Autowired
	private CsvDataReader csvDataReader;
	
	public void viewDatabaseConnection() {
		
	}
	
	public void createDatabase() {

		List<RawStationRecord> rawStations = csvDataReader.parseStations();
		rawStations.toString();
	}
}
