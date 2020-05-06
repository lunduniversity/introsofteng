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

package se.lth.cs.etsa02.basicdroid;

import se.lth.cs.etsa02.*;
import robocode.Droid;
import robocode.MessageEvent;
import robocode.TeamRobot;
import robocode.util.Utils;

import static robocode.util.Utils.normalRelativeAngleDegrees;

import java.io.IOException;


/**
 *  BasicDroid - a simple droid that fires based on leader bot's orders
 *
 * @author Markus Borg
 * @author Teodor Ahlinder, improvements for LU Rumble (2020)
 */
public class BasicDroid extends TeamRobot implements Droid {

	/**
	 * run:  Droid's default behavior
	 */
	public void run() {
		out.println("BasicDroid ready.");
		try {
			// Declare to team that this bot is a follower
			MessageWriter writer = new MessageWriter();
			writer.addLeadership("leadMe");
			broadcastMessage(writer.composeMessage());
		} catch (IOException ignored) {}
	}

	/**
	 * onMessageReceived:  What to do when our leader sends a message
	 */
	public void onMessageReceived(MessageEvent e) {
		// Set our colors
		if (e.getMessage() instanceof RobotColors) {
			RobotColors c = (RobotColors) e.getMessage();
			setBodyColor(c.bodyColor);
			setGunColor(c.gunColor);
			setRadarColor(c.radarColor);
			setScanColor(c.scanColor);
			setBulletColor(c.bulletColor);
		} else {
			MessageReader reader = new MessageReader((String)e.getMessage());
			if (reader.getMoveTo() != null) {
				goTo(reader.getMoveTo());
			}
			
			// If enemy position, fire!
			String[] values = reader.getEnemyDetails();
			if (values.length > 0) {
				String[] ss = values[0].split(";");
				Point p = null;
				try {
					double energy = Double.parseDouble(ss[4]);
					if (energy <= 0) {
						return;
					}
					double x = Double.parseDouble(ss[1]);
					double y = Double.parseDouble(ss[2]);
					p = new Point(x,y);
				} catch (RuntimeException err) {}
				if (p != null) {
					fireAtPoint(p);
				} else {
				}
			}
		}
	}

	
	private void fireAtPoint(Point p) {
		// Calculate x and y to target
		double dx = p.getX() - this.getX();
		double dy = p.getY() - this.getY();
		// Calculate angle to target
		double theta = Math.toDegrees(Math.atan2(dx, dy));

		// Turn gun to target
		turnGunRight(normalRelativeAngleDegrees(theta - getGunHeading()));
		// Fire hard!
		fire(3);
	}
	
	
	/**
	 * Set the robot to go to a certain point. 
	 * The code comes from <a href="http://robowiki.net/wiki/GoTo"> http://robowiki.net/wiki/GoTo </a>
	 * @param destination coordinates of the destination
	 */
	private void goTo(Point destination) {
		/* Transform our coordinates into a vector */
		double x = destination.getX();
		double y = destination.getY();
		x -= getX();
		y -= getY();
	 
		/* Calculate the angle to the target position */
		double angleToTarget = Math.atan2(x, y);
	 
		/* Calculate the turn required get there */
		double targetAngle = Utils.normalRelativeAngle(angleToTarget - getHeadingRadians());
	 
		/* 
		 * The Java Hypot method is a quick way of getting the length
		 * of a vector. Which in this case is also the distance between
		 * our robot and the target location.
		 */
		double distance = Math.hypot(x, y);
	 
		/* This is a simple method of performing set front as back */
		double turnAngle = Math.atan(Math.tan(targetAngle));
		setTurnRightRadians(turnAngle);
		if(targetAngle == turnAngle) {
			setAhead(distance);
		} else {
			setBack(distance);
		}
	}
}