package etsa02_basicbots.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import etsa02_basicbots.BasicLeaderBot;
import etsa02_basicbots.RadarSystem;
import robocode.ScannedRobotEvent;

public class BasicBots_UnitTests {

	// Units under test (UT = under test)
	private MockBot botUT;
	private RadarSystem radarUT;
	
	@Before
	public void setUp() throws Exception {
		botUT = new MockBot("mockBot", 100.0, 90.0, 100.0, 100.0);
		radarUT = new RadarSystem();
	}

	@After
	public void tearDown() throws Exception {
		botUT = null;
		radarUT = null;
	}

	@Test
	public void testDetectTeammate() {
		fail("Not yet implemented");
	}

	@Test
	public void testDetectNewEnemy() {
		fail("Not yet implemented");
	}

	@Test
	public void testDetectSameEnemy() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateRobotBearing() {
		// Test positive bearing
		MockScannedRobotEvent e = new MockScannedRobotEvent("mockEnemy1", 100.0, 20.0, 20.0, 20.0, 20.0, false);
		assertEquals("Check that bearing of scanned enemy is correctly calculated",
					 110, radarUT.calculateRobotBearing(botUT.getHeading(), e), 0.01);
		// Test negative bearing
		MockScannedRobotEvent e2 = new MockScannedRobotEvent("mockEnemy2", 100.0, -180, 20.0, 20.0, 20.0, false);
		System.out.println(botUT.getHeading() + " + " + e2.getBearing());
		assertEquals("Check that bearing of scanned enemy is correctly calculated", 
					 -90, radarUT.calculateRobotBearing(botUT.getHeading(), e2), 0.01);
		// Test zero bearing
		MockScannedRobotEvent e3 = new MockScannedRobotEvent("mockEnemy3", 100.0, -90.0, 20.0, 20.0, 20.0, false);
		assertEquals("Check that bearing of scanned enemy is correctly calculated", 
					 0, radarUT.calculateRobotBearing(botUT.getHeading(), e3), 0.01);
	}

	@Test
	public void testCalculateRobotPosition() {
		fail("Not yet implemented");
	}
	
}
