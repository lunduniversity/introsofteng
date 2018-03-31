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
import org.junit.Test;

import se.lth.cs.etsa02.basicmeleebot.MathUtils;

public class MathUtilsTest {

	@Test
	public void testCalcRobotPositionAtBearing0() {
		MockScannedRobotEvent e = new MockScannedRobotEvent("mockEnemy3", 100.0, 0, 100.0, 20.0, 20.0, false);
		MockBot robot = new MockBot("mock bot", 100, 0, 100, 200);

		Point2D.Double scannedRobotsPosition = MathUtils.calcEnemyPosition(robot, e);

		Point2D.Double expectedPosition = new Point2D.Double(100, 300);
		assertEquals("Check that the scanned robot's position is correctly calculated", expectedPosition.getX(),
				scannedRobotsPosition.getX(), 0.001d);
		assertEquals("Check that the scanned robot's position is correctly calculated", expectedPosition.getY(),
				scannedRobotsPosition.getY(), 0.001d);
	}
	
	@Test
	public void testCalcRobotPositionAtBearingPos() {
		MockScannedRobotEvent e = new MockScannedRobotEvent("mockEnemy3", 100.0, 90, 100.0, 20.0, 20.0, false);
		MockBot robot = new MockBot("mock bot", 100, 0, 100, 200);

		Point2D.Double scannedRobotsPosition = MathUtils.calcEnemyPosition(robot, e);

		Point2D.Double expectedPosition = new Point2D.Double(200, 200);
		assertEquals("Check that the scanned robot's position is correctly calculated", expectedPosition.getX(),
				scannedRobotsPosition.getX(), 0.001d);
		assertEquals("Check that the scanned robot's position is correctly calculated", expectedPosition.getY(),
				scannedRobotsPosition.getY(), 0.001d);
	}
	
	@Test
	public void testCalcRobotPositionAtBearing180() {
		MockScannedRobotEvent e = new MockScannedRobotEvent("mockEnemy3", 100.0, 180, 100.0, 20.0, 20.0, false);
		MockBot robot = new MockBot("mock bot", 100, 0, 100, 200);

		Point2D.Double scannedRobotsPosition = MathUtils.calcEnemyPosition(robot, e);

		Point2D.Double expectedPosition = new Point2D.Double(100, 100);
		assertEquals("Check that the scanned robot's position is correctly calculated", expectedPosition.getX(),
				scannedRobotsPosition.getX(), 0.001d);
		assertEquals("Check that the scanned robot's position is correctly calculated", expectedPosition.getY(),
				scannedRobotsPosition.getY(), 0.001d);
	}
	
	@Test
	public void testCalcRobotPositionAtBearingNeg() {
		MockScannedRobotEvent e = new MockScannedRobotEvent("mockEnemy3", 100.0, 270, 100.0, 20.0, 20.0, false);
		MockBot robot = new MockBot("mock bot", 100, 0, 100, 200);

		Point2D.Double scannedRobotsPosition = MathUtils.calcEnemyPosition(robot, e);

		Point2D.Double expectedPosition = new Point2D.Double(0, 200);
		assertEquals("Check that the scanned robot's position is correctly calculated", expectedPosition.getX(),
				scannedRobotsPosition.getX(), 0.001d);
		assertEquals("Check that the scanned robot's position is correctly calculated", expectedPosition.getY(),
				scannedRobotsPosition.getY(), 0.001d);
	}
	
	@Test
	public void testCalcAngleZero() {
		Point2D.Double p1 = new Point2D.Double(100, 200);
		Point2D.Double p2 = new Point2D.Double(100, 300);
		
		double angle = MathUtils.calcAngle(p1, p2);
		
		assertEquals("Check that angle is correctly calculated", 0, angle, 0.001d);
	}
	
	@Test
	public void testCalcAnglePositiveLessThan90() {
		Point2D.Double p1 = new Point2D.Double(100, 200);
		Point2D.Double p2 = new Point2D.Double(200, 300);
		
		double angle = MathUtils.calcAngle(p1, p2);
		
		assertEquals("Check that angle is correctly calculated", Math.PI / 4, angle, 0.001d);
	}
	
	@Test
	public void testCalcAnglePositive90() {
		Point2D.Double p1 = new Point2D.Double(100, 200);
		Point2D.Double p2 = new Point2D.Double(200, 200);
		
		double angle = MathUtils.calcAngle(p1, p2);
		
		assertEquals("Check that angle is correctly calculated", Math.PI / 2, angle, 0.001d);
	}
	
	@Test
	public void testCalcAnglePositiveGreaterThan90() {
		Point2D.Double p1 = new Point2D.Double(100, 200);
		Point2D.Double p2 = new Point2D.Double(200, 100);
		
		double angle = MathUtils.calcAngle(p1, p2);
		
		assertEquals("Check that angle is correctly calculated", Math.PI / 4 * 3, angle, 0.001d);
	}
	
