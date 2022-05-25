public class BSQ {
    // Painters Parttio's Prolem :
    // https://www.interviewbit.com/problems/painters-partition-problem/
    private final int MOD = 10000003;

    public int paint(int A, int B, int[] C) {
        int n = C.length, max = -1, sum = 0;
        for (int el : C) {
            sum += el;
            max = Math.max(max, el);
        }

        long si = max, ei = sum, mid = 0;
        while (si < ei) {
            mid = si + (ei - si) / 2;
            if (isValid(C, mid, A))
                ei = mid;
            else
                si = mid + 1;
        }
        long ans = (ei % MOD * B % MOD);
        return (int) (ans % MOD);
    }

    private boolean isValid(int[] arr, long workPerPerson, int painters) {
        int c = 1, s = 0;

        for (int el : arr) {
            s += el;
            if (s > workPerPerson) {
                s = el;
                c++;
            }
        }

        return c <= painters;
    }

    // 1283. Find the Smallest Divisor Given a Threshold
    public int smallestDivisor(int[] nums, int threshold) {
        int max = 0;
        for (int el : nums)
            max = Math.max(max, el);
        int si = 1, ei = 2 * max;
        while (si < ei) {
            int mid = si + (ei - si) / 2;
            if (isValid(nums, mid, threshold))
                ei = mid;
            else
                si = mid + 1;
        }

        return ei;
    }

    private boolean isValid(int[] arr, int divisor, int threshold) {
        int sum = 0;
        for (int el : arr)
            sum += ((el + divisor - 1) / divisor); // replacememt for sum+=(int)Math.ceil(el*1.0 / divisor)
        return sum <= threshold;
    }

}
