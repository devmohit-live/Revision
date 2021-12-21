public class SumOfNodesWithEvenGrandParent {
    // LC 1315
    public int sumEvenGrandparent(TreeNode root) {

        class Pair {
            int gp, par;
            TreeNode node;

            Pair(TreeNode node, int par, int gp) {
                this.node = node;
                this.gp = gp;
                this.par = par;
            }
        }

        if (root == null)
            return 0;
        int level = -2;
        LinkedList<Pair> q = new LinkedList<>();
        q.addLast(new Pair(root, 1, 1));
        int sum = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                Pair rm = q.removeFirst();
                TreeNode rmnode = rm.node;
                int gp = rm.gp, par = rm.par;

                if (rmnode.left != null)
                    q.addLast(new Pair(rmnode.left, rmnode.val, par));
                if (rmnode.right != null)
                    q.addLast(new Pair(rmnode.right, rmnode.val, par));

                if (level >= 0 && (gp % 2) == 0)
                    sum += rmnode.val;

            }
            level++;
        }

        return sum;
    }
}
