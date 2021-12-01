package Questions.Trees;

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
            idx++;
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
}