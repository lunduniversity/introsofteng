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
package etsa02_basicbots.test;

import robocode.HitByBulletEvent;
import robocode.ScannedRobotEvent;
import robocode.TeamRobot;

import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;

import etsa02_basicbots.BasicLeaderBot;
import etsa02_basicbots.RadarSystem;

/**
 * MockBot - a mock robot to enable unit testing for ETSA02.
 *
 * @author Markus Borg
 */
public class MockScannedRobotEvent extends ScannedRobotEvent {

	private String fakeName;
	private double fakeEnergy;
	private double fakeBearing;
	private double fakeDistance;
	private double fakeHeading;
	private double fakeVelocity;
	private boolean fakeIsSentryRobot;

	public MockScannedRobotEvent(String fakeName, double fakeEnergy, double fakeBearing, double fakeDistance,
						  double fakeHeading, double fakeVelocity, boolean fakeIsSentryRobot) {
		this.fakeName = fakeName;
		this.fakeEnergy = fakeEnergy;
		this.fakeBearing = fakeBearing;
		this.fakeDistance = fakeDistance;
		this.fakeHeading = fakeHeading;
		this.fakeVelocity = fakeVelocity;
		this.fakeIsSentryRobot = fakeIsSentryRobot;
	}

	@Override
	public double getHeading() {
		return fakeHeading;
	}

	@Override
	public double getBearing() {
		return fakeBearing;
	}
}
