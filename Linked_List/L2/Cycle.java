import java.util.Scanner;

/**
 * My Approach : Experimenting : Strictly based on the startiong and ending
 * position of A,B,C in the LinkedList
 */

// TODO: Hande the case when A = cyclelength then m = m-1 => when slow pointer
// doesn't enter the cycle;

// A = (B+C)*(m-1) + C
// M = cycle count by fast

public class Cycle {
    public static Scanner scn = new Scanner(System.in);

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    static int length(ListNode head) {
        int l = 0;
        if (head == null)
            return l;

        ListNode curr = head;
        while (curr != null) {
            l++;
            curr = curr.next;
        }
        return l;
    }

    public static void findVariables(ListNode head) {
        int A = 0, B = 0, C = 0, M = 0;
        ListNode meetPoint = null;
        ListNode startofCycle = null;
        // int acount = 0, bcount = 0, ccount = 0;
        ListNode slow = head, fast = head;

        // detect cycle and Meet point
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if (fast == slow) {
                meetPoint = slow;
                break;
            }
        }

        slow = head;
        // getting the start of cycle
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        startofCycle = slow;

        // A => [head to start of cycle)
        ListNode itr = head;
        while (itr != null && itr != startofCycle) {
            A++;
            itr = itr.next;
        }

        // // B = (start to meetup point]
        // itr = startofCycle.next;
        // while (itr != null && itr != meetPoint) {
        // B++;
        // itr = itr.next;
        // }
        // B++; // 1 jump from start cycle to start of B

        // TODO: apporach 2 : start from itr= startofCycle
        itr = startofCycle;
        // for case A=C=0
        if (itr == startofCycle) {
            B = 1;
            itr = itr.next;
        }
        while (itr != null && itr != meetPoint) {
            B++;
            itr = itr.next;
        }

        // C : meetupPoint to start
        itr = meetPoint;
        while (itr != null && itr != startofCycle) {
            C++;
            itr = itr.next;
        }

        int cycleLen = (B + C);

        M = ((A - C) / (cycleLen)) + 1;

        if (A == cycleLen)
            M = M - 1;

        System.out.printf("A = %d , B = %d , C = %d , m = %d , Cycle length = %d \n", A, B, C, M, cycleLen);
        ListNode newHead = removeCycle(head, meetPoint, startofCycle);
        System.out.println("Length of LinkedList is " + length(newHead));

        // return new String(A + " " + B + " " + C + " " + M + " " + cycleLen + " ");

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

    static ListNode removeCycle(ListNode head, ListNode meetup, ListNode startofCycle) {
        ListNode curr = meetup;

        // if(meetup == startofCycle){

        // }

        while (curr.next != startofCycle) {
            curr = curr.next;
        }

        curr.next = null;

        return head;

    }

    public static void main(String[] args) {

        // ListNode h1 = takeInput();
        ListNode a = new ListNode(1);
        ListNode b = new ListNode(2);
        ListNode c = new ListNode(3);
        ListNode d = new ListNode(4);
        ListNode e = new ListNode(5);
        ListNode f = new ListNode(6);
        ListNode g = new ListNode(7);
        ListNode h = new ListNode(8);
        ListNode i = new ListNode(9);
        ListNode j = new ListNode(10);
        a.next = b;
        b.next = c;
        c.next = d;
        d.next = e;
        e.next = f;
        f.next = g;
        g.next = h;
        h.next = i;
        i.next = j;
        j.next = e;
        System.out.println("My Approach : ");

        findVariables(a);

        ListNode h1 = takeInput();
        findVariables(h1);

        // a = c =0 m = 1
        ListNode w = new ListNode(1);
        ListNode x = new ListNode(1);
        ListNode y = new ListNode(1);
        ListNode z = new ListNode(1);
        w.next = x;
        x.next = y;
        y.next = z;
        z.next = w;

        findVariables(w);

    }

}