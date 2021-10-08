public class MinStack {
    // APrroach 1 : re-evaluating min every time the the min item is popped
    class MinStack1 {

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
    // Appriach 2 : Using stack of Pair/ int[]
    // [0] : orginalval, [1] = min till now


    



}
