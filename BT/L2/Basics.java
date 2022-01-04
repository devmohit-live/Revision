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
            List<Integer> base = new ArrayList<>(small);
            base.add(node.val);
            ans.add(base);
        }
        small.add(root.val);
        node2AllLeafes(node.left, small, ans);
        node2AllLeafes(node.right, small, ans);
        small.remove(small.size() - 1);

    }
}
