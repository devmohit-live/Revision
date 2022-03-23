class CanPlaceFlowers605 {
    // Leetcode 605
    public boolean canPlaceFlowers(int[] bed, int n) {
        if (n == 0)
            return true;

        // the current post should be an empty pot so that we can plant a flower
        // to plant flower the previous and next pot shhould be empty (maintaining the
        // adj rule)
        // extreme end are treated as empty pots
        return Approach2(bed, n);
    }

    // Approach1 : modification of array is required
    private boolean Approach1(int[] bed, int n) {
        for (int i = 0; i < bed.length; i++) {
            int prev = i == 0 ? 0 : bed[i - 1];
            int next = i == bed.length - 1 ? 0 : bed[i + 1];
            if (bed[i] == 0 && prev == 0 && next == 0) {
                bed[i] = 1; // planting the current pot (so that it wont again be considered for subsequent
                            // )
                n--;
            }
            if (n == 0)
                return true;
        }
        return false;
    }

    // Approach 2: managing the pointer properly
    private boolean Approach2(int[] bed, int n) {

        int i = 0, len = bed.length;
        while (i < len) {
            int prev = i == 0 ? 0 : bed[i - 1];
            int next = i == len - 1 ? 0 : bed[i + 1];
            int curr = bed[i];
            // valid
            if (curr == 0 && prev == 0 && next == 0) {
                n--;
                i += 2;
            } else
                i++;

            if (n == 0)
                return true;
        }

        return false;
    }
}