public class AreTreeSame {
    public static boolean areSimilar(Node n1, Node n2) {
        if (n1.children.size() != n2.children.size())
            return false;

        boolean res = true;
        // using this we don;t have to manually check for if(!status) return false;

        for (int i = 0; i < n1.children.size(); i++) {
            res = res && areSimilar(n1.children.get(i), n2.children.get(i));
        }
        return res;
    }

    // just shape
    public static boolean areMirror(Node n1, Node n2) {
        if (n1.children.size() != n2.children.size())
            return false;

        boolean res = true;
        // using this we don;t have to manually check for if(!status) return false;

        for (int i = 0; i < n1.children.size(); i++) {
            int sizeofn2 = n2.children.size() - 1;
            res = res && areMirror(n1.children.get(i), n2.children.get(sizeofn2 - i));
        }
        return res;
    }

    // including data too
    public static boolean areMirror2(Node n1, Node n2) {
        if (n1.children.size() != n2.children.size() && n1.data != n2.data)
            return false;

        boolean res = true;
        // using this we don;t have to manually check for if(!status) return false;

        for (int i = 0; i < n1.children.size(); i++) {
            int sizeofn2 = n2.children.size() - 1;
            res = res && areMirror(n1.children.get(i), n2.children.get(sizeofn2 - i));
        }
        return res;
    }

    /*
     * . For knowing symmetricity think of face and hand. Face is symmetric while
     * palm is not. Also, we are check only smmetricity of shape and not content.
     */
    public static boolean IsSymmetric(Node node) {
        // tree should be mirror of itself
        if (areMirror(node, node))
            return true;
        return false;
    }

    static int ceil;
    static int floor;

    public static int[] ceilAndFloor(Node node, int data) {
        ceil = (int) (1e9);
        floor = -(int) (1e9);
        ceilAndFloorHelper(node, data);
        return new int[] { ceil, data };

    }

    // 110 leetcode for BT
    public static void ceilAndFloorHelper(Node node, int data) {
        // IN CASE OF EQUAL DATA ASK FOR WHAT SHOULD BE RETURNED
        if (node.data <= data) {
            floor = Math.max(node.data, floor);
        }
        if (node.data >= data) {
            ceil = Math.min(node.data, ceil);
        }

        for (Node child : node.children) {
            ceilAndFloor(child, data);
        }

    }

    // Using real recursion floor =>
    // Faith child should return max of those numbers which is less than upper bound
    // in his family
    // I will compare it with myself if I am also less than the upper bound

    static int floor(Node node, int up) {
        int maxRes = -(int) 1e9;

        // faith
        for (Node child : node.children) {
            int recRes = floor(child, up);
            maxRes = Math.max(maxRes, recRes);
        }

        // self work
        if (node.data < up) {
            maxRes = Math.max(maxRes, node.data);
        }
        return maxRes;
    }

    // kth largest using recursion without using any class variables and heap
    static int kthLargest(Node node, int k) {
        int ub = (int) (1e9);
        for (int i = 0; i < k; i++) {
            ub = floor(node, ub);
        }
        return ub;
    }

      public static void traversals(Node node){
    System.out.println("Node Pre " + node.data);

    for(Node child: node.children){
      System.out.println("Edge Pre " + node.data + "--" + child.data);
      traversals(child);
      System.out.println("Edge Post " + node.data + "--" + child.data);
    }

    System.out.println("Node Post " + node.data);
  }

  public static void levelOrderLinewiseZZ(Node node){
    Stack<Node> stack = new Stack<>();
    stack.add(node);

    Stack<Node> cstack = new Stack<>();
    int level = 0;

    while(stack.size() > 0){
      node = stack.pop();
      System.out.print(node.data + " ");

      if(level % 2 == 0){
        for(int i = 0; i < node.children.size(); i++){
          Node child = node.children.get(i);
          cstack.push(child);
        }
      } else {
        for(int i = node.children.size() - 1; i >= 0; i--){
          Node child = node.children.get(i);
          cstack.push(child);
        }
      }

      if(stack.size() == 0){
        stack = cstack;
        cstack = new Stack<>();
        level++;
        System.out.println();
      }
    }
  }

  public static void createMirror(Node node){
    if(node == null) return ;
    for(Node child : node.children) createMirror(child);
    Collections.reverse(node.children);

  }
}
