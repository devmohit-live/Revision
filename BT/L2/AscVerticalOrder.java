package Questions.Trees;

public class AscVerticalOrder {

    // Leetcode 987. Vertical Order Traversal of a Binary Tree


    class vpair {
        TreeNode node;
        int vlevel;

        vpair(TreeNode node, int vlevel) {
            this.node = node;
            this.vlevel = vlevel;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        int[] minmax = new int[2];
        vminmax(root, 0, minmax);
        int length = minmax[1] - minmax[0] + 1;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < length; i++)
            ans.add(new ArrayList<>());

        PriorityQueue<vpair> q = new PriorityQueue<>((a, b) -> {
            return a.node.val - b.node.val; // since we are maintains the level in vpair so even the
            // node with lesser value will be removed fist even thoug it has higher level
            // thean others it will be added to the correct(it's appropriate arraylist) we
            // are just ensuring that lesser value will be added to the same level first
        });

        // to avoid mix and match of a level elemets for(size--)(to determine the level
        // wise filling correctly we are using this q to fill child : after each level
        // it will become parent)
        PriorityQueue<vpair> childq = new PriorityQueue<>((a, b) -> {
            return a.node.val - b.node.val;
        });

        q.add(new vpair(root, Math.abs(minmax[0])));
        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                vpair rm = q.remove();
                TreeNode node = rm.node;
                int level = rm.vlevel;

                ans.get(level).add(node.val); // add to it's appropriate level

                // add children to child q
                if (node.left != null)
                    childq.add(new vpair(node.left, level - 1));
                if (node.right != null)
                    childq.add(new vpair(node.right, level + 1));

            }
            // end of a level
            PriorityQueue<vpair> tmp = childq;
            childq = q;
            q = tmp;

        }

        return ans;
    }

    private static void vminmax(TreeNode root, int level, int[] minmax) {
        if (root == null)
            return;
        minmax[0] = Math.min(minmax[0], level);
        minmax[1] = Math.max(minmax[1], level);

        vminmax(root.left, level - 1, minmax);
        vminmax(root.right, level + 1, minmax);
    }

}
