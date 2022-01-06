/**
 * CarPooling1094
 */
public class CarPooling1094 {

    /*
     * Since we only have 1,001 stops, we can just figure out how many people get it
     * and out in each location. // for n> we can use hashmap too
     * 
     * Process all trips, adding passenger count to the start location, and removing
     * it from the end location. After processing all trips, a positive value for
     * the specific location tells that we are getting more passengers; negative -
     * more empty seats.
     * 
     * Finally, scan all stops and check if we ever exceed our vehicle capacity.
     * 
     */

    public boolean carPooling(int[][] trips, int capacity) {
        // return usingMap(trips,capacity); // nlonn
        return usingArray(trips, capacity); // o(n)+o(1000);
    }

    private boolean usingMap(int[][] trips, int capacity) {
        // TreeMap : so that from ,to are in ascending order while calculating the
        // capacity:
        // simulating the actual journey
        Map<Integer, Integer> m = new TreeMap<>();
        for (int[] t : trips) {
            m.put(t[1], m.getOrDefault(t[1], 0) + t[0]);
            m.put(t[2], m.getOrDefault(t[2], 0) - t[0]);
        }
        for (int v : m.values()) {
            capacity -= v;
            if (capacity < 0) {
                return false;
            }
        }
        return true;
    }

    private boolean usingArray(int[][] trips, int capacity) {
        // no need to sort as arrays keeps the from to in order only(trips: from, to
        // falls into the correct place, while this irder was not fixed in hashmap so we
        // have to use treemp)
        int[] dist = new int[1001]; // from ->to
        for (int[] trip : trips) {
            // seats are acquired at start of journey, left at end of journey
            int start = trip[1], end = trip[2], seat = trip[0];
            dist[start] += seat;
            dist[end] -= seat;
        }
        // calulate combned effect of journey in capacity, if at any point capcaity is
        // reched break;
        for (int i = 0; i < dist.length; i++) {
            capacity -= dist[i];
            if (capacity < 0)
                return false;
        }

        return true;
    }
}