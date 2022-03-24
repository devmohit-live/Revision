package Others.Questions.AMZ.OA;

public class TopKFrequent {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap<>();
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            int f1 = map.get(a), f2 = map.get(b);
            if (f1 == f2) {
                // same freq : I havr to eliminate the lexo greater element
                return b.compareTo(a);
            }
            return f1 - f2; // return the lesser elemetn
        });

        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        for (String key : map.keySet()) {
            pq.add(key);
            if (pq.size() > k) {
                pq.remove();
            }
        }

        // highest to lowest
        LinkedList<String> res = new LinkedList<>();
        while (pq.size() != 0) {
            res.addFirst(pq.remove());
        }
        return (List<String>) res;
    }
}
