/**	
Copyright (c) 2017 Markus Borg

Building on work by Philip Johnson, University of Hawaii.
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

package etsa02_basicbots.systemtest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import robocode.BattleResults;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.TurnEndedEvent;
import robocode.control.snapshot.IRobotSnapshot;
import robocode.control.testing.RobotTestBed;
 

/**
 * Test class for the BasicLeaderBot.
 *
 * @author Keone Hiraide
 *
 */
@RunWith(JUnit4.class)
public class BasicBots_UnitTests extends RobotTestBed {
 
	/**
	 * The names of the robots that want battling is specified.
	 * 
	 * @return The names of the robots we want battling.
	 */
	@Override
	public String getRobotNames() {
		return "etsa02_basicbots.BasicLeaderBot*";
	}

	/**
	 * Pick the amount of rounds that we want our robots to battle for.
	 *
	 * @return Amount of rounds we want to battle for.
	 */
  @Override
  public int getNumRounds() {
    return 10;
  }
 
  /**
   * Tests to see if our robot won most rounds.
   * @param event Holds information about the battle has been completed.
   */
  @Override
  public void onBattleCompleted(BattleCompletedEvent event) {
    // Return the results in order of getRobotNames.
    BattleResults[] battleResults = event.getIndexedResults();
    // Sanity check that results[0] is PewPew.
    BattleResults testBattleResults = battleResults[0];
    String robotName = testBattleResults.getTeamLeaderName();
    assertEquals("Check that testBattleResults[1] is BasicLeaderBot", "etsa02_basicbots.BasicLeaderBot*", robotName);
    // Check to make sure BasicLeaderBot won at least won over half the rounds.
    assertEquals("Check that BasicLeaderBot won all battles against SittingDuck", 10, testBattleResults.getFirsts());
  }
  
	  
  @Override
  public void onTurnEnded(TurnEndedEvent event) {
	  
  }
  
  @Override
  public String getInitialPositions() {
    return null;
  }

  /**
   * Invoked before the test battle begins. Default behavior is to do nothing. Override this method
   * in your test case to add behavior before the battle starts.
   */
  @Override
  protected void runSetup() {
    
  }

  /**
   * Invoked after the test battle ends. Default behavior is to do nothing. Override this method in
   * your test case to add behavior after the battle ends.
   */
  @Override
  protected void runTeardown() {
    // Default does nothing.
}
  
  
}