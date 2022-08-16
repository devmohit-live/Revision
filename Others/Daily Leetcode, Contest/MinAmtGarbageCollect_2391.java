public class MinAmtGarbageCollect_2391 {
    public int garbageCollection(String[] garbage, int[] travel) {
        int g = 0, m = 0, p = 0, n = garbage.length; // stores count
        int maxIndexG = 0, maxIndexP = 0, maxIndexM = 0; // stores last index

        for (int i = 0; i < n; i++) {
            for (char ch : garbage[i].toCharArray()) {
                if (ch == 'G') {
                    g++;
                    maxIndexG = Math.max(maxIndexG, i);
                } else if (ch == 'P') {
                    p++;
                    maxIndexP = Math.max(maxIndexP, i);
                } else {
                    m++;
                    maxIndexM = Math.max(maxIndexM, i);
                }
            }
        }

        int ans = g + m + p;
        ans += getCost(travel, maxIndexG);
        ans += getCost(travel, maxIndexP);
        ans += getCost(travel, maxIndexM);
        return ans;
    }

    private int getCost(int[] arr, int maxIdx) {
        int sum = 0;
        for (int i = 0; i < maxIdx; i++) {
            sum += arr[i];
        }
        return sum;
    }
}
