package Others.Questions.AMZ.OA;
import java.util.PriorityQueue;

public class MaxPassRatio {
    // Five Star Seller
    // lc: 1792. Maximum Average Pass Ratio,
    class Solution {
        class Pair {
            double ratioIfAdded, current;
            int passed, total;

            Pair(int[] data) {
                this.passed = data[0];
                this.total = data[1];
                this.ratioIfAdded = (this.passed + 1) * 1.0 / (this.total + 1);
                this.current = (this.passed) * 1.0 / (this.total);
            }
        }

        public double maxAverageRatio(int[][] classes, int extraStudents) {
            int n = classes.length;
            double ans = 0; // average growth sum

            // will give the class that will cause max growth on addition
            PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> {
                double growthA = a.ratioIfAdded - a.current;
                double growthB = b.ratioIfAdded - b.current;

                return Double.compare(growthB, growthA);
            });

            for (int i = 0; i < n; i++) {
                Pair pair = new Pair(classes[i]);
                pq.add(pair);
            }
            // add all the brilliant students to the calsses which wil cvause max growth
            while (extraStudents-- > 0) {
                Pair rm = pq.remove();
                // add the pair after modification
                int passed = rm.passed + 1, total = rm.total + 1;

                pq.add(new Pair(new int[] { passed, total }));

            }

            while (pq.size() > 0) {
                Pair rm = pq.remove();
                ans += rm.current;
            }

            return ans / n; // net avg growth
        }

    }
}
