package etsa02_lab2.test;

import static org.junit.Assert.assertEquals;

import java.awt.geom.Point2D;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import etsa02_lab2.EnemyDatabase;

public class EnemyDatabaseTest {

	private EnemyDatabase enemyHelperUT;
	private MockBot mockBot;
	
	@Before
	public void setUp() {
		mockBot = new MockBot("fake robot", 100, 0, 0, 0);
		enemyHelperUT = new EnemyDatabase(mockBot);
	}
	
	@After
	public void tearDown() {
		enemyHelperUT = null;
		mockBot = null;
	}
	
	@Test
	public void testAddEnemies() {
		MockScannedRobotEvent e1 = new MockScannedRobotEvent("fake enemy 1", 100, 0, 100, 0, 0, false);
		MockScannedRobotEvent e2 = new MockScannedRobotEvent("fake enemy 2", 100, 0, 200, 0, 0, false);
		
		enemyHelperUT.addEnemy(e1);
		enemyHelperUT.addEnemy(e2);
		Point2D.Double[] enemyPositions = enemyHelperUT.getEnemyPositions();
		
		Point2D.Double expectedPosition1 = new Point2D.Double(0, 100);
		Point2D.Double expectedPosition2 = new Point2D.Double(0, 200);
		assertEquals(2, enemyPositions.length);
		assertEquals(expectedPosition1.getX(), enemyPositions[0].getX(), 0.001d);
		assertEquals(expectedPosition1.getY(), enemyPositions[0].getY(), 0.001d);
		assertEquals(expectedPosition2.getX(), enemyPositions[1].getX(), 0.001d);
		assertEquals(expectedPosition2.getY(), enemyPositions[1].getY(), 0.001d);
	}
	
	@Test
	public void testRemoveEnemies() {
		MockScannedRobotEvent e1 = new MockScannedRobotEvent("fake enemy 1", 100, 0, 100, 0, 0, false);
		MockScannedRobotEvent e2 = new MockScannedRobotEvent("fake enemy 2", 100, 0, 200, 0, 0, false);
		
		enemyHelperUT.addEnemy(e1);
		enemyHelperUT.addEnemy(e2);
		enemyHelperUT.removeEnemy("fake enemy 1");
		Point2D.Double[] enemyPositions = enemyHelperUT.getEnemyPositions();
		
		Point2D.Double expectedPosition = new Point2D.Double(0, 200);
		assertEquals(1, enemyPositions.length);
		assertEquals(expectedPosition.getX(), enemyPositions[0].getX(), 0.001d);
		assertEquals(expectedPosition.getY(), enemyPositions[0].getY(), 0.001d);
	}
	
	@Test
	public void testRemoveEnemiesNotExists() {
		MockScannedRobotEvent e1 = new MockScannedRobotEvent("fake enemy 1", 100, 0, 100, 0, 0, false);
		MockScannedRobotEvent e2 = new MockScannedRobotEvent("fake enemy 2", 100, 0, 200, 0, 0, false);
		
		enemyHelperUT.addEnemy(e1);
		enemyHelperUT.addEnemy(e2);
		enemyHelperUT.removeEnemy("fake enemy that does not exist");
		Point2D.Double[] enemyPositions = enemyHelperUT.getEnemyPositions();
		
		Point2D.Double expectedPosition1 = new Point2D.Double(0, 100);
		Point2D.Double expectedPosition2 = new Point2D.Double(0, 200);
		assertEquals(2, enemyPositions.length);
		assertEquals(expectedPosition1.getX(), enemyPositions[0].getX(), 0.001d);
		assertEquals(expectedPosition1.getY(), enemyPositions[0].getY(), 0.001d);
		assertEquals(expectedPosition2.getX(), enemyPositions[1].getX(), 0.001d);
		assertEquals(expectedPosition2.getY(), enemyPositions[1].getY(), 0.001d);
	}
	
	@Test
	public void testGetEnemyCount() {
		MockScannedRobotEvent e1 = new MockScannedRobotEvent("fake enemy 1", 100, 0, 100, 0, 0, false);
		MockScannedRobotEvent e2 = new MockScannedRobotEvent("fake enemy 2", 100, 0, 200, 0, 0, false);
		
		enemyHelperUT.addEnemy(e1);
		enemyHelperUT.addEnemy(e2);
		
		assertEquals(2, enemyHelperUT.getEnemyCount());
	}
}
