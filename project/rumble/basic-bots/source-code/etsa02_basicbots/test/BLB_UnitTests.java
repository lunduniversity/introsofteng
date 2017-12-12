package etsa02_basicbots.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import etsa02_basicbots.BasicLeaderBot;

public class BLB_UnitTests {

	private BasicLeaderBot testBot;
	
	@Before
	public void setUp() throws Exception {
		testBot = new BasicLeaderBot();
	}

	@After
	public void tearDown() throws Exception {
		testBot = null;
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
		fail("Not yet implemented");
	}
	
	@Test
	public void testCalculateRobotPosition() {
		fail("Not yet implemented");
	}
}
