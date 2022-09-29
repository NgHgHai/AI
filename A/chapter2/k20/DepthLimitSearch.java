package k20;

import java.util.List;

public class DepthLimitSearch {
public static  Node execute(Node root, String goal, int limitedDepth) {
	if (root.getLabel().equals(goal)) {
		return root;
	}else if (limitedDepth == 0 ) {
		return null;
	}
	List<Edge> children = root.getChildren();
	for (Edge edge : children) {
		Node child = edge.getEnd();
		double newPathCost = edge.getWeight() + root.getPathCost();
		child.setParent(root);
		child.setPathCost(newPathCost);
		Node re = execute(child, goal, limitedDepth-1);
		if (re != null) {
			return re;
		}
	}
	
	return null;
}
}
