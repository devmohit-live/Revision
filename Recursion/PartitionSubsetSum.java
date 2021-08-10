import java.util.*;

public class PartitionSubsetSum {

    // 2 set of equal sum
    static int equal(int[] arr, int idx, int sum1, String set1, int sum2, String set2) {

        if (idx == arr.length) {
            if (sum1 == sum2) {
                System.out.println(set1 + " = " + set2);
                return 1;
            }
            return 0;
        }

        int count = 0;
        count += equal(arr, idx + 1, sum1 + arr[idx], set1 + arr[idx] + " ", sum2, set2);
        count += equal(arr, idx + 1, sum1, set1, sum2 + arr[idx], set2 + arr[idx] + " ");
        return count;
    }

    public static int ksubsets(int[] arr, int idx, int sum, int[] subsetSum, ArrayList<ArrayList<Integer>> ans) {
        if (idx == arr.length) {
            int s = subsetSum[0];
            if (s != sum)
                return 0;
            // checking for other k-1 sums to be same
            for (int ele : subsetSum) {
                if (s != ele) {
                    return 0;
                }
            }
            for (ArrayList<Integer> a : ans) {
                System.out.print(a + " ");
            }
            System.out.println();

            return 1;
        }
        int count = 0;
        for (int k = 0; k < subsetSum.length; k++) {
            ArrayList<Integer> set = ans.get(k);
            set.add(arr[idx]);
            subsetSum[k] += arr[idx];

            count += ksubsets(arr, idx + 1, sum, subsetSum, ans);

            subsetSum[k] -= arr[idx];
            set.remove(set.size() - 1);
            if (set.size() == 0)
                break;
        }

        return count;
    }

    // ArrayLists
    public static int ksubsets2(int[] arr, int idx, int sum, int[] subsetSum, ArrayList<ArrayList<Integer>> ans,
            List<List<List<Integer>>> res) {
        if (idx == arr.length) {
            int s = subsetSum[0];
            if (s != sum)
                return 0;

            for (int ele : subsetSum) {
                if (s != ele) {
                    return 0;
                }
            }
            List<List<Integer>> small = new ArrayList<>();
            for (ArrayList<Integer> a : ans) {
                small.add(new ArrayList<>(a));
            }
            res.add(small);

            return 1;
        }
        int count = 0;
        for (int k = 0; k < subsetSum.length; k++) {
            ArrayList<Integer> set = ans.get(k);
            set.add(arr[idx]);
            subsetSum[k] += arr[idx];

            count += ksubsets2(arr, idx + 1, sum, subsetSum, ans, res);

            subsetSum[k] -= arr[idx];
            set.remove(set.size() - 1);
            if (set.size() == 0)
                break;
        }

        return count;
    }

    public static void kSet(int[] arr, int k) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < k; i++)
            ans.add(new ArrayList<>());

        int sum = 0;
        for (int ele : arr)
            sum += ele;

        if (sum % k != 0)
            return;

        int[] sumArray = new int[k];
        // System.out.println("Sols = " + ksubsets(arr, 0, sum / k, sumArray, ans));
        List<List<List<Integer>>> res = new ArrayList<>();
        int sols = ksubsets2(arr, 0, sum / k, sumArray, ans, res);
        res.stream().forEach(a -> System.out.println(a));
    }

    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80 };
        // equalSet(arr, 0, 0, " ", 0, ""); //-> creates mirror image

        int sol = equal(arr, 1, 10, "10 ", 0, ""); // -> fixed first value positin to avoid mirror image
        System.out.println(sol + " answers");
        System.out.println("Arraylist");
        kSet(arr, 2);
        // int[] a = { 1, 2, 3, 4, 5, 6 };
        // kSumSol(arr, 2);
    }

}