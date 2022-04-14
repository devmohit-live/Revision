import java.util.ArrayDeque;
import java.util.Queue;

public class StackUsingQ {

    // Leetcode 225
    class MyStack {
        private Queue<Integer> queue;
        private Queue<Integer> temp;
        private int size;
        private int topEle;

        public MyStack() {
            this.queue = new ArrayDeque();
            this.temp = new ArrayDeque();
            this.size = 0;
            this.topEle = -1;

        }

        public void push(int x) {
            queue.add(x);
            this.topEle = x;
        }

        public void swapData(Queue<Integer> q1, Queue<Integer> q2) {
            while (q1.size() != 1) {
                q2.add(q1.remove());
            }
        }

        public int pop() {
            swapData(queue, temp);
            int rv = queue.remove();
            if (temp.size() != 0) {
                swapData(temp, queue);
                this.topEle = temp.peek(); // updating after swap
                queue.add(temp.remove());
            }

            return rv;
        }

        public int top() {
            return this.topEle;
        }

        public boolean empty() {
            return this.queue.size() == 0;
        }
    }

    // Follow Up : Implement Stack USing Single Q:

    // Approach: for each push =>add the new given element the remove item from q
    // till size of q > 1 and add again them into q

    class FU {
        private Queue<Integer> queue;

        public MyStack() {
            this.queue =new ArrayDeque();
        
        }

        public void push(int x) {
            queue.add(x);
            int size = queue.size();
            while (size-- > 1) {
                queue.add(queue.remove());
            }
        }

        public int pop() {
            return queue.remove();
        }

        public int top() {
            return queue.peek();
        }

        public boolean empty() {
            return this.queue.size() == 0;
        }
    }
}