	@Test
	public void testCalcAngle180() {
		Point2D.Double p1 = new Point2D.Double(100, 200);
		Point2D.Double p2 = new Point2D.Double(100, 100);
		
		double angle = MathUtils.calcAngle(p1, p2);
		
		assertEquals("Check that angle is correctly calculated", Math.PI, angle, 0.001d);
	}
	
	@Test
	public void testCalcAngleNegativeLessThan90() {
		Point2D.Double p1 = new Point2D.Double(100, 200);
		Point2D.Double p2 = new Point2D.Double(0, 300);
		
		double angle = MathUtils.calcAngle(p1, p2);
		
		assertEquals("Check that angle is correctly calculated", Math.PI / -4, angle, 0.001d);
	}
	
	@Test
	public void testCalcAngleNegative90() {
		Point2D.Double p1 = new Point2D.Double(100, 200);
		Point2D.Double p2 = new Point2D.Double(0, 200);
		
		double angle = MathUtils.calcAngle(p1, p2);
		
		assertEquals("Check that angle is correctly calculated", Math.PI / -2, angle, 0.001d);
	}
	
	@Test
	public void testCalcAngleNegativeGreaterThan90() {
		Point2D.Double p1 = new Point2D.Double(100, 200);
		Point2D.Double p2 = new Point2D.Double(0, 100);
		
		double angle = MathUtils.calcAngle(p1, p2);
		
		assertEquals("Check that angle is correctly calculated", Math.PI / -4 * 3, angle, 0.001d);
	}
	
	@Test
	public void testcalcAngleDegreesDegreesZero() {
		Point2D.Double p1 = new Point2D.Double(100, 200);
		Point2D.Double p2 = new Point2D.Double(100, 300);
		
		double angle = MathUtils.calcAngleDegrees(p1, p2);
		
		assertEquals("Check that angle is correctly calculated", 0, angle, 0.001d);
	}
	
	@Test
	public void testcalcAngleDegreesDegreesPositiveLessThan90() {
		Point2D.Double p1 = new Point2D.Double(100, 200);
		Point2D.Double p2 = new Point2D.Double(200, 300);
		
		double angle = MathUtils.calcAngleDegrees(p1, p2);
		
		assertEquals("Check that angle is correctly calculated", 45, angle, 0.001d);
	}
	
	@Test
	public void testcalcAngleDegreesDegreesPositive90() {
		Point2D.Double p1 = new Point2D.Double(100, 200);
		Point2D.Double p2 = new Point2D.Double(200, 200);
		
		double angle = MathUtils.calcAngleDegrees(p1, p2);
		
		assertEquals("Check that angle is correctly calculated", 90, angle, 0.001d);
	}
	
	@Test
	public void testcalcAngleDegreesDegreesPositiveGreaterThan90() {
		Point2D.Double p1 = new Point2D.Double(100, 200);
		Point2D.Double p2 = new Point2D.Double(200, 100);
		
		double angle = MathUtils.calcAngleDegrees(p1, p2);
		
		assertEquals("Check that angle is correctly calculated", 135, angle, 0.001d);
	}
	
	@Test
	public void testcalcAngleDegreesDegrees180() {
		Point2D.Double p1 = new Point2D.Double(100, 200);
		Point2D.Double p2 = new Point2D.Double(100, 100);
		
		double angle = MathUtils.calcAngleDegrees(p1, p2);
		
		assertEquals("Check that angle is correctly calculated", 180, angle, 0.001d);
	}
	
	@Test
	public void testcalcAngleDegreesDegreesNegativeLessThan90() {
		Point2D.Double p1 = new Point2D.Double(100, 200);
		Point2D.Double p2 = new Point2D.Double(0, 300);
		
		double angle = MathUtils.calcAngleDegrees(p1, p2);
		
		assertEquals("Check that angle is correctly calculated", -45, angle, 0.001d);
	}
	
	@Test
	public void testcalcAngleDegreesDegreesNegative90() {
		Point2D.Double p1 = new Point2D.Double(100, 200);
		Point2D.Double p2 = new Point2D.Double(0, 200);
		
		double angle = MathUtils.calcAngleDegrees(p1, p2);
		
		assertEquals("Check that angle is correctly calculated", -90, angle, 0.001d);
	}
	
	@Test
	public void testcalcAngleDegreesDegreesNegativeGreaterThan90() {
		Point2D.Double p1 = new Point2D.Double(100, 200);
		Point2D.Double p2 = new Point2D.Double(0, 100);
		
		double angle = MathUtils.calcAngleDegrees(p1, p2);
		
		assertEquals("Check that angle is correctly calculated", -135, angle, 0.001d);
	}
}
