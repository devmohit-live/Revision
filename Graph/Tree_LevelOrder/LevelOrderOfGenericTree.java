package Graph.Tree_LevelOrder;

public class LevelOrderOfGenericTree {
    public static void levelOrderLinewise(Node node) {
        LinkedList<Node> q = new LinkedList<>();
        int lv = 0;
        q.addLast(node);

        while (q.size() > 0) {
            int curr = q.size();
            System.out.print("Level " + lv + " -> ");

            while (curr-- > 0) {
                Node rm = q.removeFirst();
                System.out.print(rm.data + " ");
                // Remember to add children of removed node only not for root
                for (Node child : rm.children) {
                    q.addLast(child);
                }

            }
            System.out.println();
            lv++;
        }
    }

    // Leetcode 429
    public List<List<Integer>> levelOrder(Node root) {

        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;

        LinkedList<Node> q = new LinkedList<>();
        q.addLast(root);

        while (q.size() > 0) {
            int curr = q.size();
            List<Integer> ans = new ArrayList<>();
            while (curr-- > 0) {
                Node rm = q.removeFirst();
                ans.add(rm.val);
                for (Node child : rm.children) {
                    q.addLast(child);
                }

            }
            res.add(ans);
        }

        return res;
    }
}
