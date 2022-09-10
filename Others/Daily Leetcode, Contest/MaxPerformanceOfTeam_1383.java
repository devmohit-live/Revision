/**
 * MaxPerformanceOfTeam_1383
 */
public class MaxPerformanceOfTeam_1383 {

    final int MOD = (int) (1e9 + 7);

    private class Engineer {
        int speed, eff;

        Engineer(int speed, int eff) {
            this.speed = speed;
            this.eff = eff;
        }
    }

    public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
        Engineer[] engineers = new Engineer[n];
        long perf = 0, totalSpeed = 0, max = 0;

        for (int i = 0; i < n; i++) {
            engineers[i] = new Engineer(speed[i], efficiency[i]);
        }

        // sort in desc order of efficiency

        Arrays.sort(engineers, (a, b) -> {
            return b.eff - a.eff;
        });

        PriorityQueue<Engineer> pq = new PriorityQueue<>((a, b) -> a.speed - b.speed); // min pq
        // remove min speed first
        for (Engineer e : engineers) {
            pq.add(e);
            totalSpeed += e.speed;

            if (pq.size() > k) {
                Engineer rm = pq.remove();
                totalSpeed -= rm.speed;
            }

            max = Math.max(max, totalSpeed * e.eff);
        }

        return (int) (max % MOD);
    }
}