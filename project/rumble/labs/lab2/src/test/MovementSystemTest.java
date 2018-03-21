package etsa02_lab2.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import etsa02_lab2.MovementSystem;
import etsa02_lab2.EnemyDatabase;

public class MovementSystemTest {

	private MovementSystem movementUT;
	private MockBot mockBot;
	private EnemyDatabase enemyHelper;
	
	@Before
	public void setUp() {
		mockBot = new MockBot("fake robot", 100, 0, 400, 300);
		enemyHelper = new EnemyDatabase(mockBot);
		movementUT = new MovementSystem(mockBot, enemyHelper, null);
	}
	
	@After
	public void tearDown() {
		mockBot = null;
		enemyHelper = null;
		movementUT = null;
	}
	
	@Test
	public void testOneEnemyAtBearing0() {
		MockScannedRobotEvent e = new MockScannedRobotEvent("fake enemy", 100, 0, 100, 0, 0, false);
		enemyHelper.addEnemy(e);
		movementUT.update();
		
		assertEquals("Check that the turn angle is correct", 0, mockBot.getTurnRemaining(), 0.001d);
		assertTrue("Check that the moving direction is correct.", mockBot.getDistanceRemaining() < 0);
	}
	
	@Test
	public void testOneEnemyAtBearingPositiveLessThan90() {
		MockScannedRobotEvent e = new MockScannedRobotEvent("fake enemy", 100, 45, 100, 0, 0, false);
		enemyHelper.addEnemy(e);
		movementUT.update();
		
		assertEquals("Check that the turn angle is correct", 45, mockBot.getTurnRemaining(), 0.001d);
		assertTrue("Check that the moving direction is correct.", mockBot.getDistanceRemaining() < 0);
	}
	
	public void testOneEnemyAtBearingPositiveGreaterThan90() {
		MockScannedRobotEvent e = new MockScannedRobotEvent("fake enemy", 100, 135, 100, 0, 0, false);
		enemyHelper.addEnemy(e);
		movementUT.update();
		
		assertEquals("Check that the turn angle is correct", -45, mockBot.getTurnRemaining(), 0.001d);
		assertTrue("Check that the moving direction is correct.", mockBot.getDistanceRemaining() > 0);
	}
	
	@Test
	public void testOneEnemyAtBearing180() {
		MockScannedRobotEvent e = new MockScannedRobotEvent("fake enemy", 100, 180, 100, 0, 0, false);
		enemyHelper.addEnemy(e);
		movementUT.update();
		
		assertEquals("Check that the turn angle is correct", 0, mockBot.getTurnRemaining(), 0.001d);
		assertTrue("Check that the moving direction is correct.", mockBot.getDistanceRemaining() > 0);
	}
	
	@Test
	public void testOneEnemyAtBearingNegativeGreaterThan90() {
		MockScannedRobotEvent e = new MockScannedRobotEvent("fake enemy", 100, 225, 100, 0, 0, false);
		enemyHelper.addEnemy(e);
		movementUT.update();
		
		assertEquals("Check that the turn angle is correct", 45, mockBot.getTurnRemaining(), 0.001d);
		assertTrue("Check that the moving direction is correct.", mockBot.getDistanceRemaining() > 0);
	}
	
	@Test
	public void testOneEnemyAtBearingNegativeLessThan90() {
		MockScannedRobotEvent e = new MockScannedRobotEvent("fake enemy", 100, 315, 100, 0, 0, false);
		enemyHelper.addEnemy(e);
		movementUT.update();
		
		assertEquals("Check that the turn angle is correct", -45, mockBot.getTurnRemaining(), 0.001d);
		assertTrue("Check that the moving direction is correct.", mockBot.getDistanceRemaining() < 0);
	}
	
	@Test
	public void testTwoEnemies() {
		MockScannedRobotEvent e1 = new MockScannedRobotEvent("fake enemy 1", 100, 0, 100, 0, 0, false);
		MockScannedRobotEvent e2 = new MockScannedRobotEvent("fake enemy 2", 100, 90, 200, 0, 0, false);
		enemyHelper.addEnemy(e1);
		enemyHelper.addEnemy(e2);
		movementUT.update();
		
		assertEquals("Check that the turn angle is correct", 14.036, mockBot.getTurnRemaining(), 0.001d);
		assertTrue("Check that the moving direction is correct.", mockBot.getDistanceRemaining() < 0);
	}
}
