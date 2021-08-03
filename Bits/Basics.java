public class Basics {

    // set the kth bit
    static void offToOn(int n, int k) {
        int num = (1 << k);
        n = (n | num);
        System.out.println(n);
    }

    // unset the kth bit
    static void onToOff(int n, int k) {
        int num = (1 << k);
        num = (~num);
        int s = (n & num);
        System.out.println(s);
    }

    // log n
    static int countSetBits(int n) {
        int cp = n;
        int count = 0;
        while (cp > 0) {
            count += (cp & 1);
            cp = (cp >>> 1); // >>> so as to always add 0 at start instead of copying msb at start
        }

        return count;
    }

    // karinghan algo
    static int karninghan(int n) {
        int count = 0;
        while (n > 0) {
            count++;
            n = (n & (n - 1));
        }
        return count;
    }

    // Leetcode 338

    // Basiclly in n has x set bits so n-1 will have x-1 set bits
    // so if we have n-1's set bit info(starting from 0) we can say that n will bw
    // having no.of set bits in n-1 +1
    public int[] countBits(int n) {
        int res[] = new int[n + 1];
        for (int i = 1; i <= n; i++)
            res[i] = res[(i & (i - 1))] + 1;
        return res;
    }

    // Leetcode 268
    public int missingNumber(int[] nums) {
        // Since we know we can have number from 0 to n(inclusive) => n+1 number and
        // size of the array is n => 1 no. os missing

        // that is every number will correspond to it's index 0,1,2......n-1,n
        // xoring number with index will lead us to missing number idx corresponding to
        // same number will both be 0
        // since last index in loop for array will bw n-1 so we have to manually check
        // for nth idx, idx=n

        int x = nums.length;
        for (int i = 0; i < nums.length; i++) {
            x ^= i;
            x ^= nums[i];
        }

        return x;
    }

    public static void main(String[] args) {
        int n = 13;
        onToOff(n, 2);
        offToOn(n, 1);
        System.out.println(countSetBits(n));
        System.out.println(karninghan(n));
    }
}
