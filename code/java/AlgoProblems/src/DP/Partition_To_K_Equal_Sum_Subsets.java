package DP;

public class Partition_To_K_Equal_Sum_Subsets {
	
	public static void main(String[] args) {
		int arr[] = {4, 3, 2, 3, 5, 2, 1} ; 
		System.out.println(canPartitionKSubsets( arr, 4) );
		
		System.out.println(isKPartitionPossible(arr, 7, 4));
	} 
	
	 public static boolean canPartitionKSubsets(int[] arr, int k) {
		    /*
		      Get the sum of all items in the array. We will use this to
		      divide by k to get the sum that each bucket needs to hit
		    */
		    int sumOfAllArrayItems = 0;
		    for (int num : arr) {
		        sumOfAllArrayItems += num;
		    }

		    /*
		      1.) k can not be negative or 0 because we can not fill 0
		      or negative buckets
		      2.) k can not be greater than the length of the array,
		      we can't partition more buckets than there are elements
		      in the array
		      3.) sumOfAllArrayItems % k must be 0. If it is not then
		      we would have to have to fill buckets to a floating point
		      sum which would be impossible with only integers
		    */
		    if (k <= 0 || k > arr.length || sumOfAllArrayItems % k != 0) { 
		      return false;
		    }

		    return canPartition(0, arr, new boolean[arr.length], k, 0, sumOfAllArrayItems / k);
		  }

		  private static boolean canPartition(int iterationStart, int[] arr, boolean[] used, int k,
		                        int inProgressBucketSum, int targetBucketSum) {
		    /*
		      If we have filled all k - 1 buckets up to this point and we are now on
		      our last bucket, we can stop and be finished.
		      
		      Example:
		      arr = [4, 3, 2, 3, 5, 2, 1]
		      k = 4
		      targetBucketSum = 5
		      If we get to the point in our recursion that k = 1 that means we have filled
		      k - 1 buckets (4 - 1 = 3). 3 buckets have been filled, each a value of 5 meaning
		      we have "eaten" 15 "points" of value from an array that sums to 20.
		      This means we have 5 "points" to extract from the array and that for sure will fill
		      the last bucket. So at the point there is 1 bucket left, we know we can complete the
		      partitioning (we don't have to though, we just want to know whether we can or not).
		    */
		    if (k == 1) { // Base Case 
		      return true;
		    }

		    /*
		      Bucket full. continue the recursion with k - 1 as the new k value, BUT the
		      targetBucketSum stays the same. We just have 1 less bucket to fill.
		    */
		    if (inProgressBucketSum == targetBucketSum) {
		      return canPartition(0, arr, used, k - 1, 0, targetBucketSum);
		    }

		    /*
		      Try all values from 'iterationStart' to the end of the array ONLY if:
		      
		      1.) They have not been used up to this point in the recursion's path
		      2.) They do not overflow the current bucket we are filling
		    */
		    for (int i = iterationStart; i < arr.length; i++) {
		    	
		      if (!used[i] && inProgressBucketSum + arr[i] <= targetBucketSum) {
		       
		    	  used[i] = true;
		        /*
		          See if we can partition from this point with the item added
		          to the current bucket progress
		        */
		        
		        if (canPartition(i + 1, arr, used, k, inProgressBucketSum + arr[i], targetBucketSum)) {
		          return true;
		        }
		        
		        used[i] = false;
		        
		      }
		    }

		    /*
		      Partitioning from this point is impossible. Whether we are at the
		      top level of the recursion or deeper into it.
		    */
		    return false; // When we go out of bounds. 
		  
		  }
		  
		  
		  	// Dynamic Programming Approach 
		  
		   static boolean vis[][];
		  
		    static boolean partition(int arr[], int start, boolean used[], int k, int curSum, int targetSum)
		    {
		        if(k == 1)
		            return true;
		        
		        if(curSum == targetSum)
		            return partition(arr, 0, used, k-1, 0, targetSum);
		        
		        for(int i=start ; i<arr.length ; i++ ) ////  
		        {
		            int temp = curSum + arr[i]; 
		            
		            if(!used[i] && temp <= targetSum) 
		            {
		                if(!vis[temp - 1][i]) // Don't go in if we have already been through this recursive call.  
		                {
		                    used[i] = true;
		                    
		                    if(partition(arr, i + 1, used, k, curSum + arr[i], targetSum))
		                        return true;
		                    
		                    vis[temp - 1][i] = true; // We have already been through this recursive call. 
		                    
		                    used[i] = false;
		                }
		            }
		            
		        }
		        
		        return false;
		    }
		    
		    static boolean isKPartitionPossible(int arr[], int n, int k)
		    {
			    int sum = 0;
			    
			    for(int i=0 ; i<n ; i++)
			        sum += arr[i];
			        
			    if(k <= 0 || k > n || sum % k != 0)
			        return false;
			       
			    vis = new boolean[sum / k][n];
			    
			    return partition(arr, 0, new boolean[n], k, 0, sum / k);
		    }
		  
}
