/**
 * GasStation134
 */
public class GasStation134 {

    // Approach1: checking first if even it is possible or not then aaplying greedy
    // O(2n)
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int tank = 0;
        for (int i = 0; i < n; i++) {
            tank += gas[i] - cost[i];
        }

        if (tank < 0)
            return -1;

        int ans = 0;
        tank = 0;
        for (int i = 0; i < n; i++) {
            int gain = gas[i] - cost[i];
            tank += gain;
            if (tank < 0) {
                ans = i + 1;
                tank = 0;
            }
        }
        return ans;
    }

    // Approach2 : maintaining the defficient tank in meanwhile to avoid extra loop
    // : O(n)
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int n = gas.length, def = 0, tank = 0, net = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            tank += gas[i] - cost[i];
            net += gas[i] - cost[i];
            if (tank < 0) {
                ans = i + 1;
                tank = 0;
            }

        }

        return net < 0 ? -1 : ans;
    }

    // Approach 3 : We would like to start from the point with max defficiency so
    // that we can reach other stations(we can say for sure)
    public int canCompleteCircuit3(int[] gas, int[] cost) {
        int n = gas.length;
        int minCityIdx = -1;
        int gallon = 0, minGallon = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) { // starting from city 0
            gallon += gas[i]; // refuel at i
            gallon -= cost[i]; // deduct cost from i to i + 1
            // update
            if (gallon < minGallon) {
                minGallon = gallon;
                minCityIdx = (i + 1) % n; // consider the calculation for starting point at last
            }
        }
        // when getting back to the starting point, check if the gallon is negative (no
        // ample city)
        return (gallon >= 0) ? minCityIdx : -1;
    }

}