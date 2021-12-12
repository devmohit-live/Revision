package Questions.Graph;

import java.util.List;

public class MColoring {
    public static boolean graphColoring(List<Integer>[] G, int[] color, int i, int C) {
        int n = G.length;
        return isPossibleToColor(G, i, n, color, C);
    }

    private static boolean isPossibleToColor(List<Integer>[] G, int src, int n, int[] colors, int M) {
        if (src == n)
            return true; // we have reached the end node after marking all node within m colors(included)

        // try all colors on this node
        for (int i = 1; i <= M; i++) {
            if (isSafe(G, colors, i, src)) {
                colors[src] = i; // mark
                // ask to next node
                boolean rec = isPossibleToColor(G, src + 1, n, colors, M);
                if (rec)
                    return true;
                // else
                colors[src] = 0; // umark
            }
        }

        return false;
    }

    private static boolean isSafe(List<Integer>[] G, int[] colors, int color, int node) {
        for (int nbr : G[node]) {
            if (colors[nbr] == color) // if any adjacent neighbour contains same color then it is not possible
                return false;
        }

        return true;
    }
}
