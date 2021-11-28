import java.util.Arrays;

public class CyclicSort {
    // Apply where the numbers are int the short predefined range
    // 0->n or 1->n

    // Time: Worst = > O(2n) => avg: =O(n)

    // if it is 1(range starts from 1) based the nums should be art nums-1 pos 
    // if it 0 based(range start from 0 => number starts from 0) it is cum should be at arr[num], same position
    static void sort(int[] arr) {
        int n = arr.length;
        // put elemet to it's correct place
        int i = 0;
        while (i < n) {
            int num = arr[i];
            int correctIdx = num - 1; // if sorted
            if (arr[i] != arr[correctIdx]) {
                swap(arr, i, correctIdx);
            } else
                i++;
        }

    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 5, 2, 1, 4 };
        // int[] arr = { 5, 4, 3, 2, 1 };
        // int[] arr = { 1, 2, 3, 4, 5 };
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    // LC 268
    public int missingNumber(int[] nums) {
        // this can also be done using xor
        // xor of xor^i ans xor^nums[i] will cross eaxh other if at correct position

        // 0 to n : predefined range and number starts from 0 : cyclic sort variation2
        cyclicSort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i])
                return i;

            // if all elements are at their correct position that means the n(last ele) is
            // not plcaes
            // since array length is n contains n-1 elements

        }
        return nums.length;
    }

    private void cyclicSort(int[] arr) {
        int i = 0, n = arr.length;
        while (i < n) {
            int num = arr[i];
            int correctIdx = num; // 0 based : same idx
            // avoid when num == n as it will give out of bound 
            if (arr[i] != n && arr[i] != arr[correctIdx])
                swap(arr, i, correctIdx);
            else
                i++;
        }

    }

}