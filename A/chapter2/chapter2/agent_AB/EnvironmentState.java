package chapter2.agent_AB;

import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import chapter2.agent_AB.Environment.LocationState;
import chapter2.swing.Enviroment2D;

public class EnvironmentState {
	private Map<String, Environment.LocationState> state = new HashMap<String, Environment.LocationState>();
	private String agentLocation = null;//
	Enviroment2D  e;
	

	
	
	public EnvironmentState(Environment.LocationState locAState, Environment.LocationState locBState) {
		this.state.put(Environment.LOCATION_A, locAState);
		this.state.put(Environment.LOCATION_B, locBState);
	}

	public void setAgentLocation(String location) {
		this.agentLocation = location;
	}

	public String getAgentLocation() {
		return this.agentLocation;
	}

	public LocationState getLocationState(String location) {
		return this.state.get(location);
	}

	public void setLocationState(String location, LocationState locationState) {
		this.state.put(location, locationState);
	}

//	public void display() {
//		System.out.println("Environment state: \n\t" + this.state); // can viet lai ham nay
//	}



	// @hoanghai
	private static Map<Point, Environment.LocationState> statePoint = new HashMap<Point, Environment.LocationState>();
	private Point agentLocationPoint = null;

	public EnvironmentState(int x, int y, double wall, double dirty) {
		// @hoanghai
		// tao moi truong
		  e = new Enviroment2D(x,y);

		Random r = new Random();
		int luckyX = r.nextInt(x);
		int luckyY = r.nextInt(y);

		int numOfWall = (int) (x * y * wall);
		int numOfDirty = (int) (x * y * dirty);

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				setLocationState(new Point(i, j), LocationState.CLEAN);
			}
		}

		for (int i = 0; i < numOfDirty; i++) {
			if (getLocationState(new Point(luckyX, luckyY)) == LocationState.CLEAN) {
				setLocationState(new Point(luckyX, luckyY), LocationState.DIRTY);
				luckyX = r.nextInt(x);
				luckyY = r.nextInt(y);
			} else {
				i--;
				luckyX = r.nextInt(x);
				luckyY = r.nextInt(y);
			}
		}

		for (int i = 0; i < numOfWall; i++) {
			if (getLocationState(new Point(luckyX, luckyY)) == LocationState.CLEAN) {
				setLocationState(new Point(luckyX, luckyY), LocationState.WALL);
				luckyX = r.nextInt(x);
				luckyY = r.nextInt(y);
			} else {
				i--;
				luckyX = r.nextInt(x);
				luckyY = r.nextInt(y);
			}
		}

	}

	public static Map<Point, Environment.LocationState> getStatePoint() {
		return statePoint;
	}

	public Point getAgentLocationPoint() {
		return agentLocationPoint;
	}

	public void setAgentLocationPoint(Point agentLocationPoint) {
		this.agentLocationPoint = agentLocationPoint;
	}

	public LocationState getLocationState(Point location) {
		return this.statePoint.get(location);
	}

	public void setLocationState(Point location, LocationState locationState) {
		this.statePoint.put(location, locationState);
	}

	public static void main(String[] args) {
		new EnvironmentState(3, 4, 0.1, 0.2);
	}

	public boolean isWall(Point p) {
		// @hoanghai

		if (!statePoint.containsKey(p))
			return true;
		if (getLocationState(p) == LocationState.WALL)
			return true;
		return false;
	}
	// @hoanghai
	public void display(int x, int y) {

		
		e.updateMap(x, y, statePoint, agentLocationPoint);
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (getAgentLocationPoint().equals(new Point(i, j))) {
					System.out.print("ooooo" + " ");
				} else
					System.out.print(statePoint.get(new Point(i, j)) + " ");
			}
			System.out.println();
		}
	}
}