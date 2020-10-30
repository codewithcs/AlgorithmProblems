package LeetCodePremium.Amazon.SearchingAndSorting;
/*
We have a list of points on the plane.  Find the K closest points to the origin (0, 0).
(Here, the distance between two points on a plane is the Euclidean distance.)
You may return the answer in any order.  The answer is guaranteed to
be unique (except for the order that it is in.)

Note:
1 <= K <= points.length <= 10000
-10000 < points[i][0] < 10000
-10000 < points[i][1] < 10000
 */

import java.util.Arrays;

// Need to swap both points and distances elements.
public class KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int k) {

        double[] distances = new double[points.length];

        for(int i=0 ; i < points.length ; i++){
            distances[i] = Math.sqrt(points[i][0]*points[i][0] + points[i][1]*points[i][1]);
        }

        partition(0, points.length-1, points, distances, k);

        return Arrays.copyOfRange(points, 0, k);
    }

    public void partition(int start, int end, int[][] points, double[] distances, int k){
        if(start>= end) return ;

        int left = start+1; int right = end;
        double pivot = distances[start];

        while(left <= right){
            if(distances[left] > pivot && distances[right] < pivot ){
                int[] temp = points[left];
                points[left] = points[right];
                points[right] = temp;

                double temp1 = distances[left];
                distances[left] = distances[right];
                distances[right] = temp1;

                left++ ; right-- ;
            } else if(distances[left] <= pivot){
                left++ ;
            } else if(distances[right] >= pivot){
                right-- ;
            }
        }

        int[] temp = points[right];
        points[right] = points[start];
        points[start] = temp;

        double temp1 = distances[start];
        distances[start] = distances[right];
        distances[right] = temp1;

        if(k-1 == right){
            return ;
        } else if(k-1 > right){
            partition(right+1, end, points, distances, k);
        } else {
            partition(start, right-1, points, distances, k);
        }

    }

    // No need to create distances[] array.
    public int[][] kClosest2(int[][] points, int k) {
        partition2(0, points.length-1, points, k);
        return Arrays.copyOfRange(points, 0, k);
    }

    public void partition2(int start, int end, int[][] points, int k){
        if(start>= end) return ;

        int left = start+1; int right = end;
        double pivot = Math.sqrt(points[start][0]*points[start][0] + points[start][1]*points[start][1]);

        while(left <= right){
            if(Math.sqrt(points[left][0]*points[left][0] + points[left][1]*points[left][1]) > pivot && Math.sqrt(points[right][0]*points[right][0] + points[right][1]*points[right][1]) < pivot ){
                int[] temp = points[left];
                points[left] = points[right];
                points[right] = temp;
                left++ ; right-- ;
            } else if(Math.sqrt(points[left][0]*points[left][0] + points[left][1]*points[left][1]) <= pivot){
                left++ ;
            } else if(Math.sqrt(points[right][0]*points[right][0] + points[right][1]*points[right][1]) >= pivot){
                right-- ;
            }
        }

        int[] temp = points[right];
        points[right] = points[start];
        points[start] = temp;


        if(k-1 == right){
            return ;
        } else if(k-1 > right){
            partition2(right+1, end, points, k);
        } else {
            partition2(start, right-1, points, k);
        }

    }
}
