package Questions.Trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
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
    // calculate the width first , and pass q.add(root,abs(min)) as base => no need
    // of hasmap

    private static void vminmax(TreeNode root, int level, int[] minmax) {
        if (root == null)
            return;
        minmax[0] = Math.min(minmax[0], level);
        minmax[1] = Math.max(minmax[1], level);

        vminmax(root.left, level - 1, minmax);
        vminmax(root.right, level + 1, minmax);
    }

    public List<List<Integer>> verticalTraversal2(TreeNode root) {
        int[] minmax = new int[2];
        vminmax(root, 0, minmax);
        int length = minmax[1] - minmax[0] + 1;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < length; i++)
            ans.add(new ArrayList<>());
        verticalTraversal2_(root, Math.abs(minmax[0]), ans);
        return ans;

    }

    private void verticalTraversal2_(TreeNode root, int level, List<List<Integer>> ans) {
        LinkedList<vpair> q = new LinkedList<>();
        int hlevel = 0;
        q.addLast(new vpair(root, level));
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                vpair rm = q.removeFirst();
                ans.get(rm.vlevel).add(rm.node.val);
                if (rm.node.left != null)
                    q.addLast(new vpair(rm.node.left, rm.vlevel - 1));
                if (rm.node.left != null)
                    q.addLast(new vpair(rm.node.right, rm.vlevel + 1));
            }
            hlevel++;
        }
    }

    // vertical order sum => sum of all the nodes on same vertical line
    public static ArrayList<Integer> verticalOrderSum(TreeNode root) {
        int[] minmax = new int[2];
        vminmax(root, 0, minmax);
        int length = minmax[1] - minmax[0] + 1;
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 0; i < length; i++)
            ans.add(0); // default 0 sum

        LinkedList<vpair> q = new LinkedList<>();
        q.add(new vpair(root, Math.abs(minmax[0])));
        int hl = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                vpair rm = q.remove();
                TreeNode node = rm.node;
                int level = rm.vlevel;
                int prev = ans.get(level);
                ans.set(level, prev + node.val);

                // add
                if (node.left != null)
                    q.addLast(new vpair(node.left, level - 1));
                if (node.right != null)
                    q.addLast(new vpair(node.right, level + 1));

            }
            hl++;
        }

        return ans;
    }

}
