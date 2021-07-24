import java.util.*;

class Solution {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int caseNum = sc.nextInt();
        for (int ks = 1; ks <= caseNum; ks++) {
            int[][] arr = new int[3][3];
            for (int i = 0; i < 3; i++) {
                arr[i][0] = sc.nextInt();

                if (i != 1)
                    arr[i][1] = sc.nextInt();
                arr[i][2] = sc.nextInt();
            }
            System.out.println(String.format("Case #%d: %s", ks, solve(arr)));
        }
    }

    static int solve(int[][] arr) {
        int count = 0;
        // consts
        if (arr[0][0] + arr[2][0] == 2 * arr[1][0])
            ++count;
        if (arr[0][0] + arr[0][2] == 2 * arr[0][1])
            ++count;
        if (arr[0][2] + arr[2][2] == 2 * arr[1][2])
            ++count;
        if (arr[2][0] + arr[2][2] == 2 * arr[2][1])
            ++count;

        // vars
        HashMap<Long, Integer> map = new HashMap<>();

        double ld = (double) (arr[0][0] + arr[2][2]) / 2;
        if (ld % 1 == 0)
            map.put((long) ld, map.getOrDefault((long) ld, 0) + 1);

        double rd = (double) (arr[0][2] + arr[2][0]) / 2;
        if (rd % 1 == 0)
            map.put((long) rd, map.getOrDefault((long) rd, 0) + 1);

        double mc = (double) (arr[0][1] + arr[2][1]) / 2;
        if (mc % 1 == 0)
            map.put((long) mc, map.getOrDefault((long) mc, 0) + 1);

        double mr = (double) (arr[1][0] + arr[1][2]) / 2;
        if (mr % 1 == 0)
            map.put((long) mr, map.getOrDefault((long) mr, 0) + 1);

        int max = -(int) 1e9;
        boolean found = false;

        for (long k : map.keySet()) {
            if (map.get(k) > max) {
                max = map.get(k);
                found = true;
            }
        }
        // System.out.println("Map is " + map);
        if (found) {
            count += max;
        }
        return count;
    }

}