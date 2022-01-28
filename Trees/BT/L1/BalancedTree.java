// Balanced if : -1 <= (lh -rh ) <=1
public class BalancedTree {

    /*
     * Method1 : calling height for each node O(n*n)
     */
    public static int height(Node node) {
        if (node == null)
            return -1;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    // faith checking slef is balanced by taking diff of left and right height and
    // doing same for children -> ok
    public static boolean isBalanced(Node node) {
        if (node == null)
            return true;

        int factor = height(node.left) - height(node.right);
        if (!(-1 <= factor && factor <= 1)) {
            return false;
        } else {
            // calling height for each node of it's children
            boolean left = isBalanced(node.left);
            boolean right = isBalanced(node.right);
            if (!left || !right) {
                return false;
            }
        }

        return true;
    }

    // Faith : is left balanced, is right balanced? if both are balanced am I
    // balanced? -> Good
    public static boolean isBalanced(Node node) {
        if (node == null)
            return true;

        boolean leftBal = isBalanced(node.left);
        if (!leftBal)
            return false;
        boolean rightBal = isBalanced(node.right);
        if (!rightBal)
            return false;

        int lh = height(node.left);
        int rh = height(node.right);
        int factor = Math.abs(lh - rh);
        if (factor > 1)
            return false;

        return true;
    }

    /*
     * Method2 : Making a class that stores isBalanced and height of node while
     * coming back => O(n)
     * 
     */

    static class Pair {
        // good default values for null
        int height = -1;
        boolean isBalanced = true;
    }

    // using the same faith
    public static Pair isBalancedBetter(Node node) {
        if (node == null)
            return new Pair();

        Pair myAns = new Pair();

        Pair lPair = isBalancedBetter(node.left);
        if (!lPair.isBalanced) {
            myAns.isBalanced = false;
            return myAns;
        }
        Pair rPair = isBalancedBetter(node.right);
        if (!rPair.isBalanced) {
            myAns.isBalanced = false;
            return myAns;
        }
        int factor = Math.abs(lPair.height - rPair.height);
        if (factor > 1) {
            myAns.isBalanced = false;
            return myAns;
        }
        myAns.height = Math.max(lPair.height, rPair.height) + 1;

        return myAns;
    }
    // At calling function check for result.isBalanced

    // Using the logic of invalid value representation to represnt an invalid case
    // ex height can be from -1 to inf , so we can use -2 to represent not balaced
    // factor, Use height function with further modification => any number except -2
    // represent the height and tree is balanced , -2 represent tree is unbalanced
    // somewhere so we have'nt returned height

    public static int isBalancedBetter2(Node node) {
        if (node == null)
            return -1;

        int lh = isBalancedBetter2(node.left);
        if (lh == -2)
            return -2;
        int rh = isBalancedBetter2(node.right);
        if (rh == -2)
            return -2;

        int factor = Math.abs(lh - rh);
        if (factor > 1)
            return -2;

        int height = Math.min(lh, rh) + 1;
        return height;
    }
    // at calling function :
    // System.out.println(isBalancedBetter2(root)==-2?false:true);

}
