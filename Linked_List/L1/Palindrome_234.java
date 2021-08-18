package Linked_List;

class Palindorme_234 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;

        ListNode mid = middleNode(head);
        ListNode newHead = mid.next;
        mid.next = null;

        ListNode p1 = head;
        ListNode p2 = newHead;

        p2 = reverse(p2);

        boolean ans = true;

        while (p1 != null && p2 != null) {
            if (p1.val != p2.val) {
                // as we have to correct the ll -> undo the changes we made to the list
                ans = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // undo changes
        newHead = reverse(newHead);
        mid.next = newHead;
        return ans;
    }

    private ListNode middleNode(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head;
        ListNode fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode curr = head;
        ListNode prev = null;

        while (curr != null) {
            ListNode forw = curr.next;
            curr.next = prev;
            prev = curr;
            curr = forw;
        }
        return prev;
    }
}