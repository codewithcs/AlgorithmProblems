package LeetCodePremium.Microsoft.ArraysAndStrings;

/*
Given an array, rotate the array to the right by k steps, where k is non-negative.

Follow up:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space ?

Constraints:
1 <= nums.length <= 2 * 104
-231 <= nums[i] <= 231 - 1
0 <= k <= 105
 */
public class RotateArray {
    public static void main(String[] args) {
        rotate(new int[]{-1, -100, 3, 99}, 2);
    }

    // This solution helped me to develop the thought process. 
    public static void rotate(int[] nums, int k) {
        if(nums.length < 2) return ;
        if(k==0 ) return ;

        if(nums.length % 2 == 0){ // Even Length
            int count = 0;
            int index = 0;
            int temp = nums[index%nums.length];

            while(count < nums.length/2 ){
                int second = nums[(index+k) %nums.length];
                nums[(index+k) %nums.length] = temp ;
                temp = second;
                index = (index+k) %nums.length;
                count++;
            }

            System.out.println("Array is : ");
            for(int i=0; i< nums.length ; i++){
                System.out.print(nums[i] + ", ");
            }
            System.out.println("Count is : " + count);

            index = 1; temp = nums[index%nums.length];
            while(count < nums.length){
                int second = nums[(index+k) %nums.length];
                nums[(index+k) %nums.length] = temp ;
                temp = second;
                index = (index+k) %nums.length;
                count++;
            }
            System.out.println("Array is : ");
            for(int i=0; i< nums.length ; i++){
                System.out.print(nums[i] + ", ");
            }
            System.out.println("Count is : " + count);

        } else { // Odd Length Array
            int count = 0;
            int index = 0;
            int temp = nums[index%nums.length];

            while(count < nums.length){
                int second = nums[(index+k) %nums.length];
                nums[(index+k) %nums.length] = temp ;
                temp = second;
                index = (index+k) %nums.length;
                count++;
            }
        }
    }

    // Cyclic Replacements: Important technique to move in an array by some k.
    public void rotate2(int[] nums, int k) {
        if(nums.length < 2) return ;
        if(k==0 ) return ;

        int count = 0;
        int index = 0; // Starting Index.
        // int temp = nums[index%nums.length]; // not here as we will need a new temp when we increment index.

        while(count < nums.length){ // This way we will be able to traverse each of the array index even when the length of the array is odd or even.
            int currentIndex = index;
            int temp = nums[index% nums.length];

            do{
                int second = nums[(currentIndex+k) %nums.length];
                nums[(currentIndex+k) %nums.length] = temp ;
                temp = second;
                currentIndex = (currentIndex+k) %nums.length;
                count++;
            } while(currentIndex != index); // To deal with a cycle in case of even length array and then increment the starting index.

            index++;
        }

        // We need the outer while loop to deal with the case of even length array.
        // For odd length array we can get away with single for loop.
        // For odd length array the program will finish by using just the inner do-while and for even length array, it will come multiple times to say line 82.
    }
}
