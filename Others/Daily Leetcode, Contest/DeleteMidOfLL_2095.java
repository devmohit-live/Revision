/**
 * DeleteMidOfLL_2095
 */
public class DeleteMidOfLL_2095 {

    public ListNode deleteMiddle(ListNode head) {
        if(head == null || head.next == null) return null;
        
        ListNode midsPrev = findMidsPrev(head);
        midsPrev.next = midsPrev.next.next;
        return head;
    }
    
    private ListNode findMidsPrev(ListNode head){
        if(head == null || head.next == null) return null;
        ListNode slow = head, fast = head.next;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;
    }
}