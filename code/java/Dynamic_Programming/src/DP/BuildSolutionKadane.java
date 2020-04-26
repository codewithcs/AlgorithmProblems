package DP;

public class BuildSolutionKadane {

	public static class KadaneResult {
	    int maxSum;
	    int startIndex;
	    int endIndex;

	    public KadaneResult(int maxSum, int startIndex, int endIndex) {
	      this.maxSum = maxSum;
	      this.startIndex = startIndex;
	      this.endIndex = endIndex;
	    }
	  }
	
	
public static void main(String[] args) {
	
	int[] arr = { -2, 1, -3, 4, -1, 2, 1, -5, 4 } ; 
	KadaneResult k = kadane(arr) ; 
	
	System.out.println(k.startIndex + "   "+ k.endIndex + "  "  + k.maxSum );
	
}	
	// Kadane's Algorithm requires atleast one positive number. 


private static KadaneResult kadane(int arr[]) {
    /*
     * The best sum achieved for a region so far
     */
    int bestMaxSoFar = 0;

    /*
     * maxRegionStart: start index of the max sum region maxRegionEnd: end index of
     * the max sum region
     */
    int maxRegionStart = -1;
    int maxRegionEnd = -1;

    int currentStart = 0;
    int bestMaxAtThisIndex = 0;

    /*
     * We will process every
     */
    for (int i = 0; i < arr.length; i++) {

      /*
       * Add this item to the best subarray achieved at index i - 1. Then we will
       * decided what to do after this.
       */
      bestMaxAtThisIndex += arr[i];

      /*
       * If 'bestMaxAtThisIndex' is < 0 then we will decide to not extend the best
       * subarray at i - 1.
       * 
       * We will just set the best we can achieve for subarrays ending at this index i
       * to 0.
       * 
       * The new 'currentStart' to the max subarray region becomes i + 1
       */
      if (bestMaxAtThisIndex < 0) {
        bestMaxAtThisIndex = 0;
        currentStart = i + 1;
      }

      /*
       * If the best max (now the best max at this index) beats the best global max
       * achieved so far then we need to adjust:
       * 
       * 'maxRegionStart' becomes the 'currentStart' we were keeping track of all
       * along.
       * 
       * 'maxRegionEnd' becomes the index we are sitting at 'i'.
       * 
       * The 'bestMaxSoFar' becomes the 'bestMaxAtThisIndex'.
       */
      if (bestMaxAtThisIndex > bestMaxSoFar) {
        maxRegionStart = currentStart;
        maxRegionEnd = i;
        bestMaxSoFar = bestMaxAtThisIndex;
      }

    }

    return new KadaneResult(bestMaxSoFar, maxRegionStart, maxRegionEnd);
    
  }

  /*
   * Holds the result of running Kadan's algorithm
   * 
   * maxSum: the actual sum of the Max Contiguous Subarray Sum region startIndex:
   * start of Max Contiguous Subarray Sum region endIndex: end of Max Contiguous
   * Subarray Sum region
   */


}

