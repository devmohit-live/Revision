import java.util.Arrays;

public class BitSet {
    // Leetcode 2166 : Design Bitset : every operation id of O(1) avg
    class Bitset {

        // Time: O(1)avg for each operation
        // Space: O(2n) : O(n)

        StringBuilder sb, rev; // store the reverse : usuful whgile flipping
        int count, size;

        public Bitset(int size) {
            // O(n)
            this.sb = new StringBuilder();
            this.rev = new StringBuilder();
            this.count = 0;
            this.size = size;
            for (int i = 0; i < this.size; i++) {
                sb.append('0');
                rev.append('1');
            }
        }

        // O(1)
        public void fix(int idx) {
            char ch = sb.charAt(idx);
            if (ch == '1')
                return;
            sb.setCharAt(idx, '1');
            rev.setCharAt(idx, '0');
            this.count++;
        }

        // O(1)
        public void unfix(int idx) {
            char ch = sb.charAt(idx);
            if (ch == '0')
                return;
            sb.setCharAt(idx, '0');
            rev.setCharAt(idx, '1');
            this.count--;
        }

        // O(1)
        public void flip() {
            StringBuilder tmp = this.sb;
            this.sb = this.rev;
            this.rev = tmp;
            this.count = this.size - this.count;
        }

        // O(1)
        public boolean all() {
            return this.count == this.size;
        }

        // O(1)
        public boolean one() {
            return this.count > 0;
        }

        // O(1)
        public int count() {
            return this.count;
        }

        // O(n) : considering tostring takes O(n)
        public String toString() {
            return this.sb.toString();
        }
    }
    // Approach 2 : same time ans space: just using 1 array/stringbuilder

    // Tracking isFlip
    class Bitset_FlipBit {

        char bit[];
        boolean isflip = false;
        int count;

        public Bitset_FlipBit(int size) {
            bit = new char[size];
            Arrays.fill(bit, '0');
            count = 0;
        }

        public void fix(int idx) {
            if (!isflip && bit[idx] == '0') {
                count += 1;
                bit[idx] = '1';
            } else if (isflip && bit[idx] == '1') {
                count += 1;
                bit[idx] = '0';

            }
        }

        public void unfix(int idx) {
            if (!isflip && bit[idx] == '1') {
                count -= 1;
                bit[idx] = '0';
            } else if (isflip && bit[idx] == '0') {
                count -= 1;
                bit[idx] = '1';
            }

        }

        public void flip() {
            isflip = !isflip;
            count = bit.length - count;
        }

        public boolean all() {
            return count == bit.length;
        }

        public boolean one() {
            return count > 0;

        }

        public int count() {
            return count;
        }

        public String toString() {

            if (isflip) {
                char b[] = bit.clone();
                for (int i = 0; i < bit.length; i++) {
                    if (b[i] == '1') {
                        b[i] = '0';
                    } else {
                        b[i] = '1';
                    }
                }
                return String.valueOf(b);
            } else
                return String.valueOf(bit);
        }
    }

}
