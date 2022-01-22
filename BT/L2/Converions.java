import java.util.LinkedList;

import org.w3c.dom.Node;

public class Converions {
    class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    // Inorder : leftsubtree diubly ll bnado bactrach aate wqt right subtree ho
    // jaegi
    // in area me kaam hoga: curr node: stack simulate karega, prev node hum =>
    // using global/class/arrayofsize1
    // Binary tree to DLL :
    // https://practice.geeksforgeeks.org/problems/binary-tree-to-dll/1#

    // .. Approach 1 (Recursive): Using static => can be removed by using arr of
    // node of size 1/ treeNode.right propertyu: heap

    Node prev = null;

    Node bToDLL(Node root) {
        Node dummy = new Node(-1);
        prev = dummy; // iterator

        bToDLL_(root);
        Node head = dummy.right; // dummy.next = head(in ll)
        // prev: iterator at last will point to tail
        // dummy.next points to original head of ll
        // dummy.next.prev => oh.prev => points to dummy ie. -1, so do
        // dummy.next.prev=null, or oh.left = null;
        head.left = null;
        return head;
    }

    // inorder
    // treenode.left = listnode.prev, treenode.right = listnode.next

    private void bToDLL_(Node root) {
        if (root == null)
            return;

        bToDLL_(root.left); // leftsubtree

        // inarea
        root.left = prev;
        prev.right = root;
        prev = root; // prev = curr

        // righsubtree
        bToDLL_(root.right);
    }

    // Using a class : head propery : changes inside the heap is maintained
    // using node.left to save the static context variable;
    Node bToDLL01(Node root) {
        Node dummy = new Node(-1);
        Node itr = new Node(-1);
        ;
        itr.left = dummy;
        bToDLL_(root, itr);
        Node head = dummy.right;
        head.left = null; // pointing to dummy node : -1
        return head;
    }

    void bToDLL_01(Node root, Node itr) {
        if (root == null)
            return;

        bToDLL_01(root.left, itr);

        Node myitr = itr.left; // to avaoid writing itr.left

        root.left = myitr; // prev
        myitr.right = root; // next

        // here we have to write itr.left only as myitr = root(doesn't make changes
        // inside: heap)
        itr.left = root; // to avaoid static

        bToDLL_01(root.right, itr);
    }

    // using arr[]
    Node bToDLL02(Node root) {
        Node dummy = new Node(-1);
        Node itr = dummy;
        Node[] arr = new Node[] { itr };
        bToDLL_(root, arr);
        Node head = dummy.right;
        head.left = null; // pointing to dummy node : -1
        return head;
    }

    void bToDLL_02(Node root, Node[] arr) {
        if (root == null)
            return;

        bToDLL_02(root.left, arr);

        root.left = arr[0]; // prev
        arr[0].right = root; // next

        // here we have to write itr.left only as myitr = root(doesn't make changes
        // inside: heap)
        arr[0] = root; // to avaoid static

        bToDLL_02(root.right, arr);
    }

    // Ditto same: Circular DLL : just attach head to tail
    // https://practice.geeksforgeeks.org/problems/binary-tree-to-cdll/1/#

    Node bTreeToClist(Node root) {
        Node dummy = new Node(-1);
        prev = dummy;

        bToDLL_(root);
        Node head = dummy.right;
        head.left = dummy.right = null;

        prev.right = head; // tail to head
        head.left = prev;// head to tail previouly head.left was pointing to dummy -1

        return head;
    }

    // BT to DLL: Iterative
    public static void insertAllLeft(LinkedList<Node> st, Node node) {
        while (node != null) {
            st.addFirst(node);
            node = node.left;
        }
    }

    public static Node bToDLL_itreative(Node node) {
        Node dummy = new Node(-1);
        Node prev = dummy;

        LinkedList<Node> st = new LinkedList<>();
        insertAllLeft(st, node);

        while (st.size() != 0) {
            Node curr = st.removeFirst();
            prev.right = curr;
            curr.left = prev;
            prev = curr;

            insertAllLeft(st, curr.right);
        }

        Node head = dummy.right;

        head.left = dummy.right = null;
        return head;
    }

    // ****************** Binary Tree to BST *****************

    // bt => dll -> sorted dll -> bst
    private Node sortDLL(Node head) {
        if (head == null || head.right == null)
            return head;
        Node mid = middleOfLL(head);
        Node newHead = mid.right;

        newHead.left = mid.right = null; // break link

        Node left = sortDLL(head);
        Node right = sortDLL(newHead);
        return merge(left, right);
    }

    private Node sortedDllToBst(Node head) {
        if (head == null || head.right == null)
            return head;

        Node root = middleOfLL(head);
        Node leftDLLHead = head, rightDLLHead = root.right;
        // break the links prev,next appropriately
        root.left.right = root.right.left = null;
        root.left = root.right = null;
        // and have faith that ;eft subtree and right subtree will convert themself

        root.left = sortedDllToBst(leftDLLHead);
        root.right = sortedDllToBst(rightDLLHead);

        return root;
    }

    private Node middleOfLL(Node head) {
        if (head == null || head.right == null)
            return head;
        Node slow = head, fast = head;
        while (fast.right != null && fast.right.right != null) {
            slow = slow.right; // slow.right;
            fast = fast.right.right;
        }
        return slow;
    }

    private Node merge(Node l1, Node l2) {
        if (l1 == null || l2 == null) {
            return (l1 == null) ? l2 : l1;
        }
        Node a = l1, b = l2, ans = new Node(-1), itr = ans;
        while (a != null && b != null) {
            if (a.val <= b.val) {
                itr.right = a; // itr.right = a;
                a.left = itr; // a.prev = itr;
                a = a.right;

            } else {
                itr.right = b; // itr.right = b;
                b.left = itr; // b.prev = itr;
                b = b.right;
            }

            itr = itr.right;
        }
        if (a != null) {
            itr.right = a;
            a.left = itr;
        } else if (b != null) {
            itr.right = b;
            b.left = itr;
        }
        Node head = ans.right;
        head.left = ans.right = ans.left = null; // breaking link with -1
        return head;
    }

    public Node BT2BST(Node root) {
        if (root == null)
            return null;

        Node head = bToDLL_itreative(root); // make a dll
        head = sortDLL(head); // sort that dll

        return sortedDllToBst(head); // convert sorted dll to bst
    }

    // To check correctness run the inorder of this BST : it will be in increasing
    // order
    // https://practice.geeksforgeeks.org/problems/inorder-traversal/1#
}
