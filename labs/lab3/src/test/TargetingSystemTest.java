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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.lth.cs.etsa02.basicmeleebot.EnemyTracker;
import se.lth.cs.etsa02.basicmeleebot.TargetingSystem;

public class TargetingSystemTest {
	
	private TargetingSystem targetingUT;
	private MockBot mockBot;
	private EnemyTracker enemyTracker;
	
	@Before
	public void setUp() {
		mockBot = new MockBot("fake robot", 100, 0, 400, 300);
		enemyTracker = new EnemyTracker(mockBot);
		targetingUT = new TargetingSystem(enemyTracker, mockBot);
	}
	
	@After
	public void tearDown() {
		mockBot = null;
		enemyTracker = null;
		targetingUT = null;
	}
	
	@Test
	public void testOneEnemyAtBearing0() {
		MockScannedRobotEvent e = new MockScannedRobotEvent("fake enemy", 100, 0, 100, 0, 0, false);
		enemyTracker.addEnemy(e);
		targetingUT.update();
		
		assertEquals("Check that the gun turning angle is correct", 0, mockBot.getGunTurnRemaining(), 0.001d);
	}
	
	@Test
	public void testOneEnemyAtBearingPos() {
		MockScannedRobotEvent e = new MockScannedRobotEvent("fake enemy", 100, 90, 100, 0, 0, false);
		enemyTracker.addEnemy(e);
		targetingUT.update();
		
		assertEquals("Check that the gun turning angle is correct", 90, mockBot.getGunTurnRemaining(), 0.001d);
	}
	
	@Test
	public void testOneEnemyAtBearing180() {
		MockScannedRobotEvent e = new MockScannedRobotEvent("fake enemy", 100, 180, 100, 0, 0, false);
		enemyTracker.addEnemy(e);
		targetingUT.update();
		
		assertEquals("Check that the gun turning angle is correct", 180, Math.abs(mockBot.getGunTurnRemaining()), 0.001d);
	}
	
	@Test
	public void testOneEnemyAtBearingNeg() {
		MockScannedRobotEvent e = new MockScannedRobotEvent("fake enemy", 100, 270, 100, 0, 0, false);
		enemyTracker.addEnemy(e);
		targetingUT.update();
		
		assertEquals("Check that the gun turning angle is correct", -90, mockBot.getGunTurnRemaining(), 0.001d);
	}
}
