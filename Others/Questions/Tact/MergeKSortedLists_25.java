public class MergeKSortedLists_25 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;

        // if somehow we can find the lesser one in all lists : pq
        // pq bcz we have to find a lesser val in group of people
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode node : lists)
            if (node != null)
                pq.add(node);

        // how am I supposed to go for next pointer after I have got the least val from
        // pq, how am I supposed to move forwad
        // Simple: as we move to the next pointer in mergee 2 sorted list : we will use
        // the same approach
        ListNode ans = new ListNode(-1), ptr = ans;
        while (pq.size() > 0) {
            ListNode rm = pq.remove();
            // collected to answer
            ptr.next = rm;

            ListNode next = rm.next;
            if (next != null)
                pq.add(next);

            // manage pointers
            rm.next = null;
            ptr = ptr.next;
        }

        return ans.next;

    }

    // Brute IDEA: iterate through 2 lists at a time merge them
    // private ListNode brute(ListNode[] lists){
    // ListNode ans = new ListNode(-1), ptr = dummy;

    // }
}
