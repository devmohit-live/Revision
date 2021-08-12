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
        int sols = ksubsets(arr, 0, sumArray, ans);
        res.stream().forEach(a -> System.out.println(a));
    }

    public static int ksubsets(int[] arr, int idx, int[] subsetSum, ArrayList<ArrayList<Integer>> ans) {
        if (idx == arr.length) {
            int sum = subsetSum[0];
            // if all the sets make same sum then we got the answer
            for (int s : subsetSum)
                if (s != sum)
                    return 0;

            // else we got the answer so print the sets
            for (ArrayList<Integer> set : ans)
                System.out.print(set + " ");
            System.out.println();

            return 1;
        }

        int count = 0;

        for (int i = 0; i < subsetSum.length; i++) {
            // get the current list
            ArrayList<Integer> set = ans.get(i);
            set.add(arr[idx]);
            subsetSum[i] += arr[idx];

            count += ksubsets(arr, idx + 1, subsetSum, ans);

            subsetSum[i] -= arr[idx];
            set.remove(set.size() - 1);
            // if we removed the leader from an element we have to stop it going to next
            // empty set to avoid mirror image
            if (set.size() == 0)
                break; // stop making a recursive call for this next empty set -> move to next element

        }
        return count;
    }

    static int counter = 1;

    public static int kPartitionUsingArray(int[] arr, int idx, int n, ArrayList<ArrayList<Integer>> ans) {
        if (idx == arr.length) {

            // check if any set in ans is empty
            // for (ArrayList<Integer> set : ans) {
            // // as we are also getting the case were every element was in 1st set ans rest
            // // set were empty
            // // also we were getting some cases where atlest 1 set remoins empty
            // if (set.size() == 0)
            // return 0;
            // }

            // if any list is empty then we havn't got the answer
            // just check the last list is empty or not as we are coming from list1 to k,
            if (ans.get(ans.size() - 1).size() == 0)
                return 0;

            System.out.print(counter++ + ". ");
            for (ArrayList<Integer> set : ans)
                System.out.print(set + " ");
            System.out.println();

            return 1;
        }

        int count = 0;
        for (int i = 0; i < ans.size(); i++) {
            ArrayList<Integer> set = ans.get(i);
            set.add(arr[idx]);

            count += kPartitionUsingArray(arr, idx + 1, n, ans);

            set.remove(set.size() - 1);

            if (set.size() == 0)
                break;

        }

        return count;
    }

    public static int kPartition(int currNum, int n, ArrayList<ArrayList<Integer>> ans) {
        if (currNum > n) {
            if (ans.get(ans.size() - 1).size() == 0)
                return 0;

            System.out.print(counter++ + ". ");
            for (ArrayList<Integer> set : ans)
                System.out.print(set + " ");
            System.out.println();

            return 1;
        }

        int count = 0;
        for (int i = 0; i < ans.size(); i++) {
            ArrayList<Integer> set = ans.get(i);
            set.add(currNum);

            count += kPartition(currNum + 1, n, ans);

            set.remove(set.size() - 1);

            if (set.size() == 0)
                break;

        }

        return count;
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