public class LinkedListQ {
    // Palindrome LL :
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null)
            return true;
        ListNode mid = middleNode(head);
        ListNode newHead = mid.next;
        mid.next = null; // break the link

        ListNode p1 = head;
        ListNode p2 = reverse(newHead);

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
        ListNode prev = null, forw = null;

        while (curr != null) {
            forw = curr.next;

            curr.next = prev;

            prev = curr;
            curr = forw;
        }

        return prev;
    }

    // CYCLE :
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;
        ListNode meetPoint = meetingPoint(head);

        if (meetPoint == null)
            return null;

        ListNode fast = head;

        while (meetPoint != fast) {
            meetPoint = meetPoint.next;
            fast = fast.next;
        }

        return meetPoint;
    }

    private ListNode meetingPoint(ListNode head) {
        if (head == null || head.next == null)
            return null;

        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return slow;
            }
        }

        return null;

    }

    // Reverse in K Groups :
    ListNode th = null, tt = null;

    public ListNode reverseKGroup(ListNode head, int k) {
        // get the length;
        int len = getLength(head);

        if (head == null || head.next == null || k > len)
            return head;

        ListNode curr = head, oh = null, ot = null;

        while (len >= k) {
            int tmpK = k;
            while (tmpK-- > 0) {
                ListNode forw = curr.next;
                curr.next = null; // removing the connection

                addFirst(curr);

                // move forward
                curr = forw;
            }

            len -= k; // preocessed k elements

            // maintain my original result list
            if (oh == null) {
                oh = th;
                ot = tt;
            } else {
                ot.next = th;
                ot = tt;
            }

            // clear the tmp head and tail
            tt = th = null;

        }

        // attach the reaminig list if any
        ot.next = curr;

        return oh;

    }

    private int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        return len;

    }

    private void addFirst(ListNode node) {
        if (th == null) {
            th = tt = node;
        } else {
            node.next = th;
            th = node;
        }

    }

    // Intersection of 2 LinekedList
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int l1 = getLength(headA);
        int l2 = getLength(headB);
        ListNode curr1 = headA, curr2 = headB;
        int diff = l1 > l2 ? l1 - l2 : l2 - l1;
        while (diff-- > 0) {
            if (l1 > l2)
                curr1 = curr1.next;
            else
                curr2 = curr2.next;
        }

        while (curr1 != null && curr2 != null) {
            if (curr1 == curr2)
                return curr1;

            curr1 = curr1.next;
            curr2 = curr2.next;
        }

        return null;

    }

    int getLength(ListNode head) {
        if (head == null)
            return 0;
        int len = 0;
        ListNode curr = head;

        while (curr != null) {
            curr = curr.next;
            len++;
        }

        return len;
    }

    // Add 2numbers
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode res = new ListNode(-1);
        ListNode tmp = res;

        while (l1 != null || l2 != null || carry == 1) {
            int sum = 0;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            sum += carry;
            carry = sum / 10;
            int data = sum % 10;
            ListNode node = new ListNode(data);
            tmp.next = node;
            tmp = tmp.next;
        }
        return res.next;

    }

    // remove nyt node from end
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null)
            return null;
        ListNode dummy = new ListNode(-1); // so that we can traverse n time easily instead of n-1 times and we can
                                           // easily return dummy.next easily withour ptr/itr
        dummy.next = head;
        ListNode slow = dummy, fast = dummy;
        while (n-- > 0)
            fast = fast.next;

        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        // slow will be pointing to the last node whose next needed to be removed
        slow.next = slow.next.next;
        return dummy.next;
    }

}
