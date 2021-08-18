// Leetcode 23

import java.util.*;

class MergeKSortedLL {
    public ListNode mergeKLists(ListNode[] lists) {

        ListNode dummy = new ListNode(-1);
        ListNode itr = dummy;

        if (lists.length == 0)
            return null;

        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> {
            return a.val - b.val;
        });
        // adding head of each ListNode in lists

        for (ListNode head : lists) {
            // to prevent adding null start nodes
            if (head != null)
                pq.add(head);
        }

        // will now remove and add the next of same listnode in pq(if possible ==
        // !=null)

        while (pq.size() > 0) {
            ListNode rm = pq.remove();
            ListNode next = rm.next;

            itr.next = rm;
            itr = itr.next;

            if (next != null)
                pq.add(next);

        }

        return dummy.next;

    }
}