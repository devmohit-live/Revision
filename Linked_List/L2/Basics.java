public class Basics {
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode midNode(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;

        }

        return prev;
    }

    public static boolean isPalindrome(ListNode head) {

        if (head == null || head.next == null)
            return true;

        ListNode first = head;
        ListNode mid = midNode(head);
        ListNode second = mid.next;
        mid.next = null;

        ListNode rev = reverse(second);
        ListNode revSec = rev;
        boolean res = true;

        while (first != null) {
            if (revSec.val != first.val) {
                res = false;
                break;
            }
            revSec = revSec.next;
            first = first.next;
        }
        ListNode backup = reverse(rev);
        mid.next = second;
        return res;
    }

    public static void fold(ListNode head) {

        if (head == null || head.next == null)
            return;

        ListNode first = head;
        ListNode mid = midNode(head);
        ListNode sec = reverse(mid.next);
        mid.next = null;

        while (first != null && sec != null) {
            ListNode fbck = first.next;
            ListNode sbck = sec.next;
            first.next = sec;
            first = fbck;
            sec.next = fbck;
            sec = sbck;
        }

    }

    public static void unfold(ListNode head) {
        if (head == null || head.next == null)
            return;

        ListNode first = head;
        // ListNode fbck = first;
        ListNode sec = head.next;
        ListNode sbck = sec;

        while (first.next != null && sec.next != null) {

            first.next = first.next.next;
            // move to updated next
            first = first.next;
            sec.next = sec.next.next;
            // move to updated next
            sec = sec.next;
        }

        ListNode rev = reverse(sbck);
        first.next = rev;

    }

    // Recursive
    // ListNode[0] = me (last element being added to revese ll while coming back),
    // ListNode [1] = newHead
    private static ListNode[] reverseRec(ListNode head) {
        if (head == null || head.next == null)
            return new ListNode[] { head, head };

        ListNode me = head;
        ListNode[] recans = reverseRec(me.next);
        me.next = null;
        recans[0].next = me;
        return new ListNode[] { me, recans[1] };

    }

    static void callReverse() {
        ListNode head = reverseRec(dummy.next)[1];

        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static ListNode segregateEvenOdd(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode even = new ListNode(-1);
        ListNode e = even;

        ListNode odd = new ListNode(-1);
        ListNode o = odd;

        ListNode tmp = head;

        while (tmp != null) {
            if ((tmp.val & 1) == 1) {
                o.next = tmp;
                o = o.next;
            } else {
                e.next = tmp;
                e = e.next;
            }

            tmp = tmp.next;
        }
        // to avoid cycle in any case
        e.next = o.next = null;

        e.next = odd.next;
        return even.next;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null)
            return head;

        ListNode res = head;
        ListNode fast = head;
        ListNode slow = head;
        while (n-- > 0) {
            fast = fast.next;
        }
        // if removing first node => size()node from last
        if (fast == null)
            return res.next;

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode next = slow.next.next;
        slow.next = null;
        slow.next = next;
        return res;

    }

    public static ListNode segregate012(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode zero = new ListNode(-1);
        ListNode z = zero;
        ListNode one = new ListNode(-1);
        ListNode o = one;
        ListNode two = new ListNode(-1);
        ListNode t = two;
        ListNode curr = head;

        while (curr != null) {
            if (curr.val == 1) {
                o.next = curr;
                o = o.next;
            } else if (curr.val == 0) {
                z.next = curr;
                z = z.next;
            } else {
                t.next = curr;
                t = t.next;
            }
            curr = curr.next;
        }
        o.next = z.next = t.next = null;
        // always attach one to two first( if ll only contains 2)
        o.next = two.next;
        z.next = one.next;

        return zero.next;
    }

    private static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;

        ListNode itr1 = l1;
        ListNode itr2 = l2;
        ListNode tmp = new ListNode(-1);
        ListNode res = tmp;
        while (itr1 != null || itr2 != null) {
            int val1 = itr1 != null ? itr1.val : (int) 1e9;
            int val2 = itr2 != null ? itr2.val : (int) 1e9;
            if (val1 <= val2) {
                tmp.next = itr1;
                itr1 = itr1.next;
            } else {
                tmp.next = itr2;
                itr2 = itr2.next;
            }
            tmp = tmp.next;
        }

        return res.next;
    }

    // Time: O(k*n) space : O(1)
    public static ListNode mergeKListsBrute(ListNode[] lists) {

        if (lists.length == 0)
            return null;

        for (int i = 1; i < lists.length; i++) {
            ListNode a = lists[i - 1];
            ListNode b = lists[i];
            ListNode c = mergeTwoLists(a, b);
            lists[i] = c;
        }

        return lists[lists.length - 1];

    }

    // O(nlogK) // space : O(k)
    public static ListNode mergeKListsPQ(ListNode[] lists) {
        ListNode ans = new ListNode(-1);
        ListNode curr = ans;
        if (lists.length == 0)
            return ans.next;

        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> {
            return a.val - b.val;
        });

        for (ListNode node : lists) {
            if (node != null)
                pq.add(node);
        }

        while (pq.size() > 0) {
            ListNode rm = pq.remove();
            ListNode nextNode = rm.next;
            curr.next = rm;
            curr = curr.next;

            if (nextNode != null)
                pq.add(nextNode);

        }

        return ans.next;

    }

    // Using Divide and Conquer better than PQ as PQ's remove function is not
    // optimal in java provided PQ
    public static ListNode mergeKLists(ListNode[] lists, int si, int ei) {
        if (si == ei)
            return lists[si];

        int mid = (si + ei) / 2;

        ListNode leftSorted = mergeKLists(lists, si, mid);
        ListNode rightSorted = mergeKLists(lists, mid + 1, ei);

        ListNode res = mergeTwoLists(leftSorted, rightSorted);
        return res;
    }


    static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

}
