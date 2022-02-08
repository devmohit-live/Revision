import java.util.*;
//Time: The time complexity of this approach is till an open problem known as the Dirichlet divisor problem.

//space : O(max(n))
public class MaxPairGCD {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter size of an array");
        int n = sc.nextInt();
        System.out.println("Enter " + n + " elemets");
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        long start, end;

        start = System.nanoTime();
        approach1(arr);
        end = System.nanoTime();
        System.out.println("Approach 1 takes : " + (end - start) + " nanoseconds");

        start = System.nanoTime();
        approach2(arr);
        end = System.nanoTime();
        System.out.println("Approach 2 takes : " + (end - start) + " nanoseconds");

    }

    // Space: O(max(n))
    public static void approach1(int[] arr) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }

        int[] freq = new int[max + 1]; // num based indexing instead of searching woth 0 based => num-1(HashMap can be
                                       // used to decrease runtime)
        for (int i : arr) {
            freq[i]++;
        }

        for (int i = max; i >= 2; i--) {
            int j = i;
            int counter = 0;
            // within the range
            while (j <= max) {
                if (freq[j] > 0) {
                    counter += freq[j];
                }
                j += i; // going to next multiple
                // if number is repeated or it's multiple existed
                if (counter >= 2) {
                    System.out.println("Pairs with max gcd are " + i + " and  " + (j - i) + " with gcd of " + i);
                    return;
                }
            }
        }
        System.out.println("GCD is 1");
        return;
    }

    // Space : O(n) and reduced some unneccessary iterations but have to use treemap
    // of log n opeartion so time complexity will be increased so it is better to
    // use approach 1 where lookup takes only O(1) time

    public static void approach2(int[] arr) {
        // as we need to iterate from highest to lowest value

        // log(n) average time
        Map<Integer, Integer> map = new TreeMap<Integer, Integer>(Collections.reverseOrder());

        int high = Integer.MIN_VALUE;

        for (int i : arr) {
            high = Math.max(high, i);
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (int i : map.keySet()) {
            int j = i;
            int counter = 0;

            while (j <= high) {
                if (map.getOrDefault(j, 0) > 0)
                    counter += map.get(i);

                j += i;

                if (counter >= 2) {
                    System.out.println("Pairs with max gcd are " + i + " and  " + (j - i) + " with gcd of " + i);
                    return;
                }

            }
        }
        System.out.println("GCD is 1");
    }

}