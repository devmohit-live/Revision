import java.util.ArrayList;
import java.util.List;

public class Basics {
    // node ro all leaf paths
    public static List<List<Integer>> node2AllLeafes(TreeNode node) {
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> small = new ArrayList<>();
        node2AllLeafes(node, small, ans);
    }

    private void node2AllLeafes(TreeNode node, List<Integer> small, List<List<Integer>> ans) {
        if (node == null)
            return;
        if (node.left == null && node.right == null) {
            small.add(node.val);
            ans.add(new ArrayList<>(small));
        }
        small.add(root.val);
        node2AllLeafes(node.left);
        node2AllLeafes(node.right);
        small.remove(small.size() - 1);

    }
}
