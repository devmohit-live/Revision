package Graph.Tree_LevelOrder;

public class LevelOrderOfBT {

    public static void levelOrder(Node node) {
        int lv = 0;

        LinkedList<Node> q = new LinkedList<>();
        q.addLast(node);
        while (q.size() > 0) {
            int curr = q.size();
            System.out.print(lv + " -> ");

            while (curr-- > 0) {
                // get thr node removed
                Node rm = q.removeFirst();
                System.out.print(rm.data + " ");

                // add children of each node removed
                if (rm.left != null)
                    q.add(rm.left);
                if (rm.right != null)
                    q.add(rm.right);
            }

            System.out.println();
            lv++;

        }

    }


    //Leetcode: 102

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        LinkedList<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        while (q.size() > 0) {
            List<Integer> smallAns = new ArrayList<>();
            int curr = q.size();
            while (curr-- > 0) {
                TreeNode rm = q.removeFirst();
                // syso node.data
                smallAns.add(rm.val);
                // /add children
                if (rm.left != null)
                    q.addLast(rm.left);
                if (rm.right != null)
                    q.addLast(rm.right);
            }
            res.add(smallAns);

        }
        return res;
    }
}
