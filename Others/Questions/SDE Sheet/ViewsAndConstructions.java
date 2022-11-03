public class ViewsAndConstructions {
    // Vertical Order
    class vpair {
        TreeNode node;
        int vlevel;

        vpair(TreeNode node, int vlevel) {
            this.node = node;
            this.vlevel = vlevel;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        int[] minmax = new int[2];
        vminmax(root, 0, minmax);
        int length = minmax[1] - minmax[0] + 1;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < length; i++)
            ans.add(new ArrayList<>());

        PriorityQueue<vpair> q = new PriorityQueue<>((a, b) -> {
            return a.node.val - b.node.val; // since we are maintains the level in vpair so even the
            // node with lesser value will be removed fist even thoug it has higher level
            // thean others it will be added to the correct(it's appropriate arraylist) we
            // are just ensuring that lesser value will be added to the same level first
        });

        // to avoid mix and match of a level elemets for(size--)(to determine the level
        // wise filling correctly we are using this q to fill child : after each level
        // it will become parent)
        PriorityQueue<vpair> childq = new PriorityQueue<>((a, b) -> {
            return a.node.val - b.node.val;
        });

        q.add(new vpair(root, Math.abs(minmax[0])));
        while (!q.isEmpty()) {
            int size = q.size();

            while (size-- > 0) {
                vpair rm = q.remove();
                TreeNode node = rm.node;
                int level = rm.vlevel;

                ans.get(level).add(node.val); // add to it's appropriate level

                // add children to child q
                if (node.left != null)
                    childq.add(new vpair(node.left, level - 1));
                if (node.right != null)
                    childq.add(new vpair(node.right, level + 1));

            }
            // end of a level
            PriorityQueue<vpair> tmp = childq;
            childq = q;
            q = tmp;

        }

        return ans;
    }

    private static void vminmax(TreeNode root, int level, int[] minmax) {
        if (root == null)
            return;
        minmax[0] = Math.min(minmax[0], level);
        minmax[1] = Math.max(minmax[1], level);

        vminmax(root.left, level - 1, minmax);
        vminmax(root.right, level + 1, minmax);
    }

    // Top View:
    public static ArrayList<Integer> topView(Node root) {
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
                // the first elemet in the vertical traversal is the top view
                // the last eleemtn in the vertical traversal is the top view
                if (res.get(level) == 0)
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

    static class vpair {
        Node node;
        int vlevel;

        vpair(Node node, int vlevel) {
            this.node = node;
            this.vlevel = vlevel;
        }
    }

    // Bottom view :
    public ArrayList<Integer> bottomView(Node root) {
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
    // Pre and Inorder Construction :
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length, m = inorder.length;
        if (m != n)
            return null;

        return preIn(inorder, preorder, 0, n - 1, 0, n - 1);
    }

    private TreeNode preIn(int[] in, int[] pre, int isi, int iei, int psi, int pei) {

        if (isi > iei || psi > pei)
            return null;

        TreeNode root = new TreeNode(pre[psi]);
        int rootIdx = isi;
        while (rootIdx <= iei && in[rootIdx] != root.val)
            rootIdx++;

        int noelLeft = rootIdx - isi;

        root.left = preIn(in, pre, isi, rootIdx - 1, psi + 1, psi + noelLeft);
        root.right = preIn(in, pre, rootIdx + 1, iei, psi + noelLeft + 1, pei);

        return root;
    }
    //
    // post order: left right root (root at last, left subtree of root at start)
    HashMap<Integer, Integer> map; // to get the root idx from inorder in constant time

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        map = new HashMap<>();
        for (int i = 0; i < n; i++)
            map.put(inorder[i], i);

        return buildTree(inorder, postorder, 0, n - 1, 0, n - 1);

        // post[n-1]=> root
        // find the idx of root in inorder
        // INORDER:
        // left subtrre = si-> rootidx-1, right subtree => rootidx+1->ei

        // POSTORDER:
        // leftsubtree : si(0 at start)-> start of rightsuubtree-1
        // rightsubtree : start of rightsubtree->ei(n-2 at start)

        // how do we know the range? => since we already know the number of leftsubtree
        // and right subtree elements from the INORDER data we can use that
        // totalnumber = no. of el in leftsubtree, startof range = psi+tatoal-1

        // post left-subtree => psi -> psi+(totalnumber)-1( -1 bcz index is required)
        // post right-subtree => posi+totalcount->pei(which would be pe--)
        // (why pei--) bcz at every stage pei is the root in postorder

    }

    private TreeNode buildTree(int[] in, int[] post, int insi, int inei, int posi, int poei) {
        if (posi > poei || insi > inei)
            return null;

        int rootval = post[poei];
        TreeNode root = new TreeNode(rootval);
        int inroot_idx = map.get(root.val);

        int total_num_lst = inroot_idx - insi;
        // Inorder range :
        // left-subtree = si-> rootidx, right-subtree=> rootidx+1->ei

        // Postorder rang for same: leftsubtree->rightsubtree->root
        // left-subtree = psi-> psi+totalleftel-1, right-subtree=>totalleftel->pei-1
        // (pei is root)

        root.left = buildTree(in, post, insi, inroot_idx - 1, posi, posi + total_num_lst - 1);
        root.right = buildTree(in, post, inroot_idx + 1, inei, posi + total_num_lst, poei - 1);

        return root;

    }

}
