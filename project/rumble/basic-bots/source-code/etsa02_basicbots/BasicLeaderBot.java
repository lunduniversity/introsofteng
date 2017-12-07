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
package etsa02_basicbots;

import robocode.HitByBulletEvent;
import robocode.ScannedRobotEvent;
import robocode.TeamRobot;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;

/**
 * BasicLeaderBot (BLB) - a sample team robot for ETSA02.
 * 
 * Looks around for enemies, and orders teammates to fire.
 * If an enemy is close, it sometimes changes its position.
 *
 * @author Markus Borg
 */
public class BasicLeaderBot extends TeamRobot {
	
	private LinkedList<EnemyPosition> enemyList;

	/**
	 * run:  BLB's default behavior
	 */
	public void run() {
		// Prepare RobotColors object with LU colors
		RobotColors c = new RobotColors();
		c.bodyColor = Color.darkGray;
		c.gunColor = Color.blue;
		c.radarColor = Color.blue;
		c.scanColor = Color.darkGray;
		c.bulletColor = Color.darkGray;

		// Set the color of BLB
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
		enemyList = new LinkedList<EnemyPosition>();
		
		// Default behavior - BLB's standard sequence
		while (true) {
			setTurnRadarRight(10000);
			ahead(100);
			back(100);
		}
	}

	/**
	 * onScannedRobot:  BLB has detected another robot. If hostile, share position with the team.
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// No action if a teammate is detected 
		if (isTeammate(e.getName())) {
			return;
		}
		
		// Calculate enemy's bearing and position
		double enemyBearing = calculateEnemyBearing(e);
		Point enemyPosition = calculateEnemyPosition(e, enemyBearing);
		
		// If enemy is known, update its position
		if (enemyList.contains(e.getName())) {
			// update position
		} // otherwise, add new enemy to the list
		else {
			enemyList.add(new EnemyPosition(e.getName(), enemyPosition));
		}
		
		try {
			// Send enemy position to teammates
			broadcastMessage(enemyPosition);
		} catch (IOException ex) {
			out.println("Unable to send order: ");
			ex.printStackTrace(out);
		}
	}

	/**
	 * onHitByBullet:  BLB has been hit by a bullet. Turn perpendicular to path of the bullet.
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
	
	/**
	 * calculateEnemyPosition:  Compute enemy position using its bearing
	 */
	private Point calculateEnemyPosition(ScannedRobotEvent e, double enemyBearing) {
		double enemyX = getX() + e.getDistance() * Math.sin(Math.toRadians(enemyBearing));
		double enemyY = getY() + e.getDistance() * Math.cos(Math.toRadians(enemyBearing));
		return new Point(enemyX, enemyY);
	}
	
	private class EnemyPosition {
		
		private String enemyName;
		private Point enemyPosition;
		
		public EnemyPosition(String enemyName, Point enemyPosition) {
			this.enemyName = enemyName;
			this.enemyPosition = enemyPosition;
		}
		
		public String getEnemyName() {
			return enemyName;
		}
		
		public Point getEnemyPosition() {
			return enemyPosition;
		}
	
	}
}