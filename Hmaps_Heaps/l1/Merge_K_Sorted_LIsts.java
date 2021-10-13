import java.io.*;
import java.util.*;

// Same approach is used in merger K sorted LinkedList Using PQ => Leetcode 23
public class Merge_K_Sorted_LIsts {

    static class Pair {
        int val;
        int li;
        int di;

        Pair(int val, int li, int di) {
            this.val = val;
            this.li = li;
            this.di = di;
        }
    }

    public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> lists) {
        ArrayList<Integer> rv = new ArrayList<>();

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
            return a.val - b.val;
        });

        // adding first elemet of each list in pq
        for (int i = 0; i < lists.size(); i++) {
            pq.add(new Pair(lists.get(i).get(0), i, 0));
        }

        while (pq.size() > 0) {
            Pair rm = pq.remove();
            int data = rm.val;
            int li = rm.li;
            int idx = rm.di;
            idx++;
            rv.add(data);

            if (idx < lists.get(li).size())
                pq.add(new Pair(lists.get(li).get(idx), li, idx));

        }

        return rv;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        ArrayList<ArrayList<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            ArrayList<Integer> list = new ArrayList<>();

            int n = Integer.parseInt(br.readLine());
            String[] elements = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                list.add(Integer.parseInt(elements[j]));
            }

            lists.add(list);
        }

        ArrayList<Integer> mlist = mergeKSortedLists(lists);
        for (int val : mlist) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

}