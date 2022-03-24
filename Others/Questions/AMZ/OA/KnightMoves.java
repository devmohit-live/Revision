package Others.Questions.AMZ.OA;

import java.util.LinkedList;

public class KnightMoves {
    /*
     * A similar question with the number of islands. Use BFS to expand the area
     * from init point in coordinate to 8 directions. Keep tracking currentX,
     * currentY and steps. Once the (currentX, currentY) is equal to target
     * coordinate return steps.
     * 
     */
    class Solution {
        private final int[][] dir = { { 2, 1 }, { 1, 2 }, { -1, 2 }, { -2, 1 }, { -2, -1 },{ -1, -2 }, { 1, -2 }, { 2, -1 } };

        public int minKnightMoves(int x, int y) {
            // board can be -infi to +ifni and to dist from *x,*y to 0,0 is equal to 0,0 ->x,y
            x = Math.abs(x);
            y = Math.abs(y);

            LinkedList<int[]> q = new LinkedList<>();
            q.addLast(new int[] { 0, 0 });

            Set<String> visited = new HashSet<>(); // why set as int[x][y] can be too large and we may not neccesaatly vivit eACH CELL
            visited.add("0,0");

            //bfs without cycle
            int ans = 0;
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    int[] rm = q.removeFirst();
                    int sr = rm[0];
                    int sc = rm[1];
                    // destination
                    if (sr == x && sc == y) {
                        return ans;
                    }

                    for (int[] d : dir) {
                        int r = sr + d[0];
                        int c = sc + d[1];
                        if (!visited.contains(r + "," + c) && r >= -1 && c >= -1) { // within the boundary
                            q.addLast(new int[] { r, c });
                            visited.add(r + "," + c); //mark
                        }
                    }
                }
                ans++;
            }
            return -1;
        }
    }
}
