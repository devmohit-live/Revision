public class Converions {
    // Binary tree to DLL :
    // https://practice.geeksforgeeks.org/problems/binary-tree-to-dll/1#

    // .. Approach 1 (Recursive): Using static => can be removed by using arr of node of size 1/ treeNode.right propertyu: heap

    Node prev = null;

    Node bToDLL(Node root) {
        Node dummy = new Node(-1);
        prev = dummy;

        bToDLL_(root);
        Node head = dummy.right;
        head.left = null;
        return head;
    }

    private void bToDLL_(Node root) {
        if (root == null)
            return;
        // inorder
        bToDLL_(root.left);
        root.left = prev;
        prev.right = root;
        prev = root;

        bToDLL_(root.right);
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
        head.left = prev;// head to tail

        return head;
    }
}
