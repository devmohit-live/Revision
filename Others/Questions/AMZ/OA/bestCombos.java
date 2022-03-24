package Others.Questions.AMZ.OA;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class bestCombos {
    static PriorityQueue<Long> pq;

    public static List<Long> combos(int[] arr, int k) {
        pq = new PriorityQueue<>();
        subsetSum(arr, 0, arr.length - 1, k, 0);
        LinkedList<Long> res = new LinkedList<>();
        while (pq.size() > 0) {
            res.addFirst(pq.remove());
        }

        return (List<Long>) res;
    }

    private static void subsetSum(int[] arr, int si, int ei, int k, long sum) {
        if (si > ei) {
            pq.add((long) sum);
            if (pq.size() > k)
                pq.remove();
            return;
        }

        subsetSum(arr, si + 1, ei, k, sum + arr[si]);
        subsetSum(arr, si + 1, ei, k, sum);
    }

    public static void main(String[] args) {
        int[] arr1 = { 3, 5, -2 }; // 8 6 5
        int[] arr2 = { 1, 2, 3, 100 }; // 8 6 5
        int k1 = 3, k2 = 4;
        System.out.println(combos(arr1, k1));
        System.out.println(combos(arr2, k2));
    }
}
