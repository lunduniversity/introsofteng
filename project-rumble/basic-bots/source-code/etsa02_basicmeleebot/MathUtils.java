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

package etsa02_basicmeleebot;

import java.awt.geom.Point2D;

import robocode.Robot;
import robocode.ScannedRobotEvent;

public class MathUtils {
	
	/**
	 * Computes the position of a scanned enemy.
	 * @param robot Our own robot.
	 * @param e The ScannedRobotEvent returned by Robocode.
	 * @return The position of the enemy robot in two dimensions.
	 */
	public static Point2D.Double calcEnemyPosition(Robot robot, ScannedRobotEvent e) {
		double absBearing = e.getBearing() + robot.getHeading();
		double x = robot.getX() + e.getDistance() * Math.sin(Math.toRadians(absBearing));
		double y = robot.getY() + e.getDistance() * Math.cos(Math.toRadians(absBearing));
		return new Point2D.Double(x, y);
	}
	
	/** 
	 * Computes the angle in radians between the y-axis (equivalent to north in robocode's coordinate system) and the vector
	 * formed by the two points. (The vector points from p1 to p2).
	 * @param p1 The first point.
	 * @param p2 The second point.
	 * @return The angle between the two points, in radians.
	 */
	public static double calcAngle(Point2D.Double p1, Point2D.Double p2) {
		double dx = p2.x - p1.x;
		double dy = p2.y - p1.y;
		return Math.atan2(dx, dy);
	}
	
	/**
	 * Computes the angle in degree between the y-axis (equivalent to north in robocode's coordinate system) and the vector
	 * formed by the two points. (The vector points from p1 to p2).
	 * @param p1 The first point.
	 * @param p2 The second point.
	 * @return The angle between the two points, in degrees.
	 */
	public static double calcAngleDegrees(Point2D.Double p1, Point2D.Double p2) {
		return Math.toDegrees(calcAngle(p1, p2));
	}
}
