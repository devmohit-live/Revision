import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFreqEL_347 {
    // O(nlogk) : k is th
    public int[] topKFrequent_(int[] nums, int k) {

        class Pair {
            int it, freq;

            Pair(int it, int freq) {
                this.it = it;
                this.freq = freq;
            }
        }

        Map<Integer, Integer> map = new HashMap<>();

        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> a.freq - b.freq);

        for (int el : nums) {
            map.put(el, map.getOrDefault(el, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            pq.add(new Pair(e.getKey(), e.getValue()));
            if (pq.size() > k)
                pq.remove();
        }

        int[] ans = new int[k];
        while (pq.size() > 0) {
            ans[--k] = pq.remove().it;
        }

        return ans;
    }

    // Using Bukcet Sort : O(n)
    public int[] topKFrequent(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> buckets[] = new List[n + 1];
        int[] ans = new int[k];
        List<Integer> res = new ArrayList<>();

        for (int el : nums)
            map.put(el, map.getOrDefault(el, 0) + 1);

        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            int key = e.getKey(), freq = e.getValue();
            if (buckets[freq] == null)
                buckets[freq] = new ArrayList<>();

            buckets[freq].add(key);
        }

        for (int i = n; i >= 0 && k > 0; i--) {
            if (buckets[i] == null)
                continue;
            res.addAll(buckets[i]);
            k -= buckets[i].size();
        }

        for (int i = 0; i < res.size(); i++)
            ans[i] = res.get(i);

        return ans;
    }
}
