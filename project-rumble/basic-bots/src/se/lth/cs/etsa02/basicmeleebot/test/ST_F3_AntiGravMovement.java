/**	
Copyright (c) 2018 Markus Borg

Building on work by Philip Johnson and Keone Hiraide, University of Hawaii.
https://ics613s13.wordpress.com/

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

package se.lth.cs.etsa02.basicmeleebot.test;

import static org.junit.Assert.assertTrue;

import java.awt.geom.Point2D;
import java.util.LinkedList;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.RoundEndedEvent;
import robocode.control.events.RoundStartedEvent;
import robocode.control.events.TurnEndedEvent;
import robocode.control.snapshot.IRobotSnapshot;
import robocode.control.snapshot.RobotState;
import robocode.control.testing.RobotTestBed;

/**
 * Test class for Feature 3 - Anti-gravity movement in BasicMeleeBot.
 *
 * @author Markus Borg
 *
 */
@RunWith(JUnit4.class)
public class ST_F3_AntiGravMovement extends RobotTestBed {
	
	// constants used to configure this system test case
	private String ROBOT_UNDER_TEST = "se.lth.cs.etsa02.basicmeleebot.BasicMeleeBot*";
	private String ENEMY_ROBOTS = "sample.SittingDuck";
	private int NBR_ROUNDS = 100;
	private double THRESHOLD = 0.60; // percentage of rounds with average distance > start distance
	private double IMMOBILE_TURNS_LIMIT = 25; // maximum turns without moving
	private boolean PRINT_DEBUG = true;
			
	// attributes used in the system test case
	private double startDistance;
	private double avgDistance;
	private LinkedList<Double> allDistances;
	private LinkedList<Point2D> prevPos;
	private int nbrPassed;
	
	/**
	 * The names of the robots that want battling is specified.
	 * 
	 * @return The names of the robots we want battling.
	 */
	@Override
	public String getRobotNames() {
		return ROBOT_UNDER_TEST + "," + ENEMY_ROBOTS;
	}

	/**
	 * Pick the amount of rounds that we want our robots to battle for.
	 *
	 * @return Amount of rounds we want to battle for.
	 */
	@Override
	public int getNumRounds() {
		return NBR_ROUNDS;
	}

	/**
	 * Returns a comma or space separated list like: x1,y1,heading1,
	 * x2,y2,heading2, which are the coordinates and heading of robot #1 and #2.
	 * So "0,0,180, 50,80,270" means that robot #1 has position (0,0) and
	 * heading 180, and robot #2 has position (50,80) and heading 270.
	 * 
	 * Override this method to explicitly specify the initial positions for your
	 * test cases.
	 * 
	 * Defaults to null, which means that the initial positions are determined
	 * randomly. Since battles are deterministic by default, the initial
	 * positions are randomly chosen but will always be the same each time you
	 * run the test case.
	 * 
	 * @return The list of initial positions.
	 */
	@Override
	public String getInitialPositions() {
		return null;
	}

	/**
	 * Returns true if the battle should be deterministic and thus robots will
	 * always start in the same position each time.
	 * 
	 * Override to return false to support random initialization.
	 * 
	 * @return True if the battle will be deterministic.
	 */
	@Override
	public boolean isDeterministic() {
		return false;
	}

	/**
	 * Specifies how many errors you expect this battle to generate. Defaults to
	 * 0. Override this method to change the number of expected errors.
	 * 
	 * @return The expected number of errors.
	 */
	@Override
	protected int getExpectedErrors() {
		return 0;
	}

	/**
	 * Invoked before the test battle begins. Default behavior is to do nothing.
	 * Override this method in your test case to add behavior before the battle
	 * starts.
	 */
	@Override
	protected void runSetup() {
		nbrPassed = 0;
		prevPos = new LinkedList<Point2D>();
	}

	/**
	 * Invoked after the test battle ends. Default behavior is to do nothing.
	 * Override this method in your test case to add behavior after the battle
	 * ends.
	 */
	@Override
	protected void runTeardown() {
	}
	
	/**
	 * Check that the average distance is larger than the start distance in most cases.
	 * 
	 * @param event
	 *            Holds information about the battle has been completed.
	 */
	@Override
	public void onBattleCompleted(BattleCompletedEvent event) {
		assertTrue("Average distance should be larger than start distance in " + THRESHOLD + 
				" of the rounds, but it was true in only " + ((double) nbrPassed / NBR_ROUNDS) +
				" rounds.", ((double) nbrPassed / NBR_ROUNDS) > THRESHOLD);
	}
	
	/**
	 * Called before each round. Used to to reset all distance calculations.
	 * 
	 * @param event
	 *            The RoundStartedEvent.
	 */
	@Override
	public void onRoundStarted(RoundStartedEvent event) {
		IRobotSnapshot bmb = event.getStartSnapshot().getRobots()[0];
		double xBMB = bmb.getX();
		double yBMB = bmb.getY();
		IRobotSnapshot duck = event.getStartSnapshot().getRobots()[1];
		double xDuck = duck.getX();
		double yDuck = duck.getY();
		
		startDistance = Math.hypot(xBMB-xDuck, yBMB-yDuck);
		avgDistance = 0;
		allDistances = new LinkedList<Double>();
	}
	
	/**
	 * Tests to see that BMB moves away from the SittingDuck, i.e., average 
	 * distance is larger than the start distance.
	 * 
	 * @param event
	 *            The RoundEndedEvent.
	 */
	@Override
	public void onRoundEnded(RoundEndedEvent event) {
		// calculate average distance across all turns during the battle
		double totalDistances = 0;
		for (double i: allDistances) {
			totalDistances += i;
		}		
		avgDistance = totalDistances / allDistances.size();
				
		if (PRINT_DEBUG) {
			System.out.println("Start distance: " + startDistance +
						   	   " Average distance: " + avgDistance);
		}
				
		if (avgDistance > startDistance) {
			nbrPassed++;
		}
	}
	
	/**
	 * Check distance to SittingDuck. Also store current BMB position.
	 * 
	 * @param event
	 *            The TurnEndedEvent.
	 */
	@Override
	public void onTurnEnded(TurnEndedEvent event) {
		// verify increasing distance
		IRobotSnapshot bmb = event.getTurnSnapshot().getRobots()[0];
		double xBMB = bmb.getX();
		double yBMB = bmb.getY();
		IRobotSnapshot duck = event.getTurnSnapshot().getRobots()[1];
		double xDuck = duck.getX();
		double yDuck = duck.getY();
		
		double distance = Math.hypot(xBMB-xDuck, yBMB-yDuck);
		allDistances.add(distance);
		
		// verify unique positions from turn IMMOBILE_TURNS_LIMIT as long as SittingDuck is active
		if (event.getTurnSnapshot().getTurn() >= IMMOBILE_TURNS_LIMIT && duck.getState() == RobotState.ACTIVE) {
			boolean uniquePos = false;
			int count = 0;
			for (int i = 0; !uniquePos && i < prevPos.size(); i++) {
				for (int j = i+1; !uniquePos && j < prevPos.size(); j++) {
					if (!prevPos.get(i).equals(prevPos.get(j))) {
						uniquePos = true;
					}
					count++;
				}
			}
			assertTrue("BMB did not move for " + IMMOBILE_TURNS_LIMIT + " turns (turn " + event.getTurnSnapshot().getTurn() + ")", uniquePos);
		}
		
		// store last IMMOBILE_TURNS_LIMIT positions
		if (prevPos.size() == IMMOBILE_TURNS_LIMIT) {
			prevPos.poll();
		}
		prevPos.add(new Point2D.Double(xBMB, yBMB));	
	}
}