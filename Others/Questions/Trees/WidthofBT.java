package Questions.Trees;

public class WidthofBT {
    // based on the fact the max (width of each column/level) (we can get the number
    // of element on a particular level(not null) (can be obtained from the idx if
    // represented in array storage form))

    // representaion of bt in array form
    class Pair {
        TreeNode node;
        int idx;

        Pair() {
            this(null, -1);
        }

        Pair(TreeNode node, int idx) {
            this.node = node;
            this.idx = idx;
        }

    }

    public int widthOfBinaryTree(TreeNode root) {
        int max = 0,level=0;
        LinkedList<Pair> q = new LinkedList<>();
        q.addLast(new Pair(root,0)); //root => idx =0
        // left child => 2*rootidx +1;
        // right child => 2*rootidx +2;
        
        //bfs : (find leftmost and rightmost nodes of a level (not nill))
        // widht : diff between rightmost and leftmost +1;(contains both)
        
        //bfs
        while(q.size()>0){
            int size = q.size();
            int leftmost = q.getFirst().idx; // first memeber of a level is leftmsot
            int rightmost = q.getLast().idx; // last memeber of a level is rightmost
            max = Math.max(rightmost - leftmost +1, max);
            
            while(size-->0){
                Pair rm = q.removeFirst();
                int idx = rm.idx;
                TreeNode node = rm.node;
                if(node.left!=null) q.addLast( new Pair(node.left,2*idx +1) );
                if(node.right!=null) q.addLast( new Pair(node.right,2*idx +2) );
            }
            
            level++;
        }
        
     return max;   
    
}
