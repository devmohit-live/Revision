public class MinTaps {
    public int minTaps(int n, int[] ranges) {
        int start = 0, end = 0, openTaps = 0, tapIdx = 0;
        while (end < n) {
            // try to find a tap that can cover from start(<=start) to somewhere new
            // position
            for (int i = tapIdx; i < ranges.length; i++) {
                if (i - ranges[i] <= start && i + ranges[i] > end) {
                    end = ranges[i] + i;
                    tapIdx = i; // next scanning of taps should be from/afte this tap (as we can't go to the
                                // previos ones)
                }
            }
            // I am not able to find a tap such that it covers some distance
            // tap[i] == 0
            if (start == end) {
                break;
            }

            openTaps++;
            start = end; // update the start

        }

        return end < n ? -1 : openTaps;

    }
}
