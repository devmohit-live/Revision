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

    // TODO: Largest BST Subtreee
    public static BSTPair largestBst(Node node) {
        if (node == null)
            return new BSTPair();

        BSTPair mypair = new BSTPair();
        BSTPair left = largestBst(node.left);
        BSTPair right = largestBst(node.right);

        mypair.ht = Math.max(left.ht, right.ht) + 1;

        if (left.isBst && right.isBst && left.max < node.data && node.data < right.min) {
            mypair.bstH = Math.max(left.ht, right.ht) + 1;
            mypair.node = node;
            mypair.bstSize = left.bstSize + right.bstSize + 1;
            mypair.min = Math.min(left.min, Math.min(right.min, node.data));
            mypair.max = Math.max(left.max, Math.max(right.max, node.data));
            return mypair;
        }
        if (left.bstH > right.bstH)
            return left;
        else
            return right;

    } // will return BSTPair having largest BST staring node, height, size

}
