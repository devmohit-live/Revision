import java.util.ArrayList;

public class Questions {
    // find kth largest element => Time: klogn(average), kn(worst)

    int quickSelect(int[] arr, int k) {
        // largest => from last => n-k
        // smallest => from start => k
        int n = arr.length, idx = n - k;
        quickSelect(arr, 0, n - 1, idx);
        return arr[idx];
    }

    void quickSelect(int[] arr, int si, int ei, int idx) {
        if (si >= ei)
            return;

        int p = partition(arr, si, ei, idx, ei);
        if (p == idx)
            return; // idx is at correct place
        else if (idx > p) {
            // look into right part
            quickSelect(arr, p + 1, ei, idx);
        } else
            quickSelect(arr, si, p - 1, idx);
    }

    void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    int partition(int[] arr, int si, int ei, int idx, int pidx) {
        int p = si - 1, itr = si, data = arr[pidx];
        swap(arr, pidx, ei);
        while (itr <= ei) {
            if (arr[itr] <= data)
                swap(arr, ++p, itr);
            itr++;
        }
        return p;
    }

    public static void targetSumPair(int[] arr, int target) {

        int n = arr.length;
        QuickSort ob = new QuickSort();
        ob.sort(arr);

        int i = 0, j = arr.length - 1;

        while (i < j) {
            int sum = arr[i] + arr[j];
            if (sum == target) {
                System.out.println(arr[i] + ", " + arr[j]);
                i++;
                j--;
            } else if (sum > target)
                j--;
            else
                i++;
        }

    }

    public static void threeSum(int[] arr, int target) {
        QuickSort ob = new QuickSort();
        ob.sort(arr);

        int prev = (int) 1e9;
        for (int i = 0; i < arr.length; i++) {
            int a = arr[i];
            if (a != prev) { // to avoid duplicates
                int subTarget = target - a;

                ArrayList<int[]> sublist = twoSum(arr, subTarget, i + 1, arr.length-1);
                for (int[] ans : sublist)
                    System.out.println(a + " " + ans[0] + " " + ans[1]);

                prev = a;
            }

        }

    }

    private static ArrayList<int[]> twoSum(int[] arr, int target, int si, int ei) {
        if (si >= ei || si >= arr.length)
            return new ArrayList<int[]>();

        int i = si, j = ei;
        ArrayList<int[]> res = new ArrayList<>();

        while (i < j) {
            int sum = arr[i] + arr[j];
            if (sum == target) {
                res.add(new int[] { arr[i], arr[j] });
                i++;
                j--;
            } else if (sum > target)
                j--;
            else
                i++;
        }

        return res;
    }

}
