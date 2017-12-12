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
	private BasicLeaderBot blbUT;
	private RadarSystem radarUT;
	
	private double fakeEnergy;
	private double fakeHeading;
	
	// Mock robots detected by fake radar
	private MockScannedRobot mockTeammate;
	private MockScannedRobot mockEnemy1;
	private MockScannedRobot mockEnemy2;
	
	@Before
	public void setUp() throws Exception {
		blbUT = new BasicLeaderBot();
		radarUT = new RadarSystem();
		setUpHealthyRight();
		mockTeammate = new MockScannedRobot("mockFriend", 10, 10, 10, 10, 10, false);
		mockEnemy1 = new MockScannedRobot("mockEnemy1", 20, 20, 20, 20, 20, false);
		mockEnemy2 = new MockScannedRobot("mockEnemy2", 30, 30, 30, 30, 30, false);	
	}

	@After
	public void tearDown() throws Exception {
		blbUT = null;
		radarUT = null;
		mockTeammate = null;
		mockEnemy1 = null;
		mockEnemy2 = null;
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
		ScannedRobotEvent e = new ScannedRobotEvent(mockEnemy1.name, mockEnemy1.energy, 
													mockEnemy1.bearing, mockEnemy1.distance, 
													mockEnemy1.heading, mockEnemy1.velocity,
													mockEnemy1.isSentryRobot);
		assertEquals(90, fakeHeading, 0.01);
		assertEquals(20, mockEnemy1.bearing, 0.01);
		System.out.println(e.getHeading());
		assertEquals(20, e.getRobotBearing(), 0.01);
		assertEquals(110, radarUT.calculateRobotBearing(fakeHeading, e), 0.1);
		//assertEquals("Check that bearing of scanned enemy is correctly calculated", 
		//			 110, radarUT.calculateRobotBearing(fakeHeading, e), 0.01);
	}
	
	@Test
	public void testCalculateRobotPosition() {
		fail("Not yet implemented");
	}
	
	private void setUpHealthyRight() {
		fakeEnergy = 100;
		fakeHeading = 90;
	}
	
	private class MockScannedRobot {
		String name;
		double energy;
		double bearing;
		double distance;
		double heading;
		double velocity;
		boolean isSentryRobot;
		
		MockScannedRobot(String name, double energy, double bearing, 
						  double distance, double heading, double velocity, 
						  boolean isSentryRobot) {
			this.name = name;
			this.energy = energy;
			this.bearing = bearing;
			this.distance = distance;
			this.heading = heading;
			this.velocity = velocity;
			this.isSentryRobot = isSentryRobot;
		}
	}
	
}
