/**	
Copyright (c) 2017 Markus Borg

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
package se.lth.cs.etsa02.basicmeleebot.test;

import se.lth.cs.etsa02.basicmeleebot.BasicMeleeBot;

/**
 * MockBot - a mock robot to enable unit testing for ETSA02.
 *
 * @author Markus Borg
 */
public class MockBot extends BasicMeleeBot {
	
	private String name;
	private double fakeEnergy;
	private double fakeHeading;
	private double fakePosX;
	private double fakePosY;
	
	private double fakeAhead;
	private double fakeTurnRight;
	private double fakeGunHeading;
	private double fakeGunTurnRight;
	
	/**
	 * Construct a mock robot to be used as a substitute for BasicMeleeBot in unit testing.
	 * It overrides calls to robocode's advanced robot class to prevent robocode's exceptions from being thrown.
	 * Also it allows for an easy way to setup a robot for testing and to verify the output of classes that modify the robot's state.
	 * </br></br>
	 * The robot is setup using the input arguments. The gun starts with the same heading as the robot.
	 * The current implementation only overrides the methods used by ETSA02's BasicMeleeBot.
	 * @param name
	 * @param fakeEnergy
	 * @param fakeHeading
	 * @param fakePosX
	 * @param fakePosY
	 */
	public MockBot(String name, double fakeEnergy, double fakeHeading,
				   double fakePosX, double fakePosY) {
		this.name = name;
		this.fakeEnergy = fakeEnergy;
		this.fakeHeading = fakeHeading;
		this.fakePosX = fakePosX;
		this.fakePosY = fakePosY;
		
		fakeGunHeading = fakeHeading;
	}
	
	@Override
	public double getEnergy() {
		return fakeEnergy;
	}
	
	@Override
	public double getHeading() {
		return fakeHeading;
	}
	
	@Override
	public double getHeadingRadians() {
		return Math.toRadians(fakeHeading);
	}
	
	@Override
	public double getX() {
		return fakePosX;
	}
	
	@Override 
	public double getY() {
		return fakePosY;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setAhead(double distance) {
		fakeAhead = distance;
	}
	
	@Override
	public void setTurnRight(double degrees) {
		fakeTurnRight = degrees;
	}
	
	@Override
	public void setTurnRightRadians(double radians) {
		fakeTurnRight = Math.toDegrees(radians);
	}
	
	@Override
	public void setTurnGunRight(double degrees) {
		fakeGunTurnRight = degrees;
	}
	
	@Override
	public void setTurnGunRightRadians(double radians) {
		fakeGunTurnRight = Math.toDegrees(radians);
	}
	
	@Override
	public double getGunHeading() {
		return fakeGunHeading;
	}

	@Override
	public double getGunTurnRemaining() {
		return fakeGunTurnRight;
	}
	
	@Override
	public void setFire(double power) {}
	
	@Override
	public double getDistanceRemaining() {
		return fakeAhead;
	}
	
	@Override
	public double getTurnRemaining() {
		return fakeTurnRight;
	}
}
