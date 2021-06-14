/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class MergeSortedLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
      
      // handle all 4 cases of l1,l2 being null or not
        if(l1==null) return l2;
        if(l2==null) return l1;
      
      ListNode p1=l1, p2=l2, dm = new ListNode(-1);
      ListNode node = dm;
      
        while(p1!=null && p2!=null){
          if(p1.val <= p2.val){
            node.next = p1;
            p1=p1.next;
          }else{
            node.next = p2;
            p2=p2.next;
          }
          node=node.next; 
        }
      
      // if anyone of the list is not null
     node.next= p1!=null?p1:p2;
      
      return dm.next;
    }
}