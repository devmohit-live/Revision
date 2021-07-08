import java.util.*;

public class Kth {
    // In kth largest or smallest we just have to make a PQ of reverse nature

    // kth largest -> min PQ(default)
    // kth smallest -> max PQ(revrese) return b-a;

    // ALog: add elent to pq if pq.size at any moment > k remove element from pq

    public static int kthLargest(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int ele : arr) {
            pq.add(ele);
            if (pq.size() > k)
                pq.remove();
        }

        return pq.peek();
    }

    // https://practice.geeksforgeeks.org/problems/kth-smallest-element5635/1
    public static int kthSmallest(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        for (int ele : arr) {
            pq.add(ele);
            if (pq.size() > k)
                pq.remove();
        }

        return pq.peek();
    }
}