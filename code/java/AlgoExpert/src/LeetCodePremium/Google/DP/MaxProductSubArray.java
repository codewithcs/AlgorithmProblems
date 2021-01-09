package LeetCodePremium.Google.DP;

public class MaxProductSubArray {
    public int maxProduct(int[] nums) {
        if(nums.length == 0) return 0;

        int[] products = new int[nums.length];
        products[0] = nums[0];
        int maxProduct = products[0];

        for(int i=1; i<nums.length; i++){
            int currentProduct = nums[i];
            int product = nums[i];
            for(int j=i-1; j>=0; j--){
                product = product * nums[j];
                if(currentProduct <= product){
                    currentProduct = product ;
                }
            }
            products[i] = currentProduct;
            maxProduct = Math.max(maxProduct, products[i]);
        }

        return maxProduct;
    }

    public int maxProduct2(int[] nums) {
        int max = Integer.MIN_VALUE;
        int product = 1;

        // Forward iteration
        for(int num : nums){
            product *= num;
            max = Math.max(product, max);
            if(product == 0){ // Reset if we encounter 0.
                product = 1;
            }
        }

        product = 1;
        // Backward iteration: Takes care of the negative number separation.
        for(int i=nums.length-1; i>=0; i--){
            product *= nums[i];
            max = Math.max(product,max);
            if(product == 0) {
                product = 1;
            }
        }

        return max;
    }

    // Understand this solution.
    public int maxProduct3(int[] nums) {
        if (nums.length == 0) return 0;

        int max_so_far = nums[0];
        int min_so_far = nums[0];
        int result = max_so_far;

        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            int temp_max = Math.max(current, Math.max(max_so_far * current, min_so_far * current));
            min_so_far = Math.min(current, Math.min(max_so_far * current, min_so_far * current));

            max_so_far = temp_max;

            result = Math.max(max_so_far, result);
        }

        return result;
    }

    public int maxProduct4(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int result = nums[0];
        int preMax = nums[0];
        int preMin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] >= 0) {
                preMax = Math.max(nums[i], preMax * nums[i]);
                preMin = Math.min(nums[i], preMin * nums[i]);
            } else {
                int temp = preMax;
                preMax = Math.max(nums[i], preMin * nums[i]);
                preMin = Math.min(nums[i], temp * nums[i]);
            }
            result = Math.max(result, preMax);
        }
        return result;
    }
}
