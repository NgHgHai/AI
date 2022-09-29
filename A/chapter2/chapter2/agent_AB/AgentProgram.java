package chapter2.agent_AB;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Stack;

import chapter2.agent_AB.Environment.LocationState;

public class AgentProgram {
	Action[] action = { Environment.MOVE_RIGHT, Environment.MOVE_DOWN, Environment.MOVE_UP, Environment.MOVE_LEFT };
	ArrayList<Point> visited = new ArrayList<>();
	Stack<Action> logAction = new Stack<>();
	Environment env;
	EnvironmentState envState;

	public AgentProgram(Environment env) {
		this.env = env;
		envState = env.getCurrentState();
	}

	public Action execute1(Percept p) {// location, status
		// TODO
		// @hoanghai
		Random r = new Random();

		if (p.getLocationState() == LocationState.DIRTY) {
			return Environment.SUCK_DIRT;
		}
		if (p.getLocationState() == LocationState.CLEAN) {

			return action[r.nextInt(4)];
		}
		if (p.getLocationState() == LocationState.WALL) {
			return action[r.nextInt(4)];
		}

		return NoOpAction.NO_OP;

	}

	public Action execute(Percept p) {// location, status
		// TODO
		// @hoanghai
		visited.add(p.getAgentLocationPoint());
		if (p.getLocationState() == LocationState.DIRTY) {
			return Environment.SUCK_DIRT;
		}

		if (isStuck(p.getAgentLocationPoint())) {
			System.out.println("bi ketttttt");
			System.out.println(logAction.toString());

			return goBack();
		}

		Random r = new Random();

		if (p.getLocationState() == LocationState.CLEAN) {
			int temp = r.nextInt(4);
			Point nextPoint = checkMove(action[temp], p.getAgentLocationPoint());
			while (

			envState.isWall(nextPoint) || visited.contains(nextPoint)

			) {
				temp = r.nextInt(4);
				nextPoint = checkMove(action[temp], p.getAgentLocationPoint());

//				System.out.println("caculator" + temp + " " + nextPoint.getLocation().toString());

			}
			logAction.push(action[temp]);
//			System.out.println(p.getAgentLocation());
//			System.out.println("Da luu vao log");
//			System.out.println(logAction.toString());
			return action[temp];
		}
		if (p.getLocationState() == LocationState.WALL) {
			return action[r.nextInt(4)];
		}

		return NoOpAction.NO_OP;

	}

	private Action goBack() {
		if (logAction.isEmpty()) {
			return NoOpAction.NO_OP;
		}
		Action a = logAction.pop();
		if (a.equals(env.MOVE_LEFT)) {
			return env.MOVE_RIGHT;
		}
		if (a.equals(env.MOVE_RIGHT)) {
			return env.MOVE_LEFT;
		}
		if (a.equals(env.MOVE_UP)) {
			return env.MOVE_DOWN;
		}
		if (a.equals(env.MOVE_DOWN)) {
			return env.MOVE_UP;
		}
		return null;
	}

	private boolean isStuck(Point point) {
		for (int i = 0; i < 4; i++) {
			if (!envState.isWall(checkMove(action[i], point)) &&
					!visited.contains(checkMove(action[i], point))   ) {
				
				return false;
			}
		}

		return true;
	}

	private Point checkMove(Action action, Point point) {
		// TODO Auto-generated method stub
		Point p = (Point) point.clone();
		if (action.equals(Environment.MOVE_LEFT)) {
			p.setLocation(p.getX(), p.getY() - 1);

		}
		if (action.equals(Environment.MOVE_RIGHT)) {

			p.setLocation(p.getX(), p.getY() + 1);

		}
		if (action.equals(Environment.MOVE_UP)) {

			p.setLocation(p.getX() - 1, p.getY());

		}
		if (action.equals(Environment.MOVE_DOWN)) {

			p.setLocation(p.getX() + 1, p.getY());

		}
		return p;
	}
}