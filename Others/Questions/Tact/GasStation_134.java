public class GasStation_134 {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length, def = 0, tank = 0, net = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            tank += gas[i] - cost[i];
            net += gas[i] - cost[i]; // tracks if it is possible to caomplete the journey
            if (tank < 0) {
                ans = i + 1;
                tank = 0;
            }

        }

        return net < 0 ? -1 : ans;

    }
}
