package com.ffx.data.reader;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ffx.data.models.RawApparatusRecord;
import com.ffx.data.models.RawApparatusTypeRecord;
import com.ffx.data.models.RawPersonnelRecord;
import com.ffx.data.models.RawStationRecord;

@SpringBootTest
class CsvDataReaderTest {

	@Autowired
	private CsvDataReader csvDataReader;
	
	private String areaDescription = "Demand Zone 401 is an Urban/Suburban zone of 18.00 Square "
			+ "miles within the 2nd Battalion that serves a population of 45,169. Station 401 consists "
			+ "of numerous residential, commercial and government high rise complexes. There are several "
			+ "high security/limited access intelligence facilities within the zone.";
	
	private String fireHazardDescription = "Numerous residential and commercial high-rises. Several high "
			+ "security government intelligence campus present unique risk for delay access to seat of the "
			+ "fire. Large single-family homes with limited access due to small access bridges that do not "
			+ "support heavy fire department apparatus. Large homes with limited water or rural water supply areas.";
	
	private String nonFireHazardDescription = "Numerous vehicle accidents on I-495, I-66, Rt. 267, Rt. 123, "
			+ "Rt. 193. Numerous residential high rise assisted living facilities and lower income housing in "
			+ "the Pimmit Hills section produces numerous calls for medical assistance. Sometimes this includes "
			+ "several calls simultaneously. During the summer months calls for service to Great Falls Park and "
			+ "Scotts Run increase time needed to reach patients due to terrain and limited access. A large number"
			+ " of VIP''s, CEO''s, and International/Federal Government Officials live in the first due area.";
	
	@Test
	public void parserStationsCountTest() {
		List<RawStationRecord> stations = csvDataReader.parseStations();
		assertEquals(38, stations.size());
	}
	
	@Test
	public void parseStationsContentTest() {
		List<RawStationRecord> stations = csvDataReader.parseStations();
		RawStationRecord station = stations.get(0);
		assertNotNull(station.getStationId());
		assertNotNull(station.getFacilityId());
		assertNotNull(station.getAddressId());
		assertEquals("401", station.getStationNumber());
		assertEquals("Fairfax County Fire and Rescue Department", station.getDepartmentName());
		assertEquals("402", station.getBattalion());
		assertEquals("38.93171", station.getLatitude());
		assertEquals("-77.17721", station.getLongitude());
		assertEquals("McLean", station.getStationName());
		assertEquals("1455 Laughlin Avenue", station.getStreetAddress());
		assertEquals("McLean", station.getCity());
		assertEquals("VA", station.getState());
		assertEquals("22101", station.getZipCode());
		assertEquals("703-356-6671", station.getPhoneNumber());
		assertEquals("21", station.getFireBoxes());
		assertTrue(station.isVolunteer());
		assertEquals("Suburban", station.getDensity());
		assertEquals(areaDescription, station.getAreaDescription());
		assertEquals(fireHazardDescription, station.getFireHazardDescription());
		assertEquals(nonFireHazardDescription, station.getNonFireHazardDescription());
	}
	
	@Test
	public void parseApparatusTypesTest() {
		List<RawApparatusTypeRecord> apparatusTypes = csvDataReader.parseApparatusTypes();
		RawApparatusTypeRecord apparatusType = apparatusTypes.get(8);
		assertNotNull(apparatusType.getApparatusTypeId());
		assertEquals("Engine", apparatusType.getApparatusTypeName());
		assertEquals("Suppression", apparatusType.getApparatusTypeCategory());
		assertEquals("engine", apparatusType.getApparatusTypeImage());
		assertEquals("engine", apparatusType.getApparatusTypeImage());
		assertEquals(6, apparatusType.getMaxStaffCount());
		assertEquals(3, apparatusType.getMinFireFighterStaffCount());
		assertEquals(0, apparatusType.getMinTechStaffCount());
		assertEquals(1, apparatusType.getMinOfficerStaffCount());
		assertEquals(0, apparatusType.getMinCommanderStaffCount());
		assertTrue(apparatusType.isParamedicRequired());
		assertFalse(apparatusType.isCrossStaffed());
		assertFalse(apparatusType.isVolunteerStaffed());
	}
	
	@Test
	public void parseApparatusTest() {
		List<RawApparatusRecord> apparatusList = csvDataReader.parseApparatus();
		RawApparatusRecord apparatus = apparatusList.get(0);
		assertNotNull(apparatus.getApparatusId());
		assertEquals("410", apparatus.getStationNumber());
		assertEquals("Fairfax County Fire and Rescue Department", apparatus.getDepartmentName());
		assertEquals("A410", apparatus.getUnitDesignator());
		assertEquals("Ambulance", apparatus.getApparatusTypeName());
		assertNull(apparatus.getRescueType());
		assertNull(apparatus.getTruckType());
		assertFalse(apparatus.isHasFoamCell());
		assertFalse(apparatus.isInService());
		assertTrue(apparatus.isReserved());
	}
	
	@Test
	public void parsePersonnelTest() {
		List<RawPersonnelRecord> personnel = csvDataReader.parsePersonnel();
		RawPersonnelRecord person = personnel.get(0);
		assertNotNull(person.getPersonId());
		assertEquals("Kenny", person.getForename());
		assertEquals("Wolfrey", person.getSurname());
		assertEquals("Battalion Chief", person.getRank());
		assertEquals("Paramedic", person.getMedCert());
		assertEquals("408", person.getStationNumber());
		assertEquals("BC404", person.getCommandPosition());
		assertNull(person.getShiftLeader());
		assertNull(person.getStationLeader());
	}
}