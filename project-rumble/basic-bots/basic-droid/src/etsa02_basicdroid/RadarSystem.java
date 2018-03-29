/**	
Copyright (c) 2017 Markus Borg

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
package etsa02_basicbots;

import robocode.ScannedRobotEvent;
import robocode.TeamRobot;

public class RadarSystem {

	/**
	 * calculateEnemyBearing: Compute bearing by adding own robot's bearing and
	 * enemy bearing
	 */
	public double calculateRobotBearing(double myBearing, ScannedRobotEvent e) {
		return myBearing + e.getBearing();
	}

	/**
	 * calculateRobotPosition: Compute enemy position using its bearing
	 * 
	 * @param The
	 *            ScannedRobotEvent
	 * @param Bearing
	 *            of the scanned robot
	 * @return A Point representing the scanned robot's position
	 */
	public Point calculateRobotPosition(TeamRobot robot, ScannedRobotEvent e, double enemyBearing) {
		double enemyX = robot.getX() + e.getDistance() * Math.sin(Math.toRadians(enemyBearing));
		double enemyY = robot.getY() + e.getDistance() * Math.cos(Math.toRadians(enemyBearing));

		/*
		 * Saved for debug purposes System.out.println( "x = " + robot.getX() +
		 * " + " + e.getDistance() + " * sin(" + Math.toRadians(enemyBearing) +
		 * ") = " + enemyX); System.out.println( "y = " + robot.getY() + " + " +
		 * e.getDistance() + " * cos(" + Math.toRadians(enemyBearing) + ") = " +
		 * enemyY);
		 */

		return new Point(enemyX, enemyY);
	}
}
