import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LargetsTime {
    // LC 949
    public String largestTimeFromDigits(int[] A) {
        // uniques indexs should be used as each digit can be used exactly once(indexed
        // wise)
        // i!= j && j!= k && k!=i
        // indexex possible => 0,1,2,3
        // if I know i,j,k I can simply find l as i+j+k+l = 6 => 0+1+2+3
        // why? bcz each index should be used once i = 0, j={1,2,3}
        // j = 2, k={1,3}, k => 3, l can only be 1

        String ans = "";
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                for (int k = 0; k < 4; ++k) {
                    if (i == j || i == k || j == k)
                        continue; // avoid duplicate among i, j & k.
                    // as each digit can be used exactly once(indexed wise)

                    String h = "" + A[i] + A[j], m = "" + A[k] + A[6 - i - j - k], t = h + ":" + m;
                    // hour, minutes, & time.
                    if (h.compareTo("24") < 0 && m.compareTo("60") < 0 && ans.compareTo(t) < 0)
                        ans = t;
                    // hour < 24; minute < 60; update result.
                }
            }
        }
        return ans;
    }

    // *********************** My Follow Ups : *******************************
    // format => 00:00:00 => 23:59:59
    public static void main(String[] args) {
        List<int[]> nums = List.of(new int[] { 1, 2, 3, 4, 5, 5 }, new int[] { 2, 2, 2, 2, 2, 2 },
                new int[] { 5, 5, 5, 5, 5, 5 });
        List<int[]> nums2 = List.of(new int[] { 1, 2, 3, 4, 5, 5, 5, 5 }, new int[] { 2, 2, 2, 2, 2, 2, 2, 2 },
                new int[] { 5, 5, 5, 5, 5, 5, 5, 5 });
        for (int i = 0; i < nums.size(); i++) {
            System.out.println(largestTime2(nums.get(i), 6));
            System.out.println(largestTime3(nums2.get(i), 8));
        }
    }

    public static String largestTime2(int[] arr, int maxPlaces) {
        String ans = "";
        int sum = getSum(maxPlaces - 1);
        for (int i = 0; i < maxPlaces; i++)
            for (int j = 0; j < maxPlaces; j++)
                for (int k = 0; k < maxPlaces; k++)
                    for (int l = 0; l < maxPlaces; l++)
                        for (int m = 0; m < maxPlaces; m++) {

                            if (areIndexesValid(i, j, k, l, m)) {
                                int n = sum - (i + j + k + l + m);
                                String hours = arr[i] + "" + arr[j];
                                String minutes = arr[k] + "" + arr[l];
                                String seconds = arr[m] + "" + arr[n];
                                String curr = hours + ":" + minutes + ":" + seconds;

                                if (hours.compareTo("23") <= 0 && minutes.compareTo("59") <= 0
                                        && seconds.compareTo("59") <= 0 && ans.compareTo(curr) < 0) {
                                    ans = curr;
                                    // System.out.println("Inside FX : "+ans);
                                }
                            }

                        }

        return ans;
    }

    private static String largestTime3(int[] arr, int maxPlaces) {
        // hh:mm:ss:msms
        // 8 places 0+1+2+3+4+5+maxPlaces+7 => 15 + 13 => 28
        // 8th place => 28 - (sum of i,j,k,l,m,n,o)
        String ans = "";
        int sum = getSum(maxPlaces - 1);
        for (int i = 0; i < maxPlaces; i++)
            for (int j = 0; j < maxPlaces; j++)
                for (int k = 0; k < maxPlaces; k++)
                    for (int l = 0; l < maxPlaces; l++)
                        for (int m = 0; m < maxPlaces; m++)
                            for (int n = 0; n < maxPlaces; n++)
                                for (int o = 0; o < maxPlaces; o++) {

                                    if (areIndexesValid(i, j, k, l, m, n, o)) {
                                        int p = sum - (i + j + k + l + m + n + o);
                                        String hours = arr[i] + "" + arr[j];
                                        String minutes = arr[k] + "" + arr[l];
                                        String seconds = arr[m] + "" + arr[n];
                                        String miliSeconds = arr[o] + "" + arr[p];
                                        String curr = hours + ":" + minutes + ":" + seconds + ":" + miliSeconds;

                                        if (hours.compareTo("23") <= 0 && minutes.compareTo("59") <= 0
                                                && seconds.compareTo("59") <= 0 && miliSeconds.compareTo("59") <= 0
                                                && ans.compareTo(curr) < 0) {
                                            ans = curr;
                                            // System.out.println("Inside FX : "+ans);
                                        }
                                    }

                                }

        return ans;
    }

    private static boolean areIndexesValid(int... arr) {
        Set<Integer> set = new HashSet<>();
        for (int el : arr)
            if (!set.add(el))
                return false;
        return true;
    }

    private static int getSum(int n) {
        return (int) (n * 1.0 * (n + 1)) / 2;
    }

}
