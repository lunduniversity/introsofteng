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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import robocode.BattleResults;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.testing.RobotTestBed;

/**
 * Test class for the BasicMeleeBot.
 *
 * @author Markus Borg
 *
 */
@RunWith(JUnit4.class)
public class ST_Q_MeleeSpinBots extends RobotTestBed {
	
	// constants used to configure this system test case
	private String ROBOT_UNDER_TEST = "se.lth.cs.etsa02.basicmeleebot.BasicMeleeBot*";
	private String ENEMY_ROBOTS = "sample.SpinBot,sample.SpinBot,sample.SpinBot";
	private int NBR_ROUNDS = 100;
	private double THRESHOLD = 0.33; // win rate in melee battles against 3 SpinBots
	private boolean PRINT_DEBUG = false;
		
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
		return true;
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
	
	/**
	 * Tests to see if our robot won most rounds.
	 * 
	 * @param event
	 *            Holds information about the battle has been completed.
	 */
	@Override
	public void onBattleCompleted(BattleCompletedEvent event) {
		// all battle results
		BattleResults[] battleResults = event.getIndexedResults();
		// BMB results
		BattleResults bmbResults = battleResults[0];

		// check that BMB won the overall battle
		String robotName = bmbResults.getTeamLeaderName();		
		assertEquals("Basic Melee Bot should be first in the results array",
				"se.lth.cs.etsa02.basicmeleebot.BasicMeleeBot*", robotName);
		
		// check that the required win rate has been reached
		double bmbWinRate = (((double) bmbResults.getFirsts()) / NBR_ROUNDS);
		
		if (PRINT_DEBUG) {
			System.out.println("BMB won " + bmbResults.getFirsts() + " out of " + NBR_ROUNDS + 
					" rounds (win rate = " + bmbWinRate + ")");
		}
		assertTrue("Basic Melee Bot should have a win rate of at least 33% in this melee battle",
				bmbWinRate >= THRESHOLD);
	}
}