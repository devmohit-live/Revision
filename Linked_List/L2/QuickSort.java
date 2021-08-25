public class QuickSort {
    // TODO:

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    // Base =>

    // Segregate LinkedList with last index elemet as pivot
    public static ListNode segregateOnLastIndex(ListNode head) {
        ListNode pivot = head, curr = head;
        ListNode left = new ListNode(-1), litr = left, right = new ListNode(-1), ritr = right;

        // get the pivot => here the pivot is last element (given in question)
        while (pivot.next != null)
            pivot = pivot.next;

        // segregating
        while (curr != null) {
            ListNode forw = curr.next;

            if (curr != pivot && curr.val <= pivot.val) {
                litr.next = curr;
                litr = litr.next;
            } else if (curr != pivot) {
                ritr.next = curr;
                ritr = ritr.next;
            }

            curr.next = null;
            curr = forw;
        }

        // releasing any connection to avoid cycle
        pivot.next = litr.next = ritr.next = null;

        // making proper connections
        litr.next = pivot;
        pivot.next = right.next;

        // return left.next; //if want to return the segregated list

        // returning pivot
        return pivot;

    }

    // Segregate LinkedList with given index elemet as pivot

    public static ListNode segregate(ListNode head, int pivotIdx) {
        ListNode pivot = head, curr = head;
        ListNode left = new ListNode(-1), litr = left, right = new ListNode(-1), ritr = right;

        // get the pivot
        while (pivotIdx-- > 0)
            pivot = pivot.next;

        // segregating
        while (curr != null) {
            ListNode forw = curr.next;

            if (curr != pivot && curr.val <= pivot.val) {
                litr.next = curr;
                litr = litr.next;
            } else if (curr != pivot) {
                ritr.next = curr;
                ritr = ritr.next;
            }

            curr.next = null;
            curr = forw;
        }

        // releasing any connection to avoid cycle
        pivot.next = litr.next = ritr.next = null;

        // making proper connections
        litr.next = pivot;
        pivot.next = right.next;

        return left.next;

    }

    // Base ends here

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

    public static ListNode[] mergeLists(ListNode[] left, ListNode pivotNode, ListNode[] right) {
        ListNode fh = null, ft = null;
        if (left[0] != null && right[0] != null) {
            fh = left[0];
            left[1].next = pivotNode;
            pivotNode.next = right[0];
            ft = right[1];
        } else if (left[0] == null && right[0] == null) {
            fh = ft = pivotNode;
        } else if (left[0] == null) {
            fh = pivotNode;
            pivotNode.next = right[0];
            ft = right[1];
        } else {
            fh = left[0];
            left[1].next = pivotNode;
            ft = pivotNode;
        }

        return new ListNode[] { fh, ft };
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
