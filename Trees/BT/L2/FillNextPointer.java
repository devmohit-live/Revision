public class FillNextPointer {

    // Leetcode : 116. Populating Next Right Pointers in Each Node

    // https://leetcode.com/problems/populating-next-right-pointers-in-each-node/discuss/1651712/2-ways-or-Easy-to-Understand-or-DFS-BFS

    public Node connect(Node root) {
        // return connectDfs(root); // faster
        return connectBFS(root); //
    }

    // dfs
    public Node connectDFS(Node root) {
        if (root == null || root.left == null)
            return root;
        root.left.next = root.right;
        if (root.next != null) {
            root.right.next = root.next.left;
        }
        connectDFS(root.left);
        connectDFS(root.right);
        return root;
    }

    // bfs
    public Node connectBFS(Node root) {
        // lvlv ordere traversal
        if (root == null)
            return root;

        LinkedList<Node> q = new LinkedList<>();
        LinkedList<Node> child = new LinkedList<>();
        q.addLast(root);

        int level = 0;
        while (q.size() > 0) {
            int size = q.size();
            while (size-- > 0) {
                Node rm = q.removeFirst();
                // maintain it's next pointer
                if (q.size() > 0)
                    rm.next = q.getFirst();

                // add children
                if (rm.left != null)
                    child.addLast(rm.left);
                if (rm.right != null)
                    child.addLast(rm.right);
            }
            level++;
            // swap
            LinkedList<Node> tmp = q;
            q = child;
            child = tmp;
        }

        return root;
    }
}
