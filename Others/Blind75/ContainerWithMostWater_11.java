/**
 * ContainerWithMostWater_11
 */
public class ContainerWithMostWater_11 {

    // Brute force: try every pair and the min heigth from both the end will decide
    // the final height of container and diff b/w their indexes will define the
    // width

    private int solveBrute(int[] ht) {
        int max = 0, min = Integer.MAX_VALUE, n = ht.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int h = Math.min(ht[i], ht[j]);
                int w = j - i;
                max = Math.max(max, h * w);
            }
        }

        return max;
    }

    // Approach : try to move from the idx that has min height in i,j => bcz
    // decreasig witdh will tend to decrease the area we can increase area by
    // increasing the height : treat width as constant
    private int solve2Pointers(int[] ht) {
        int max = 0, min = Integer.MAX_VALUE, n = ht.length, i = 0, j = n - 1;
        while (i < j) {
            int width = j - i;
            int height = Math.min(ht[i], ht[j]);
            max = Math.max(max, width * height);
            if (ht[i] < ht[j])
                i++;
            else
                j--;

        }

        return max;
    }
}