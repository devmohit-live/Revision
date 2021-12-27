package Questions.Trees;
// Why not levelorder traversal : because first we have to convert it inot thr graph first

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
import java.util.*;

public class Main {
    public static Scanner scn = new Scanner(System.in);

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        TreeNode(int val) {
            this.val = val;
        }
    }

    public static ArrayList<ArrayList<Integer>> burningTree2(TreeNode root, int data) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        burningTree(root, data, res);
        return res;
    }

    private static int burningTree(TreeNode root, int fire, ArrayList<ArrayList<Integer>> res) {

        if (root == null)
            return -1;

        if (root.val == fire) {
            // burning startes here
            burn(root, null, 0, res);// started burning from 0
            return 1;// end burning this node in 1 sec

        }

        int leftBurn = burningTree(root.left, fire, res);
        if (leftBurn != -1) {

            burn(root, root.left, leftBurn, res);

            return leftBurn + 1;
        }

        int rightBurn = burningTree(root.right, fire, res);
        if (rightBurn != -1) {

            burn(root, root.right, rightBurn, res);

            return rightBurn + 1;
        }

        return -1;
    }

    private static void burn(TreeNode root, TreeNode blocked, int time, ArrayList<ArrayList<Integer>> res) {

        if (root == null || root == blocked)
            return;

        if (res.size() == time) {
            // that means new max time is observed for which arraylist doesn't ecits
            ArrayList<Integer> small = new ArrayList<>(); // create a contaienr
            res.add(small);
        }

        // add to the appropriate container
        res.get(time).add(root.val);

        burn(root.left, blocked, time + 1, res);
        burn(root.right, blocked, time + 1, res);
    }



}
