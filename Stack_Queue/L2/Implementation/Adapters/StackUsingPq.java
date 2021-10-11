import java.util.HashMap;
import java.util.PriorityQueue;

public class StackUsingPq {
    // Assuming all valid operations
    private class Pair implements Comparable<Pair> {
        int val, idx;

        Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }

        // latest idx will be popped
        @Override
        public int compareTo(Pair o) {
            return o.idx - this.idx;
        }
    }

    private int idx;
    private PriorityQueue<Pair> pq;
    private HashMap<Integer, Integer> map;

    StackUsingPq() {
        this.idx = 0;
        this.map = new HashMap<>();
        this.pq = new PriorityQueue<>();
    }

    public void push(int val) {
        map.put(val, map.getOrDefault(val, idx++));
    }

    public int pop() {
        Pair rm = pq.remove();
        return rm.val;
    }

    public int top() {
        Pair rm = pq.peek();
        return rm.val;
    }

}
