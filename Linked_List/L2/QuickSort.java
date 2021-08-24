public class QuickSort {

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static ListNode[] getSegregateNodes(ListNode head, int pivotIdx) {
        if (head == null || head.next == null)
            return new ListNode[] { null, head, null };

        ListNode pivotNode = head;
        while (pivotIdx-- > 0)
            pivotNode = pivotNode.next;

        ListNode smaller = new ListNode(-1), larger = new ListNode(-1), sp = smaller, lp = larger, curr = head;
        while (curr != null) {
            if (curr != pivotNode && curr.val <= pivotNode.val) {
                sp.next = curr;
                sp = sp.next;
            } else if (curr != pivotNode) {
                lp.next = curr;
                lp = lp.next;
            }

            curr = curr.next;
        }

        sp.next = lp.next = pivotNode.next = null;

        return new ListNode[] { smaller.next, pivotNode, larger.next };
    }

    static int getLength(ListNode head) {
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

    public static ListNode[] mergeLists(ListNode[] left, ListNode pivot, ListNode[] right) {
        ListNode dummy = new ListNode(-1), curr = dummy;
        ListNode newLeft = left[0];
        ListNode newRight = right[0];

        ListNode head = dummy, tail = right[1];

        if (newRight == null) {
            newRight = dummy;
        }
        if (pivot == null) {
            pivot = right[0];
        }
        if (newLeft == null) {
            newLeft = pivot;
        }

        newLeft.next = pivot;
        if (newLeft.next == pivot.next) {
            dummy.next = pivot;
        }
        pivot.next = newRight;
        if (pivot.next == newRight.next) {
            dummy.next = newRight;
        }

        return new ListNode[] { dummy.next, tail };
    }

    // {head,tail}
    public static ListNode[] quickSort(ListNode head) {
        if (head == null || head.next == null)
            return new ListNode[] { head, head };

        int len = getLength(head);
        ListNode[] segregateNodes = getSegregateNodes(head, len / 2);

        ListNode[] left = quickSort(segregateNodes[0]);
        ListNode[] right = quickSort(segregateNodes[2]);

        return mergeLists(left, segregateNodes[1], right);
    }


}
