import java.util.ArrayList;
import java.util.Collections;

public class BST {
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

    // sum , height will be same as BT

    // min max

    public static int min(Node node) {
        if (node == null)
            return -(int) 1e9;
        while (node.left != null) {
            node = node.left;
        }
        return node.data;
    }

    public static int max(Node node) {
        if (node == null)
            return -(int) 1e9;
        while (node.right != null) {
            node = node.right;
        }
        return node.data;
    }

    public static boolean find(Node node, int data) {
        while (node != null) {
            if (node.data == data)
                return true;
            if (node.data > data)
                node = noed.left;
            else
                node = node.right;
        }
        return false;
    }

    // logn time , constant space => as no recursion is required
    public static ArrayList<Node> rootToNodePath(Node node, int data) {
        ArrayList<Node> res = new ArrayList<>();
        boolean found = false;
        while (node != null) {
            if (node.data == data) {
                res.add(node);
                found = true;
                break;
            }
            if (node.data > data) {
                node = node.left;
                // adding parent
                res.add(node);
            } else {
                node = node.right;
                // adding parent
                res.add(node);
            }
        }
        if (!found) {
            res = new ArrayList<Node>();
        }
        return res;
    }

    // logn time , constant space => as no recursion is required
    public static ArrayList<Node> nodeToRootPath(Node node, int data) {
        ArrayList<Node> res = rootToNodePath(node, data);
        // reverse will take log n time as there will be max logn elemets in arraylist
        Collections.reverse(res);
    }

    // here nodetoroot path approach fails at some cases where lca is either d1 or
    // d2 itself
    public static int lca(Node node, int d1, int d2) {
        int lca = -1;
        while (node != null) {
            if (node.data < d1 && node.data < d2) {
                node = node.right;
            } else if (node.data > d1 && node.data > d2) {
                node = node.left;
            } else {
                // we aren't able to decide ehich side to go d1 states different thing d2 states
                // different -> this point is lca
                lca = node.data;
                break;
            }
        }
        return lca;

    }

    public static void printInRange(Node node, int d1, int d2) {
        if (node == null)
            return;

        printInRange(node.left, d1, d2);

        // in area

        if (d1 <= node.data && node.data <= d2) {
            System.out.println(node.data);
        }

        printInRange(node.right, d1, d2);
    }

    // ******** */ Use recursive approach in bst when there is certain modification
    // required in
    // bst structure, ex: adding, removing node, or somewhere where we need
    // parent/grandparent ********

    // Add Data (Iterative (My Approach -> Accepted))
    public static Node add(Node node, int data) {
        Node toAdd = new Node(data);

        Node prev = null;
        Node root = node;

        if (node == null) {
            return toAdd;
        }

        while (node != null) {
            if (node.data >= data) {
                prev = node;
                node = node.left;
            } else {
                prev = node;
                node = node.right;
            }
        }

        if (prev.data >= data) {
            prev.left = toAdd;
        } else {
            prev.right = toAdd;
        }

        return root;

    }

    // Add data Recursive Accepted
    // faith
    public static Node addRecursive(Node node, int data) {
        if (node == null)
            return new Node(data);

        if (node.data >= data) {
            node.left = addRecursive(node.left, data);
        } else {
            node.right = addRecursive(node.right, data);
        }
        return node;
    }

    // // If duplicates aren't allowed tobe added => ignore task if duplicate exist
    // => pep portal

    // public static Node add(Node node, int data) {

    // // Using recusrion
    // if(node==null) return new Node(data,null,null);

    // if(node.data > data){
    // node.left = add(node.left, data);
    // }
    // else if(node.data < data){
    // node.right = add(node.right, data);
    // }
    // return node;

    // // Using iteration

    // Node toAdd = new Node(data,null,null);

    // Node prev = null;
    // Node root = node;

    // if (node == null) {
    // return toAdd;
    // }

    // while (node != null) {
    // will go to left even for duplicates but will not add them
    // if (node.data >= data) {
    // prev = node;
    // node = node.left;
    // } else{
    // prev = node;
    // node = node.right;
    // }
    // }
    // wil not add data when node.data==data
    // if (prev.data > data) {
    // prev.left = toAdd;
    // } else if(prev.data!=data){
    // prev.right = toAdd;
    // }

    // return root;
    // }

    /// Remove a node => LeetCode 450

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;

        if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            // leave or contains single child
            if (root.left == null || root.right == null) {
                return root.left != null ? root.left : root.right;
            }

            // contains both child
            int min = min(root.right);
            // or int max = max(node.left) and do call for left
            root.val = min;
            root.right = deleteNode(root.right, min);
        }
        return root;
    }

}
