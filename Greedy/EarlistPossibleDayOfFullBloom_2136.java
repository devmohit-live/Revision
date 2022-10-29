public class EarlistPossibleDayOfFullBloom_2136 {
    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        int n = plantTime.length;

        // Explanation
        {
            // First of all we put this in 2 dimensional array.
            // Then sort on the basis of growTime in decreasing order.
            // After this longest growing time will come first so it would be most optimal.
            // In this, we will have to add all plantTime in sequential order to get optimal
            // solution just focusing on highest growTime should come first.
        }

        class Plant implements Comparable<Plant> {
            int plantTime, growTime;

            Plant(int plantTime, int growTime) {
                this.plantTime = plantTime;
                this.growTime = growTime;
            }

            @Override
            public int compareTo(Plant o) {
                return o.growTime - this.growTime;
            }

            @Override
            public String toString() {
                return "PlantTime : " + this.plantTime + " GrowTime : " + growTime;
            }

        }

        Plant[] times = new Plant[n];
        for (int i = 0; i < n; i++)
            times[i] = new Plant(plantTime[i], growTime[i]);

        Arrays.sort(times);
        int totalTime = 0, curStart = 0;
        for (int i = 0; i < n; i++) {
            totalTime = Math.max(totalTime, curStart + times[i].plantTime + times[i].growTime);
            curStart += times[i].plantTime;
        }
        return totalTime;
    }
}
