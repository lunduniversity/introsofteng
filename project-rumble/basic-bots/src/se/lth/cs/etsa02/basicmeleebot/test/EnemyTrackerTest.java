/**	
Copyright (c) 2018 David Phung

Building on work by Mathew A. Nelson and Robocode contributors.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package se.lth.cs.etsa02.basicmeleebot.test;

import static org.junit.Assert.assertEquals;

import java.awt.geom.Point2D;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.lth.cs.etsa02.basicmeleebot.EnemyTracker;

public class EnemyTrackerTest {

	private EnemyTracker enemyHelperUT;
	private MockBot mockBot;
	
	@Before
	public void setUp() {
		mockBot = new MockBot("fake robot", 100, 0, 0, 0);
		enemyHelperUT = new EnemyTracker(mockBot);
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
