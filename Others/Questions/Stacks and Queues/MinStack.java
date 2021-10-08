public class MinStack {
    // APrroach 1 : re-evaluating min every time the the min item is popped
    class MinStack1 {
        // Time: Worst O(n) in pop
        // Space : O(1)

        private int min;
        private Stack<Integer> st;

        public MinStack1() {
            min = Integer.MAX_VALUE;
            st = new Stack<>();

        }

        public void push(int val) {
            this.min = Math.min(this.min, val);
            st.push(val);
        }

        public void pop() { // worst : O(N)
            int rm = this.st.pop();
            if (rm == this.min) {
                reEvaluateMin();
            }
        }

        private void reEvaluateMin() {
            Stack<Integer> tmp = new Stack<>();
            this.min = Integer.MAX_VALUE; // intial value

            while (this.st.size() != 0) {
                tmp.push(this.st.pop());
            }

            while (tmp.size() != 0)
                push(tmp.pop()); // call the created push fx as it will automatically update the min

        }

        public int top() {
            return this.st.peek();
        }

        public int getMin() {
            return this.min;
        }
    }

    // Approach: Use two stacks 1 for main 1 for min
    class MinStack2 {
        // Time: O(1)
        // Space : O(N)
        private Stack<Integer> st;
        private Stack<Integer> minStack;

        public MinStack2() {
            this.st = new Stack<>();
            this.minStack = new Stack<>();

        }

        public void push(int val) {
            if (this.minStack.size() == 0)
                this.minStack.push(val);
            // >= very important to include = to else will cause inconsistency and underflow
            // of stack error
            else if (this.minStack.peek() >= val)
                this.minStack.push(val);

            this.st.push(val);
        }

        public void pop() {
            int rm = this.st.pop();
            if (this.minStack.size() > 0 && this.minStack.peek() == rm)
                this.minStack.pop();

        }

        public int top() {
            return this.st.peek();
        }

        public int getMin() {
            if (this.minStack.size() == 0)
                return Integer.MAX_VALUE;
            return this.minStack.peek();
        }
    }

    // Approach 3 : Using stack of Pair/ int[]
    // [0] : orginalval, [1] = min till now
    class MinStack3 {
        // time : O(1)
        // space: O(N), 2n if you count no of int stored in stack relative
        private class Pair {
            int val, min;

            Pair() {

            }

            Pair(int val, int min) {
                this.val = val;
                this.min = min;
            }
        }

        private Stack<Pair> st;

        public MinStack3() {
            this.st = new Stack<>();
        }

        public void push(int val) {
            int prevMin = st.size() == 0 ? Integer.MAX_VALUE : st.peek().min;
            this.st.push(new Pair(val, Math.min(prevMin, val)));
        }

        public void pop() {
            this.st.pop();
        }

        public int top() {
            return this.st.peek().val;
        }

        public int getMin() {
            return this.st.peek().min;
        }
    }

    // Approach 4 : Using arithmatics => storing modified values and checking min acc to condition
    

}
