public class ReverseInKGroups {
    ListNode th = null, tt = null;

    public ListNode reverseKGroup(ListNode head, int k) {
        int len = getLength(head);
        if (head == null || head.next == null || k <= 1 || k > len)
            return head;

        ListNode curr = head, oh = null, ot = null;
        while (len >= k) {
            int tempk = k;

            while (tempk-- > 0) {
                ListNode forw = curr.next;
                curr.next = null;
                // op
                addFirst(curr);

                curr = forw;
            }

            len -= k;

            if (oh == null) {
                oh = th;
                ot = tt;
            } else {
                ot.next = th; // attach 2 groups
                ot = tt; // update the tail
            }

            th = tt = null;

        }
        // leftovers
        ot.next = curr;

        return oh;

    }

    private void addFirst(ListNode node) {
        if (th == null) {
            th = node;
            tt = node;
        } else {
            node.next = th;
            th = node;
        }
    }

    private int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }

        return len;
    }
}
