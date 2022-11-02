public class MinStacks {
    // 1 : Reevaluating min:
    class MinStack {

        private int min;
        private Stack<Integer> st;

        public MinStack() {
            min = Integer.MAX_VALUE;
            st = new Stack<>();

        }

        public void push(int val) {
            this.min = Math.min(this.min, val);
            st.push(val);
        }

        public void pop() {
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

    /// Pushing and popping smartly
    class MinStack {

        private Stack<Long> st;
        private long minSf;

        public MinStack() {
            this.st = new Stack<>();
            this.minSf = 0;
        }

        public void push(int val) {
            long x = val;
            if (st.size() == 0) {
                st.push(x);
                minSf = x;
                return;
            }

            if (x < minSf) {
                st.push(2 * x - minSf);//this will always be lesser than x (new min) : easy to point out
                minSf = x;
            } else {
                st.push(x);
            }
        }

        public void pop() {
            if (st.peek() < minSf) {
                minSf = 2 * minSf - st.peek();
            }

            st.pop();
        }

        public int top() {
            if (st.peek() < minSf) {
                return (int) minSf;
            }

            return (int) (long) st.peek();
        }

        public int getMin() {
            return (int) minSf;
        }
    }
}
