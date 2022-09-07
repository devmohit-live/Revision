public class ConstructBTFromString_606 {
     public String tree2str(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        traverse(root,sb);
        return sb.toString();
        // return returnType(root);
    }
    
    //slower due to new String Formation
    private String returnType(TreeNode root){
        if (root == null) return "";
        
        //root
        String result = root.val + "";
        //;eft and right
        String left = tree2str(root.left);
        String right = tree2str(root.right);
        
        if (left == "" && right == "") 
            return result;
        
        if (left == "") 
            return result + "()" + "(" + right + ")";
        
        if (right == "") 
            return result + "(" + left + ")";
        
        return result + "(" + left + ")" + "(" + right + ")";
    }
    
    private void traverse(TreeNode root, StringBuilder sb){
        if(root == null) return;
       //preorder root, left, right;
        sb.append(root.val);
        
        if(root.left!=null){
            sb.append('(');
            traverse(root.left,sb);
            sb.append(')');
        }
        
        if(root.right!=null){
            if (root.left == null) {
                sb.append("()");
            }
            
            sb.append('(');
            traverse(root.right,sb);
            sb.append(')');
        }
        
    }
}
