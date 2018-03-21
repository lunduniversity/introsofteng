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
import java.util.Arrays;

import robocode.AdvancedRobot;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

/**
 * @author David Phung
 * 
 * Example robot for ETSA02. A melee bot implemented following the The Blob anti-pattern.
 * https://sourcemaking.com/antipatterns/the-blob
 */
public class BasicMeleeBot_AntiPattern extends AdvancedRobot {
	
	private Point2D.Double[] enemyPositions;
	private String[] enemyNames;
	private int enemyCount;

	/**
	 * The main loop controlling the robot behavior.
	 */
	@Override
	public void run() {
		enemyNames = new String[1];
		enemyPositions = new Point2D.Double[enemyNames.length];
		
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		while (true) {
			setTurnRadarRight(Double.POSITIVE_INFINITY);
			
			Point2D.Double robotPosition = new Point2D.Double(getX(), getY());
			
			// MOVEMENT
			// compute forces
			double xForce = 0;
			double yForce = 0;
			for (int i = 0; i < enemyCount; i++) {
				Point2D.Double enemyPosition = enemyPositions[i];
				double absBearing=Utils.normalAbsoluteAngle(calcAngle(robotPosition, enemyPosition));
				double distance = enemyPosition.distance(robotPosition);
				xForce -= Math.sin(absBearing) / (distance * distance);
				yForce -= Math.cos(absBearing) / (distance * distance);
			}
			
			// set the movement
			double angle = Math.atan2(xForce, yForce);
			if (xForce == 0 && yForce == 0) {
				// If no force, do nothing
			} else if (Math.abs(angle - Utils.normalRelativeAngle(getHeadingRadians())) < Math.PI / 2) {
				setTurnRightRadians(Utils.normalRelativeAngle(angle - getHeadingRadians()));
				setAhead(Double.POSITIVE_INFINITY);
			} else {
				setTurnRightRadians(Utils.normalRelativeAngle(angle + Math.PI - getHeadingRadians()));
				setAhead(Double.NEGATIVE_INFINITY);
			}
			
			// AIMING AND SHOOTING
			// find the closest enemy to shoot at.
			double smallestDistanceSq = Double.POSITIVE_INFINITY;
			Point2D.Double pointToTurnTo = null;
			
			for (int i = 0; i < enemyCount; i++) {
				Point2D.Double enemyPosition = enemyPositions[i];
				double d = enemyPosition.distanceSq(robotPosition);
				if (d < smallestDistanceSq) {
					smallestDistanceSq = d;
					pointToTurnTo = enemyPosition;
				}
			}
			double angleToTurn = 0;
			if (pointToTurnTo != null) {
				double dx = pointToTurnTo.x - robotPosition.x;
				double dy = pointToTurnTo.y - robotPosition.y;
				angleToTurn = Math.toDegrees(Math.atan2(dx, dy)) - getGunHeading();
			}

			if (getGunTurnRemaining() == 0) {
				setFire(1);
			}
			setTurnGunRight(Utils.normalRelativeAngleDegrees(angleToTurn));
			
			execute();
		}
	}

	/**
	 * Describes the action taken when a robot has been scanned.
	 * 
	 * @param event The ScannedRobotEvent provided by Robocode.
	 */
	@Override
	public void onScannedRobot(ScannedRobotEvent event) {
		double absBearing = event.getBearing() + getHeading();
		double x = getX() + event.getDistance() * Math.sin(Math.toRadians(absBearing));
		double y = getY() + event.getDistance() * Math.cos(Math.toRadians(absBearing));
		Point2D.Double enemyPosition = new Point2D.Double(x, y);
		int index = findEnemyName(event.getName());
		if (index >= 0) {
			enemyPositions[index] = enemyPosition;
		} else {
			if (enemyCount == enemyNames.length) {
				enemyNames = Arrays.copyOf(enemyNames, enemyNames.length * 2);
				enemyPositions = Arrays.copyOf(enemyPositions, enemyNames.length);
			}
			enemyNames[enemyCount] = event.getName();
			enemyPositions[enemyCount] = enemyPosition;
			enemyCount++;
		}
	}
	
	/**
	 * Describes the action taken when a robot has been destroyed.
	 * 
	 * @param event The RobotDeathEvent provided by Robocode.
	 */
	@Override
	public void onRobotDeath(RobotDeathEvent event) {
		int index = findEnemyName(event.getName());
		if (index >= 0) {
			for (int i = index + 1; i < enemyCount; i++) {
				enemyNames[i - 1] = enemyNames[i];
				enemyPositions[i-1] = enemyPositions[i];
			}
			enemyCount--;
		}
	}
	
	/**
	 * Find the index of an enemy robot given its name.
	 * 
	 * @param name The name of the robot.
	 * @return The index of the robot in the internal array, -1 if not found.
	 */
	private int findEnemyName(String name) {
		for (int i = 0; i < enemyCount; i++) {
			if (enemyNames[i].equals(name)) return i;
		}
		return -1;
	}
	
	/**
	 * Calculate the angle between two points in two dimensions.
	 * 
	 * @param p1 The first point.
	 * @param p2 The second point.
	 * @return The angle between the two points.
	 */
	private static double calcAngle(Point2D.Double p1, Point2D.Double p2) {
		double dx = p2.x - p1.x;
		double dy = p2.y - p1.y;
		return Math.atan2(dx, dy);
	}
}
