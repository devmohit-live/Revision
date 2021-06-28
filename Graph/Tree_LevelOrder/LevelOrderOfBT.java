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
}
