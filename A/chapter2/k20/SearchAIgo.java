package k20;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class SearchAIgo implements ISearchAlgo {

	@Override
	public Node execute(Node root, String goal) {
		// TODO Auto-generated method stub	
		Stack<Node> fronter = new Stack<>();
//		Queue<Node> fronter = new LinkedList<Node>();
		ArrayList<Node> explored = new  ArrayList<>();
		
		fronter.add(root);
		while (!fronter.isEmpty()) {
			Node current = fronter.pop();
			System.out.print(current.getLabel()+ " ,");
			explored.add(current);
			if (current.getLabel().equals(goal)) {
//				System.out.println(current.getLabel());
				
//				while (current.getParent().equals(root)) {
//					System.out.println(current.getParent().getLabel()); 
//					current = current.getParent();
//				} 
				
				
				return current;
			}else {
				List<Node>	children = current.getChildrenNodes();
			
			for (Node child : children) {
				if (!fronter.contains(child) || !explored.contains(child) ) {
					child.setParent(current);
					fronter.add(child);
				}
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
