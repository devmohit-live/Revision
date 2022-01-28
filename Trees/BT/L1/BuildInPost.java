class BuildInPost {
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

    // Approach 2: using global/static varibale not recommended
    int idx; // global vari to store the idx of root in post
    // global to get the changes reflected in recursion

    public TreeNode buildTree01(int[] inorder, int[] postorder) {
        int n = inorder.length;
        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        idx = n - 1;
        return make_tree(0, n - 1, postorder, inorder);

    }

    private TreeNode make_tree(int start, int end, int[] postorder, int[] inorder) {
        // If range for inorder is NOT valid then return NULL
        if (start > end)
            return null;

        // Create a node for our root node of current subtree
        TreeNode root = new TreeNode(postorder[idx]);

        // Find position of current root in inorder array
        int inorder_root_idx = map.get(root.val);

        idx--; // update the idx of root

        // Make a call to create right subtree, inorder range [i+1, end]
        root.right = make_tree(inorder_root_idx + 1, end, postorder, inorder);

        // Make a call to create left subtree, inorder range [start, i-1]
        root.left = make_tree(start, inorder_root_idx - 1, postorder, inorder);

        return root;
    }

}