import java.util.Stack;

public class Questions {
    // Leetcode 20 : Valid Parantheis
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for (char ch : s.toCharArray()) {

            if (ch == '(' || ch == '{' || ch == '[')
                st.push(ch);

            else if (st.size() == 0)
                return false;
            else if (st.size() != 0 && ch == ')' && st.peek() != '(')
                return false;
            else if (st.size() != 0 && ch == '}' && st.peek() != '{')
                return false;
            else if (st.size() != 0 && ch == ']' && st.peek() != '[')
                return false;

            else
                st.pop();
        }

        return st.size() == 0; // if any opening brackets left

    }

    // Stock Spac: https://
    // practice.geeksforgeeks.org/problems/stock-span-problem-1587115621/1#
    public static int[] calculateSpan(int arr[], int n) {
        // ngor => but here are are checking for whom I am the ngor (for all previos)
        // not finding ngor for myself

        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        for (int i = 0; i < n; i++) {
            while (st.peek() != -1 && arr[st.peek()] <= arr[i]) {
                st.pop(); // continously all the smaller elements before
            }

            ans[i] = i - st.peek();
            st.push(i);
        }

        return ans;
    }

    // Leetcode 739 : Daily Temperature

    public int[] dailyTemperatures(int[] arr) {
        // ngor
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(-1); // represents no element is there

        for (int i = 0; i < n; i++) {
            while (st.peek() != -1 && arr[st.peek()] < arr[i]) {
                // ..only this line is modified
                int idx = st.pop();
                ans[idx] = i - idx;
            }

            st.push(i);
        }

        return ans;
    }

    // Leetcode : 901
    class StockSpanner {
        private Stack<int[]> st;
        private int day;

        // {0: index, 1: price}
        public StockSpanner() {
            this.day = 0; // acts as index global
            this.st = new Stack<int[]>();
            this.st.push(new int[] { -1, -1 });
        }

        public int next(int price) {
            while (st.peek()[0] != -1 && st.peek()[1] <= price) {
                st.pop();
            }

            int span = day - st.peek()[0];
            st.push(new int[] { day++, price });
            return span;
        }
    }

    // Leetcode 735: Asteroid Collision
    // opposite sides will collide
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        for (int el : asteroids) {
            // push all the positive numbers

            if (el > 0)
                st.push(el);

            while (st.size() != 0 && st.peek() > 0 && st.peek() < -el)
                st.pop();

            if (st.size() != 0 && st.peek() == -el) {
                st.pop(); // ans dosn't push el as it is also destroyed
            } else if (st.size() == 0 || st.peek() < 0)
                st.push(el);
            else {
                // nothing to do in other cases as if we don't push the el it is considered as
                // destroyed
            }

        }

        int[] ans = new int[st.size()];
        int idx = ans.length - 1;
        while (st.size() != 0) {
            ans[idx--] = st.pop();
        }

        return ans;
    }

    // Leetcode 946 : Validate Stack Sequences
    public boolean validateStackSequences(int[] pushed, int[] popped) {

        if (pushed.length == 0 && popped.length == 0)
            return true;
        // should be of same length
        else if (pushed.length != popped.length)
            return false;

        int j = 0; // counts the no of pop operations actually done
        Stack<Integer> st = new Stack<>();
        for (int i : pushed) {
            st.push(i); // push to check for popping sequnce

            // while valid and top of st is actually equal to stack seq
            while (st.size() > 0 && st.peek() == popped[j]) {
                st.pop();
                j++;
            }

        }
        return j == popped.length;// or st.size() == 0;
    }

    // Leetcode 856 : Score of Parentheses
    public int scoreOfParentheses(String s) {
        // Intuttion : ek pichla state b solve kro

        int ans = 0;
        Stack<Integer> st = new Stack<>();
        st.push(0); // extra 0 for intial mark and adding b=0 in eq
        for (char c : s.toCharArray()) {
            if (c == '(') {
                st.push(0);
            } else {
                int a = st.pop();
                int b = st.pop();
                ans = b + Math.max(2 * a, 1);
                st.push(ans);
            }
        }
        return st.pop();
    }

    // Leetcode 84 : Largest Area of Histogram

    // Using both boundaries:
    public int largestRectangleArea(int[] heights) {
        // next smaller on both sides(left,right) => will give excluding boundaries
        // height is already given , area = height * (B - A) -1 (b= nsor , a = nsol)(as
        // both boundaries are excluded)

        int[] nsol = nsol(heights);
        int[] nsor = nsor(heights);
        // System.out.println("Left boundary "+ Arrays.toString(nsol));
        // System.out.println("Right boundary "+ Arrays.toString(nsor));

        int area = 0, n = heights.length;

        for (int i = 0; i < n; i++) {
            int ht = heights[i];
            int rightExcludedBoundary = nsor[i];
            int leftExcludedBoundary = nsol[i];

            int width = rightExcludedBoundary - leftExcludedBoundary - 1;
            int currarea = ht * width;
            // System.out.println(i+" "+width+" "+currarea);
            area = Math.max(area, currarea);
        }

        return area;
    }

    // Just calculating the right boundary and procssing left in place with right
    // O(2n)
    public int largestRectangleArea2(int[] heights) {
        int area = 0, n = heights.length;
        Stack<Integer> st = new Stack<>();
        st.push(-1); // ref for end of st

        // nsor kind of thing
        for (int i = 0; i < n; i++) {
            while (st.peek() != -1 && heights[st.peek()] > heights[i]) {
                int ht = heights[st.pop()];
                int width = i - st.peek() - 1; // right boundary = i , left = st.peek()
                area = Math.max(area, ht * width);
            }

            st.push(i);
        }

        // some elemtns left in stack => for them right boundary is arr.length = n;
        while (st.peek() != -1) {
            int ht = heights[st.pop()];
            int width = n - st.peek() - 1; // right boundary = n, left = st.peek()
            area = Math.max(area, ht * width);
        }

        return area;
    }

    // Leetcode 85 : Maximal Rectangle: Treat every row as histogram's base

    // O(3nm)
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int n = matrix.length, m = matrix[0].length, area = 0, curr = 0;

        int[] height = new int[m]; // prefix arr representing histogram at a time

        for (int i = 0; i < n; i++) {// O(n)
            for (int j = 0; j < m; j++) { // O(m)
                // if matrix[i][j] was zero at base then no histogram is possible else
                // matrix[][] = 1 add h=1 more to the heihgt
                height[j] = matrix[i][j] == '0' ? 0 : height[j] + 1;
            }
            curr = largestRectangleArea2(height);// O(2m)
            area = Math.max(area, curr);
        }
        return area;
    }

    // Leetcode 32: Longest Valid Parentheses
    // based on lasrgest area histogram 2

    public int longestValidParentheses(String s) {
        int ans = 0, n = s.length();
        if (n == 0)
            return 0;

        Stack<Integer> st = new Stack<>();
        st.push(-1);
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == ')' && st.peek() != -1 && s.charAt(st.peek()) == '(') {
                st.pop();
                ans = Math.max(ans, i - st.peek()); // length
            }

            else
                st.push(i); // either '(' or ')'

        }

        // for longest invalid seq return st.size() -1 // the leftovers in stack -1(bcz
        // we have manually added extra -1 at start)

        return ans;
    }
}
