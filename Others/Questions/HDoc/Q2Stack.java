public class Q2Stack {
    // LC: 560
    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap<Integer, Integer> sumOccurrencesMap = new HashMap<>();
        sumOccurrencesMap.put(0, 1); // sum = 0, empty subarray
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sumOccurrencesMap.containsKey(sum - k))
                count += sumOccurrencesMap.get(sum - k);
            sumOccurrencesMap.put(sum, sumOccurrencesMap.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
