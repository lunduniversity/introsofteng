package etsa02_lab2;

import java.awt.geom.Point2D;

import etsa02_basicbots.BattleField.Wall;
import robocode.util.Utils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Handles the computation for a type of movement called Anti-gravity and is based on
 * the following tutorial: <a href="http://robowiki.net/wiki/Anti-Gravity_Tutorial">http://robowiki.net/wiki/Anti-Gravity_Tutorial</a>
 * <br/>
 * Each enemy is assigned a repulsive force that pushes the robot away. The sum of all forces will
 * determine the direction of the robot.
 */
public class MovementSystem {
	
	/** 
	 * Construct an object to handle a type of movement called anti-gravity.
	 * @param robot the robot we are working on.
	 * @param enemyHelper the object managing enemies.
	 * @param battleField the object handling battle field related calculations.
	 */
	public MovementSystem(BasicMeleeBot robot, EnemyDatabase enemyHelper, PositioningSystem battleField) {
		throw new NotImplementedException();
	}

	/**
	 * To be called every turn. Compute the forces and set the movement for the robot.
	 */
	public void update() {
		throw new NotImplementedException();
	}
	
	private void computeForce(Point2D.Double robotPosition, Point2D.Double repulsivePoint, double weight) {
		throw new NotImplementedException();
	}
}
