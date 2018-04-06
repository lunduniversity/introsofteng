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
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import robocode.control.events.RoundEndedEvent;
import robocode.control.events.RoundStartedEvent;
import robocode.control.events.TurnEndedEvent;
import robocode.control.snapshot.IRobotSnapshot;
import robocode.control.testing.RobotTestBed;

/**
 * Test class for Feature 1 - Radar system in BasicMeleeBot.
 *
 * @author Markus Borg
 *
 */
@RunWith(JUnit4.class)
public class ST_F1_RadarSystem extends RobotTestBed {
	// constants used to configure this system test case
	private String ROBOT_UNDER_TEST = "se.lth.cs.etsa02.basicmeleebot.BasicMeleeBot*";
	private String ENEMY_ROBOTS = "sample.SittingDuck";
	private int NBR_ROUNDS = 1;
	
	private double previousHeading;
	private boolean clockwise;
	
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
	 * Called after every turn. Used to check whether the radar spins clockwise or not by
	 * comparing current radar heading with radar heading in the previous turn.
	 */
	@Override
	public void onTurnEnded(TurnEndedEvent event) {
		IRobotSnapshot bmb = event.getTurnSnapshot().getRobots()[0];
		double radarHeading = Math.toDegrees(bmb.getRadarHeading());
		//We base our test on the fact that the radar cannot turn more than 75 degrees per turn.
		if (previousHeading + 75 >= 360 && radarHeading < 75) {
			radarHeading += 360;
		}
		if (radarHeading - previousHeading <= 0 || radarHeading - previousHeading > 75) {
			clockwise = false;
		}
		previousHeading = Math.toDegrees(bmb.getRadarHeading());
	}
	
	/**
	 * Called before each round. Used to set initial values of variables.
	 */
	@Override
	public void onRoundStarted(RoundStartedEvent event) {
		IRobotSnapshot bmb = event.getStartSnapshot().getRobots()[0];
		previousHeading = Math.toDegrees(bmb.getRadarHeading());
		clockwise = true;
	}
	
	/**
	 * Called after each round. Used to assert that the radar has been spinning correctly the whole round.
	 */
	@Override
	public void onRoundEnded(RoundEndedEvent event) {
		assertTrue("Check that the radar spins clockwise all the time", clockwise);
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
		// Put BMB in top left corner facing right
		// Put one SittingDuck in lower right corner and one in bottom left
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
		// Default does nothing.
	}

	/**
	 * Invoked after the test battle ends. Default behavior is to do nothing.
	 * Override this method in your test case to add behavior after the battle
	 * ends.
	 */
	@Override
	protected void runTeardown() {
		// Default does nothing.
	}

}