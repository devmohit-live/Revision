package Questions.Trees;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import org.w3c.dom.Node;

public class BottomView {
    // https://practice.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1#

    // The last element we got for a specific level in the vertical order is viewd :
    // so just update that level's value in arr

    public ArrayList<Integer> bottomView(Node root) {// Using Vertical 2
        // clean and uses less space
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

    class vpair {
        Node node;
        int vlevel;

        vpair(Node node, int vlevel) {
            this.node = node;
            this.vlevel = vlevel;
        }
    }

    private void getMinMaxWidth(Node root, int[] arr, int level) {
        if (root == null)
            return;

        arr[0] = Math.min(arr[0], level);
        arr[1] = Math.max(arr[1], level);

        // calls
        getMinMaxWidth(root.left, arr, level - 1);
        getMinMaxWidth(root.right, arr, level + 1);

    }

    // Bottom view: Used verticalOrder
    public ArrayList<Integer> bottomViewUsingVertical1(Node root) {
        // Uses mopre space
        return verticalTraversal(root);
    }

    public ArrayList<Integer> verticalTraversal(Node root) {
        ArrayList<Integer> ans = new ArrayList<>();
        // bfs : level order traversal along with maintaing the -1,0,+1 thing of each
        // node
        LinkedList<vpair> q = new LinkedList<>();
        q.addLast(new vpair(root, 0));
        int min = 0, max = 0;
        // level, node's data : nodes belonging of that v level
        HashMap<Integer, Integer> map = new HashMap<>();
        int hlevel = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                vpair rm = q.removeFirst();

                // put this node's data to appropriate level's entry
                map.put(rm.vlevel, rm.node.data);
                // add left and right children if present to appropriate level
                if (rm.node.left != null)
                    q.addLast(new vpair(rm.node.left, rm.vlevel - 1));
                if (rm.node.right != null)
                    q.addLast(new vpair(rm.node.right, rm.vlevel + 1));

                // update the min and max vlevel
                min = Math.min(rm.vlevel, min);
                max = Math.max(rm.vlevel, max);
            }

            hlevel++;
        }

        // add answer to the list

        for (int i = min; i <= max; i++) {
            ans.add(map.get(i));
        }

        return ans;
    }

}
