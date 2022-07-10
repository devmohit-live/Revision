/**
 * KthLargestEll_215
 */
public class KthLargestEll_215 {

    public int findKthLargest(int[] nums, int k) {
        // Brute force : sort ans return n-kthe element : nlogn

        // Optimized : Nlogk : pq only have k elemets in it
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int el : nums) {
            pq.add(el);
            if (pq.size() > k)
                pq.remove();
        }

        return pq.remove();

    }
}