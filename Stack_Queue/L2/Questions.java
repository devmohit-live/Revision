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

}
