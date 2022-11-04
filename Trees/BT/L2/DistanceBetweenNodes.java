public class DistanceBetweenNodes {
    // https://practice.geeksforgeeks.org/problems/min-distance-between-two-given-nodes-of-a-binary-tree/1
    int findDist(Node root, int a, int b) {
        Node lca = lca(root, a, b);
        if (lca == null)
            return -1;

        int x = getDistance(lca, a, 0);
        int y = getDistance(lca, b, 0);
        return x + y;
    }

    Node lca(Node root, int a, int b) {
        if (root == null)
            return root;
        if (root.data == a || root.data == b)
            return root;

        Node l = lca(root.left, a, b);
        Node r = lca(root.right, a, b);
        if (l != null && r != null)
            return root;
        return l != null ? l : r;
    }

    int getDistance(Node root, int a, int ans) {
        if (root == null)
            return -1;
        if (root.data == a)
            return ans;
        int l = getDistance(root.left, a, ans + 1);
        int r = getDistance(root.right, a, ans + 1);
        return Math.max(l, r);
    }
}
