/**	
Copyright (c) 2017-2019 Markus Borg

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
import robocode.MessageEvent;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;
import robocode.TeamRobot;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
/**
 * BasicLeaderBot (BLB) - a sample team robot for ETSA02.
 * 
 * Looks around for enemies, and orders team mates to fire.
 * If hit by a bullet, it sometimes changes its motvement.
 *
 * @author Markus Borg
 * @author Teodor Ahlinder, improvements for LU Rumble (2020)
 */
public class BasicLeaderBot extends TeamRobot {
	private static final Color BODYCOLOR = Color.white;
	private static final Color GUNCOLOR = Color.white;
	private static final Color RADARCOLOR = Color.white;
	private static final Color SCANCOLOR = Color.white;
	private static final Color BULLETCOLOR = Color.white;
	
	private ArrayList<DummyRobot> knownEnemyRobots;
	private ArrayList<DummyRobot> knownAlliedRobots;
	
	private static final boolean PRINT_DEBUG = true;
	
	/**
	 * run:  BLB's new behavior, improved by Teodor Ahlinder (2020)
	 */
	public void run() {
		// ----------------------------------------------
		// ------------- Starting behavior --------------
		// ----------------------------------------------
		
		// ------------- Configuring lists --------------
		knownEnemyRobots = new ArrayList<DummyRobot>();
		knownAlliedRobots = new ArrayList<DummyRobot>();
		
		// ---------- Asserting leader control ----------
		try {
			// Declare to team that this bot is the leader
			MessageWriter writer = new MessageWriter();
			writer.addLeadership("followMe");
			broadcastMessage(writer.composeMessage());
		} catch (IOException ignored) {}
		
		// ----------- Setting team colors --------------
		RobotColors c = new RobotColors();
		c.bodyColor = BODYCOLOR;
		c.gunColor = GUNCOLOR;
		c.radarColor = RADARCOLOR;
		c.scanColor = SCANCOLOR;
		c.bulletColor = BULLETCOLOR;

		// Set the color of BLB
		setBodyColor(c.bodyColor);
		setGunColor(c.gunColor);
		setRadarColor(c.radarColor);
		setScanColor(c.scanColor);
		setBulletColor(c.bulletColor);
		try {
			// Send RobotColors object to the entire team
			broadcastMessage(c);
		} catch (IOException ignored) {
			System.out.println("Could not broadcast team colors.");
		}
		
		
		// ----------------------------------------------
		// ------------- Running Behavior ---------------
		// ----------------------------------------------
		
		// Default behavior - BLB's standard sequence
		while (true) {
			setTurnRadarRight(10000);
			ahead(100);
			back(100);
		}
	}

	/**
	 * onScannedRobot: BLB has detected another robot. Update internal lists and transmit them to team.
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Calculate robot bearing
		double robotBearing = this.getHeading() + e.getBearing();
		
		// Calculate robot's position
		double robotX = getX() + e.getDistance() * Math.sin(Math.toRadians(robotBearing));
		double robotY = getY() + e.getDistance() * Math.cos(Math.toRadians(robotBearing));
		
		// Store robot in appropriate list
		String scannedName = e.getName();
		DummyRobot r = new DummyRobot(scannedName, robotX, robotY, e.getVelocity(), e.getEnergy(), e.getHeading(), 0);
		if (isTeammate(scannedName)) {
			updateList(r, knownAlliedRobots);
		} else {
			updateList(r, knownEnemyRobots);
		}
		
		try {
			// Send positions of known allies to team
			MessageWriter writer = new MessageWriter();
			writer.addMyPos(getX(), getY());
			for (DummyRobot allies : knownAlliedRobots) {
				writer.addFriendPos(allies.getX(), allies.getY());
			}
			// Send positions of known enemies to team
			for (DummyRobot enemies : knownEnemyRobots) {
				writer.addEnemyDetails(enemies.getName(), enemies.getX(), enemies.getY(), enemies.getVelocity(), enemies.getEnergy(), enemies.getHeading(), enemies.getGunHeading());
			}
			broadcastMessage(writer.composeMessage());
		} catch (IOException ex) {
			out.println("Unable to send order: ");
			ex.printStackTrace(out);
		}
	}

	/**
	 * Adds a new robot to the list, or updates the robots values if it is already in the list
	 * @param r robot to add or update
	 * @return 0 if robot was already in list, 1 if it was added as a new robot, -1 if robot energy was <=0
	 */
	private int updateList(DummyRobot r, ArrayList<DummyRobot> list) {
		for (int i = 0; i < list.size(); i++) {
			DummyRobot robot = list.get(i);
			if (r.getName() == robot.getName()) {
				if (r.getEnergy() <= 0) {
					if (PRINT_DEBUG) {
						System.out.println("lists: energy 0, remove " + r.getName());
					}
					list.remove(i);
					return -1;
				}
				list.get(i).update(r.getX(), r.getY(), r.getVelocity(), r.getEnergy(), r.getHeading(), r.getGunHeading());
				return 0;
			}
		}
		for (DummyRobot robot : list) {
			if (r.getName() == robot.getName()) {
				robot = r;
				return 0;
			}
		}
		list.add(r);
		return 1;
	}

	/**
	 * onHitByBullet: BLB has been hit by a bullet. Turn perpendicular to path of the bullet.
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		turnLeft(90 - e.getBearing());
	}
	
	/**
	 * onDeath: Code executed when this robot dies.
	 */
	public void onDeath() {
		try {
			// Declare to team that this bot is dead and should not be followed anymore
			MessageWriter writer = new MessageWriter();
			writer.addLeadership("leadMe");
			broadcastMessage(writer.composeMessage());
		} catch (IOException ignored) {}
	}
	
	/**
	 * onRobotDeath: Code executed when another robot dies.
	 */
	public void onRobotDeath(RobotDeathEvent e) {
		String name = e.getName();
		if (isTeammate(name)) {
			for (int i = 0; i < knownAlliedRobots.size(); i++) {
				if (e.getName() == knownAlliedRobots.get(i).getName()) {
					knownAlliedRobots.remove(i);
					return;
				}
			}
		} else {
			for (int i = 0; i < knownEnemyRobots.size(); i++) {
				if (e.getName() == knownEnemyRobots.get(i).getName()) {
					knownEnemyRobots.remove(i);
					return;
				}
			}
		}
	}
	
	public void onMessageReceived(MessageEvent e) {
		MessageReader reader = new MessageReader((String)e.getMessage());
		String[] enemies = reader.getEnemyDetails();
		for (String values : enemies) {
			String[] v = values.split(";");
			if (v.length >= 7) {
				try {
					double x = Double.parseDouble(v[1]);
					double y = Double.parseDouble(v[2]);
					double velocity = Double.parseDouble(v[3]);
					double energy = Double.parseDouble(v[4]);
					double heading = Double.parseDouble(v[5]);
					double gunHeading = Double.parseDouble(v[6]);
					DummyRobot r = new DummyRobot(v[0], x, y, velocity, energy, heading, gunHeading);
					updateList(r, knownEnemyRobots);
				} catch (RuntimeException err) {}
			}
		}
	}

	
	public ArrayList<DummyRobot> getKnownEnemyRobots() {
		return knownEnemyRobots;
	}

	public ArrayList<DummyRobot> getKnownAlliedRobots() {
		return knownAlliedRobots;
	}
}
