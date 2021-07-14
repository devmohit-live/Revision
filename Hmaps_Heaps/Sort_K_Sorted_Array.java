public class Sort_K_Sorted_Array {
    /*
     * Works only in case pf arrays of m*n , not on list as in lists each row is of
     * different size, for that use Merge K SOrted Lists
     */
    // 2d array to 1d mapping -> (n*m)
    // Encode to a single idx for 1 d : (i,j) -> i*m + j
    // Decode into i,j of 2d array x -> i = x/m, j=x%m

    public static void sortKsortedArray(int[][] arr) {
        int n = arr.length, m = arr[0].length;

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            // getting decode i,j
            int r1 = a / m, c1 = a % m, r2 = b / m, c2 = b % m;
            return arr[r1][c1] - arr[r2][c2];
        });

        for (int i = 0; i < n; i++) {
            // adding starting cluster address of each cluster(1 -d array)
            pq.add(i * m + 0);
        }

        int[] ans = new int[n * m];
        int idx = 0;

        while (pq.size() != 0) {
            Integer eidx = pq.remove(); // encoded index
            int r = eidx / m, c = eidx % m; // decoding to i,j
            ans[idx++] = arr[r][c];

            // going to next element of arr[r] (same row next column)
            c++;
            // checking if c< no of columns
            if (c < m)
                pq.add(r * m + c); // adding encoded address of that element
        }

        // At a time there would be max m(no. of columns) element will be there in PQ
        for (int ele : ans)
            System.out.print(ele + " ");
    }

}
