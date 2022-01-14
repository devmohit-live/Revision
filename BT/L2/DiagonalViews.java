public class DiagonalViews {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    // top view of diagonal order : first el of arrayList
    public static List<Integer> diagonalOrderTopView(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if (root == null)
            return ans;

        LinkedList<TreeNode> q = new LinkedList<>();
        q.addLast(root);

        while (!q.isEmpty()) { // all diagonals
            int size = q.size();
            ans.add(q.getFirst());
            while (size-- > 0) { // traverse each components(as components of a diagonal may not be connected
                                 // (part of different subtree)) of that particular diagonal()
                TreeNode rm = q.removeFirst();

                while (rm != null) { // traverse nodes in a component
                    if (rm.left != null)
                        q.addLast(rm.left);
                    rm = rm.right;
                }

            }
        }

        return ans;
    }

    // bottom view : last el of every arrayList
    public static List<Integer> diagonalOrderBottomView(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();

        if (root == null)
            return ans;
        LinkedList<TreeNode> q = new LinkedList<>();
        q.addLast(root);

        while (!q.isEmpty()) { // all diagonals
            int size = q.size();
            ans.add(q.getLast());
            while (size-- > 0) { // traverse each components(as components of a diagonal may not be connected
                                 // (part of different subtree)) of that particular diagonal()
                TreeNode rm = q.removeFirst();

                while (rm != null) { // traverse nodes in a component
                    if (rm.left != null)
                        q.addLast(rm.left);
                    rm = rm.right;
                }

            }
        }

        return ans;
    }

    // top view and anti diagonal
    // top view and anti diagonal

    public static void main(String[] args) {

    }

}
