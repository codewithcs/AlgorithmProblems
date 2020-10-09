package AlgoExpert_Hard;

public class WaterArea {
	public static void main(String[] args) {
		int[] array = {1, 0, 5, 0, 3} ; //{0, 8, 0, 0, 5, 0, 0, 10, 0, 0, 1, 1, 0, 3};
		System.out.println(waterArea(array));
		System.out.println();
	}

	// Trapping Rain Water question.
	public static int waterArea(int[] heights) { // bit confusing

		int[] maxes = new int[heights.length];
		int leftMax = 0;

		for (int i = 0; i < heights.length; i++) {
			leftMax = Math.max(leftMax, heights[i]);
			maxes[i] = leftMax;
		}

		int rightMax = 0;
		int sum = 0;
		System.out.println();

		for (int i = heights.length - 1; i > 0; i--) {
			rightMax = Math.max(rightMax, heights[i]);
			System.out.println("rightMax is : " + rightMax);
			int minHeight = Math.min(rightMax, maxes[i]);

			if (minHeight > heights[i]) {
				sum = sum + minHeight - heights[i];
			}

		}
		System.out.println();
		return sum;

	}

	public static int waterArea2(int[] heights) { // easy to understand.

		int[] maxes = new int[heights.length];
		int leftMax = 0;
		for (int i = 0; i < heights.length; i++) {
			int height = heights[i];
			maxes[i] = leftMax;
			leftMax = Math.max(leftMax, height);
		}

		int rightMax = 0;

		for (int i = heights.length - 1; i >= 0; i--) {
			int height = heights[i];
			int minHeight = Math.min(rightMax, maxes[i]);
			if (height < minHeight) {
				maxes[i] = minHeight - height;
			} else {
				maxes[i] = 0;
			}
			rightMax = Math.max(rightMax, height);
		}

		int total = 0;

		for (int i = 0; i < heights.length; i++) {
			total += maxes[i];
		}
		return total;
	}
}