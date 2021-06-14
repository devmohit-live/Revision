public class midNode {
    // returns middle n/2-1 in case of even -> preferred when middle is used as base
    // for solvinf problems
    public static middleNode(ListNode head){
        if(head==null || head.next==null) return head;
        ListNode slow=head, fast=head;

        while(fast.next!=null && fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    // returns n/2 in case of even -> when question demands to return second one ex:
    // mid of list leetcode 876
    public static middleNode(ListNode head){
        if(head==null || head.next==null) return head;
        ListNode slow=head, fast=head;

        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }
}
