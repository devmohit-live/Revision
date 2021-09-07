import java.util.HashMap;
import java.util.PriorityQueue;

public class Basics {
    public static class ListNode {
        int val = 0;
        ListNode next = null;
        ListNode random = null;

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

    private static int length(ListNode head) {
        int size = 0;
        if (head == null || head.next == null)
            return size;
        ListNode curr = head;

        while (curr != null) {
            size++;
            curr = curr.next;
        }
        return size;
    }

    private static ListNode th = null, tt = null;

    private static void addFirstNode(ListNode node) {
        if (th == null) {
            th = tt = node;
        } else {
            node.next = th;
            th = node;
        }
    }

    public static ListNode reverseInKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k <= 1)
            return head;

        int len = length(head);
        ListNode curr = head, oh = null, ot = null;
        while (len >= k) {
            int tempK = k;
            while (tempK-- > 0) {
                ListNode forw = curr.next;
                curr.next = null;
                addFirstNode(curr);
                curr = forw;
            }

            if (oh == null) {
                oh = th;
                ot = tt;
            } else {
                ot.next = th;
                ot = tt;
            }

            th = tt = null;
            len -= k;
        }

        ot.next = curr;
        return oh;
    }

    public static ListNode reverseInRange(ListNode head, int n, int m) {
        if (head == null || head.next == null || n == m)
            return head;

        ListNode dummy = new ListNode(-1), prev = dummy, curr = head;
        prev.next = head;
        int i = 1;
        while (i <= m) {
            while (i >= n && i <= m) {
                ListNode forw = curr.next;
                curr.next = null;
                addFirstNode(curr);
                curr = forw;
                i++;
            }

            if (i > m) {
                prev.next = th;
                tt.next = curr;
                break;
            }

            i++;
            prev = curr;
            curr = curr.next;
        }

        return dummy.next;
    }

    public static ListNode removeDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode ans = new ListNode(-1), itr = ans, prev = null, curr = head;

        while (curr != null) {
            // backup
            ListNode forw = curr.next;
            int data = prev == null ? -1 : prev.val;
            if (data != curr.val) {
                itr.next = curr;
                itr = itr.next;
                prev = curr;
            }

            // break the links between the nodes(duplicate nodes)
            curr.next = null;
            curr = forw;
        }

        return ans.next;
    }

    public static ListNode removeDuplicatesClass(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode curr = head.next, prev = head;
        while (curr != null) {
            while (curr != null && curr.val == prev.val) {
                ListNode forw = curr.next;
                curr.next = null;
                curr = forw;
            }

            prev.next = curr;
            prev = prev.next;
            if (curr != null)
                curr = curr.next;
        }

        return head;
    }

    public static ListNode removeAllDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode ans = new ListNode(-1), itr = ans;
        ListNode curr = head.next;
        itr.next = head;

        while (curr != null) {
            boolean isLoopRun = false;
            ListNode forw;

            while (curr != null && curr.val == itr.next.val) {

                forw = curr.next;
                // break the links between same val nodes
                curr.next = null;
                curr = forw;
                isLoopRun = true;
            }
            if (isLoopRun) {
                // break the links between uniq node & first node that will be repeated
                itr.next = null;
                itr.next = curr;
            } else {
                itr = itr.next;
                itr.next = curr;
            }

            if (curr != null) {
                forw = curr.next;
                curr.next = null;
                curr = forw;
            }
        }
        return ans.next;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode res = new ListNode(-1);
        ListNode tmp = res;
        ListNode c1 = reverse(l1), c2 = reverse(l2);

        while (c1 != null || c2 != null || carry != 0) {
            int sum = 0;
            if (c1 != null) {
                sum += c1.val;
                c1 = c1.next;
            }
            if (c2 != null) {
                sum += c2.val;
                c2 = c2.next;
            }
            sum += carry;
            carry = sum / 10;
            int data = sum % 10;
            ListNode node = new ListNode(data);
            tmp.next = node;
            tmp = tmp.next;
        }
        return reverse(res.next);
    }

    private static int getLength(ListNode head) {
        if (head == null)
            return 0;
        int len = 0;
        ListNode curr = head;
        while (curr != null) {
            len++;
            curr = curr.next;
        }

        return len;

    }

    private static boolean isBigger(ListNode head1, ListNode head2) {
        ListNode c1 = head1, c2 = head2;

        int l1 = getLength(c1);
        int l2 = getLength(c2);
        if (l1 > l2)
            return true;
        if (l1 < l2)
            return false;

        while (c1 != null) {
            if (c1.val > c2.val)
                return true;
            else if (c1.val < c2.val)
                return false;

            c1 = c1.next;
            c2 = c2.next;
        }

        return true; // both lists are exactly same

    }

    public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null)
            return null; // not possible

        ListNode dummy = new ListNode(-1), curr = dummy;
        ListNode c1 = l1, c2 = l2;

        if (!isBigger(l1, l2)) {
            // swap;
            ListNode tmp = c1;
            c1 = c2;
            c2 = tmp;
        }

        // reverse
        c1 = reverse(c1);
        c2 = reverse(c2);
        int borrow = 0;

        while (c1 != null || c2 != null) {
            int digit = borrow + (c1 == null ? 0 : c1.val) - (c2 == null ? 0 : c2.val);

            if (digit < 0) {
                borrow = -1;
                digit += 10;
            } else {
                // always set borrow to 0 again for next itr
                borrow = 0;
            }

            curr.next = new ListNode(digit);
            curr = curr.next;

            if (c1 != null)
                c1 = c1.next;
            if (c2 != null)
                c2 = c2.next;
        }

        ListNode ans = reverse(dummy.next);
        // return ans;

        // check for preceding zeros in answer
        ListNode myans = new ListNode(-1), itr = ans;
        myans.next = null;

        while (itr != null) {
            ListNode forw = itr.next;
            if (itr.val != 0) {
                // found the first non zero element;
                myans.next = itr;
                break;
            }
            // break the links for starting zeros
            itr.next = null;
            itr = forw;
        }

        // nothing is attached to myans => all were zeroes
        if (myans.next == null)
            return new ListNode(0);

        return myans.next;

    }

    // TODO: multiplication of ll

    // add two list (got after multiplication) // here we will makes changes to
    // original list only => inplace
    public static void addList(ListNode prev, ListNode list) {
        // checking for prev.next , here prev represents => x in addition process of
        // multiplication
        int carry = 0;
        while (list != null || carry != 0) {
            int sum = carry + (list != null ? list.val : 0) + (prev.next != null ? prev.next.val : 0);
            int digit = sum % 10;
            carry = sum / 10;

            // make changes to the original list at it's correct place
            if (prev.next != null)
                prev.next.val = digit;
            else
                prev.next = new ListNode(digit);

            prev = prev.next;
            if (list != null)
                list = list.next;
        }
    }

    // multiply list 1 with a sibgle digit of l2
    public static ListNode multiplyDigit(ListNode list, int d) {
        ListNode dummy = new ListNode(-1), curr = list, prev = dummy;

        int carry = 0;
        while (curr != null || carry != 0) {
            int ans = carry + (curr != null ? curr.val : 0) * d;
            int digit = ans % 10;
            carry = ans / 10;

            prev.next = new ListNode(digit);
            // moving the cross (x)
            prev = prev.next;

            if (curr != null)
                curr = curr.next;
        }

        return dummy.next;
    }

    public static ListNode multiplyTwoLL(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);

        ListNode ans = new ListNode(-1), prev = ans;
        while (l2 != null) {
            ListNode multipliedList = multiplyDigit(l1, l2.val);
            addList(prev, multipliedList);
            prev = prev.next; // acts as shifting -> or appeding zeros at last
            l2 = l2.next;
        }

        return reverse(ans.next);
    }

    // copy list with random pointers ->leetcode 138
    
    public ListNode copyRandomListWithSpace(ListNode head) {
        HashMap<ListNode, ListNode> map = new HashMap<>();
        ListNode curr1 = head;
        ListNode curr2 = head;
        // ListNode curr = null;
        ListNode dummy = new ListNode(-1);
        ListNode itr = dummy;
        ListNode itr2 = dummy;

        if (head == null)
            return head;

        // normal deep copy with next link is made
        while (curr1 != null) {
            ListNode x = new ListNode(curr1.val);
            itr.next = x;
            map.put(curr1, x);
            curr1 = curr1.next;
            itr = itr.next;
        }

        // random pointers
        while (curr2 != null) {
            ListNode origListRandom = curr2.random;
            // respective new list ListNode
            ListNode newListRandom = map.get(origListRandom);
            itr2.next.random = newListRandom;

            itr2 = itr2.next;
            curr2 = curr2.next;
        }

        return dummy.next;
    }

    public ListNode copyRandomList(ListNode head) {
        if (head == null)
            return head;

        ListNode curr = head;
        copyList(curr);
        setRandomPointer(curr);

        return extractList(curr);
    }

    private void copyList(ListNode head) {
        if (head == null)
            return;

        ListNode curr = head;
        while (curr != null) {
            ListNode forw = curr.next;
            ListNode node = new ListNode(curr.val);
            curr.next = node;
            node.next = forw;

            curr = forw;
        }

    }

    private void setRandomPointer(ListNode head) {

        ListNode curr = head;

        while (curr != null) {
            ListNode forw = curr.next.next;
            ListNode copiedNode = curr.next;

            if (curr.random != null)
                curr.next.random = curr.random.next;

            curr = forw;
        }

    }

    private ListNode extractListMy(ListNode head) {

        ListNode dummy = new ListNode(-1);
        ListNode curr = head;
        ListNode ans = curr.next;
        dummy.next = ans;

        while (curr != null) {
            ListNode forw = curr.next.next;
            ListNode copiedForw = null;
            if (ans.next != null)
                copiedForw = ans.next.next;

            curr.next = ans.next = null;

            curr.next = forw;
            ans.next = copiedForw;

            curr = forw;
            ans = copiedForw;
        }

        return dummy.next;
    }

    public ListNode extractList(ListNode head) {
        ListNode curr = head, dummy = new ListNode(-1), prev = dummy;
        while (curr != null) {
            ListNode forw = curr.next.next; // backup

            prev.next = curr.next; // links
            curr.next = forw;

            curr = forw; // move
            prev = prev.next;
        }

        return dummy.next;
    }

    static void printList(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
    }

}
