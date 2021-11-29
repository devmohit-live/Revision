package Questions.Trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import javax.swing.tree.TreeNode;

public class VerticalOrder {
    // Approach 1: LevelOrder traversal, the internal order of the node's data can
    // be diferent but the vertical line overall level order will be excatly same
    class vpair {
        TreeNode node;
        int vlevel;

        vpair(TreeNode node, int vlevel) {
            this.node = node;
            this.vlevel = vlevel;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        // bfs : level order traversal along with maintaing the -1,0,+1 thing of each
        // node
        Queue<vpair> q = new ArrayDeque<>();
        q.add(new vpair(root, 0));
        int min = 0, max = 0;
        // level, node's data : nodes belonging of that v level
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int hlevel = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                vpair rm = q.remove();
                // update the min and max vlevel
                min = Math.min(rm.vlevel, min);
                max = Math.max(rm.vlevel, max);
                // put this node's data to appropriate level's entry

                map.putIfAbsent(rm.vlevel, new ArrayList<Integer>());
                map.get(rm.vlevel).add(rm.node.val);
                // add left and right children if present to appropriate level
                if (rm.node.left != null)
                    q.add(new vpair(rm.node.left, rm.vlevel - 1));
                if (rm.node.right != null)
                    q.add(new vpair(rm.node.right, rm.vlevel + 1));

            }

            hlevel++;
        }

        // add answer to the list

        for (int i = min; i <= max; i++) {
            ans.add(map.get(i));
        }

        return ans;
    }

    //
    // Approach 2:

}
