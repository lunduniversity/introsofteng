package etsa02_lab2;

import java.awt.geom.Point2D;
import java.util.Arrays;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * A class to help with the management of enemies.
 */
public class EnemyDatabase {
	
	/**
	 * Construct an object to help with the management of enemies.
	 * @param robot the robot we are working on.
	 */
	public EnemyDatabase(AdvancedRobot robot) {
		throw new NotImplementedException();
	}
	
	/** 
	 * To be called when an enemy is scanned.
	 * @param e The ScannedRobotEvent received from the onScannedRobot method.
	 */ 
	public void addEnemy(ScannedRobotEvent e) {
		throw new NotImplementedException();
	}
	
	/**
	 * Stop tracking an enemy. E.g: when that enemy is dead.
	 * @param name the name of enemy to stop tracking.
	 */
	public void removeEnemy(String name) {
		throw new NotImplementedException();
	}
	
	/**
	 * Returns the last known positions of the enemies.
	 * @return An array of the last known enemy positions.
	 */
	public Point2D.Double[] getEnemyPositions() {
		throw new NotImplementedException();
	}
	
	/**
	 * @return the number of enemies currently being tracked.
	 */
	public int getEnemyCount() {
		throw new NotImplementedException();
	}
	
	private int findEnemyName(String name) {
		throw new NotImplementedException();
	}
}
