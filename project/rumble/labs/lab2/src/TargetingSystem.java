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

package etsa02_lab2;

import java.awt.geom.Point2D;

import robocode.util.Utils;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * A simple class handling aiming and firing. It simply shoots at the closest target 
 * based on the last known positions of all enemies.
 */
public class TargetingSystem {
	
	/**
	 * Construct a simple object to handle head-on targeting. It simply shoots at the closest target 
	 * based on the last known positions of all enemies.
	 * @param enemyHelper the object managing enemies.
	 * @param robot the robot we are currently working on
	 */
	public TargetingSystem(EnemyDatabase enemyHelper, BasicMeleeBot robot) {
		throw new NotImplementedException();
	}
	
	/**
	 * To be called every turn. Find the closest target and shoot at it.
	 */
	public void update() {
		throw new NotImplementedException();
	}
}
