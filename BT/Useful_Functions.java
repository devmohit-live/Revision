import java.util.ArrayList;

public class Useful_Functions {
    // find

    public static boolean find(Node node, int data) {
        if (node == null)
            return false;
        if (node.data == data)
            return true;

        return find(node.left, data) || find(node.right, data);
    }

    /*-------------------- Method2 -----------------------*/
    // this method/approach of left,right check is used in k nodes away a lot to
    // save space (does the work in
    // constant space)
    public static ArrayList<Integer> nodeToRootPath(Node node, int data) {
        if (node == null)
            return new ArrayList<Integer>(); // base

        if (node.data == data) {
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(node.data);
            return arr;
        }

        ArrayList<Integer> left = nodeToRootPath(node.left, data);
        // got an answer from left subtree -> path lies here
        if (left.size() > 0) {
            left.add(node.data);
            return left;
        }
        // got an answer from left subtree -> path lies here
        ArrayList<Integer> right = nodeToRootPath(node.right, data);
        if (right.size() > 0) {
            right.add(node.data);
            return right;
        }
        // base -> default
        return new ArrayList<Integer>();
    }

    /*------------- Method 1 -------------------*/
    // k noed away's space takes o(n) space
    public static ArrayList<Integer> nodeToRootPath(Node node, int data) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        node2root(node, data, res);
        return res;
    }

    private static boolean node2root(Node node, int data, ArrayList<Integer> res) {
        if (node == null)
            return false;
        if (node.data == data) {
            res.add(node.data);
            return true;
        }
        boolean status = node2root(node.left, data, res) || node2root(node.right, data, res);
        // found in any of it's child(subtree)
        if (status) {
            res.add(node.data);
        }
        return status;
    }

    // K Level Down

    public static void printKLevelsDown(Node node, int k) {
        // generic k level down with block node if any
        Node block = null;
        kLevelDown(node, block, k);
    }

    private static void kLevelDown(Node node, Node block, int k) {
        if (node == null || node == block || k < 0)
            return;

        if (k == 0) {
            System.out.println(node.data);
            return;
        }

        // k level down in left and right
        kLevelDown(node.left, block, k - 1);
        kLevelDown(node.right, block, k - 1);
    }

    // K nodes away

    /* Method 1 => Time : O(n) Space: O(n) */
    // here in node2root we return arrlist of nodes
    private static boolean node2root(Node node, int data, ArrayList<Node> path) {
        if (node == null)
            return false;

        if (node.data == data) {
            path.add(node);
            return true;
        }

        boolean res = node2root(node.left, data, path) || node2root(node.right, data, path);
        if (res) {
            path.add(node);
            return true;
        }
        return false;

    }

    public static void printKNodesFar(Node node, int data, int k) {
        ArrayList<Node> path = new ArrayList<>(); // O(n) space
        node2root(node, data, path);

        Node block = null;

        for (int i = 0; i < path.size(); i++) {
            kLevelDown(path.get(i), block, k - i);
            // updating the block for next kLevel
            block = path.get(i);
        }

    }

    /* Method 2 => Time : O(n) Space: O(1) */
    private static void KLevelsDown(Node node, int k, Node block) {
        if (node == null || k < 0 || node == block)
            return;

        if (k == 0) {
            System.out.println(node.data);
            // ans.add(node.data);
            return;
        }

        KLevelsDown(node.left, k - 1, block);
        KLevelsDown(node.right, k - 1, block);
    }

    // similar to second appraoch of node to root path
    private static int kaway(Node node, int data, int k) {
        if (node == null)
            return -1;

        if (node.data == data) {
            Node block = null;
            KLevelsDown(node, k, block);
            return 1;
        }

        int ld = kaway(node.left, data, k);
        if (ld != -1) {
            // as we got ld anser from left node is it will be blocked in kleveldowns
            Node block = node.left;
            KLevelsDown(node, k - ld, block);
            return ld + 1;
        }

        int rd = kaway(node.right, data, k);
        if (rd != -1) {
            // as we got ld anser from right node is it will be blocked in kleveldowns
            Node block = node.right;
            KLevelsDown(node, k - rd, node.right);
            return rd + 1;
        }

        return -1;
    }

    public static void printKNodesFar(Node node, int data, int k) {
        kaway2(node, data, k);
        /*----- If wanted to reuturn arraylist instead of void ---- */
        // ArrayList<Integer> res=new ArrayList<Integer>();
        // kaway2(node,data,k,res);
        // System.out.println(res);
    }

}
