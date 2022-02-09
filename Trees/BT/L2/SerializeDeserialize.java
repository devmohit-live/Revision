package Questions.Trees;

// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/discuss/1606109/Simple-Preorder-Traversal-Trick-of-using-references
// Leetcode 297
public class SerializeDeserialize {
    private int idx = 0;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        traverse(root, sb);
        sb.deleteCharAt(sb.length() - 1); // to remove last ","(delimeter)
        // System.out.println("Serialized: "+ sb.toString());

        return sb.toString();

    }

    // preorder
    private void traverse(TreeNode node, StringBuilder res) {
        if (node == null) {
            res.append("null,");
            return;
        }

        res.append(node.val + ",");
        traverse(node.left, res);
        traverse(node.right, res);

    }

    private TreeNode deserialize(String[] arr) {
        if (this.idx >= arr.length) {
            return null;
        }

        String s = arr[this.idx++];
        if (s.equals("null"))
            return null;

        TreeNode root = new TreeNode(Integer.parseInt(s));
        root.left = deserialize(arr);
        root.right = deserialize(arr);
        return root;
    }

    // Decodes your encoded data to tree
    public TreeNode deserialize(String data) {
        StringBuilder sb = new StringBuilder();
        String[] nodes = data.split(",");
        // System.out.println("Deserialized: "+Arrays.toString(nodes)+""+nodes.length);
        return deserialize(nodes);
    }

    // LevelOrder Approach

    public String serializeLevelOrder(TreeNode root) {
        if (root == null)
            return "";
        StringBuilder sb = new StringBuilder();
        LinkedList<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        while (!q.isEmpty()) {
            TreeNode rnode = q.removeFirst();
            if (rnode == null) {
                sb.append("# ");
                continue; // else will give null ptr exception while adding left,right child of null
            } else
                sb.append(rnode.val + " ");

            q.addLast(rnode.left);
            q.addLast(rnode.right);

        }

        return sb.toString();
    }

    public TreeNode deserializeLevelOrder(String data) {
        if (data.length() == 0)
            return null;
        String[] s = data.split(" ");
        int idx = 1, n = s.length;
        TreeNode root = new TreeNode(Integer.parseInt(s[0]));
        LinkedList<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        // starts from 1
        while (idx < n) {
            TreeNode rnode = q.removeFirst();
            // left
            if (!s[idx].equals("#")) {
                TreeNode left = new TreeNode(Integer.parseInt(s[idx]));
                q.addLast(left);
                rnode.left = left;
            }
            idx++;

            // right
            if (!s[idx].equals("#")) {
                TreeNode right = new TreeNode(Integer.parseInt(s[idx]));
                q.addLast(right);
                rnode.right = right;
            }
            idx++;

        }

        return root;
    }
}