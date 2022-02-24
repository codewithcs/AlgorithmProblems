package DP;

import java.util.ArrayList;
import java.util.List;

public class IPAddressDecomposition {

	public static void main(String[] args) {
	
		String input = "25525511135" ; 
	
		System.out.println( restoreIPAddresses(input) ) ; 
	}
	
	 public  static List<String> restoreIPAddresses(String rawIpString) {
		    List<String> restoredIps = new ArrayList<>();
		    restoreIPs(0, 0, new int[4], rawIpString, restoredIps);
		    return restoredIps;
		  }

		  private  static void restoreIPs( int progressIndex, int currentSegment, int[] ipAddressSegments, String rawIpString, List<String> restoredIps ) {
			  
		    /*
		      If we have filled 4 segments (0, 1, 2, 3) and we are on the 4th,
		      we will only record an answer if the string was decomposed fully
		    */
		    if ( currentSegment == 4 && progressIndex == rawIpString.length() ) {
		      restoredIps.add(buildIpStringFromSegments(ipAddressSegments));
		    } 
		    else if (currentSegment == 4) { // Base Cases  // Case when the IP string is too long or too short. 
		      return;
		    }
		    // Check for base cases before doing the work: Tail Recursion? 
		    
		    /*
		      Generate segments to try, a segment can be 1 to 3 digits long.
		    */
		    // Generate all possibilities down here. 
		    for ( int segLength = 1; segLength <= 3 && progressIndex + segLength <= rawIpString.length(); segLength++ ) {

		      // Calculate 1 index past where the segment ends index-wise in the original raw ip string
		      int onePastSegmentEnd = progressIndex + segLength;

		      // Extract int value from our snapshot from the raw ip string
		      String segmentAsString = rawIpString.substring(progressIndex, onePastSegmentEnd);
		      
		      int segmentValue = Integer.parseInt(segmentAsString); // Convert the String snippet into an Integer. 

		      // Check the "snapshot's" validity - if invalid break iteration
		      if (segmentValue > 255 || segLength >= 2  && segmentAsString.charAt(0) == '0') { // Our constraints // this is the condition when the required constraint is not satisfied.
		        break;
		      }

		      // Add the extracted segment to the working segments
		      ipAddressSegments[currentSegment] = segmentValue; 

		      // Recurse on the segment choice - when finished & we come back here, the next loop iteration will try another segment
		      restoreIPs(progressIndex + segLength, currentSegment + 1, ipAddressSegments, rawIpString, restoredIps);
		    }
		  }

		  private static String buildIpStringFromSegments(int[] ipAddressSegments) {
		    return ipAddressSegments[0] + "." + ipAddressSegments[1] + "."+ ipAddressSegments[2] + "." + ipAddressSegments[3];
		  }
	
		  
		  // My Method: 
		  
		  	  
		  
}
