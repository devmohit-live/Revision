public class FourSum {
    // ?Leetcode 18
    public List<List<Integer>> fourSum(int[] arr, int target) {
        int n = arr.length;
        List<List<Integer>> res = new ArrayList<>();
        if (n <= 3)
            return res;
        Arrays.sort(arr);
        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {

                int tar = target - (arr[i] + arr[j]);
                int front = j + 1, back = n - 1;

                while (front < back) {
                    if (arr[front] + arr[back] < tar)
                        front++;
                    else if (arr[front] + arr[back] > tar)
                        back--;
                    else {
                        List<Integer> small = new ArrayList<>();
                        small.add(arr[i]);
                        small.add(arr[j]);
                        small.add(arr[front]);
                        small.add(arr[back]);

                        res.add(small);
                        int tmpfront = front, tmpback = back;
                        // Handle duplicates for fron and back

                        while (front < back && arr[tmpfront] == arr[front])
                            front++;
                        while (front < back && arr[tmpback] == arr[back])
                            back--;
                    }

                }

                // +1 < n bcz for loop will also update the j and i
                // handle duplicate j's
                while (j + 1 < n && arr[j] == arr[j + 1])
                    j++;
            }

            // handle duplicate i's
            while (i + 1 < n && arr[i] == arr[i + 1])
                i++;

        }

        return res;
    }
}
