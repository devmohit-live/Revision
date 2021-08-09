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

    public static int equalSet(int[] arr, int idx, int sum, ArrayList<ArrayList<Integer>> ans) {
        if (idx == arr.length) {
            int sum1 = 0;
            Set<Integer> set1 = new HashSet<Integer>(ans.get(0));
            Set<Integer> set2 = new HashSet<Integer>(ans.get(1));
            for (int i = 0; i < arr.length; i++) {
                if (set1.contains(arr[i]))
                    sum1 += arr[i];
                else
                    set2.add(arr[i]);
            }
            if (sum1 == sum) {
                System.out.println(set1 + " = " + set2);
                return 1;
            }
            return 0;
        }
        int count = 0;
        ArrayList<Integer> set1 = ans.get(0);
        ArrayList<Integer> set2 = ans.get(1);
        for (int i = idx; i < arr.length; i++) {
            set1.add(arr[idx]);
            count += equalSet(arr, idx + 1, sum, ans);
            set1.remove(set1.size() - 1);

        }
        return count;
    }

    public static int equalSet(int[] arr) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < 2; i++)
            ans.add(new ArrayList<>());

        int sum = 0;
        for (int ele : arr)
            sum += ele;

        if ((sum & 1) != 0)
            return 0;

        int sols = equalSet(arr, 0, sum / 2, ans);
        System.out.println(ans);
        return sols;
    }

    static int kSumPartition(int[] arr, int idx, int sum, int ssf, String ans) {
        if (idx == arr.length || sum == ssf) {
            if (sum == ssf) {
                System.out.println("{ " + ans + " }");
                return 1;
            }
            return 0;
        }

        int count = 0;
        count += kSumPartition(arr, idx + 1, sum, ssf + arr[idx], ans + arr[idx] + " ");
        count += kSumPartition(arr, idx + 1, sum, ssf, ans);
        return count;
    }

    static void kSumSol(int[] arr, int k) {
        int s = 0;
        for (int i : arr)
            s += i;
        if (s % k != 0)
            return;
        int sum = s / k;
        System.out.println(kSumPartition(arr, 0, sum, 0, ""));
    }

    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, 40, 50, 60, 70, 80 };
        // equalSet(arr, 0, 0, " ", 0, ""); //-> creates mirror image

        int sol = equal(arr, 1, 10, "10 ", 0, ""); // -> fixed first value positin to avoid mirror image
        System.out.println(sol + " answers");
        System.out.println("Arraylist");
        System.out.println(equalSet(arr));
        // int[] a = { 1, 2, 3, 4, 5, 6 };
        // kSumSol(a, 3);
    }

}