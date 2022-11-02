public class TrappingRainWater {
    public int trap(int[] height) {
        if (height == null || height.length < 2)
            return 0;
        // return usingStack(height);
        // return usingMaxHeightLEftAndRight(height);
        return usingMaxConstantSpace(height);
    }

    private int usingStack(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int water = 0, i = 0;
        while (i < height.length) {
            if (stack.isEmpty() || height[i] <= height[stack.peek()]) {
                stack.push(i++);
            } else {
                int pre = stack.pop();
                if (!stack.isEmpty()) {
                    // find the smaller height between the two sides
                    int minHeight = Math.min(height[stack.peek()], height[i]);
                    // calculate the area
                    water += (minHeight - height[pre]) * (i - stack.peek() - 1);
                }
            }
        }
        return water;
    }

    private int usingMaxHeightLEftAndRight(int[] heights) {
        int n = heights.length, ans = 0;
        int[] leftMax = new int[n], rightMax = new int[n];

        leftMax[0] = heights[0];
        rightMax[n - 1] = heights[n - 1];

        for (int i = 1; i < n; i++)
            leftMax[i] = Math.max(leftMax[i - 1], heights[i]);
        for (int i = n - 2; i >= 0; i--)
            rightMax[i] = Math.max(rightMax[i + 1], heights[i]);

        for (int i = 0; i < n; i++)
            ans += Math.min(leftMax[i], rightMax[i]) - heights[i];

        return ans;
    }

    private int usingMaxConstantSpace(int[] height) {
        int n = height.length;
        int l = 0, r = n - 1;
        int lmax = Integer.MIN_VALUE, rmax = Integer.MIN_VALUE;
        int res = 0;
        while (l <= r) {
            // left
            if (height[l] <= height[r]) {
                // lmax needed to be updated
                if (height[l] > lmax)
                    lmax = height[l];
                else {
                    res += lmax - height[l];
                }
                l++;
            } else {
                if (height[r] > rmax)
                    rmax = height[r];
                else {
                    res += rmax - height[r];
                }
                r--;
            }
        }
        return res;
    }
}
