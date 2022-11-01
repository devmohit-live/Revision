public class SearchIn2dMatrix {
    static boolean findTargetInMatrix(ArrayList<ArrayList<Integer>> mat, int m, int n, int target) {
        return binarySearch(mat, target);
        // return pointerApproach(mat,m,n,target);

    }

    private static boolean pointerApproach(ArrayList<ArrayList<Integer>> mat, int m, int n, int target) {
        if (n == 0 || m == 0)
            return false;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (mat.get(i).get(j) == target)
                return true;
            if (mat.get(i).get(j) > target)
                j--;
            else
                i++;
        }
        return false;
    }

    private static boolean binarySearch(ArrayList<ArrayList<Integer>> arr, int target) {
        int si = 0, n = arr.size(), m = arr.get(0).size(), ei = m * n - 1;
        while (si <= ei) {
            int mid = si + (ei - si) / 2;
            int r = mid / m, c = mid % m;
            if (arr.get(r).get(c) == target)
                return true;
            else if (arr.get(r).get(c) > target)
                ei = mid - 1;
            else
                si = mid + 1;
        }
        return false;
    }
}
