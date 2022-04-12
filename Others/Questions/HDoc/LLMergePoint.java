public class LLMergePoint {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int l1 = getLength(headA);
        int l2 = getLength(headB);
        ListNode p1 = headA;
        ListNode p2 = headB;
        int k;

        if (l1 >= l2) {
            k = l1 - l2;
        } else {
            k = l2 - l1;
            // making p1 to point bigger list
            ListNode temp = p1;
            p1 = p2;
            p2 = temp;
        }

        while (k-- > 0) {
            p1 = p1.next;
        }
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;

    }

    private int getLength(ListNode head) {
        int count = 0;
        ListNode itr = head;
        while (itr != null) {
            count++;
            itr = itr.next;
        }
        return count;
    }
}
