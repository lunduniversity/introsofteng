package etsa02_basicbots.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import etsa02_basicbots.RadarSystem;
import etsa02_basicbots.Point;

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

	/*
	 * @Test public void testDetectTeammate() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testDetectNewEnemy() { fail("Not yet implemented"); }
	 * 
	 * @Test public void testDetectSameEnemy() { fail("Not yet implemented"); }
	 */

	@Test
	public void testRadarSystem_calculateRobotBearing_pos() {
		// Test positive bearing (110)
		MockScannedRobotEvent e = new MockScannedRobotEvent("mockEnemy1", 100.0, 20.0, 20.0, 20.0, 20.0, false);
		assertEquals("Check that bearing of scanned enemy with positive bearing is correctly calculated", 110,
				radarUT.calculateRobotBearing(botUT.getHeading(), e), 0.01);
	}

	@Test
	public void testRadarSystem_calculateRobotBearing_neg() {
		// Test negative bearing (-75)
		MockScannedRobotEvent e = new MockScannedRobotEvent("mockEnemy2", 100.0, -165, 20.0, 20.0, 20.0, false);
		assertEquals("Check that bearing of scanned enemy with negative bearing is correctly calculated", -75,
				radarUT.calculateRobotBearing(botUT.getHeading(), e), 0.01);
	}
	
	@Test
	public void testRadarSystem_calculateRobotBearing_zero() {
		// Test zero bearing (0)
		MockScannedRobotEvent e = new MockScannedRobotEvent("mockEnemy3", 100.0, -90.0, 20.0, 20.0, 20.0, false);
		assertEquals("Check that bearing of scanned enemy with zero bearing is correctly calculated", 0,
				radarUT.calculateRobotBearing(botUT.getHeading(), e), 0.01);
	}

	@Test
	public void testRadarSystem_calculateRobotPosition_pos() {
		// Test positive bearing (110)
		MockScannedRobotEvent e = new MockScannedRobotEvent("mockEnemy1", 100.0, 20.0, 223.61, 20.0, 20.0, false);
		double enemyBearing = radarUT.calculateRobotBearing(botUT.getHeading(), e); 		
		Point expectedPos = new Point(310.12, 23.52);
		assertEquals("Check that X position of scanned enemy with positive bearing is correctly calculated", expectedPos.getX(),
				radarUT.calculateRobotPosition(botUT, e, enemyBearing).getX(), 0.01);
		assertEquals("Check that Y position of scanned enemy is correctly calculated", expectedPos.getY(),
				radarUT.calculateRobotPosition(botUT, e, enemyBearing).getY(), 0.01);
	}
	
	@Test
	public void testRadarSystem_calculateRobotPosition_neg() {
		// Test negative bearing (-75)
		MockScannedRobotEvent e = new MockScannedRobotEvent("mockEnemy2", 100.0, -165, 50.0, 20.0, 20.0, false);
		double enemyBearing = radarUT.calculateRobotBearing(botUT.getHeading(), e); 		
		Point expectedPos = new Point(51.70, 112.94);
		assertEquals("Check that X position of scanned enemy with negative bearing is correctly calculated", expectedPos.getX(),
				radarUT.calculateRobotPosition(botUT, e, enemyBearing).getX(), 0.01);
		assertEquals("Check that Y position of scanned enemy is correctly calculated", expectedPos.getY(),
				radarUT.calculateRobotPosition(botUT, e, enemyBearing).getY(), 0.01);
	}
	
	@Test
	public void testRadarSystem_calculateRobotPosition_zero() {
		// Test zero bearing (0)
		MockScannedRobotEvent e = new MockScannedRobotEvent("mockEnemy3", 100.0, -90.0, 200.0, 20.0, 20.0, false);
		double enemyBearing = radarUT.calculateRobotBearing(botUT.getHeading(), e);
		Point expectedPos = new Point(100.00, 300.00);
		assertEquals("Check that X position of scanned enemy with zero bearing is correctly calculated", expectedPos.getX(),
				radarUT.calculateRobotPosition(botUT, e, enemyBearing).getX(), 0.01);
		assertEquals("Check that Y position of scanned enemy is correctly calculated", expectedPos.getY(),
				radarUT.calculateRobotPosition(botUT, e, enemyBearing).getY(), 0.01);
	}

}
