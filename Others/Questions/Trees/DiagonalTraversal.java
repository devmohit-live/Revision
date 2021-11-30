package Questions.Trees;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.tree.TreeNode;

public class DiagonalTraversal {

    // Just the node at q is acting as the start of component ans we are just adding
    // the left ones to be the start for next component

    // At max every node is traversed 2 time : Time : O(2n) => O(n)
    // space: (q at a time can contains max n elemets)

    public static ArrayList<ArrayList<Integer>> diagonalOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        if (root == null)
            return ans;

        LinkedList<TreeNode> q = new LinkedList<>();
        q.addLast(root);

        while (!q.isEmpty()) { // all diagonals
            int size = q.size();
            ArrayList<Integer> diagonal = new ArrayList<>();

            while (size-- > 0) { // traverse each components(as components of a diagonal may not be connected
                                 // (part of different subtree)) of that particular diagonal()
                TreeNode rm = q.removeFirst();

                while (rm != null) { // traverse nodes in a component
                    diagonal.add(rm.val);
                    if (rm.left != null)
                        q.addLast(rm.left);
                    rm = rm.right;
                }

            }
            ans.add(diagonal);
        }

        return ans;
    }

    // Anticlockwise
    public static ArrayList<ArrayList<Integer>> diagonalOrderAntiClockWise(TreeNode root) {
        LinkedList<TreeNode> q = new LinkedList<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if (root == null)
            return ans;

        q.addLast(root);

        while (q.size() > 0) { // traverse all diagonals
            int size = q.size();
            ArrayList<Integer> diagonal = new ArrayList<>();
            while (size-- > 0) { // traverse all componenets (disconnected too)
                TreeNode rm = q.removeFirst();

                // iterate through each node of a current componenet
                while (rm != null) {
                    diagonal.add(rm.val);
                    if (rm.right != null)
                        q.addLast(rm.right);
                    rm = rm.left;
                }
            }
            ans.add(diagonal);
        }

        return ans;
    }

    // https://www.interviewbit.com/problems/diagonal-traversal/
    // returns all diagonal in same array list
    public ArrayList<Integer> diagonalTraversal(TreeNode root) {

        LinkedList<TreeNode> q = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        q.addLast(root);
        while (!q.isEmpty()) { // traverse all diagonals

            int size = q.size();
            // components
            while (size-- > 0) { // will go through all the componets

                TreeNode rm = q.removeFirst();
                while (rm != null) { // will add the nodes to the current component and will also add start of next
                                     // component to the q
                    ans.add(rm.val);
                    if (rm.left != null)
                        q.addLast(rm.left);
                    rm = rm.right;
                }

            }

        }

        return ans;

    }

    //diagonal sum
    public static ArrayList<Integer> diagonalOrderSum(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();

        if (root == null)
            return ans;

        LinkedList<TreeNode> q = new LinkedList<>();
        q.addLast(root);
        while (!q.isEmpty()) { // diagonal
            int size = q.size();
            int sum = 0;
            // all the componenets of diagonal
            while (size-- > 0) {
                TreeNode rm = q.removeFirst();
                // each node in a current component
                while (rm != null) {
                    sum += rm.val;
                    if (rm.left != null)
                        q.addLast(rm.left);
                    rm = rm.right;
                }
            }
            ans.add(sum);

        }
        return ans;
    }

}