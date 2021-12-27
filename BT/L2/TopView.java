package Questions.Trees;

public class TopView {
    // https://practice.geeksforgeeks.org/problems/top-view-of-binary-tree/1#
    // The first element we got for a specific level in the vertical order is viewd
    // in top view
    public static ArrayList<Integer> topView(Node root) {
        int[] minmax = new int[2];
        getMinMaxWidth(root, minmax, 0);
        int length = minmax[1] - minmax[0] + 1;
        LinkedList<vpair> q = new LinkedList<>();
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < length; i++)
            res.add(0); // filling the arraylist so that we can set a particular index later without
                        // havinf indexoutofbound exception

        q.addLast(new vpair(root, Math.abs(minmax[0])));

        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                vpair rm = q.removeFirst();
                Node node = rm.node;
                int level = rm.vlevel;
                // the first elemet in the vertical traversal is the top view
                // the last eleemtn in the vertical traversal is the top view
                if (res.get(level) == 0)
                    res.set(level, node.data); // update with latest val(lowest possible view node)

                // add
                if (node.left != null)
                    q.add(new vpair(node.left, level - 1));
                if (node.right != null)
                    q.add(new vpair(node.right, level + 1));

            }

        }

        return res;
    }

}
