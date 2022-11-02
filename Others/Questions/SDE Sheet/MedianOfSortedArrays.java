import java.util.PriorityQueue;

public class MedianOfSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double ans = -1.0;
        if (nums1 == null || nums2 == null || nums1.length == 0 && nums2.length == 0)
            return ans;
        //brute: create array m+n sort array => m+n log m+n find midean 
        // N : merge arrayas as they are already sorted => m+n, find median 
        // return PQ(nums1,nums2);
        // return oNApproach(nums1,nums2);
        return optimal(nums1, nums2);
    }

    // create an another array os size m+n from these 2 sorted arrays and find media
    // n + m
    private double oNApproach(int[] A, int[] B) {
        int[] merged = mergeTwoSortedArrays(A, B);
        int n = merged.length;
        double ans = 1.0 * merged[n / 2];
        if ((n & 1) == 1)
            return ans;
        else
            return (1.0 * merged[n / 2 - 1] + ans) / 2;
    }

    private int[] mergeTwoSortedArrays(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int[] arr = new int[n + m];
        int i = 0, j = 0, k = 0;
        while (i < n && j < m) {
            if (A[i] <= B[j]) {
                arr[k++] = A[i++];
            } else
                arr[k++] = B[j++];
        }

        while (i < n)
            arr[k++] = A[i++];
        while (j < m)
            arr[k++] = B[j++];

        return arr;
    }

    //Bad Approach : m+n log m+n
    // n+m=>Q , Qlog(Q) + Q/4(log(Q/4))(balacing from left to right max->min)
    // +Q/3(log(q/3))(balacing from right to left max->min) - Q/12(log(q/12))(coomon
    // counted twice)
    private double PQ(int[] A, int[] B) {
        PriorityQueue<Integer> max = new PriorityQueue<>((a, b) -> b - a); // left
        PriorityQueue<Integer> min = new PriorityQueue<>(); // right
        int n = A.length, m = B.length;
        for (int el : A)
            addNum(el, max, min);
        for (int el : B)
            addNum(el, max, min);
        int size = A.length + B.length;

        if ((size & 1) == 1)
            return 1.0 * max.peek();
        else
            return (1.0 * max.peek() + min.peek()) / 2;

    }

    private void addNum(int el, PriorityQueue<Integer> max, PriorityQueue<Integer> min) {
        // left oriented
        if (max.size() == 0 || max.peek() >= el)
            max.add(el);
        else
            min.add(el);

        // balancing
        if (max.size() - min.size() == 2) {
            min.add(max.remove());
        } else if (min.size() - max.size() == 1)
            max.add(min.remove());

        // System.out.print("PQ 1 (max) is " + max+" ");
        // System.out.println("PQ 2 (min) is " + min);
    }


    //Correct
    private double optimal(int[] A, int[] B) {
        int n = A.length, m = B.length;
        // apply bs on smaller Array to execute it faster
        if (n > m)
            return optimal(B, A);

        final int MIN = Integer.MIN_VALUE, MAX = Integer.MAX_VALUE;
        int si = 0, total = (n + m), ei = n; // ruuning bs on A only
        double ans = -1.0;
        while (si <= ei) {
            int cut1 = si + (ei - si) / 2;// mid(for cut in A)
            int cut2 = (total + 1) / 2 - cut1; // cut for B

            // data points
            int left1 = cut1 - 1 < 0 ? MIN : A[cut1 - 1];
            int right1 = cut1 >= n ? MAX : A[cut1];
            int left2 = cut2 - 1 < 0 ? MIN : B[cut2 - 1];
            int right2 = cut2 >= m ? MAX : B[cut2];

            // correct point situation
            if (left1 <= right2 && left2 <= right1 && Math.max(left1, left2) <= Math.min(right1, right2)) { // other
                                                                                                            // cases
                                                                                                            // will
                                                                                                            // satisfy
                                                                                                            // as arrays
                                                                                                            // ara
                                                                                                            // sorted

                if ((total & 1) == 1)
                    ans = Math.max(left1, left2) * 1.0;
                else
                    ans = (Math.max(left1, left2) * 1.0 + Math.min(right1, right2)) / 2;
                break;
            } else if (left1 > right2) {
                // shrink left part //shrink cut1
                ei = cut1 - 1;
            } else
                si = cut1 + 1;

        }

        return ans;
    }
}
