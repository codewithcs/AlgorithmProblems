package AlgoExpert_Hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopologicalSort2 {

	public static void main(String[] args) {
		List<Integer> jobs = Arrays.asList(1, 2, 3, 4 ) ;
		Integer[][] i = { {1, 2}, {1, 3}, {3, 2}, {4, 2}, {4, 3} } ; 
		
		List<Integer[]> deps = new ArrayList<>();
		for(Integer[] i1 : i) {
			deps.add(i1) ; 
		}

		long startTime = System.nanoTime();
		System.out.println(topologicalSort(jobs, deps));
		long stopTime = System.nanoTime();
		System.out.println("Time : "  + (stopTime - startTime));
	
	}
	
	// O(j+d) time and space. 
	public static List<Integer> topologicalSort(List<Integer> jobs, List<Integer[]> deps) {
		JobGraph jobGraph = createJobGraph(jobs, deps); 
		return getOrderedJobs(jobGraph); 
	}
	
	public static JobGraph createJobGraph(List<Integer> jobs, List<Integer[]> deps) {
		JobGraph graph = new JobGraph(jobs) ; // add the vertices first in the graph. 
		for(Integer[] dep: deps) {
			graph.addDep(dep[0], dep[1]) ;  // Pass first element first as first element has the second element as its dependency. 
		}
		return graph; 
	}
	
	public static List<Integer> getOrderedJobs(JobGraph graph){
		
		List<Integer> orderedJobs = new ArrayList<>(); 
		
		List<JobNode> nodesWithNoPrereqs = new ArrayList<>(); 
		
		for(JobNode node: graph.nodes) { // iterate over the list of nodes. 
			if(node.numOfPrereqs == 0) {
				nodesWithNoPrereqs.add(node); // Add the nodes which have no pre-requisites in the list. 
			}
		}
		
		while(!nodesWithNoPrereqs.isEmpty()) {
			JobNode node = nodesWithNoPrereqs.get(nodesWithNoPrereqs.size()-1); // get the last node in this list. 
			nodesWithNoPrereqs.remove(nodesWithNoPrereqs.size()-1); // remove it. 
			orderedJobs.add(node.job); 
			removeDeps(node, nodesWithNoPrereqs) ; // call 
		}
		
		boolean graphHasEdges = false ; 
		
		for(JobNode node: graph.nodes) { // Cycle Check 
			if(node.numOfPrereqs > 0) {
				graphHasEdges = true ; 
			}
		}
		
		return graphHasEdges ? new ArrayList<>() : orderedJobs ; 
	}
	
	public static void removeDeps(JobNode node, List<JobNode> nodesWithNoPrereqs ) {
		
		while(!node.deps.isEmpty()) {
			JobNode dep = node.deps.get(node.deps.size()-1); // Get the last node. 
			node.deps.remove(node.deps.size()-1);  // Important Step. 
			dep.numOfPrereqs--;                    // Decrease the number of pre-requisites. 
			if(dep.numOfPrereqs == 0) {
				nodesWithNoPrereqs.add(dep); 
			}
		}
		
	}
	
	static class JobGraph {
		public List<JobNode> nodes; 
		public Map<Integer, JobNode> graph; 
		
		public JobGraph(List<Integer> jobs) {
			nodes = new ArrayList<>() ; 
			graph = new HashMap<>() ; 
			for(Integer job: jobs) {
				addNode(job) ; 
			}
		}
		
		public void addDep(Integer job, Integer dep) { // Add dependency 
			JobNode jobNode = getNode(job); 
			JobNode depNode = getNode(dep) ; 
			jobNode.deps.add(depNode); 
			depNode.numOfPrereqs++;  // increment the number of dependencies for the dependency node. 
		}
		
		public void addNode(Integer job) {
			graph.put(job, new JobNode(job)) ; 
			nodes.add(graph.get(job)); 
		}
		
		public JobNode getNode(Integer job) {
			if(!graph.containsKey(job)) {
				addNode(job) ; 
			}
			return graph.get(job); 
		}	
	}
	
	static class JobNode{
		public Integer job; 
		public List<JobNode> deps; // Here we also have dependencies. And we are storing the number of pre-requisites. 
		public Integer numOfPrereqs; 
		
		public JobNode(Integer job) {
			this.job = job; 
			deps = new ArrayList<>() ; 
			numOfPrereqs = 0;
		}
	}
	
}
