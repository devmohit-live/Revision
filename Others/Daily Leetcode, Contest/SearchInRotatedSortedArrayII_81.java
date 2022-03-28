public class SearchInRotatedSortedArrayII_81 {

    //Worst case : O(n), Avg case : O(logn)
    public boolean search(int[] arr, int tar) {
        int n = arr.length, si = 0, ei = n - 1;
        while (si <= ei) {
            int mid = si + (ei - si) / 2;

            if (arr[mid] == tar)
                return true;
            // write this condition first
            else if (arr[si] == arr[mid] && arr[mid] == arr[ei]) {
                // unable to determuine which part to move
                // arr[si] == arr[mid] == arr[ei]
                // ex: [2,2,2,3,2,2,2]
                si++;
                ei--;
            } else if (arr[si] <= arr[mid]) {
                // left part is sorted
                // check if tar lies wiyhin that range or not
                if (arr[si] <= tar && arr[mid] > tar) {
                    // search space: si-> mid
                    ei = mid - 1;
                } else {
                    si = mid + 1;
                }
            } else if (arr[mid] <= arr[ei]) {
                // right part is sorted
                // check if tar lies wiyhin that range or not
                if (arr[mid] < tar && arr[ei] >= tar) {
                    si = mid + 1;
                } else {
                    ei = mid - 1;
                }
            }
        }

        return false; // not found
    }
}
