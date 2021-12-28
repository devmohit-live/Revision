import java.util.Arrays;

public class FloyydWarshall {
    // Step: convert it into 2d matrix representation

    public static int[][] floyydwarshall(int n, int[][] edges) {
        int[][] mat = new int[n][n];
        for (int[] d : mat)
            Arrays.fill(d, (int) 1e9);

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            // System.out.println(u + "->" + v + " @ " + w);
            mat[u][v] = w;
            mat[v][u] = w; // for bi derctional
        }
        for (int i = 0; i < n; i++)
            mat[i][i] = 0;

        // System.out.println("Matrix is : ");
        // Arrays.stream(mat).forEach(ar -> System.out.println(Arrays.toString(ar)));
        // System.out.println("\n");

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][k] != (int) 1e9 && mat[k][j] != (int) 1e9 && mat[i][j] > mat[i][k] + mat[k][j])
                        mat[i][j] = mat[i][k] + mat[k][j];
                    // or
                    // mat[i][j] = Math.min(mat[i][j], mat[i][k]+mat[k][j]); // hadles the if
                    // comdition
                }
            }
        }

        // printing the distance

        for (int i = 0; i < n; i++) {
            System.out.print("From " + i + " -> ");
            for (int j = 0; j < n; j++) {
                if (i != j)
                    System.out.print(j + "@" + mat[i][j] + " ");
            }
            System.out.println();
        }

        return mat;
    }

    // Question based on floyyd warshall:
    // 1334. Find the City With the Smallest Number of Neighbors at a Threshold
    // Distance
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] mat = floyydwarshall(n, edges);
        int[] connectedTo = new int[n];
        int ans = -1;
        int mincities = (int) 1e9;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && mat[i][j] <= distanceThreshold)
                    connectedTo[i]++;
            }
        }

        for (int i = 0; i < n; i++) {
            if (connectedTo[i] < mincities) {
                mincities = connectedTo[i];
                ans = i;
            } else if (connectedTo[i] == mincities)
                ans = Math.max(i, ans);
        }

        return ans;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] edges = { { 0, 1, 3 }, { 1, 2, 1 }, { 1, 3, 4 }, { 2, 3, 1 } };
        floyydwarshall(n, edges);
    }

}
