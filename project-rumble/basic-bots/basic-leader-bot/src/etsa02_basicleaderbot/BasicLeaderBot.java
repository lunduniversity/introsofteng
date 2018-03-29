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
	
	private RadarSystem radar;
	private LinkedList<EnemyPosition> enemyList;
	
	/**
	 * Added to make unit testing possible.
	 */
	protected void init() {
		radar = new RadarSystem();
		enemyList = new LinkedList<EnemyPosition>();
	}

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
		/* Temporarily commented out since the communication protocol currently does not support color commands.
		try {
			// Send RobotColors object to the entire team
			broadcastMessage(c);
		} catch (IOException ignored) {}
		*/
		
		// Initiate attributes
		//radar = new RadarSystem();
		//enemyList = new LinkedList<EnemyPosition>();
		init();
		
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
		double myHeading = getHeading();
		double enemyBearing = radar.calculateRobotBearing(myHeading, e);
		Point enemyPosition = radar.calculateRobotPosition(this, e, enemyBearing);
		
		try {
			// Send enemy position to teammates
			MessageWriter writer = new MessageWriter();
			writer.addTargetPos(enemyPosition.getX(), enemyPosition.getY());
			broadcastMessage(writer.composeMessage());
		} catch (IOException ex) {
			out.println("Unable to send order: ");
			ex.printStackTrace(out);
		}
		
		// Keep track of all enemy positions
		EnemyPosition ePos = findEnemy(e.getName());
		if (ePos != null) {
			// If enemy is known, update its position
			ePos.setEnemyPosition(enemyPosition);			
		} // otherwise, add new enemy to the list
		else {
			enemyList.add(new EnemyPosition(e.getName(), enemyPosition));
		}
	}

	/**
	 * onHitByBullet:  BLB has been hit by a bullet. Turn perpendicular to path of the bullet.
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		turnLeft(90 - e.getBearing());
	}
	
	/**
	 * 
	 * @param enemyName
	 * @return The EnemyPosition or null if not found.
	 */
	private EnemyPosition findEnemy(String enemyName) {
		
		for (int i=0; i<enemyList.size(); i++) {
			EnemyPosition tempEnemy = enemyList.get(i);
			if (enemyName.equals(tempEnemy.getEnemyName())) {
				return tempEnemy;
			}
		}
		return null;
	}
	
	/**
	 * EnemyPosition - Represents an enemy through its name and position.
	 */
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
		
		public void setEnemyPosition(Point ePos) {
			this.enemyPosition = ePos;
		}
		
		@Override
		public boolean equals(Object obj) {
			String otherRobot = ((EnemyPosition) obj).getEnemyName();
		    return enemyName.equals(otherRobot);
		  }
	
	}
}
