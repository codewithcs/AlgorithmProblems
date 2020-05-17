package AlgoExpert_Medium;
import java.util.ArrayList ;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue; 

public class BreadthFirstSearch {

	static class Node {
	    String name;
	    List<Node> children = new ArrayList<Node>();

	    public Node(String name) {
	      this.name = name;
	    }

	    public List<String> breadthFirstSearch(List<String> array) { // bfs starting at this node. 
	    	
	    	List<Node> queue = new ArrayList<>(); 
	    	queue.add(this) ; 
	    	Node currentNode ;
			
			while(!queue.isEmpty()) {
				
				currentNode = queue.remove(0) ; 
				array.add(currentNode.name) ;
				
				List<Node> children = currentNode.children ;
				
				for(int i=0 ; i<children.size() ; i++) {
					queue.add(children.get(i)) ;
				}
						
			}
						
			return array;
	    }

	    public List<String> bfs(List<String> array) { // Video solution
	    	
	    	Queue<Node> queue = new LinkedList<Node>() ; 
	    	
	    	queue.add(this); 
	    	
	    	while(!queue.isEmpty()) {
	    		Node current = queue.poll();
	    		array.add(current.name); 
	    		queue.addAll(current.children) ; 
	    	}
	    	
	    	return array; 
	    }
	    
	    
	    public Node addChild(String name) {
	      Node child = new Node(name);
	      children.add(child);
	      return this;
	    }
	    
	    
	    
	  }
	
}
