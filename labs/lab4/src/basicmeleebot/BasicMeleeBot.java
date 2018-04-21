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

package se.lth.cs.etsa02.basicmeleebot;

import robocode.TeamRobot;
import robocode.RobotDeathEvent;
import robocode.ScannedRobotEvent;

/**
 * @author David Phung
 * 
 * Example robot for ETSA02. A melee bot implemented following an object oriented design.
 */
public class BasicMeleeBot extends TeamRobot {

	private EnemyTracker enemyTracker;
	private PositioningSystem positioningSystem;
	private MovementSystem movementSystem;
	private TargetingSystem targetingSystem;
	
	/**
	 * The main loop controlling the robot behavior.
	 */
	@Override
	public void run() {
		enemyTracker = new EnemyTracker(this);
		positioningSystem = new PositioningSystem(getBattleFieldWidth(), getBattleFieldHeight());
		movementSystem = new MovementSystem(this, enemyTracker, positioningSystem);
		targetingSystem = new TargetingSystem(enemyTracker, this);
		
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		
		setTurnRadarRight(Double.POSITIVE_INFINITY);
		targetingSystem.update();
		movementSystem.update();
		execute();

		while (true) {
			setTurnRadarRight(Double.POSITIVE_INFINITY);
			targetingSystem.update();
			movementSystem.update();
			execute();
		}
	}
	
	/**
	 * Describes the action taken when a robot has been scanned.
	 * 
	 * @param event The ScannedRobotEvent provided by Robocode.
	 */
	@Override
	public void onScannedRobot(ScannedRobotEvent event) {
		if (isTeammate(event.getName())) {
			return;
		}
		
		enemyTracker.addEnemy(event);
	}
	
	/**
	 * Describes the action taken when a robot has been destroyed.
	 * 
	 * @param event The RobotDeathEvent provided by Robocode.
	 */
	@Override
	public void onRobotDeath(RobotDeathEvent event) {
		enemyTracker.removeEnemy(event.getName());
	}
}