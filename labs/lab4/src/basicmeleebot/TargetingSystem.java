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

package etsa02_lab4;

import java.awt.geom.Point2D;
import robocode.util.Utils;

/**
 * A simple class handling aiming and firing. It simply shoots at the closest target 
 * based on the last known positions of all enemies.
 */
public class TargetingSystem {
	
	private EnemyTracker enemyTracker;
	private BasicMeleeBot robot;
	
	/**
	 * Construct a simple object to handle head-on targeting. It simply shoots at the closest target 
	 * based on the last known positions of all enemies.
	 * @param enemyTracker the object managing enemies.
	 * @param robot the robot we are currently working on
	 */
	public TargetingSystem(EnemyTracker enemyTracker, BasicMeleeBot robot) {
		this.enemyTracker = enemyTracker;
		this.robot = robot;
	}
	
	/**
	 * To be called every turn. Find the closest target and shoot at it.
	 */
	public void update() {
		// find the closest enemy and store its position
		Point2D.Double robotPosition = new Point2D.Double(robot.getX(), robot.getY());
		
		double smallestDistanceSq = Double.POSITIVE_INFINITY;
		Point2D.Double pointToTurnTo = null;
		Point2D.Double[] enemyPositions = enemyTracker.getEnemyPositions();
		for (int i = 0; i < enemyTracker.getEnemyCount(); i++) {
			Point2D.Double enemyPosition = enemyPositions[i];
			double d = enemyPosition.distanceSq(robotPosition);
			if (d < smallestDistanceSq) {
				smallestDistanceSq = d;
				pointToTurnTo = enemyPosition;
			}
		}
		//compute how much our robot's gun has to turn
		double angleToTurn = 0;
		if (pointToTurnTo != null) {
			double dx = pointToTurnTo.x - robotPosition.x;
			double dy = pointToTurnTo.y - robotPosition.y;
			angleToTurn = Math.toDegrees(Math.atan2(dx, dy)) - robot.getGunHeading();
		}

		// fire when the gun has finished turning
		if (robot.getGunTurnRemaining() == 0) {
			robot.setFire(1);
		}
		// turn the gun
		robot.setTurnGunRight(Utils.normalRelativeAngleDegrees(angleToTurn));
	}
}
