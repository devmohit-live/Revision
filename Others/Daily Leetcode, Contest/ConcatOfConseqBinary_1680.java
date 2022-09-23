import java.math.BigInteger;

public class ConcatOfConseqBinary_1680 {
    // Article :
    // https://leetcode.com/problems/concatenation-of-consecutive-binary-numbers/discuss/2612207/Java-oror-Explained-in-Detailoror-Fast-O(N)-Solutionoror-Bit-Manipulation-and-Math

    final int MOD = (int) 1e9 + 7;

    public int concatenatedBinary(int n) {
        // return usingMaths(n);
        return usingBits(n);
    }

    private int usingMaths(int n) {
        // Approach:
        // We concatenate by shifting position of result with division and
        // multiplication, then add the number.
        // As there are a lot of repetitions in shifting of positions, it is much less
        // efficient than using bit manipulation.
        long result = 0;
        for (int i = 1; i <= n; i++) {
            // For each i, we shift left the position of result with * 2,
            // while shifting right the position of i with / 2.
            int temp = i;
            while (temp > 0) {
                temp /= 2;
                result *= 2;
            }
            // Add the i to the result and get the remainder of modulo.
            result = (result + i) % MOD;
        }
        return (int) result;
    }

    // Time Complexity: O(N)
    // Space Complexity: O(1

    // As number are continous 1...n
    private int usingBits(int n) {
        // Approach:
        // Using bit manipulation as described below.
        // A bit of description for the bitwise operations used, if you are not
        // familiar.
        // 1. & - Bitwise AND operation:
        // 0 & 0 = 0,
        // 1 & 0 = 0,
        // 1 & 1 = 1.
        // Example : 1101 & 1010 = 1000
        //
        // 2. << - Shift Left operation, by n position:
        // Example:
        // 11 (3) << 2 (n position) = 1100 (14)
        long result = 0;
        // This records the number of binaryDigits we need to shift left.
        int binaryDigits = 0;

        for (int i = 1; i <= n; i++) {

            // If i is a power of 2, we add one additional binaryDigits to shift.
            // Example:
            // i = 8 (1000), i-1 = 7 (111)
            // i & (i-1) = 1000 & 111 = 0
            // So we know we have increased the binaryDigits from 3 (in 111) to 4 (in 1000).
            if ((i & (i - 1)) == 0) {
                binaryDigits++;
            }

            // With the updated binaryDigits, we now can concatenate i to the result.
            // Each time get the remainder of the result % modulo.
            // Example:
            // i = 2
            // result = 1 (1) << 2 (n position) + 10 (2) = 100 (4) + 10 (2) = 110 (6).
            // i = 3
            // result = 110 (6) << 2 (n position) + 11 (3) = 11000 (24) + 11 (3) = 11011
            // (27).
            //
            result = ((result << binaryDigits) + i) % MOD;
        }
        return (int) result;
    }

    // TLE
    private int usingBigInteger(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            String bs = Integer.toBinaryString(i);
            sb.append(bs);
        }

        // System.out.println(sb.toString());
        // int ans = (int)(Long.parseLong(sb.toString()) % MOD);

        BigInteger num = new BigInteger(sb.toString(), 2);
        BigInteger netVal = num.mod(new BigInteger("" + MOD));
        return netVal.intValue();
    }

    {
        // Approach:
        // the idea is to use bit manipulation to set the current number based on the
        // previous number
        // for example,
        // n = 1, ans = 0b1
        // n = 2 (10), we need to shift 2 bits of the previous ans to the left and add
        // `n`
        // i.e. 1 -> 100 (shift 2 bits to the left) -> 110 (set `10`). ans = 0b110
        // n = 3 (11), we need to shift 2 bits of the previous ans to the left and add
        // `n`
        // i.e 110 -> 11000 (shift 2 bits to the left) -> 11011 (set `11`). ans =
        // 0b11011
        // n = 4 (100), we need to shift 3 bits of the previous ans to the left and add
        // `n`
        // i.e. 11011 -> 11011000 (shift 3 bits to the left) -> 11011100 (set `100). ans
        // = 0b11011100
        // so now we can see a pattern here
        // we need to shift `l` bits of the previous ans to the left and add the current
        // `i`
        // how to know `l`? it is not difficult to see `x` only increases when we meet
        // power of 2

        // i & (i - 1) means removing the rightmost set bit
        // e.g. 100100 -> 100000
        // 000001 -> 000000
        // 000000 -> 000000
        // after removal, if it is 0, then it means it is power of 2
        // as all power of 2 only contains 1 set bit
        // if it is power of 2, we increase the bit length `l`
        // if ((i & (i - 1)) == 0) l += 1;
        // (ans << l) means shifting the orginal answer `l` bits to th left
        // (x | i) means using OR operation to set the bit
        // e.g. 0001 << 3 = 0001000
        // e.g. 0001000 | 0001111 = 0001111
        // ans = ((ans << l) | i) % M;
    }
}
