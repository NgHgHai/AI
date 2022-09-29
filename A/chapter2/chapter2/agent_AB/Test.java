package chapter2.agent_AB;

import java.awt.Point;
import java.util.Iterator;
import java.util.Random;

import chapter2.agent_AB.Environment.LocationState;

public class Test {
	public static void main(String[] args) {
		Environment env = new Environment(10, 10,0.2,0.5,100);
		Agent agent = new Agent(new AgentProgram(env));
		env.addAgent(agent, new Point(0, 0));
		
		env.stepUntilDone();
	}
}