package LeetCodePremium.Google.ArrayAndString;

public class JumpGame2 {
    public static void main(String[] args) {
        System.out.println(jump(new int[] {2, 3, 0, 1, 4}));
    }

    public static int jump(int[] nums) {
        if(nums.length == 1) return 0;

        int lastPosition =nums.length-1;
        int[] cache = new int[nums.length-1];

        for(int i=nums.length-2; i>=0 ; i--){
            if(i + nums[i] >= nums.length-1){
                cache[i] = 1;
                lastPosition = i;
            } else if(i + nums[i] >= lastPosition){
                int min = Integer.MAX_VALUE;
                for(int j=1; j<=nums[i] ; j++){
                    min = Math.min(min, cache[i + j]);
                }

                cache[i] = 1 + min;
                lastPosition = i;
            } else{
                cache[i] = Integer.MAX_VALUE;
            }
        }

        return cache[0];
    }
}
