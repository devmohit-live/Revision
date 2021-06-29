public class Diameter {
    // Leetcode 543
    /* Method 1: O(n*n) => calling height at each stage */
    public int diameter(Node node) {
        if (node == null)
            return 0;

        int ld = diameter(node.left);
        int rd = diameter(node.right);

        int lh = height(node.left);
        int rh = height(node.right);

        return Math.max(Math.max(ld, rd), lh + rh + 2);
    }

    /*
     * Method 2: O(n) => Using Pair-> class to store height and dia of each node
     * coming back
     */
    class Pair {
        int height = -1;
        int dia = 0;
    }

    public Pair diameter(Node node) {
        if (node == null)
            return new Pair();
        Pair mypair = new Pair();

        Pair lpair = diameter(node.left);
        Pair rpair = diameter(node.right);
        mypair.dia = Math.max(Math.max(lpair.dia, rpair.dia), lpair.height + rpair.height + 2);
        mypair.height = Math.max(lpair.height, rpair.height) + 1;
        return mypair;

    }

    /* Method 3 : Using Global varibale , traverse and reflect */
    int dia = -(int) 1e9;

    public int diameterOfBinaryTree(Node root) {
        height(root);
        return dia;
    }

    private int height(Node node) {
        if (node == null)
            return -1;

        int lh = height(node.left);
        int rh = height(node.right);
        int ht = Math.max(lh, rh) + 1;
        dia = Math.max(lh + rh + 2, dia);

        return ht;
    }
}
