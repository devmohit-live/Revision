import java.util.Scanner;

/**
 * The approach thaught
 */

public class CycleCLass {
    public static Scanner scn = new Scanner(System.in);

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // All Variable
    public static int getCycleLen(ListNode mp) {
        int cycleLen = 1;
        ListNode curr = mp.next;

        while (curr != mp) {
            curr = curr.next;
            cycleLen++;
        }

        return cycleLen;
    }

    public static ListNode cycleVariable(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow)
                break;
        }

        if (slow != fast)
            return null;

        slow = head;
        ListNode mp = fast; // meetingPoint
        int cycleCount = 0;
        int A = 0;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;

            if (mp == fast)
                cycleCount++;
            A++;
        }

        int cycleLen = getCycleLen(mp);
        int m = 0, C = 0, B = 0;
        if (A != 0 && A % cycleLen == 0) {
            m = cycleCount - 1;
            B = cycleLen;
        } else {
            m = cycleCount + 1;
            C = A - cycleCount * cycleLen;
            B = cycleLen - C;
        }

        System.out.printf("A = %d , B = %d , C = %d , m = %d , Cycle length = %d \n", A, B, C, m, cycleLen);
        return slow;
    }

    public static ListNode takeInput() {
        int n = scn.nextInt();
        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy;
        while (n-- > 0) {
            prev.next = new ListNode(scn.nextInt());
            prev = prev.next;
        }
        int idx = scn.nextInt();
        if (idx >= 0) {
            ListNode curr = dummy.next;
            while (idx-- > 0) {
                curr = curr.next;
            }
            prev.next = curr;
        }
        return dummy.next;
    }

    public static void main(String[] args) {

        // ListNode h1 = takeInput();
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(4);
        ListNode f = new ListNode(4);
        ListNode g = new ListNode(4);
        ListNode h = new ListNode(4);
        ListNode i = new ListNode(4);
        ListNode j = new ListNode(4);
        ListNode k = new ListNode(4);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;
        g.next = h;
        h.next = i;
        i.next = j;
        j.next = k;
        k.next = e;

        // ListNode h3 = null;
        // ListNode h4 = null;
        // ListNode h5 = null;
        System.out.println("Class Approach : ");
        cycleVariable(a);

        ListNode h1 = takeInput();
        cycleVariable(h1);

        ListNode w = new ListNode(1);
        ListNode x = new ListNode(1);
        ListNode y = new ListNode(1);
        ListNode z = new ListNode(1);
        w.next = x;
        x.next = y;
        y.next = z;
        z.next = w;

        cycleVariable(w);

    }

}
