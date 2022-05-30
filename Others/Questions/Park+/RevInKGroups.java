public class RevInKGroups {
    ListNode th = null, tt = null;

    public ListNode reverseKGroup(ListNode head, int k) {
        // get the length;
        int len = getLength(head);

        if (head == null || head.next == null || k > len)
            return head;

        ListNode curr = head, oh = null, ot = null;

        while (len >= k) {
            int tmpK = k;
            while (tmpK-- > 0) {
                ListNode forw = curr.next;
                curr.next = null; // removing the connection

                addFirst(curr);

                // move forward
                curr = forw;
            }

            len -= k; // preocessed k elements

            // maintain my original result list
            if (oh == null) {
                oh = th;
                ot = tt;
            } else {
                ot.next = th;
                ot = tt;
            }

            // clear the tmp head and tail
            tt = th = null;

        }

        // attach the reaminig list if any
        ot.next = curr;

        return oh;

    }

    private int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        return len;

    }

    private void addFirst(ListNode node) {
        if (th == null) {
            th = tt = node;
        } else {
            node.next = th;
            th = node;
        }

    }

}
