public class DeleteNodeInLL_237 {
    public void deleteNode(ListNode node) {

        // correct
        // ListNode curr = node, prev = null;
        // while(curr.next!=null){
        // curr.val = curr.next.val;
        // prev = curr;
        // curr = curr.next;
        // }
        // prev.next = null; //remove the last node

        // second way
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
