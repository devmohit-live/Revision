public class SequentialDigits1291 {

    // The main things to notice here are:
    // 1. dgits have diff of 1 : 12 , 123, 1234(consecutive : given)
    // 2. the next digits have differene in 1->11->111->1111, ex: 1,12,123,1234

    // Approach1:
    // https://leetcode.com/problems/sequential-digits/discuss/1710951/Java-Just-generate-them-all-0ms

    // Approach2: Using
    // String:https://thefellowprogrammer.blogspot.com/2020/09/sequential-digits.html

    // Approahc3: bfs

    // Approahc 4: Specific to question contraints but amazing:
    // https://leetcode.com/problems/sequential-digits/discuss/451851/Java-Just-a-joke
    public List<Integer> sequentialDigits4(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        // 1->10^9 : write all consecutive numners of d dgits: d=1->9
        int[] nums = { 1, 12, 23, 34, 45, 56, 67, 78, 89, 123, 234, 345, 456, 567, 678, 789, 1234, 2345, 3456, 4567,
                5678, 6789, 12345, 23456, 34567, 45678, 56789, 123456, 234567, 345678, 456789, 1234567, 2345678,
                3456789, 12345678, 23456789, 123456789 };

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= low && nums[i] <= high)
                ans.add(nums[i]);
        }
        return ans;
    }

}
