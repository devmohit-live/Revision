//Leetcode 148
class MergeSorteLL_148 {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode mid = middleNode(head);
        ListNode nHead = mid.next;
        mid.next = null;

        ListNode leftSortedList = sortList(head);
        ListNode rightSortedList = sortList(nHead);

        return mergeTwoLists(leftSortedList, rightSortedList);
    }

    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        ListNode p1 = l1, p2 = l2, dm = new ListNode(-1);
        ListNode node = dm;

        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                node.next = p1;
                p1 = p1.next;
            } else {
                node.next = p2;
                p2 = p2.next;
            }
            node = node.next;
        }

        // if anyone of the list is not null
        node.next = p1 != null ? p1 : p2;

        return dm.next;
    }

}