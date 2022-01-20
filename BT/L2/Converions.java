import java.util.LinkedList;

import org.w3c.dom.Node;

public class Converions {

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
        prev = dummy; //iterator

        bToDLL_(root);
        Node head = dummy.right; // dummy.next = head(in ll) 
        //prev: iterator at last will point to tail
        //dummy.next points to original head of ll
        // dummy.next.prev => oh.prev => points to dummy ie. -1, so do dummy.next.prev=null, or oh.left = null;
        head.left = null; 
        return head;
    }

    // inorder
    //treenode.left = listnode.prev, treenode.right = listnode.next
    
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

    //using arr[]
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
    
}
