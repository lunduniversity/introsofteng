package etsa02_lab2.test;

import static org.junit.Assert.assertEquals;
import java.awt.geom.Point2D;
import org.junit.Test;
import etsa02_lab2.MathUtils;

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
