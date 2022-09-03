import java.util.LinkedList;

/**
 * NumberWithSameConseqDiff_967
 */
public class NumberWithSameConseqDiff_967 {
    // lc 967
    public int[] numsSameConsecDiff(int n, int k) {
        LinkedList<Integer> q = new LinkedList<>();
        for (int i = 1; i < 10; i++)
            q.addLast(i);

        int level = 1; // level signifies no od digits in the number present at current level, ex: 123
                       // => 3 digit number 3 level;

        while (!q.isEmpty() && level < n) {
            int size = q.size();
            while (size-- > 0) {
                int rm = q.removeFirst();
                addValidNumbers(q, rm, k);
            }
            level++;
        }

        int[] ans = new int[q.size()];
        int idx = 0;
        while (!q.isEmpty()) {
            ans[idx++] = q.removeFirst();
        }

        return ans;
    }

    // Making a number: absolute difference between every two consecutive digits is
    // k
    // ie operations valid => currNo + k , or currNo - k or both
    // such that the number should be valid
    // when op1(curr+k) : when curr+k < 10 && k>=0 (equals to 0 is also valid here)
    // op2 : when k>0 and k-curr >=0

    private void addValidNumbers(LinkedList<Integer> q, int num, int k) {
        int lastDigit = num % 10;
        int newLastDigit = -1, newDigit = -1;
        if (k >= 0 && lastDigit + k < 10) {
            newLastDigit = lastDigit + k;
            newDigit = num * 10 + newLastDigit;
            q.addLast(newDigit);
        }
        if (k > 0 && lastDigit - k >= 0) {
            newLastDigit = lastDigit - k;
            newDigit = num * 10 + newLastDigit;
            q.addLast(newDigit);
        }
    }
    
}