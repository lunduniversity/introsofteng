/**	
Copyright (c) 2017 David Phung

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

package se.lth.cs.etsa02;

/**
 * A class to help with the reading of messages.
 * @author DavidPhung
 */
public class MessageReader {
	
	private String[] lines;

	/**
	 * Construct an object to help with reading messages.
	 * @param message
	 */
	public MessageReader(String message) {
		lines = message.split("\n");
	}
	
	/**
	 * Returns the value of the leadership line if the message contains it. Otherwise returns empty string.
	 * @return value of leadership line or empty string if the line is not included in the message.
	 */
	public String getLeaderShip() {
		String[] values = getValues("leaderShip");
		if (values.length > 0) return values[0];
		return "";
	}
	/**
	 * Returns the value of the teamMode line if the message contains it. Otherwise returns empty string.
	 * @return value of teamMode line or empty string if the line is not included in the message.
	 */
	public String getTeamMode() {
		String[] values = getValues("teamMode");
		if (values.length > 0) return values[0];
		return "";
	}
	
	/**
	 * Returns the value of the myPos line if the message contains it. Otherwise returns null.
	 * @return a point created from the (x,y) values in the myPos line or null if the line is not included in the message or parsing fails.
	 */
	public Point getMyPos() {
		String[] values = getValues("myPos");
		if (values.length > 0) return parsePoint(values[0]);
		return null;
	}
	
	/**
	 * Returns the value of the friendPos line if the message contains it. Otherwise returns null.
	 * @return a point created from the (x,y) values in the friendPos line or null if the line is not included in the message or parsing fails.
	 */
	public Point getFriendPos() {
		String[] values = getValues("friendPos");
		if (values.length > 0) return parsePoint(values[0]);
		return null;
	}
	
	/**
	 * Returns the values of the enemyPos lines if the message contains any. Otherwise returns an empty array.
	 * @return an array of points created from (x,y) values in the enemyPos lines or an empty array if no enemyPos line is included in the message.
	 */
	public Point[] getEnemyPos() {
		String[] values = getValues("enemyPos");
		Point[] p = new Point[values.length];
		for (int i = 0; i < values.length; i++) {
			p[i] = parsePoint(values[i]);
		}
		return p;
	}
	
	/**
	 * Returns the value of the targetEnemy line if the message contains it. Otherwise returns null.
	 * @return a point created from the (x,y) values in the targetEnemy line or null if the line is not included in the message.
	 */
	public String getTargetEnemy() {
		String[] values = getValues("targetEnemy");
		if (values.length > 0) return values[0];
		return "";
	}
	
	/**
	 * Returns the value of the targetPos line if the message contains it. Otherwise returns null.
	 * @return a point created from the (x,y) values in the targetPos line or null if the line is not included in the message or parsing fails.
	 */
	public Point getTargetPos() {
		String[] values = getValues("targetPos");
		if (values.length > 0) return parsePoint(values[0]);
		return null;
	}
	
	/**
	 * Returns the value of the moveTo line if the message contains it. Otherwise returns null.
	 * @return a point created from the (x,y) values in the moveTo line or null if the line is not included in the message or parsing fails.
	 */
	public Point getMoveTo() {
		String[] values = getValues("moveTo");
		if (values.length > 0) return parsePoint(values[0]);
		return null;
	}
	
	/**
	 * Create a point from a string of form "x;y"
	 * @param s input string, should follow the form "x;y"
	 * @return The point (x,y) or null if parsing fails.
	 */
	private Point parsePoint(String s) {
		Point p = null;
		String[] ss = s.split(";");
		try {
			double x = Double.parseDouble(ss[0]);
			double y = Double.parseDouble(ss[1]);
			p = new Point(x,y);
		}catch (RuntimeException e) {}
		return p;
	}
	
	/**
	 * Get the values of a line. E.g. if we have the following line: "enemyPos;12;56"
	 * Then the values of this line will be the string "12;56"
	 * Since there can be multiple lines that starts with same name (e.g. multiple enemyPos lines), 
	 * the method returns an array of the values of all these lines.
	 * @param lineName the name of the line
	 * @return an array of values
	 */
	private String[] getValues(String lineName) {
		//We first count the number of lines starting with the lineName
		//Then we construct an array of values and return it.
		String prefix = lineName + ";";
		int count = 0;
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].startsWith(prefix)) {
				count++;
			}
		}
		String[] values = new String[count];
		int k = 0;
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].startsWith(prefix)) {
				values[k] = lines[i].substring(prefix.length());
				k++;
			}
		}
		return values;
	}
}
