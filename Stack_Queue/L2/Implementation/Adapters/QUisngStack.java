public class QUisngStack {
    // Leetcode 232

    // Approach 1 "Using default stack"

    class MyQueue {
        private Stack<Integer> st;
        private Stack<Integer> tmp;
        int size;

        /** Initialize your data structure here. */
        public MyQueue() {
            this.st = new Stack<>();
            this.tmp = new Stack<>();
            this.size = 0;
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            this.size++;
            this.st.push(x);
        }

        private void swap(Stack<Integer> a, Stack<Integer> b) {
            while (a.size() != 0) {
                b.push(a.pop());
            }

        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            swap(this.st, this.tmp);
            int data = this.tmp.pop();
            // re swapping
            swap(this.tmp, this.st);
            this.size--;
            return data;
        }

        /** Get the front element. */
        public int peek() {
            swap(this.st, this.tmp);
            int data = this.tmp.peek();
            // re swapping
            swap(this.tmp, this.st);

            return data;
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return this.size == 0;
        }
    }

    // apprach 2 : "Using Linkedlist as stack"

    class MyQueue2 {
        private LinkedList<Integer> st;
        private LinkedList<Integer> tmp;
        int size;

        /** Initialize your data structure here. */
        public MyQueue2() {
            this.st = new LinkedList<>();
            this.tmp = new LinkedList<>();
            this.size = 0;
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            this.size++;
            this.st.push(x);
        }

        private void swap(LinkedList<Integer> a, LinkedList<Integer> b) {
            while (a.size() != 0) {
                b.addFirst(a.removeFirst());
            }

        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            swap(this.st, this.tmp);
            int data = this.tmp.pop();
            // re swapping
            swap(this.tmp, this.st);
            this.size--;
            return data;
        }

        /** Get the front element. */
        public int peek() {
            swap(this.st, this.tmp);
            int data = this.tmp.peek();
            // re swapping
            swap(this.tmp, this.st);

            return data;
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return this.size == 0;
        }
    }

}
