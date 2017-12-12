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

package etsa02_basicbots.test;

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
public class BLB_SurviveSittingDuck_SystemTest extends RobotTestBed {
  /**
   * The names of the robots that want battling is specified.
   * @return The names of the robots we want battling.
   */
  @Override
  public String getRobotNames() {
    return "etsa02_basicbots.BasicLeaderBot*,sample.SittingDuck";
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
  
  /**
   * Called after each turn. Provided here to show that you could use this method as part of your
   * testing.
   * 
   * @param event The TurnEndedEvent.
   */
  @Override
  public void onTurnEnded(TurnEndedEvent event) {
    /*IRobotSnapshot robot = event.getTurnSnapshot().getRobots()[1];
    double xPos = robot.getX();

    /*
     * Run through all the debug properties set in the WallRunner.java file.
     */
    /*
    for (IDebugProperty debug : robot.getDebugProperties()) {

      if (debug.getKey().compareToIgnoreCase("bulletHit") == 0
          && debug.getValue().compareToIgnoreCase("3") == 0) {
        this.robotNewTarget = true;
      }
      else if (debug.getKey().compareToIgnoreCase("phase") == 0 && this.robotPhase == 0) {
        this.robotPhase = Integer.valueOf(debug.getValue());
      }
      else if (debug.getKey().compareToIgnoreCase("enemyOnTheWall") == 0 && !this.enemyOnTheWall) {
        this.enemyOnTheWall = Boolean.valueOf(debug.getValue());
      }
    }*/

    /*
     * <ol> <li> Phase 1. Check to see if WallRunner moves to the closest wall. </li> <li> Phase 2.
     * Check to see if WallRunner moves up or down the side of the wall. Note : If a robot is also
     * on the wall, then a check for whether WallRunner moved to the top/bottom of the screen may
     * fail. </il> <li> Phase 2. Check to see if a new target was found because bullets have failed
     * to hit the target three times. </ol>
     */
    /*if (this.robotPhase == 0) {
      if ((closestWall.compareToIgnoreCase("Right") == 0 && xPos > this.width - 40.0)
          || (closestWall.compareToIgnoreCase("Left") == 0 && xPos < 40.0)) {
        this.robotOnWall = true;
      }
    }
    else if (this.robotPhase == 1) {
      if (!this.robotVelocityNegative && robot.getVelocity() < 0.0) {
        this.robotVelocityNegative = true;
      }
      else if (!this.robotVelocityPositive && robot.getVelocity() > 0.0) {
        this.robotVelocityPositive = true;
      }
    }*/
  }

  /**
   * Returns a comma or space separated list like: x1,y1,heading1, x2,y2,heading2, which are the
   * coordinates and heading of robot #1 and #2. So "0,0,180, 50,80,270" means that robot #1 has
   * position (0,0) and heading 180, and robot #2 has position (50,80) and heading 270.
   * 
   * Override this method to explicitly specify the initial positions for your test cases.
   * 
   * Defaults to null, which means that the initial positions are determined randomly. Since battles
   * are deterministic by default, the initial positions are randomly chosen but will always be the
   * same each time you run the test case.
   * 
   * @return The list of initial positions.
   */
  @Override
  public String getInitialPositions() {
    return null;
  }

  /**
   * Returns true if the battle should be deterministic and thus robots will always start in the
   * same position each time.
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
   * Specifies how many errors you expect this battle to generate. Defaults to 0. Override this
   * method to change the number of expected errors.
   * 
   * @return The expected number of errors.
   */
  @Override
  protected int getExpectedErrors() {
    return 0;
  }

  /**
   * Invoked before the test battle begins. Default behavior is to do nothing. Override this method
   * in your test case to add behavior before the battle starts.
   */
  @Override
  protected void runSetup() {
    // Default does nothing.
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