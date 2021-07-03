import java.io.*;
import java.util.*;



public class sumOfLargerQuestion {
    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }



    // replace with sum of larger => portal 

    // here we have to replace the elemnt with summatiom(larger elements)

    public static void rwsol(Node node){
    int[] arr=new int[1];
    sumOfLarger(node,arr);
  }
  
  public static void sumOfLarger(Node node,int[] arr){
      if(node==null) return;
      
      sumOfLarger(node.right,arr);
      
      int temp = node.data;
      node.data = arr[0];
      arr[0]+=temp;
      
      
      sumOfLarger(node.left,arr);
  }

//   Add all greater values to every node in a BST GFG
// all greater values in the given BST are added to every node.

// https://practice.geeksforgeeks.org/problems/add-all-greater-values-to-every-node-in-a-bst/1#



// modify the BST and return its root

    public Node modify(Node root) {
        // if(root==null) return null;
        // use of arr of size 1 instead of variable bcz arr builds on heap so will work
        // as global/static here
        int[] arr = new int[1];
        modify(root, arr);
        
        return root;
    }

    // inverse inorder traversal
    public void modify(Node root, int[] arr) {
        if (root == null)
            return;

        modify(root.right, arr);

        root.data += arr[0];
        arr[0] = root.data;

        modify(root.left, arr);
    }



}
