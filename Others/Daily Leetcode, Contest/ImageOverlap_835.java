public class ImageOverlap_835 {
    public int largestOverlap(int[][] A, int[][] B) {
        // return largestOverlap_DiffernceCordinates(A,B);
        return largestOverlap_DiffernceCordinatesOptimized(A, B);
        // return largestOverlapConvultaion(A,B);
    }

    // O(N^3) : worst case if hashMap(String is used) :
    // But is slower than convulation

    private int largestOverlap_DiffernceCordinates(int[][] A, int[][] B) {
        if (A == null || A.length == 0 || A[0].length == 0)
            return 0;
        int n = A.length;
        List<int[]> p1 = new ArrayList<int[]>();
        List<int[]> p2 = new ArrayList<int[]>();

        // n*n
        for (int i = 0; i < n * n; i++) {
            if (A[i / n][i % n] == 1)
                p1.add(new int[] { i / n, i % n });
            if (B[i / n][i % n] == 1)
                p2.add(new int[] { i / n, i % n });
        }
        // N worst case
        Map<String, Integer> diffMap = new HashMap<String, Integer>();
        int max = 0;

        // n*n*n
        for (int[] a : p1) {
            for (int[] b : p2) { // n*n
                String diff = (a[0] - b[0]) + " " + (a[1] - b[1]); // unique
                if (diffMap.containsKey(diff))
                    diffMap.put(diff, diffMap.get(diff) + 1);
                else
                    diffMap.put(diff, 1);
                max = Math.max(max, diffMap.get(diff));
            }
        }

        return max;
    }

    private int largestOverlap_DiffernceCordinatesOptimized(int[][] A, int[][] B) {
        if (A == null || A.length == 0 || A[0].length == 0)
            return 0;
        int n = A.length;
        List<Integer> p1 = new ArrayList<>();
        List<Integer> p2 = new ArrayList<>();

        // n*n

        // why 100 and not n for normal encoding
        // Sol: use some arbitary number > 2N-1 ( as 2n-1 as the possible slides of
        // row,col)

        // Explanation:
        {// already discussed in colvultaion :

            // max shiting for +ve vals of x , y => N-1
            // max shifting fot -ve vals of x and y => N-1
            // total => 2N-1 shifting(as 0 Counted in both) => range => [-N to N-1] //0
            // counted in both

            // why100? 100 is big enough and very clear.
            // For example, If I slide 13 rows and 19 cols, it will be 1319.
            // why not 30? 30 is not big enough.
            // For example: 409 = 13 * 30 + 19 = 14 * 30 - 11.
            // 409 can be taken as sliding "14 rows and -11 cols" or "13 rows and 19 cols"
            // at the same time.
            // How big is enough? It should be bigger than 2N-1
            // Can we replace i / N * 100 + i % N by i? No, it's wrong.
        }
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1)
                    p1.add(i * 100 + j);
                if (B[i][j] == 1)
                    p2.add(i * 100 + j);
            }

        // System.out.println(p1+" "+p2);

        Map<Integer, Integer> diffMap = new HashMap<>();
        int max = 0;

        // failing as diff between encoded points(multiple) can be same
        for (int a : p1)
            for (int b : p2) {
                int diff = a - b;
                diffMap.put(diff, diffMap.getOrDefault(diff, 0) + 1);
            }
        // System.out.println(diffMap);
        for (int val : diffMap.values())
            max = Math.max(max, val);

        return max;
    }

    // Using convulation:
    // If you had the two pictures in your hand, what's the most intuitive thing to
    // do? Move them around until you get the most overlap. This is what the code
    // does:

    // Move the second image around starting from the bottom right corner to the top
    // left hand corner
    // For each move, see whats the overlap
    // Return the highest value

    // O(N^4)
    private int largestOverlapConvultaion(int[][] A, int[][] B) {
        final int N = A.length;
        int largest = 0;

        // max shiting for +ve vals of x , y => N-1
        // max shifting fot -ve vals of x and y => N-1
        // total => 2N shifting => range => [-N to N-1] //0 counted in both

        for (int rowOffset = -N; rowOffset < N; ++rowOffset) {
            for (int colOffset = -N; colOffset < N; ++colOffset) { // N*n
                largest = Math.max(largest, getOverlap(rowOffset, colOffset, A, B));// N*N
            }
        }

        return largest;
    }

    // N*N
    private int getOverlap(int rowOffset, int colOffset, int[][] A, int[][] B) {
        final int N = A.length;
        int overlap = 0;

        for (int row = 0; row < N; ++row) {
            for (int col = 0; col < N; ++col) {
                int r = row + rowOffset;
                int c = col + colOffset;
                if (r < 0 || r >= N || c < 0 || c >= N)
                    continue;

                if (A[row][col] == 1 && B[r][c] == 1)
                    ++overlap;
            }
        }

        return overlap;
    }
}
