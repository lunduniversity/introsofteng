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

package etsa02_basicmeleebot;

import java.awt.geom.Point2D;

import robocode.ScannedRobotEvent;

/**
 * A class to help with the management of enemies.
 */
public class EnemyTracker {
	
	//The 3 following variables are used to track enemies. An enemy will have its name and position stored
	//at the same index in the two arrays enemyNames and enemyPositions. The number of enemies is stored in enemyCount.
	private Point2D.Double[] enemyPositions;
	private String[] enemyNames;
	private int enemyCount;
	
	private BasicMeleeBot robot;
		
	/**
	 * Construct an object to help with the management of enemies.
	 * @param robot the robot we are working on.
	 */
	public EnemyTracker(BasicMeleeBot robot) {
		this.robot = robot;
		enemyNames = new String[10];
		enemyPositions = new Point2D.Double[enemyNames.length];
	}
	
	/** 
	 * To be called when an enemy is scanned.
	 * @param e The ScannedRobotEvent received from the onScannedRobot method.
	 */ 
	public void addEnemy(ScannedRobotEvent e) {
		// if the scanned robot is already added to the arrays, update its position. Otherwise add it as a new element.
		Point2D.Double enemyPosition = MathUtils.calcEnemyPosition(robot, e);
		int index = findEnemyByName(e.getName());
		if (index >= 0) {
			enemyPositions[index] = enemyPosition;
		} else {
			enemyNames[enemyCount] = e.getName();
			enemyPositions[enemyCount] = enemyPosition;
			enemyCount++;
		}
	}
	
	/**
	 * Stop tracking an enemy, i.e., when that enemy is dead.
	 * @param name the name of enemy to stop tracking.
	 */
	public void removeEnemy(String name) {
		// remove the destroyed robot from the arrays
		int index = findEnemyByName(name);
		if (index >= 0) {
			for (int i = index + 1; i < enemyCount; i++) {
				enemyNames[i - 1] = enemyNames[i];
				enemyPositions[i-1] = enemyPositions[i];
			}
			enemyCount--;
		}
	}
	
	/**
	 * Returns the last known positions of the enemies.
	 * @return An array of the last known enemy positions.
	 */
	public Point2D.Double[] getEnemyPositions() {
		Point2D.Double[] positions = new Point2D.Double[enemyCount];
		for (int i = 0; i < enemyCount; i++) {
			positions[i] = enemyPositions[i];
		}
		return positions;		
	}
	
	/**
	 * @return the number of enemies currently being tracked.
	 */
	public int getEnemyCount() {
		return enemyCount;
	}
	
	/**
	 * Find the index of an enemy robot given its name. The index can then be used to access the robot's position in the position array.
	 * 
	 * @param name The name of the robot.
	 * @return The index of the robot in the internal array, -1 if not found.
	 */
	private int findEnemyByName(String name) {
		for (int i = 0; i < enemyCount; i++) {
			if (enemyNames[i].equals(name)) return i;
		}
		return -1;
	}
}
