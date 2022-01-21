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

    

}