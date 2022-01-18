class MaxDisBw2Person_849 {/*
                 * keep track of the position of the last seated person. (Distance between
                 * last seated person and current seated person)/2 will give us the position
                 * where Alex could be seated. We do this for all positions in the array to find
                 * the max distance.
                 */
    public int maxDistToClosest(int[] seats) {
        int posOfPreviousPerson = -1, maxDistance = 0, n = seats.length;

        for (int i = 0; i < n; i++) {
            if (seats[i] == 1) {
                maxDistance = (posOfPreviousPerson < 0) ? i : Math.max(maxDistance, ((i - posOfPreviousPerson) / 2));
                posOfPreviousPerson = i;
            }
        }

        maxDistance = Math.max(maxDistance, (n - 1 - posOfPreviousPerson));

        return maxDistance;
    }
}