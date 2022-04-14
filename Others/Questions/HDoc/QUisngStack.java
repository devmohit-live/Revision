import java.util.LinkedList;
import java.util.Stack;

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

    // follow Up : Make the Stack AMortized O(1):

    // Basically we dont swap back the temp stack to original stack
    // at every pop /top opeartion which was taking O(2n) time for every single
    // opeartion, we first check whether the tmp stack
    // has something, if it has then give that directly if not swap to get the items
    // in stack

    // Since in Q fifo is followed so while we pop at any stage the previosly added
    // elements will be there in tmp stack
    // even if newly added elements are in st stack, for every pop(remove in q)
    // elemets from tmp st will be deleted first

    class FU {
        private Stack<Integer> st;
        private Stack<Integer> tmp;
        int size;

    /** Initialize your data structure here. */
    public MyQueue() {
        this.st =new Stack<>();
        this.tmp =new Stack<>();
        this.size =0;
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

        /**
         * Check if the op/tmp stack has some element(in reverse/actual q order) if not
         * swap the stack , and get the element
         */
        public int pop() {
            if (tmp.size() == 0)
                swap(st, tmp);

            int data = tmp.pop();
            this.size--;
            return data;
        }

        /** Get the front element. */
        public int peek() {
            if (tmp.size() == 0)
                swap(st, tmp);

            int data = tmp.peek();
            return data;
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return this.size == 0;
        }
    }

}
