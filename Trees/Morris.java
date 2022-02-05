import java.util.List;
import java.util.ArrayList;

public class Morris {

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }

    }

    public static TreeNode getRightMostNode(TreeNode node, TreeNode curr) {
        // node == curr : means reached to parent (as we have attached a thread)and have
        // to stop
        // :else it will keep on going to tree's rightmost node
        while (node.right != null && node.right != curr) {
            node = node.right;
        }
        return node;
    }

    public static List<Integer> morrisInorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                ans.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode rightmostNode = getRightMostNode(left, curr);
                if (rightmostNode.right == null) {
                    // create thread
                    rightmostNode.right = curr;
                    curr = curr.left;
                } else {
                    // rihtmost.right == curr (already)
                    // delete thread
                    rightmostNode.right = null;
                    // print curr
                    ans.add(curr.val);
                    curr = curr.right;
                }

            }

        }

        return ans;
    }

    public static List<Integer> morrisPreordreTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        TreeNode curr = root;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                ans.add(curr.val);// leave nodes
                curr = curr.right;
            } else {
                TreeNode rightmostNode = getRightMostNode(left, curr);
                if (rightmostNode.right == null) {
                    ans.add(curr.val);
                    // create thread : crearion is done in preorder
                    rightmostNode.right = curr;
                    curr = curr.left;
                } else {
                    // rihtmost.right == curr (already)
                    // delete thread
                    rightmostNode.right = null;
                    // print curr
                    // ans.add(curr.val);
                    curr = curr.right;
                }

            }

        }

        return ans;
    }

    // is valid bst : 3 ways
    // 1 using recursion
    private boolean validate(TreeNode root, TreeNode[] prev) {
        boolean res = true;
        if (root == null)
            return res;

        res = res && validate(root.left, prev);
        if (prev[0] != null && prev[0].val >= root.val)
            res = res && false;
        prev[0] = root;
        res = res && validate(root.right, prev);

        return res;
    }

    // using stack
    public boolean isValidBST2(TreeNode root) {
        Integer pre = null;
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (stack.isEmpty()) {
                return true;
            }
            TreeNode node = stack.pop();
            if (pre != null && pre >= node.val) {
                return false;
            }
            pre = node.val;
            root = node.right;
        }
    }
    // using morris

    private boolean validateMorris(TreeNode root) {
        if (root == null)
            return true;

        TreeNode curr = root;
        long prev = -(long) 1e17;

        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                // ans
                if (prev >= curr.val)
                    return false;
                prev = curr.val;

                curr = curr.right;

            } else {
                TreeNode righMost = getRightMost(left, curr);
                if (righMost.right == null) {
                    // thread creation
                    righMost.right = curr;
                    curr = curr.left;

                } else {
                    // thread deletion
                    righMost.right = null;

                    // work.ans

                    if (prev >= curr.val)
                        return false;
                    prev = curr.val;

                    // right call
                    curr = curr.right;
                }

            }

        }

        return true;
    }
    // Kth smallest elemet in BST using Morris : no need of recursive space, static
    // var/array of size 1 for k

    // can also be done itertaively in contant space: Using stack

    private int morris(TreeNode root, int k) {
        TreeNode curr = root;
        while (curr != null) {
            TreeNode left = curr.left;
            if (left == null) {
                if (--k == 0)
                    return curr.val;
                curr = curr.right;
            } else {
                TreeNode rightmost = getRightMost(left, curr);
                if (rightmost.right == null) {
                    // thread creation
                    rightmost.right = curr;
                    // go towards left
                    curr = curr.left;
                } else {
                    // thread deletion
                    rightmost.right = null;

                    // go to right now (right call in rec)
                    // work
                    if (--k == 0)
                        return curr.val;
                    curr = curr.right;
                }
            }
        }

        return -1; // null
    }

    // Kth largest element: run reverse Inorder
    // In morris: jusr swap left and right => right,left calls and l=rihtmost =
    // leftmost
    private Node getLeftMost(Node node, Node curr) {
        while (node.left != null && node.left != curr)
            node = node.left;
        return node;
    }

    private int morrisReverseInorder(Node root, int k) {
        if (root == null || k < 0)
            return -1;

        // just make reverse calls : right first

        Node curr = root;
        while (curr != null) {
            Node right = curr.right;
            if (right == null) {
                if (--k == 0)
                    return curr.data;
                curr = curr.left;
            } else {
                Node leftmost = getLeftMost(right, curr);
                if (leftmost.left == null) {
                    // thread creation
                    leftmost.left = curr;
                    curr = curr.right;
                } else {
                    // thread deletion
                    leftmost.left = null;

                    // inorder and right call : inversae means left
                    if (--k == 0)
                        return curr.data;
                    curr = curr.left;
                }
            }
        }

        return -1;
    }

    // BST Iterator;

    // BST to DLL

    class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public static Node bstToDLL(Node root) {
        if (root == null)
            return null;
        Node dummy = new Node(-1), prev = dummy;

        Node curr = root;
        while (curr != null) {
            Node left = curr.left;
            if (left == null) {
                // work
                prev.right = curr; // next
                curr.left = prev; // prev
                prev = curr;
                // right call
                curr = curr.right;
            } else {
                Node rightmost = getRightMost(left, curr);
                if (rightmost.right == null) {
                    // thread creation
                    rightmost.right = curr;

                    curr = curr.left;

                } else {
                    rightmost.right = null;
                    // work
                    prev.right = curr; // next
                    curr.left = prev; // prev
                    prev = curr;
                    // right call
                    curr = curr.right;
                }
            }
        }

        Node head = dummy.right;
        head.left = dummy.right = null;

        // https://www.lintcode.com/problem/1534/
        // //cicular link
        // head.left = prev;
        // prev.right = head;

        return head;

    }

    //BST Iterator: LC: 173. Binary Search Tree Iterator
    class BSTIterator {
        // Using morris
        private TreeNode curr;

        private TreeNode getRightMost(TreeNode node, TreeNode curr) {
            while (node.right != null && node.right != this.curr)
                node = node.right;
            return node;
        }

        public BSTIterator(TreeNode root) {
            this.curr = root;
        }

        public int next() {
            int rv = -1;
            while (this.curr != null) {
                TreeNode left = this.curr.left;
                if (left == null) {
                    rv = this.curr.val;
                    this.curr = this.curr.right;
                    break;
                } else {
                    TreeNode rightmost = getRightMost(left, this.curr);
                    if (rightmost.right == null) {
                        // thread creation
                        rightmost.right = curr;
                        this.curr = this.curr.left;
                    } else {
                        // thread deletion
                        rightmost.right = null;

                        // work
                        rv = this.curr.val;
                        // call
                        this.curr = this.curr.right;

                        break;
                    }
                }
            }

            return rv;
        }

        public boolean hasNext() {
            return this.curr != null;
        }
    }

}
