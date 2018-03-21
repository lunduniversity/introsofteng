package etsa02_lab2;

import java.awt.geom.Point2D;

import robocode.Robot;
import robocode.ScannedRobotEvent;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MathUtils {
	
	/**
	 * Computes the position of a scanned enemy.
	 * @param robot
	 * @param e
	 * @return
	 */
	public static Point2D.Double calcEnemyPosition(Robot robot, ScannedRobotEvent e) {
		throw new NotImplementedException();
	}
	
	/** 
	 * Computes the angle in radians between the y-axis (equivalent to north in robocode's coordinate system) and the vector
	 * formed by the two points. (The vector points from p1 to p2).
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double calcAngle(Point2D.Double p1, Point2D.Double p2) {
		throw new NotImplementedException();
	}
	
	/**
	 * Computes the angle in degree between the y-axis (equivalent to north in robocode's coordinate system) and the vector
	 * formed by the two points. (The vector points from p1 to p2).
	 * @param p1
	 * @param p2
	 * @return
	 */
	public static double calcAngleDegrees(Point2D.Double p1, Point2D.Double p2) {
		throw new NotImplementedException();
	}
}
