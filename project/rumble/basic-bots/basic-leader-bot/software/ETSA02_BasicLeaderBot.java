/**	
Copyright (c) 2017 Markus Borg

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
package sampleteam;

import robocode.HitByBulletEvent;
import robocode.ScannedRobotEvent;
import robocode.TeamRobot;
import java.awt.*;
import java.io.IOException;

/**
 * ETSA02_BasicLeaderBot - a sample team robot for ETSA02.
 * <p/>
 * Looks around for enemies, and orders teammates to fire.
 * If an enemy is close, it sometimes changes its position.
 *
 * @author Markus Borg
 */
public class ETSA02_BasicLeaderBot extends TeamRobot {
	
	private LinkedList<Point> enemyPositions;

	/**
	 * run:  Leader's default behavior
	 */
	public void run() {
		// Prepare RobotColors object
		RobotColors c = new RobotColors();

		c.bodyColor = Color.red;
		c.gunColor = Color.red;
		c.radarColor = Color.red;
		c.scanColor = Color.yellow;
		c.bulletColor = Color.yellow;

		// Set the color of this robot containing the RobotColors
		setBodyColor(c.bodyColor);
		setGunColor(c.gunColor);
		setRadarColor(c.radarColor);
		setScanColor(c.scanColor);
		setBulletColor(c.bulletColor);
		try {
			// Send RobotColors object to the entire team
			broadcastMessage(c);
		} catch (IOException ignored) {}
		
		// Initiate attributes
		enemyPositions = new LinkedList<Position>();
		
		// Normal behavior
		while (true) {
			setTurnRadarRight(10000);
			ahead(100);
			back(100);
		}
	}

	/**
	 * onScannedRobot:  What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Don't fire on teammates
		if (isTeammate(e.getName())) {
			return;
		}
		// Calculate enemy bearing
		double enemyBearing = calculateEnemyBearing(e);
		// Calculate enemy's position
		double enemyX = getX() + e.getDistance() * Math.sin(Math.toRadians(enemyBearing));
		double enemyY = getY() + e.getDistance() * Math.cos(Math.toRadians(enemyBearing));

		// 
		
		try {
			// Send enemy position to teammates
			broadcastMessage(new Point(enemyX, enemyY));
		} catch (IOException ex) {
			out.println("Unable to send order: ");
			ex.printStackTrace(out);
		}
	}

	/**
	 * onHitByBullet:  Turn perpendicular to bullet path
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		turnLeft(90 - e.getBearing());
	}
	
	/**
	 * calculateEnemyBearing:  Compute bearing by adding own robot's bearing and enemy bearing
	 */
	private double calculateEnemyBearing(ScannedRobotEvent e) {
		return this.getHeading() + e.getBearing();
	}
	
	private class EnemyPosition {
		
		private String enemyName;
		private Point enemyPosition;
		
		public String getEnemyName() {
			return enemyName;
		}
		
		public Point enemyPosition() {
			return enemyPosition();
		}
	
	}
}
