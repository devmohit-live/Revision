import java.util.ArrayList;
import java.util.List;

import javax.swing.tree.TreeNode;

public class Basics {
    // IN BST try to do eveything iteratively

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> la = new ArrayList<>();
        rootToNodePath(root, p, la);
        List<TreeNode> lb = new ArrayList<>();
        rootToNodePath(root, q, lb);
        int sa = 0, sb = 0;
        while (sa < la.size() && sb < lb.size() && la.get(sa).val == lb.get(sb).val) {
            sa++;
            sb++;
        }
        sa--;
        return la.get(sa);

    }

    // n
    private void rootToNodePath(TreeNode root, TreeNode node, List<TreeNode> list) {
        if (root == null)
            return;

        while (root != null) {
            list.add(root);
            if (root == node)
                return;
            else if (root.val < node.val)
                root = root.right;
            else
                root = root.left;

        }

        list.clear(); // not found clear list
        return;
    }

    // 2n
    private List<TreeNode> n2rp(TreeNode root, TreeNode node) {
        List<TreeNode> list = new ArrayList<>();
        rootToNodePath(root, node, list);
        int i = 0, j = list.size();
        while (i < j) {
            TreeNode tmp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, tmp);
            i++;
            j--;
        }
        return list;
    }

}
