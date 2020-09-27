package AlgoExpert_Hard;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TopologicalSort {

	public static void main(String[] args) {
		
		List<Integer> jobs = Arrays.asList(1, 2, 3, 4) ; 
		
		Integer[][] i = { {1, 2}, {1, 3}, {3, 2}, {4, 2}, {4, 3} } ; 
		List<Integer[]> deps = new ArrayList<>(); 
		for(Integer[] i1: i) {
			deps.add(i1) ; 
		}
		
		long startTime = System.nanoTime();
		System.out.println(topologicalSort(jobs, deps));
		long stopTime = System.nanoTime();
		System.out.println("Time : "  + (stopTime - startTime));
	}
	
	// O(j+d) time and space
	public static List<Integer> topologicalSort(List<Integer> jobs, List<Integer[]> deps) {
	   JobGraph jobGraph = createJobGraph(jobs, deps) ; // create the nodes and add the pre-reqs. 
	   return getOrderedJobs(jobGraph);	
	}
	
	public static JobGraph createJobGraph(List<Integer> jobs, List<Integer[]> deps) {
		JobGraph graph = new JobGraph(jobs) ; 
		for(Integer[] dep: deps) {
			graph.addPrereq(dep[1], dep[0]) ; // passing the 2nd element first and then first element. Adding pre-requisites. 
		} 									  // If we were adding dependencies then we will pass 0th index first and then 1st index. 
		return graph; 
	}

	public static List<Integer> getOrderedJobs(JobGraph graph) {
		List<Integer> orderedJobs = new ArrayList<>(); 
		List<JobNode> nodes = new ArrayList<>(graph.nodes); 
		
		while(!nodes.isEmpty()) { // Can do this because a list is dynamic. Avoiding using indices. 
			
			JobNode node = nodes.get(nodes.size()-1); 
			nodes.remove(nodes.size()-1) ; 
			
			boolean containsCycle = depthFirstTraverse(node, orderedJobs) ; 
			
			if(containsCycle) {
				return new ArrayList<>() ; 
			}
			
		}
		
		return orderedJobs; 
	}
	
	public static boolean depthFirstTraverse(JobNode node, List<Integer> orderedJobs ) {
		
		if(node.visited) {
			return false; 
		}
		if(node.visiting) {
			return true; 
		}
		node.visiting = true ; 
		
		for(JobNode prereqNode: node.prereqs) {  // Iterate over all of the pre-requisites of this node. 
			boolean containsCycle = depthFirstTraverse(prereqNode, orderedJobs) ; 
			
			if(containsCycle) {
				return true ; 
			}
		}
		
		node.visited = true; 
		node.visiting = false ; 
		orderedJobs.add(node.job); 
		return false ;
		
	}
	
	static class JobGraph {
		public List<JobNode> nodes; 
		public Map<Integer, JobNode> graph; 
		
		public JobGraph(List<Integer> jobs) {
			nodes = new ArrayList<>(); 
			graph = new HashMap<>(); 
			for(Integer job: jobs) {
				addNode(job); 
			}
		}
	
		public void addPrereq(Integer job, Integer prereq) {
			JobNode jobNode = getNode(job); 
			JobNode prereqNode = getNode(prereq); 
			jobNode.prereqs.add(prereqNode); 
		}
		
		public void addNode(Integer job) {
			graph.put(job, new JobNode(job)) ; 
			nodes.add(graph.get(job)); 
		}
		
		public JobNode getNode(Integer job) {
			if(!graph.containsKey(job)) {
				addNode(job) ; 
			}
			return graph.get(job) ;
		}
	}
	
	static class JobNode {
		public Integer job ; 
		public List<JobNode> prereqs; 
		public boolean visited; 
		public boolean visiting; 
		
		public JobNode(Integer job) {
			this.job = job; 
			prereqs = new ArrayList<>() ; 
			visited = false; 
			visiting = false; 
		}
	}
	
}








