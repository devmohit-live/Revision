class IsBST {
    // Method 1 : USing inorder property of BST => inorder is sorted (Using static
    // variable ) , Time: O(n)
    static Node prev = null;

    public static boolean isBST(Node node) {

        if (node == null)
            return true;

        // pre
        boolean left = isBST(node.left);
        if (!left)
            return false;

        // in
        // checking for increasing order sequence as -> as inorder area has increasing
        // seq of BST
        if (prev != null && prev.data > node.data)
            return false;
        prev = node;

        // post
        boolean right = isBST(node.right);
        if (!right)
            return false;

        return true;
    }

}


// TODO: Largest BST Subtreee

