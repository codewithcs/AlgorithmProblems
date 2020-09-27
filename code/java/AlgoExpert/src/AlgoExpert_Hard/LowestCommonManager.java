package AlgoExpert_Hard;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class LowestCommonManager {

	  public static OrgChart getLowestCommonManager(OrgChart topManager, OrgChart reportOne, OrgChart reportTwo) {
		  
		  OrgChart lcm = null ;
		  Map<Integer, OrgChart> map = new HashMap<>(); 
		  map.put(2, null) ; 
		  
		  int num = getLowestCommonManagerHelper(topManager, reportOne, reportTwo, map) ;
		  
		  if(num != 2) {
			  return null ; 
		  }
		  
		  return map.get(2); 
	  }
	  
	  // My Solution. 
	  public static int getLowestCommonManagerHelper(OrgChart topManager, OrgChart reportOne, OrgChart reportTwo, Map<Integer, OrgChart> lcm) {
		  
		  	List<OrgChart> list = topManager.directReports ; 
		  	
		  	int num = 0; 
		  	
		  	// Imp Step
		  	if(topManager == reportOne || topManager == reportTwo ) {
		  		num = num + 1 ; // we do not return here as we have to go on. 
		  	}
		  	
//		  	if(list.isEmpty()) { // leaf node. // can remove this check. 
//		  		return num ; 
//		  	}
		  			  	
		  	for(OrgChart orgChart: list) {
		  		num = num + getLowestCommonManagerHelper(orgChart, reportOne, reportTwo, lcm) ;   
		  		
		  		if(num == 2) {
		  			if(lcm.get(2) == null) {
			  			lcm.put(2, topManager);  
		  			}
		  			return num ; 
		  		}
		  	}
		  
		    return num;
	  }
	  
	  
	  // Algo Expert solution
	  public static OrgChart getLowestCommonManager2(OrgChart topManager, OrgChart reportOne, OrgChart reportTwo) {
		  return getOrgInfo(topManager, reportOne, reportTwo).lowestCommonManager;
	  }

	  public static OrgInfo getOrgInfo(OrgChart manager, OrgChart reportOne, OrgChart reportTwo) {
		 
		  int numImportantReports = 0; 
		  
		  for(OrgChart directReport : manager.directReports) {
			  
			  OrgInfo orgInfo = getOrgInfo(directReport, reportOne, reportTwo); 
			  
			  if(orgInfo.lowestCommonManager != null) {
				  return orgInfo; 
			  }
			  
			  numImportantReports += orgInfo.numImportantReports; 
		  }
		  
		  if(manager == reportOne || manager == reportTwo) { // Can also have this before for loop. 
			  numImportantReports++; 
		  }
		  
		  OrgChart lowestCommonManager = numImportantReports == 2 ? manager : null ; 
		  OrgInfo newOrgInfo = new OrgInfo(lowestCommonManager, numImportantReports); 
		  
		  return newOrgInfo ; 
	  }

	  static class OrgInfo{
		  public OrgChart lowestCommonManager; 
		  int numImportantReports;
		  
		  OrgInfo(OrgChart lowestCommonManager, int numImportantReports){
			  this.lowestCommonManager = lowestCommonManager;
			  this.numImportantReports = numImportantReports;
		  }
	  }
	  
	  
	  
	  
	  
	  static class OrgChart {
		  
	    public char name;
	    public List<OrgChart> directReports;
	
	    OrgChart(char name) {
	      this.name = name;
	      this.directReports = new ArrayList<>();
	    }
	
	    // This method is for testing only.
	    public void addDirectReports(OrgChart[] directReports) {
	      for (OrgChart directReport : directReports) {
	        this.directReports.add(directReport);
	      }
	    }
	    
	  }
	
}
