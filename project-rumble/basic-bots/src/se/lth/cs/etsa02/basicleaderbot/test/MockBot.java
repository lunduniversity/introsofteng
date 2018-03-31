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
package se.lth.cs.etsa02.basicleaderbot.test;

import se.lth.cs.etsa02.basicleaderbot.*;

import robocode.HitByBulletEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import robocode.TeamRobot;

import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;


/**
 * MockBot - a mock robot to enable unit testing for ETSA02.
 *
 * @author Markus Borg
 */
public class MockBot extends BasicLeaderBot {
		
	private String name;
	private double fakeEnergy;
	private double fakeHeading;
	private double fakePosX;
	private double fakePosY;
	
	private ArrayList<Robot> teammates;
	private ArrayList<MethodCall> methodCalls;
	
	public MockBot(String name, double fakeEnergy, double fakeHeading,
				   double fakePosX, double fakePosY) {
		this.name = name;
		this.fakeEnergy = fakeEnergy;
		this.fakeHeading = fakeHeading;
		this.fakePosX = fakePosX;
		this.fakePosY = fakePosY;
		
		teammates = new ArrayList<>();
		methodCalls = new ArrayList<>();
	}
	
	/**
	 * Return the number of times a certain method was called.
	 * @param methodName
	 * @return
	 */
	public int getCountMethodCalls(String methodName) {
		int count = 0;
		for (MethodCall call : methodCalls) {
			if (call.isMethod(methodName)) count++;
		}
		return count;
	}
	
	/**
	 * Return the list of all method calls in chronological order.
	 * @param methodName
	 * @return
	 */
	public ArrayList<MethodCall> getMethodCalls(String methodName) {
		return methodCalls;
	}
	
	/**
	 * Return the calls of a certain method in chronological order.
	 * @param methodName
	 * @return
	 */
	public ArrayList<MethodCall> getCallsOfMethod(String methodName) {
		ArrayList<MethodCall> calls = new ArrayList<>();
		for (MethodCall call : methodCalls) {
			if (call.isMethod(methodName)) calls.add(call);
		}
		return calls;
	}
	
	/**
	 * Add a teammate to the teammate list to setup conditions for testing.
	 * @param teammate
	 */
	public void addTeammate(Robot teammate) {
		teammates.add(teammate);
	}
	
	/**
	 * A fake implementation, used for testing.
	 */
	@Override
	public boolean isTeammate(String name) {
		// TODO Auto-generated method stub
		methodCalls.add(new MethodCall("isTeammate", name));
		for (Robot r : teammates) {
			if (r.getName().equals(name)) return true;
		}
		return false;
	}
	
	/**
	 * A fake implementation, used for testing.
	 */
	@Override
	public void broadcastMessage(Serializable message) throws IOException {
		// TODO Auto-generated method stub
		methodCalls.add(new MethodCall("broadcastMessage", message));
	}
	
	@Override
	public double getHeading() {
		return fakeHeading;
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
		// TODO Auto-generated method stub
		return name;
	}
}
