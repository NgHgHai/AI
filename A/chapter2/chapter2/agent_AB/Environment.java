package chapter2.agent_AB;

import java.awt.Point;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";

	// @hoanghai
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	public static int score = 0;
	public int x =0;
	public int y =0;
	public int s = 300;
	// ---

	public enum LocationState {
		CLEAN, DIRTY, WALL
	}

	private EnvironmentState envState;
	private boolean isDone = false;// all squares are CLEAN
	private Agent agent = null;

	public Environment(LocationState locAState, LocationState locBState) {
		envState = new EnvironmentState(locAState, locBState);
	}

	public Environment(int x, int y) {
		// tao moi truong X hang Y cot
		this.x = x;
		this.y = y;
		envState = new EnvironmentState(x, y, 0.1, 0.2);

	}

	public Environment(int x, int y, Double w, Double d, int s) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.s = s;
		System.out.println(x+ "  "+ y);
		envState = new EnvironmentState(x, y, w, d);
	}

	// add an agent into the enviroment
	public void addAgent(Agent agent, String location) {
		// TODO
	}

	public void addAgent(Agent agent, Point location) {
		// @hoanghai
		this.agent = agent;
		envState.setAgentLocationPoint(location);
	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update enviroment state when agent do an action
	public EnvironmentState executeAction(Action action) {
		// TODO
		Point prePoint = envState.getAgentLocationPoint();

		if (action.equals(MOVE_LEFT)) {
			Point p = (Point) prePoint.clone();
			p.setLocation(p.getX(), p.getY() - 1);
			executeWall(prePoint, p);
		}
		if (action.equals(MOVE_RIGHT)) {
			Point p = (Point) prePoint.clone();
			p.setLocation(p.getX(), p.getY() + 1);
			executeWall(prePoint, p);
		}
		if (action.equals(MOVE_UP)) {
			Point p = (Point) prePoint.clone();
			p.setLocation(p.getX() - 1, p.getY());
			executeWall(prePoint, p);
		}
		if (action.equals(MOVE_DOWN)) {
			Point p = (Point) prePoint.clone();
			p.setLocation(p.getX() + 1, p.getY());
			executeWall(prePoint, p);
		}
		if (action.equals(SUCK_DIRT)) {
			envState.setLocationState(envState.getAgentLocationPoint(), LocationState.CLEAN);
			score += 500;
		}

		return envState;
	}

	private void executeWall(Point prePoint, Point p) {
		// @hoanghai
		// neu la tuong thi giu nguyen vi tri cu
		if (envState.isWall(p)) {
			score -= 100;
			envState.setAgentLocationPoint(prePoint);
		} else {
			envState.setAgentLocationPoint(p);
			score -= 10;
		}

	}

	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
		// TODO
		return new Percept(envState.getAgentLocationPoint(),
				envState.getLocationState(envState.getAgentLocationPoint()));
	}

	public void step() {
		try {

			Thread.sleep(s);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		envState.display(x,y);
		String agentLocation = this.envState.getAgentLocation();
		//@hoanghai
		Point agentLocationPoint = this.envState.getAgentLocationPoint();
		
		Action anAction = agent.execute(getPerceptSeenBy());
		EnvironmentState es = executeAction(anAction);

		System.out.println("Agent Loc.: " + agentLocationPoint.toString() + "\tAction: " + anAction  + "\tLocAfter.: " + this.envState.getAgentLocationPoint());

		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN))
			isDone = true;// if both squares are clean, then agent do not need to do any action

		es.display(x,y);

		// @hoanghai
		isDone = checkIsDone(es);

	}

	private boolean checkIsDone(EnvironmentState es) {
		for (Entry<Point, LocationState> entry : es.getStatePoint().entrySet()) {
			Point key = entry.getKey();
			LocationState val = entry.getValue();
			if (val == LocationState.DIRTY) {
				return false;
			}

		}

		return true;
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
			System.out.println("-------------------------");
		}
	}
	public static int step;
	public void stepUntilDone() {
		int i = 0;
		step = 0;

		while (!isDone) {
			System.out.println("step: " + step++   );
			step();
			System.out.println( "\tScore: " + score);
			System.out.println("-------------------------");
		}
	}
}
