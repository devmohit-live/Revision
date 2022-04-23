public class ModeInBST {
    // LC 501 :

    // find mode in BST in constant space
    // using space: use hashmap and traverse
    HashMap<Integer, Integer> map;

    public int[] findModeONSpace(TreeNode root) {
        map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        int[] max = new int[1];
        inorderFirst(root, max);
        int count = 0;
        for (int key : map.keySet()) {
            if (map.get(key) == max[0])
                list.add(key);
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            ans[i] = list.get(i);
        list = null;
        return ans;
    }

    private void inorderFirst(TreeNode root, int[] max) {
        if (root == null)
            return;
        inorderFirst(root.left, max);
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        max[0] = Math.max(max[0], map.get(root.val));
        inorderFirst(root.right, max);
    }

    private int currVal;
    private int currCount = 0;
    private int maxCount = 0;
    private int modeCount = 0;
    private int[] modes;

    // Using Inorder : have recursive space:
    public int[] findModeInOrder(TreeNode root) {
        inorder(root);
        modes = new int[modeCount];
        modeCount = 0;
        currCount = 0;
        inorder(root);
        return modes;
    }

    private void inorder(TreeNode root) {
        if (root == null)
            return;
        inorder(root.left);
        handleValue(root.val);
        inorder(root.right);
    }

    // Using Morris : even eliminates recursive space
    public int[] findModeMorris(TreeNode root) {
        morris(root);
        modes = new int[modeCount];
        modeCount = 0;
        currCount = 0;
        morris(root);
        return modes;
    }

    private void handleValue(int val) {
        if (val != currVal) {
            currVal = val;
            currCount = 0;
        }
        currCount++;
        if (currCount > maxCount) {
            maxCount = currCount;
            modeCount = 1;
        } else if (currCount == maxCount) {

            if (modes != null) { // cheking for null
                // System.out.println("Inside modeset");
                modes[modeCount] = currVal;
            }

            modeCount++;
        }
    }

    private void morris(TreeNode root) {
        TreeNode node = root;
        while (node != null) {
            if (node.left == null) {
                handleValue(node.val);
                node = node.right;
            } else {
                TreeNode prev = node.left;
                while (prev.right != null && prev.right != node)
                    prev = prev.right;
                if (prev.right == null) {
                    prev.right = node;
                    node = node.left;
                } else {
                    prev.right = null;
                    handleValue(node.val);
                    node = node.right;
                }
            }
        }
    }

}
