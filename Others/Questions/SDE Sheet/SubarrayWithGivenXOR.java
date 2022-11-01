/**
 * SubarrayWithGivenXOR
 */
public class SubarrayWithGivenXOR {

    public int solve(int[] A, int X) {
        // Brute : create all suarray=> calculate xor of each and check if xor == x
        int prefixXor = 0;
        HashMap<Integer, Integer> xorOccurance = new HashMap<>();

        int count = 0;
        for (int el : A) {
            prefixXor ^= el;
            if (prefixXor == X)
                count++;

            int compliment = X ^ prefixXor;

            if (xorOccurance.containsKey(compliment)) {
                count += xorOccurance.get(compliment);
            }

            // unlike largest subarray with 0 sum we don;t have to just keep the first val
            // in map but to update it wach time
            // so we don't put it in else case
            xorOccurance.put(prefixXor, xorOccurance.getOrDefault(prefixXor, 0) + 1);

        }

        return count;
    }
}