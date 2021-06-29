package BT;

import java.util.ArrayList;

public class BinaryTree {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        Node(int data) {
            // this calls for same class -> same class constructor
            this(data, null, null);
        }
    }

    ArrayList<Integer> ans = new ArrayList<Integer>();

    public static void preOrder(Node node, ArrayList<Integer> ans) {
        if (node == null)
            return;

        ans.add(node.data);
        preOrder(node.left, ans);
        preOrder(node.right, ans);
    }

    public static void inOrder(Node node, ArrayList<Integer> ans) {
        if (node == null)
            return;

        inOrder(node.left, ans);
        ans.add(node.data);
        inOrder(node.right, ans);
    }

    public static void postOrder(Node node, ArrayList<Integer> ans) {
        if (node == null)
            return;

        postOrder(node.left, ans);
        postOrder(node.right, ans);
        ans.add(node.data);
    }

    public static int size(Node node) {
        if (node == null)
            return 0;
        int leftSize = size(node.left);
        int rightSize = size(node.right);
        return leftSize + rightSize + 1;
    }

    public static int max(Node node) {
        if (node == null)
            return -(int) 1e9;
        // return Integer.MIN_VALUE;

        int leftMax = max(node.left);
        int rightMax = max(node.right);
        return Math.max(Math.max(leftMax, rightMax), node.data);
    }

    public static int min(Node node) {
        if (node == null)
            return (int) 1e9;
        // return Integer.MAX_VALUE;

        int leftMin = min(node.left);
        int rightMin = min(node.right);
        return Math.min(Math.min(leftMin, rightMin), node.data);
    }

    public static int sum(Node node) {
        if (node == null)
            return 0;
        int leftSum = sum(node.left);
        int rightSum = sum(node.right);
        return leftSum + node.data + rightSum;

    }

    public static int height(Node node) {
        if (node == null)
            return -1;
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    int countLeaves(Node node) {
        if (node == null)
            return 0;
        if (node.left == null && node.right == null)
            return 1;

        int countLeft = countLeaves(node.left);
        int countRight = countLeaves(node.right);
        return countLeft + countRight;

        /* Old Way Using static var not Recommended */
        // if(node==null) return 0;

        // if(node.left==null && node.right==null) count++;

        // countLeaves(node.left);
        // countLeaves(node.right);
        // return count;
    }

    public static void singleChildNode(Node node, ArrayList<Integer> res) {
        if (node == null || (node.left == null && node.right == null))
            return;
        // if (node.left == null && node.right != null)
        // res.add(node.data);
        // else if (node.left != null && node.right == null)
        // res.add(node.data);

        if (node.left == null || node.right != null)
            res.add(node.data);

        singleChildNode(node.left, res);
        singleChildNode(node.right, res);
    }

    public static int singleChildNode(Node node) {
        // checking here for leafes too
        if (node == null || (node.left == null && node.right == null))
            return 0;

        int left = singleChildNode(node.left);
        int right = singleChildNode(node.right);
        int status = 0;

        if (node.left == null || node.right != null)
            status = 1;

        return left + right + status;
    }

    // Remove Leaves : faith left,right subtree will removeleaves from them
    // I will attach them as left and right subtree

    public static Node removeLeaves(Node node) {
        // in case of leaves we should return null else return head;
        if (node == null || (node.left == null && node.right == null))
            return null;

        Node left = removeLeaves(node.left);
        Node right = removeLeaves(node.right);
        node.left = left;
        node.right = right;

        return node;
    }

    public static void main(String[] args) {
        // Node root = new Node(7);
        // root.left = new Node(6);
        // root.right = new Node(5);

        // root.left.left = new Node(4);
        // root.left.right = new Node(6);
        // root.right.left = new Node(8);
        // root.right.right = new Node(9);
        // ArrayList<Integer> arr = new ArrayList<Integer>();
        // inOrder(root, arr);
        // preOrder(root, arr);
        // // // postOrder(root, arr);
        // System.out.println("Inorder is " + arr);
        // System.out.println("Sum " + sum(root));
        // System.out.println("Size " + size(root));
        // System.out.println("Height " + height(root));
        // System.out.println("Max " + max(root));
        // System.out.println("Min " + min(root));

        // Skew Tree
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(3);
        root.left.left.left = new Node(4);

        Node root2 = new Node(2);
        root2.left = new Node(3);
        root2.right = new Node(5);
        root2.left.left = new Node(7);
        root2.right.left = new Node(8);
        root2.right.right = new Node(9);

        ArrayList<Integer> single = new ArrayList<>();
        singleChildNode(root2, single);
        System.out.println("Node having single child are " + single);
        System.out.println("Node having single child are " + singleChildNode(root2));

    }

}