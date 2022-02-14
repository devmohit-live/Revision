import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class Contest280 {
    // Question 1: 2169. Count Operations to Obtain Zero
    // Approach 1 : Better O(1) space
    public int countOperations(int num1, int num2) {
        int ans = 0;
        while (num1 > 0 && num2 > 0) {
            if (num1 == num2)
                return ans + 1;
            if (num1 > num2)
                num1 -= num2;
            else
                num2 -= num1;
            ans++;
        }

        return ans;
    }

    // Approach 2 : Recursion :
    public int countOperations2(int num1, int num2) {
        if (num1 == 0 || num2 == 0)
            return 0;
        if (num1 < num2)
            return countOperations(num2, num1);
        return 1 + countOperations(num1 - num2, num2);
    }

    // Question 2: 2170. Minimum Operations to Make the Array Alternating
    public int minimumOperations(int[] nums) {

        int[] freqOdd = new int[(int) 1e5 + 1]; // O(n)
        int[] freqEven = new int[(int) 1e5 + 1];// O(n)
        int n = nums.length, maxel = 0;
        // O(n)
        for (int i = 0; i < n; i++) {
            maxel = Math.max(maxel, nums[i]);
            if ((i & 1) != 0) {
                // odd
                freqOdd[nums[i]]++;
            } else {
                freqEven[nums[i]]++;
            }
        }

        // Getting secondmax freq ele from both for adjacent duplicates
        // ex: [1,2,2,2,2], [3,3,3,3,1,1,1,1,1,2,2,2,4,5]

        // find first and second max for odd and even according to the frequncy
        int firstMaxEven = 0, secondMaxEven = 0;
        int firstmaxevenFreq = 0, secondmaxevenFreq = 0;
        int firstMaxOdd = 0, secondMaxOdd = 0;
        int firstmaxoddFreq = 0, secondmaxoddFreq = 0;

        // Time : O(max) => all same => O(n)
        for (int i = 1; i <= maxel; i++) {
            // even
            if (freqEven[i] > firstmaxevenFreq) {
                secondMaxEven = firstMaxEven;
                secondmaxevenFreq = firstmaxevenFreq;
                firstMaxEven = i;
                firstmaxevenFreq = freqEven[i];
                ;
            } else if (freqEven[i] > secondmaxevenFreq) {
                secondMaxEven = i;
                secondmaxevenFreq = freqEven[i];
            }

            // odd
            if (freqOdd[i] > firstmaxoddFreq) {
                secondMaxOdd = firstMaxOdd;
                secondmaxoddFreq = firstmaxoddFreq;
                firstMaxOdd = i;
                firstmaxoddFreq = freqOdd[i];
                ;
            } else if (freqOdd[i] > secondmaxoddFreq) {
                secondMaxOdd = i;
                secondmaxoddFreq = freqOdd[i];
            }

        }

        // both numbers are not same: that is no problem with same alternating(odd,even)
        // elemets
        if (firstMaxEven != firstMaxOdd) {
            return n - (freqEven[firstMaxEven] + freqOdd[firstMaxOdd]);
        }

        // adjacent elemets can be same so use the secondmax
        int choice1 = n - (freqEven[firstMaxEven] + freqOdd[secondMaxOdd]);
        int choice2 = n - (freqEven[secondMaxEven] + freqOdd[firstMaxOdd]);
        return Math.min(choice1, choice2);

    }

    // Question 3 : 2171. Removing Minimum Number of Magic Beans
    public long minimumRemoval(int[] beans) {
        Arrays.sort(beans);
        int n = beans.length; // taking int causes a problem

        long ans = Long.MAX_VALUE, sum = 0;
        for (int el : beans)
            sum += el;

        for (int i = 0; i < n; i++) {
            long changes = sum - ((n - i * 1l) * beans[i]); // all number are onverted to b=beasn[i];

            ans = Math.min(ans, changes);
        }

        return ans;
    }

    // Question4 :

    class Pair {
        int hashCode;
        int idx;
        int[] arr;

        Pair(int idx, int[] arr) {
            this.idx = idx;
            this.arr = Arrays.copyOfRange(arr, 0, arr.length);
            this.hashCode = Objects.hash(idx, this.arr);
        }

        @Override
        public boolean equals(Object obj) {
            Pair o = (Pair) obj;

            if (this == obj)
                return true;
            if (obj == null)
                return false;

            if (getClass() != obj.getClass())
                return false;

            if (this.idx == o.idx && Arrays.equals(this.arr, o.arr))
                return true;
            return false;
        }

        @Override
        public int hashCode() {
            return this.hashCode;
        }
    }

    HashMap<Pair, Integer> dp;

    // Approach 1: TLE: need to save the states of idx, slots array
    int solve1(int[] arr, int idx, int[] slots, int ns) {
        if (idx >= arr.length)
            return 0;
        // search
        // for(Pair key : dp.keySet()){
        // if(key.idx == idx && Arrays.equals(key.arr,slots)) return dp.get(key);
        // }
        Pair check = new Pair(idx, slots); //produces same hash
        if (dp.containsKey(check))
            return dp.get(check);

        int max = -1;
        // try for each slot each number
        for (int i = 1; i <= ns; i++) {
            if (slots[i] < 2) { // not filled completely yet
                slots[i]++;
                int ans = (arr[idx] & i) + solve1(arr, idx + 1, slots, ns);
                max = Math.max(max, ans);
                slots[i]--;
            }
        }

        dp.put(new Pair(idx, slots), max); // not working
        return max;
    }

}
