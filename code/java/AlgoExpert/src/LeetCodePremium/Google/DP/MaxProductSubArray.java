package LeetCodePremium.Google.DP;

public class MaxProductSubArray {
    public int maxProduct(int[] nums) {
        if(nums.length == 0) return 0;

        int[] products = new int[nums.length];
        products[0] = nums[0];
        int maxProduct = products[0];

        for(int i=1; i<nums.length; i++){
            if(nums[i] * products[i-1] > nums[i]){
                products[i] = nums[i] * products[i-1];
            } else{
                products[i] = nums[i];
            }
            maxProduct = Math.max(maxProduct, products[i]);
        }

        return maxProduct;
    }
}
