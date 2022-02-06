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

}
