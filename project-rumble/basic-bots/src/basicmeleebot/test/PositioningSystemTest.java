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

package se.lth.cs.etsa02.basicmeleebot.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.awt.geom.Point2D;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import se.lth.cs.etsa02.basicmeleebot.PositioningSystem;
import se.lth.cs.etsa02.basicmeleebot.PositioningSystem.Wall;

public class PositioningSystemTest {
	
	private PositioningSystem positioningSystemUT;
	
	@Before
	public void setUp() {
		positioningSystemUT = new PositioningSystem(800, 600);
	}
	
	@After
	public void tearDown() {
		positioningSystemUT = null;
	}
	
	@Test
	public void testCheckCloseToWallTop() {
		Point2D.Double point = new Point2D.Double(400, 590);
		Wall wall = positioningSystemUT.checkCloseToWall(point, 20);
		assertEquals("Check that positioning system can correctly detect when a point is close to top wall", Wall.TOP, wall);
	}
	
	@Test
	public void testCheckCloseToWallLeft() {
		Point2D.Double point = new Point2D.Double(10, 300);
		Wall wall = positioningSystemUT.checkCloseToWall(point, 20);
		assertEquals("Check that positioning system can correctly detect when a point is close to left wall", Wall.LEFT, wall);
	}
	
	@Test
	public void testCheckCloseToWallBottom() {
		Point2D.Double point = new Point2D.Double(400, 10);
		Wall wall = positioningSystemUT.checkCloseToWall(point, 20);
		assertEquals("Check that positioning system can correctly detect when a point is close to bottom wall", Wall.BOTTOM, wall);
	}
	
	@Test
	public void testCheckCloseToWallRight() {
		Point2D.Double point = new Point2D.Double(790, 300);
		Wall wall = positioningSystemUT.checkCloseToWall(point, 20);
		assertEquals("Check that positioning system can correctly detect when a point is close to right wall", Wall.RIGHT, wall);
	}
	
	@Test
	public void testCheckCloseToWallNot() {
		Point2D.Double point = new Point2D.Double(400, 300);
		Wall wall = positioningSystemUT.checkCloseToWall(point, 20);
		assertNull("Check that positioning system can correctly detect when a point is not close to any wall", wall);
	}
	
	@Test
	public void testGetProjectionOnWallTop() {
		Point2D.Double point = new Point2D.Double(400, 300);
		Point2D.Double projection = positioningSystemUT.getProjectionOnWall(point, Wall.TOP);
		assertEquals("Check that the X-coordinate of a point projected on top wall is correctly calculated", 400, projection.getX(), 0.001d);
		assertEquals("Check that the Y-coordinate of a point projected on top wall is correctly calculated", 600, projection.getY(), 0.001d);
	}
	
	@Test
	public void testGetProjectionOnWallLeft() {
		Point2D.Double point = new Point2D.Double(400, 300);
		Point2D.Double projection = positioningSystemUT.getProjectionOnWall(point, Wall.LEFT);
		assertEquals("Check that the X-coordinate of a point projected on left wall is correctly calculated", 0, projection.getX(), 0.001d);
		assertEquals("Check that the Y-coordinate of a point projected on left wall is correctly calculated", 300, projection.getY(), 0.001d);
	}
	
	@Test
	public void testGetProjectionOnWallBottom() {
		Point2D.Double point = new Point2D.Double(400, 300);
		Point2D.Double projection = positioningSystemUT.getProjectionOnWall(point, Wall.BOTTOM);
		assertEquals("Check that the X-coordinate of a point projected on bottom wall is correctly calculated", 400, projection.getX(), 0.001d);
		assertEquals("Check that the X-coordinate of a point projected on bottom wall is correctly calculated", 0, projection.getY(), 0.001d);
	}
	
	@Test
	public void testGetProjectionOnWallRight() {
		Point2D.Double point = new Point2D.Double(400, 300);
		Point2D.Double projection = positioningSystemUT.getProjectionOnWall(point, Wall.RIGHT);
		assertEquals("Check that the X-coordinate of a point projected on right wall is correctly calculated", 800, projection.getX(), 0.001d);
		assertEquals("Check that the Y-coordinate of a point projected on right wall is correctly calculated", 300, projection.getY(), 0.001d);
	}
}
