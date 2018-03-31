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
package se.lth.cs.etsa02.basicleaderbot;

import robocode.HitByBulletEvent;
import robocode.ScannedRobotEvent;
import robocode.TeamRobot;
import se.lth.cs.etsa02.MessageWriter;
import se.lth.cs.etsa02.RobotColors;

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
		
		// Calculate enemy bearing
		double enemyBearing = this.getHeading() + e.getBearing();
		
		// Calculate enemy's position
		double enemyX = getX() + e.getDistance() * Math.sin(Math.toRadians(enemyBearing));
		double enemyY = getY() + e.getDistance() * Math.cos(Math.toRadians(enemyBearing));
		
		try {
			// Send enemy position to teammates
			MessageWriter writer = new MessageWriter();
			writer.addTargetPos(enemyX, enemyY);
			broadcastMessage(writer.composeMessage());
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
}
