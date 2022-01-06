package Questions.Trees;
// Why not levelorder traversal : because first we have to convert it inot thr graph first

import java.util.ArrayList;

// because we can't go directly to parent from node, in graph  we can go from a to b or b to a if there is an edge

public class BurnTree {
    // based on k level(in recursion with node2root path) down with node 2 root path
    int max = 0;

    public int solve(TreeNode A, int B) {
        burnTree(A, B);
        return max;
    }

    private int burnTree(TreeNode root, int fire) {
        if (root == null)
            return -1;

        if (root.val == fire) {
            burn(root, null, 0); // actual burning starts form here
            return 1;
        }

        // for all calls(node below fire node => called after fire node) where /until we
        // have'nt found the firenode we get -1
        // for all calls(node above fire node => called before fire node(by which fire
        // node is called)) : will get non negative value
        // immediate parent : will get 1, grandparet will get 2 and so on
        // ie k level down making the firenode/parent blocked will do the job

        // left
        int leftTime = burnTree(root.left, fire);
        if (leftTime != -1) {
            // non -ve value means we are near the some burned/fire node in our subtree
            // so we should burn our connected trees
            burn(root, root.left, leftTime);
            return leftTime + 1;
        }
        int rightTime = burnTree(root.right, fire);
        if (rightTime != -1) {
            burn(root, root.right, rightTime);
            return rightTime + 1;
        }

        return -1;
    }

    private void burn(TreeNode root, TreeNode blocked, int time) {
        if (root == null || root == blocked)
            return;

        max = Math.max(max, time);
        burn(root.left, blocked, time + 1);
        burn(root.right, blocked, time + 1);
    }


    // Retunrning all the nodes according to the burn time
    public static ArrayList<ArrayList<Integer>> burningTree(TreeNode root, int data) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        // n2r/find data : boolean type code
        burningTree(root, data, ans);
        return ans;

    }

    public static int burningTree(TreeNode root, int val, ArrayList<ArrayList<Integer>> res) {
        if (root == null)
            return -1;

        if (root.val == val) {
            // found the source of burning tree: intial time is 0
            kdown(root, null, 0, res);
            return 1; // next nodes will b 1 distance away from this node

        }

        int left = burningTree(root.left, val, res);
        if (left != -1) {
            kdown(root, root.left, left, res); // curr node = root, blocked = left(target of fire was found in left)
                                               // (exactly like k distance away)
            return left + 1; // next nodes will be curr + 1 distance away from this node
        }

        int right = burningTree(root.right, val, res);
        if (right != -1) {
            kdown(root, root.right, right, res);
            return right + 1;
        }

        return -1; // not found

    }

    // k = time : time will increse by 1 here, in k down ( it decreases by 1)
    private static void kdown(TreeNode node, TreeNode blocked, int time, ArrayList<ArrayList<Integer>> res) {
        if (node == null || node == blocked)
            return;

        if (res.size() == time)
            res.add(new ArrayList<>()); // new time explored

        res.get(time).add(node.val); // adding val to respective time
        kdown(node.left, blocked, time + 1, res);
        kdown(node.right, blocked, time + 1, res);
    }


}
