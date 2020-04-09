/**	
Copyright (c) 2020 Teodor Ahlinder

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

package se.lth.cs.etsa02.basicleaderbot;

public class DummyRobot {
	private String name;
	private double x;
	private double y;
	private double velocity;
	private double energy;
	private double heading;
	private double gunHeading;
	
	public DummyRobot(String name, double x, double y, double velocity, double energy, double heading, double gunHeading) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.velocity = velocity;
		this.energy = energy;
		this.heading = heading;
		this.gunHeading = gunHeading;
	}
	
	// Getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public double getEnergy() {
		return energy;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}

	public double getHeading() {
		return heading;
	}

	public void setHeading(double heading) {
		this.heading = heading;
	}

	public double getGunHeading() {
		return gunHeading;
	}

	public void setGunHeading(double gunHeading) {
		this.gunHeading = gunHeading;
	}

	public void update(double x2, double y2, double velocity2, double energy2, double heading2, double gunHeading2) {
		x = x2;
		y = y2;
		velocity = velocity2;
		energy = energy2;
		heading = heading2;
		gunHeading = gunHeading2;
	}
}
