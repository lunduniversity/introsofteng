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

package etsa02_lab2;

import java.awt.geom.Point2D;

import robocode.Robot;
import robocode.ScannedRobotEvent;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MathUtils {
	
	/**
	 * Computes the position of a scanned enemy.
	 * @param robot Our own robot.
	 * @param e The ScannedRobotEvent returned by Robocode.
	 * @return The position of the enemy robot in two dimensions.
	 */
	public static Point2D.Double calcEnemyPosition(Robot robot, ScannedRobotEvent e) {
		// ETSA02 Lab2: Implement this using basic trigonometry, i.e., bearing and heading.
		// This is described in http://mark.random-article.com/weber/java/robocode/lesson4.html
		// Note that this is already implemented in onScannedRobot() in BasicMeleeBot_AntiPattern		
		throw new NotImplementedException();
	}
	
	/** 
	 * Computes the angle in radians between the y-axis (equivalent to north in robocode's coordinate system) and the vector
	 * formed by the two points. (The vector points from p1 to p2).
	 * @param p1 The first point.
	 * @param p2 The second point.
	 * @return The angle between the two points, in radians.
	 */
	public static double calcAngle(Point2D.Double p1, Point2D.Double p2) {
		// ETSA02 Lab2: Copy this method from the BasicMeleeBot_AntiPattern class.
		throw new NotImplementedException();
	}
	
	/**
	 * Computes the angle in degree between the y-axis (equivalent to north in robocode's coordinate system) and the vector
	 * formed by the two points. (The vector points from p1 to p2).
	 * @param p1 The first point.
	 * @param p2 The second point.
	 * @return The angle between the two points, in degrees.
	 */
	public static double calcAngleDegrees(Point2D.Double p1, Point2D.Double p2) {
		// ETSA02 Lab2: Call the method above and convert to degrees using Math.toDegrees()
		throw new NotImplementedException();
	}
}
