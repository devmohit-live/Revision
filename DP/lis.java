import java.util.Arrays;

public class lis {
    static int[] arr = { 0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15, 14 };

    // Meaning:
    // dp[ei] = ei th index pe khtm hone wala (arr[ei]th element ko include krne
    // wala longest inc subseq)
    static int longestIncreasingSubsequenceMem(int ei, int[] dp) {

        if (dp[ei] != 0)
            return dp[ei];

        int maxlen = 1; // single number is always a increasing/decreasing subseq
        // this loop will handle the base case itself
        for (int i = ei - 1; i >= 0; i--) {
            if (arr[i] < arr[ei]) {
                int recans = longestIncreasingSubsequenceMem(i, dp);
                maxlen = Math.max(maxlen, recans + 1);
            }
        }
        return dp[ei] = maxlen;
    }

    // mujhpe khtm hone wala lis
    static int longestIncreasingSubsequenceTab_LR(int[] dp) {
        // Left to Right LIS
        int ans = 0;

        for (int i = 0; i < arr.length; i++) {
            dp[i] = 1; // base case
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    // abtk ka max lis jo merepe khtm hota hai =>
                    // mujhse pehle ke chote ke max lis +mai khud
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }

            }

            ans = Math.max(ans, dp[i]); // answer can we anywhere in dp
        }
        return ans;
    }

    // mujhse shuru hone wala lis == mujhe khtm hone wala lds from right to left
    static int longestIncreasingSubsequenceTab_RL(int[] dp) {
        // Right to left LIS
        int n = arr.length, maxLen = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i]) { // lis logic
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            maxLen = Math.max(dp[i], maxLen);
        }

        return maxLen;
    }

    static int longestIncreasingSubsequence() {
        int n = arr.length;
        int[] dp = new int[n];
        int[] dp2 = new int[n];
        int[] dp3 = new int[n];
        int max = -1, max2 = -1;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, longestIncreasingSubsequenceMem(i, dp));
        }
        System.out.println("Memo:  " + max);

        max2 = longestIncreasingSubsequenceTab_LR(dp2);
        System.out.println(Arrays.toString(dp2));
        System.out.println("Right to Left : " + longestIncreasingSubsequenceTab_RL(dp3));
        return max2;
    }

    // dp[ei] = eith index pe khtm hone wala longest dec subseq
    // same bs ab mai piche apne se bde elemets pe rakhunga faith
    static int longestDecreasingSubsequenceMem(int[] arr, int ei, int[] dp) {
        if (dp[ei] != 0)
            return dp[ei];

        int maxlen = 1; // single number is always a increasing/decreasing subseq
        // this loop will handle the base case itself
        for (int i = ei - 1; i >= 0; i--) {
            if (arr[i] > arr[ei]) { // arr[i] pehle ke elments jo mujhse bde h
                int recans = longestDecreasingSubsequenceMem(arr, i, dp);
                maxlen = Math.max(maxlen, recans + 1);
            }
        }
        return dp[ei] = maxlen;
    }

    // Longest Decreasing Subsq Left to Right: mujhpr khtm hone wala lds
    private static int ldsTab_LR(int[] arr, int[] dp) {
        int max = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] > arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    // Longest Decreasing Subsq Left to Right: mujhse shuru hone wala lds
    // mujhse Shuru hone wala lds == mujhpe khtm hone wala lis from right to left
    private static int ldsTab_RL(int[] arr, int[] dp) {
        int max = 0, n = arr.length;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i]) { // lds logic
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }

    static int longestDecreasingSubsequence() {
        int n = arr.length;
        int[] dp = new int[n];
        int[] dp2 = new int[n];
        int[] dp3 = new int[n];
        int max = -1, max2 = -1;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, longestDecreasingSubsequenceMem(arr, i, dp));
        }
        System.out.println("Memo:  " + max);

        max2 = ldsTab_LR(arr, dp2);
        System.out.println(Arrays.toString(dp2));
        return max;
    }

    // Longest Bitomic Subsequence :
    // https://practice.geeksforgeeks.org/problems/longest-bitonic-subsequence0824/1#
    public int LongestBitonicSequence(int[] nums) {
        int[] lis = Lis(nums);
        int[] lds = Lis_RL(nums); // mujhse shuru hone wala lds
        int n = nums.length;

        int max = -1;
        for (int i = 0; i < n; i++) {
            max = Math.max(lis[i] + lds[i] - 1, max);
        }

        return max;

    }

    int[] Lis(int[] arr) {
        int n = arr.length, maxlen = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1; // base single element
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxlen = Math.max(maxlen, dp[i]);
        }
        return dp;
    }

    // basically LDS => jo mujhse shuru hota hai(na ki mujhpe khtm honta ho)
    int[] Lis_RL(int[] arr) {
        int n = arr.length, maxlen = 0;
        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1; // base single element
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxlen = Math.max(maxlen, dp[i]);
        }
        return dp;
    }

    // reverse bitonic seq
    static int reverse_bitonic(int[] arr) {
        // int[] lis = Lis(nums);
        // int[] lds = Lis_RL(nums); // mujhse shuru hone wala lds
        // int n = nums.length;

        // int max = -1;
        // for (int i = 0; i < n; i++) {
        // max = Math.max(lis[i] + lds[i] - 1, max);
        // }

        // return max;
        return -1;
    }

    // Maximum sum increasing subsequence : We just have to check/compare with sum
    // https://practice.geeksforgeeks.org/problems/maximum-sum-increasing-subsequence4749/1#

    public int maxSumIS(int arr[], int n) {
        int max = 0;
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            dp[i] = arr[i]; // single element base case
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);

                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }

    // Maximum Sum Bitonic Subsequence :
    // https://practice.geeksforgeeks.org/problems/maximum-sum-bitonic-subsequence1857/1

    public int LongestBitonicSequenceSum(int[] nums) {
        int[] lis = LisSum(nums);
        int[] lds = Lis_RL_Sum(nums);
        int n = nums.length;

        int max = -1;
        for (int i = 0; i < n; i++) {
            max = Math.max(lis[i] + lds[i] - 1, max);
        }

        return max;

    }

    int[] LisSum(int[] arr) {
        int n = arr.length, maxlen = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1; // base single element
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxlen = Math.max(maxlen, dp[i]);
        }
        return dp;
    }

    // basically LDS => jo mujhse shuru hota hai(na ki mujhpe khtm honta ho)
    int[] Lis_RL_Sum(int[] arr) {
        int n = arr.length, maxlen = 0;
        int[] dp = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = 1; // base single element
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxlen = Math.max(maxlen, dp[i]);
        }
        return dp;
    }

    // Min deletion required to make an array sorted = Basically Length - lis;
    // Here we have to remember that duplicates are alos counted as sorted ex : 2 2
    // 2 1 => req only 1 del not 4

    private static int minDeletion(int[] arr) {
        int n = arr.length, max = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] <= arr[i]) { // equal for duplicates
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return n - max;
    }

    public static void main(String[] args) {

        // System.out.println(longestIncreasingSubsequence());
        // System.out.println("Dec " + longestDecreasingSubsequence());
        System.out.println(minDeletion(arr));
        System.out.println(minDeletion(new int[] { 2, 2, 2, 1 }));
        System.out.println(minDeletion(new int[] { 1, 2, 2, 2, 1, 4, 4, 4, 6, 5 }));
    }
}
