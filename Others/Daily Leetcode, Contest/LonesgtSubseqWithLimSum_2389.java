public class LonesgtSubseqWithLimSum_2389 {
    public int[] answerQueries(int[] A, int[] queries) {
        // Here arrangemet doesn't matter as we have to find the length(no. of el) req
        // to make sum <= q[i]

        Arrays.sort(A);

        // Why sort : so that we can start with min number for making prefix sum :
        // starting with min numbers will make the lenght to be maximum as we are
        // starting with min numbers to make a given sum

        // prefix sum
        for (int i = 1; i < A.length; i++)
            A[i] = A[i - 1] + A[i];

        int idx = 0, n = queries.length;
        int[] ans = new int[n];

        for (int q : queries) {
            // find the pos of that elemetn in sum : prefix sum is always increasing(sorted)
            int pos = Arrays.binarySearch(A, q);
            ans[idx++] = Math.abs(pos + 1); // length is asked not index so +1
        }

        return ans;
    }
}
