import java.util.*;

public class SubarryWithXorK {
    // count no. of subarrays with a given XOR
    // https://www.geeksforgeeks.org/count-number-subarrays-given-xor/

    /*
     * Input : arr[] = {4, 2, 2, 6, 4}, m = 6 Output : 4 Explanation : The subarrays
     * having XOR of their elements as 6 are {4, 2}, {4, 2, 2, 6, 4}, {2, 2, 6}, and
     * {6}
     * 
     * Input : arr[] = {5, 6, 7, 8, 9}, m = 5 Output : 2 Explanation : The subarrays
     * having XOR of their elements as 5 are {5} and {5, 6, 7, 8, 9}
     * 
     */

    public int subXors(int[] arr, int k) {
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        // bascially check for :
        // 1 : net xor till now == k => count++
        // 2: net xor till now ^ k exists in map => any in between subarray(not starting
        // from start point(0)) exists int this subarry
        // whose xor is Y such that Y ^ k(req) = Xortillnow =>
        int xor = 0;
        for (int el : arr) {
            xor = (xor ^ el);
            if (xor == k)
                count++; // 1
            map.put(xor, map.getOrDefault(xor, 0) + 1);

            int inBetweenSubarrayXor = (xor ^ k);
            count += map.getOrDefault(inBetweenSubarrayXor, 0);

        }

        return count;
    }
}
