
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * AlternatePermutations
 * 
 * //Problem : IN BOTH HALF WAY IS IN REVERSE ORDERS : not solved even from
 * LinkeList: sol : try using sort
 */
public class AlternatePermutations {

    public static void main(String[] args) {
        int[] inps = { 1, 2, 3, 4, 5, 6, 7, 9, 10 };
        for (int el : inps) {
            System.out.println("\nN is " + el);
            List<List<Integer>> ans = generateAlternatePermutations(el);
            display(ans);
            System.out.println("\n");
        }
    }

    private static List<List<Integer>> generateAlternatePermutations(int n) {
        LinkedList<LinkedList<Integer>> ans = new LinkedList<>();
        LinkedList<Integer> start = new LinkedList<>();
        LinkedList<Integer> rev = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            start.add(i + 1);
            rev.add(n - i);
        }
        generateAlternatePermutations_(start, ans, false);
        if (n > 2)
            generateAlternatePermutations_(rev, ans, true);
        // System.out.println(start + " " + rev);

        Collections.sort(ans, (a, b) -> {
            int i = 0;
            while (i < a.size()) {
                if (a.get(i) < b.get(i))
                    return -1;
                else if (a.get(i) > b.get(i))
                    return 1;
                i++;
            }
            return 0;
        });

        return (List) (ans);
    }

    private static void generateAlternatePermutations_(LinkedList<Integer> arr, LinkedList<LinkedList<Integer>> ans,
            boolean isRev) {
        // System.out.println(arr);
        ans.add(new LinkedList<>(arr));
        int n = arr.size();
        if ((n & 1) == 0) {
            // shift
            for (int i = 1; i <= arr.size() - 1; i++) {
                int first = arr.removeFirst();
                arr.addLast(first);
                // System.out.println(arr);
                ans.add(new LinkedList<>(arr));

            }
        } else {
            // swaps
            int i = 2, j = n - 2;
            while (i < j) { // why this patter : to get things in lexo order
                int first = arr.get(i), second = arr.get(j);
                arr.set(i, second);
                arr.set(j, first);
                i++;
                j--;
                ans.add(new LinkedList<>(arr));

            }

        }

    }

    private static void display(List<List<Integer>> ans) {
        for (List<Integer> small : ans)
            System.out.println(small);
    }
}