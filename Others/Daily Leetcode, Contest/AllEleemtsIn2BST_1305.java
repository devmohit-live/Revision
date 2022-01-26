import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.tree.TreeNode;

class AllEleemtsIn2BST_1305{
    //Approach1 : 
    // Time: O(n) + O(m) + O(n+m) => O(n+m) : inorder + inorder + merge
    //Space: O(n) + O(m) + O(n+m) => O(n+m) : listA + listB + ListAns
     public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> ans = new ArrayList<>();
        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();
        inorder(root1,a);
        inorder(root2,b);
        mergeLists(a,b,ans);
        return ans;
    }
    
    //O(n): skew tree
    private void inorder(TreeNode root, List<Integer> ans){
        if(root == null) return;
        
        inorder(root.left,ans);
        ans.add(root.val);
        inorder(root.right,ans);
    }
    
    //O(m+n)
    private void mergeLists(List<Integer> list1,List<Integer> list2,List<Integer> ans){
        int n = list1.size(), m = list2.size(), i =0, j=0;
        while(i<n && j<m){
            if(list1.get(i)<= list2.get(j)) ans.add(list1.get(i++));
            else ans.add(list2.get(j++));
        }
        
        while(i<n) ans.add(list1.get(i++));
        while(j<m) ans.add(list2.get(j++));
    }

   
    
}