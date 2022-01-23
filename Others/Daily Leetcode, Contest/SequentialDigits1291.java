public class SequentialDigits1291 {

    // The main things to notice here are:
    // 1. dgits have diff of 1 : 12 , 123, 1234(consecutive : given)
    // 2. the next digits have differene in 1->11->111->1111, ex: 1,12,123,1234

    // Approach1:
    // https://leetcode.com/problems/sequential-digits/discuss/1710951/Java-Just-generate-them-all-0ms
    /*
     * t means the number of times we are going to loop this layer (i.e. 12, 23, ..,
     * 89 -> t = 8, 123, 234, ..., 789 -> t = 7)
     * 
     * inc is always 11 or 111 or 1111 depending on digits (i.e. inc = 10 * inc + 1)
     * start marks the starting point of each digit, 1, 12, 123, 1234 (i.e. start =
     * 10 * start + 10 - t)
     */
    public List<Integer> sequentialDigits1(int low, int high) {
        int start = 1, cur = 1, inc = 11, t = 8;
        List<Integer> ans = new ArrayList<>();
        while (cur <= high) {
            for (int i = 0; i < t; i++) {
                cur += inc;
                if (cur >= low && cur <= high)
                    ans.add(cur);
            }
            inc = 10 * inc + 1;
            start = 10 * start + 10 - t;
            cur = start;
            t--;
        }

        return ans;
    }

    // Approach2: Using
    // String:https://thefellowprogrammer.blogspot.com/2020/09/sequential-digits.html
    // not so efficient
    public List<Integer> sequentialDigits2(int low, int high) {
        List<Integer> result = new ArrayList();
        String str = "123456789";
        int i, j, length = str.length(), num;
        // i =2 min seq num formed can be 12
        for (i = 2; i <= length; i++) {
            for (j = 0; j <= length - i; j++) {
                num = Integer.parseInt(str.substring(j, j + i));
                if (num > high)
                    return result;
                if (num >= low)
                    result.add(num);
            }
        }

        return result;
    }

    // Approahc3: bfs
    public List<Integer> sequentialDigits3(int low, int high) {
        // We have to be aware f the digits ending with 9as from that no of digits will
        // increase not the consecutiveness of number
        // Ex: 45->56->67->67->78->89, but 89->123 (bcz here no of digits changed from 2
        // to 3)

        // first add all the numbers from 1 to 9 to perform operations of increments of
        // nimber and digits on them
        List<Integer> ans = new ArrayList<>();
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 1; i <= 9; i++)
            q.addLast(i); // adding intial seeders

        // what bfs is doing:
        /*
         * 1 is adding 12 2 is adding 23 3 is adding 34 4 is adding 45 5 is adding 56 6
         * is adding 67 7 is adding 78 8 is adding 89 12 is adding 123 23 is adding 234
         * 34 is adding 345 45 is adding 456 123 is adding 1234 234 is adding 2345 3456
         * is adding 34567 4567 is adding 45678 34567 is adding 345678 45678 is adding
         * 456789 123456 is adding 1234567
         */

        while (!q.isEmpty()) {
            int rm = q.removeFirst();

            if (rm >= low && rm <= high)
                ans.add(rm);
            int lastdigit = rm % 10;
            if (lastdigit != 9) {
                // add the next consecutive element which have pattern of difference of
                // 1,11,111,111 : rm*10 +rm%10 1
                // read pepconding getting any digits from a number
                int num = rm * 10 + rm % 10 + 1;
                // System.out.println(rm+" is adding "+num);
                q.addLast(num);
            }
        }

        return ans;
    }

    // Approahc 4: Specific to question contraints but amazing:
    // https://leetcode.com/problems/sequential-digits/discuss/451851/Java-Just-a-joke
    public List<Integer> sequentialDigits4(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        // 1->10^9 : write all consecutive numners of d dgits: d=1->9
        int[] nums = { 1, 12, 23, 34, 45, 56, 67, 78, 89, 123, 234, 345, 456, 567, 678, 789, 1234, 2345, 3456, 4567,
                5678, 6789, 12345, 23456, 34567, 45678, 56789, 123456, 234567, 345678, 456789, 1234567, 2345678,
                3456789, 12345678, 23456789, 123456789 };

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= low && nums[i] <= high)
                ans.add(nums[i]);
        }
        return ans;
    }

}
