class ZigZag {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
      int lv=0;
      LinkedList<TreeNode>q =new LinkedList<>();
      LinkedList<TreeNode>st =new LinkedList<>();
      q.addLast(root);
      List<List<Integer>> res=new ArrayList<>();
      
      if(root==null) return res;
      
      while(q.size()>0){
      List<Integer> smallAns = new ArrayList<>();
        int curr= q.size();
        while(curr-->0){
          TreeNode rm = q.removeFirst();
          smallAns.add(rm.val);
          
          if(lv%2==0){  //add left then right
            if(rm.left!=null) st.addFirst(rm.left);
            if(rm.right!=null) st.addFirst(rm.right); 
          }else{
            //add right then left
            if(rm.right!=null) st.addFirst(rm.right); 
            if(rm.left!=null) st.addFirst(rm.left);
          }
           
          
        }
        
        LinkedList<TreeNode> tmp =q;
        q=st;
        st=tmp;
        res.add(smallAns); lv++;
        
      }
      
      
      
      return res;
    }
  
    public List<List<Integer>> brute_zigzagLevelOrder(TreeNode root) {
        
      int lv=0;
      LinkedList<TreeNode>q =new LinkedList<>();
      q.addLast(root);
      List<List<Integer>> res=new ArrayList<>();
      if(root==null) return res;
      
      while(q.size()>0){
      List<Integer> tmp = new ArrayList<>();
        int curr= q.size();
        while(curr-->0){
          TreeNode rm = q.removeFirst();
          tmp.add(rm.val);
          
          if(rm.left!=null) q.add(rm.left);
          if(rm.right!=null) q.add(rm.right);
          
        }
        if((lv&1)==1)
          Collections.reverse(tmp);
        
        res.add(tmp); lv++;
        
      }
      
      
      
      return res;
    }

}