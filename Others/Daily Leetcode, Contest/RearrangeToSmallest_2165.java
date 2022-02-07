class RearrangeToSmallest_2165 {
    // LC : 2165 : Weekly Contest 2779 : Q 2

    // https://leetcode.com/problems/smallest-value-of-the-rearranged-number/discuss/1748511/Sort-and-Swap
    public long smallestNumber(long num) {
        int flag = 1;
        if (num < 0)
            flag = -1;
        num = Math.abs(num);
        StringBuilder sb = new StringBuilder(Long.toString(num));
        char[] arr = sb.toString().toCharArray();
        if (arr.length == 1)
            return flag * num;
        Arrays.sort(arr);
        // System.out.println(Arrays.toString(arr));
        long ans = 0;

        if (flag == -1) {
            // negative: smallest negative: largest value in negative : sort in descendig=in
            // order
            // Arrays.sort(arr,Collections.reverseOrder()); : Primitve data type can't be
            // custom sorted
            // reverse teh ascending order array
            reverse(arr);
        } else {
            // +ve number : ascending order first character will be non zero after sorting
            // Arrays.sort(arr);
            // first character should be non zeror
            int i = 0;
            if (arr[0] == '0') {
                while (i < arr.length && arr[i] == '0')
                    i++;
                arr[0] = arr[i];
                arr[i] = '0';
            }

            // System.out.println(Arrays.toString(arr));
        }

        ans = Long.parseLong(new String(arr));
        return flag * ans;
    }

    private void reverse(char[] arr) {
        int i = 0, j = arr.length - 1;
        while (i < j) {
            char tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
    }
}