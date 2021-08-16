public class Basics {
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode midNode(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;

        }

        return prev;
    }

    public static boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null)
            return true;

        ListNode first = head;
        ListNode mid = midNode(head);
        ListNode second = mid.next;
        mid.next = null;

        ListNode rev = reverse(second);
        ListNode revSec = rev;
        boolean res = true;

        while (first != null) {
            if (revSec.val != first.val) {
                res = false;
                break;
            }
            revSec = revSec.next;
            first = first.next;
        }
        ListNode backup = reverse(rev);
        mid.next = second;
        return res;
    }

    public static void fold(ListNode head) {

        if (head == null || head.next == null)
            return;

        ListNode first = head;
        ListNode mid = midNode(head);
        ListNode sec = reverse(mid.next);
        mid.next = null;

        while (first != null && sec != null) {
            ListNode fbck = first.next;
            ListNode sbck = sec.next;
            first.next = sec;
            first = fbck;
            sec.next = fbck;
            sec = sbck;
        }

    }

    public static void unfold(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode first = head;
        // ListNode fbck = first;
        ListNode sec = head.next;
        ListNode sbck = sec;

        while (first.next != null && sec.next != null) {

            first.next = first.next.next;
            // move to updated next
            first = first.next;
            sec.next = sec.next.next;
            // move to updated next
            sec = sec.next;

        }

        ListNode rev = reverse(sbck);
        first.next = rev;

    }

    // Recursive
    // ListNode[0] = me (last element being added to revese ll while coming back),
    // ListNode [1] = newHead
    private static ListNode[] reverseRec(ListNode head) {
        if (head == null || head.next == null)
            return new ListNode[] { head, head };

        ListNode me = head;
        ListNode[] recans = reverseRec(me.next);
        me.next = null;
        recans[0].next = me;
        return new ListNode[] { me, recans[1] };

    }

    static void callReverse() {
        ListNode head = reverseRec(dummy.next)[1];

        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

}
