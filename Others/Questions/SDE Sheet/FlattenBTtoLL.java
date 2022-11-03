/**
 * FlattenBTtoLL
 */
public class FlattenBTtoLL {

    TreeNode prev = null;

    // postorder
    public void flatten(TreeNode root) {
        if (root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        // work
        root.right = prev;
        root.left = null; // make let part null
        prev = root;
    }


}