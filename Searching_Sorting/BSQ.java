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

    // Lc 875 : Kokok Eating Bananas
    public int minEatingSpeed(int[] piles, int h) {
        if (piles == null || piles.length == 0 || h < piles.length)
            return -1;

        int si = 1, ei = maximum(piles);
        while (si < ei) {
            int mid = si + (ei - si) / 2;
            if (isValidSpeed(piles, mid, h))
                ei = mid;
            else
                si = mid + 1;
        }

        return ei;
    }

    private int maximum(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int el : arr)
            max = Math.max(max, el);
        return max;
    }

    private boolean isValidSpeed(int[] arr, int speed, int maxTime) {
        int time = 0;

        for (int el : arr) {
            time += (el + speed - 1) / speed; // to avoid ceil part time+=(int)Math.ceil(el*1.0/speed);
        }

        return time <= maxTime;
    }

    // LC 1011. Capacity To Ship Packages Within D Days

    public int shipWithinDays(int[] weights, int days) {
        if (weights == null || weights.length == 0 || days == 0)
            return -1;

        int max = -1, sum = 0;
        for (int el : weights) {
            max = Math.max(max, el);
            sum += el;
        }

        int si = max, ei = sum;
        // System.out.println(si+" "+ei);

        while (si < ei) {
            int mid = si + (ei - si) / 2;
            if (isValidCapacityOfShip(weights, mid, days))
                ei = mid;
            else
                si = mid + 1;
        }

        return ei;
    }

    private boolean isValidCapacityOfShip(int[] arr, int cap, int days) {
        int d = 1, curr = 0; //
        for (int el : arr) {
            if (el > cap)
                return false;

            curr += el;
            if (curr > cap) {
                d++;
                curr = el;
            }
        }

        return d <= days;
    }

}
