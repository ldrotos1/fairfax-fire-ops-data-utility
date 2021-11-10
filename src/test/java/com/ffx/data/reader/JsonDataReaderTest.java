package com.ffx.data.reader;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ffx.data.models.RawFirstDuePolygonCollection;

@SpringBootTest
class JsonDataReaderTest {

	@Autowired
	private JsonDataReader jsonDataReader;
	
	@Test
	public void parserPolygonsCountTest() {
		RawFirstDuePolygonCollection polygons = jsonDataReader.getStationFirstDuePolygons();
		assertEquals(38, polygons.getFeatures().size());
	}
}
