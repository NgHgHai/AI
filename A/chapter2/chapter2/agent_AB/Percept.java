package chapter2.agent_AB;

import java.awt.Point;

import chapter2.agent_AB.Environment.LocationState;

public class Percept {
	private String agentLocation;
	private Environment.LocationState state;

	public Percept(String agentLocation, Environment.LocationState state) {
		this.agentLocation = agentLocation;
		this.state = state;
	}
	
	public Environment.LocationState getLocationState() {
		return this.state;
	}

	public String getAgentLocation() {
		return this.agentLocation;
	}
	
	private Point agentLocationPoint;
	
	

	public Point getAgentLocationPoint() {
		return agentLocationPoint;
	}

	public Percept( Point agentLocationPoint,LocationState state) {
		super();
		this.agentLocationPoint = agentLocationPoint;
		this.state = state;
	}
	
}