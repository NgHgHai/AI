package k20;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class UniformCostSearchAlgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub
		PriorityQueue<Node> frontier = new PriorityQueue<>(new Comparator<Node>() {
			
			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				return o1.getPathCost() > o2.getPathCost() ? 1:-1;
			}
		});
		Set<Node> explored = new HashSet<>();
		frontier.add(root);
		while (!frontier.isEmpty()) {
			Node curent = frontier.poll();
		if (curent.getLabel().equals(goal)) {
			return curent;
		}else {
			explored.add(curent);
//			System.out.println(curent.getPathCost());
//			System.out.println("da add curent : "+ curent.getLabel()+"  "+curent.getPathCost() );
		}
		List<Edge> children = curent.getChildren();
		for (Edge edge : children) {
			Node child = edge.getEnd();
			double newPathCost = edge.getWeight() + curent.getPathCost();
//			System.out.println(curent.getPathCost());
			if (!frontier.contains(child) && !explored.contains(child)) {
				frontier.add(child);
				child.setParent(curent);
				child.setPathCost(newPathCost);
			}
			if (frontier.contains(child) && child.getPathCost() > newPathCost ) {
				child.setParent(curent);
				child.setPathCost(newPathCost);
			}
		}
		
		}
		return null;
	}

	@Override
	public Node execute(Node root, String start, String goal) {
		// TODO Auto-generated method stub
		return null;
	}

}
