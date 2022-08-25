/* IMPORTANT: Multiple classes and nested static classes are supported */

/*
 * uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
import java.util.*;
*/

// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail
import java.util.*;
import java.io.*;

class TestClass {
    public static void main(String args[] ) throws Exception {
        // Write your code here
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        q = Integer.parseInt(br.readLine());

        buildTree();

        for(int i=0;i<q;i++){
            String inpsString = br.readLine();
            String[] inp = inpsString.split(" ");
            int type = Integer.parseInt(inp[0]);
            String s = inp[1];
            int id = Integer.parseInt(inp[2]);

            if(type == 1)
                System.out.println(lock(s,id));
            else if(type == 2)
                System.out.println(unlock(s,id));
            else if(type == 3)
                System.out.println(upgrade(s,id));
      }

    }
    
    private static class Node{
        int lockedBy;
        // Integer isLocked; // main cause 
      boolean isLocked;
      	AtomicInteger lockCount;
        Node parent;
        int desCount;
        String name;
      	Set<String> lockedChildren;

        Node(String name, Node par){
            this.lockedBy = -1;
            // this.isLocked = 0;
          this.isLocked = false;
          this.isInUse = new AtomicInteger(0);
            this.parent = par;
            this.desCount = 0;
          	this.lockedChildren = new HashSet<>();
            this.name = name;
        }

    }

    private static Node root;
    private static Map<String, Node> map = new HashMap<>();
    private static List<Node>nodes = new ArrayList<>();
    // private static List<String>name = new ArrayList<>();
    // private static boolean nodeslocked = false;
    // static Scanner sc = new Scanner(System.in);
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int n, m , q;
    
    private static void buildTree() throws IOException{
        for(int i=0;i<n;i++){
        String s = br.readLine();
        if(i==0){
            Node newnode = new Node(s,null);
            nodes.add(newnode);
            map.put(s,newnode);
            root = newnode;

        }else{
            int pidx  = (i-1)/m;
            Node par = nodes.get(pidx);
            Node newNode = new Node(s,par);
            nodes.add(newNode);
            map.put(s,newNode);
        }
      }

      //add children
      for(int i=0;i<n;i++){
          for(int j=1;j<=m;j++){
              int cidx = m*i + j;
              if(cidx < n){
                  Node par = nodes.get(i);
                  par.children.add(nodes.get(cidx));
              }
          }
      }
    }
   
  
  //Semaphore: allows only k thread to worrk on a resource at a time , here k is 1
  // if thread > 1 if have to stop it somehow
  
  //sempahore : wait(), work, signal()
  // entry section work , critical section, exit section work
  
  
  // variable : V
  // so I have to just avoid the thread from reading the islocked value simultaneously 
// I just had to make other thread wait or return from the execution of nodes' locked value, ie not allowing further into the code.
  // entry exit works on some variable(sema) : 
  // entry  : update v , exit ; update V (producer, consumer problem)
  
  
  
  // Mutual exclusion: usng value of V(no of threads using the resource : int) , 
//  Bounded Wait : other thread should be executed eventually : give true or false  : can use V ?, 
//  Progress : one thread should not stop other from executing if it is not using a resource : no continuous while: if while(condi)
  // ie whle(V) : V operation should be atomic and must be updated in extry and exit section
  
  // V =>  should be an Integer to know how many threads are there : will allow only one
  
  // enrty and exit should be able to update it atomitically
  
  
//   ********************************** Approach 1 : Using Integer to count the no of threads accessing the node to be locked ********
  
  
    private static boolean lock(String s, int uid){
        Node curr = map.get(s);
        if(curr == null) return false;
      
      	curr.lockCount.getAndIncrement(); // increase the counter : to check for race condition
        // race check
        if (curr.lockCount.get() > 1) {
            curr.lockCount.decrementAndGet();
            return false;
        }
      
      	  if (curr.isLocked)
            return false; // already locked
        // check for desendants
       	 if (curr.desCount > 0) {
            curr.isLocked = false; //thread nopde is unlocked
            return false;
       	 }
      
      
       //ancestors
        Node par = curr.parent;
        while (par != null) { // this condition will help me in race for parent and child in threads
            if (par.isLocked || par.lockCount.get() > 0) {
                return false;
            }

            par = par.parent;
        }
        // every coondition is followed: lock this node and increase it;s parent desc
        // count
        curr.lockedBy = uid;
        curr.isLocked = true;

        par = curr.parent;
        while (par != null) {
            par.desCount++;
            par = par.parent;
        }

        return true;
      
      
      

//       //check for ansectors
//         while(par != null){
//             if(par.isLocked >= 1 ){ // already in use by some other thread or parent is already locked
//               curr.isLocked = 0; //exit condition
//               return false;
//             }
//             par = par.parent;
//         }

//        // critical 
//         curr.lockedBy = uid;
//         curr.isLocked = 1; // cam only be acquired by a single lock


//         par = curr.parent;
      
//       //crirical
//         while(par != null){
//           if(par.isLocked  >= 1 ){
//               curr.lockedBy = -1;
//         			curr.isLocked = 0;
//             	return false;
//           }
//             par.desCount++;
//           	par.lockedChildren.add(curr.name); // alll ancestors will have the information of the locked desendants
//             par = par.parent;
//         }

//         return true;
    }
  
  
  
  
  
  
// //   ************** Approach 2 : making the threads to wait if resource it wants is already in use ******************/
  
//   private static boolean lock_(String name, int uid){
//       Node curr = map.get(s);
//         if(curr == null) return false;
//     		List<Node> inuse = new ArrayList<>();
//     	//race t1 ,t2
//     while(curr.isInUse.get() > 0);
// 		// t1,t2
//     curr.isInUse.incrementAndGet();
    
//       if(curr.isLocked){
//         if(curr.isInUse.get() > 1)
//         	return false;
//       }


//       if(curr.desCount > 0){
//         curr.isInUse = false;
//         return false;
//       }

//        // check for ansectors
//           while(par != null){
//             while(par.isInUse);
//             		par.isInUse  = true;
//             		inUse.add(par);
//               if(par.isLocked ){ // already in use by some other thread or parent is already locked
                
//                 par.isInUse = false;
//                 curr.isInUse = false;
//                 for(Node node : inUse) node.isInUse = false;
//                 return false;
//               }
//               // par.isInUse = false;
//               par = par.parent;
//           }
// 					// for(Node node : inUse) node.isInUse = false;
//          // critical 
//           curr.lockedBy = uid;
//           curr.isLocked = true; // cam only be acquired by a single lock

//           par = curr.parent;
      
//       //update desc coutn and locked children
    
//         while( par != null){
//           while(par.isInUse);
//          		par.isInUse = true;
//             par.desCount++;
//           	par.lockedChildren.add(curr.name); // alll ancestors will have the information of the locked desendants
//           	// par.isInUse = false;
//             par = par.parent;
          
//         }
    
//     		for(Node node : inUse) node.isInUse = false;

//         return true;
//   }


    private static boolean unlock(String s, int uid){
        Node curr = map.get(s);
        if(curr == null) return false;
        if(curr.isLocked == 0) return false; //read
        if(curr.lockedBy != uid) return false;

      	//write
        curr.isLocked = 0;
        curr.lockedBy = -1;

        Node par = curr.parent;
        while(par != null){
            par.desCount--;
          	par.lockedChildren.remove(curr.name);
            par = par.parent;
        }

        return true;
    }


  // lockenodes * logn + log n + lockedndoes = > O(lockednodes * logn)
    private static boolean upgrade(String s, int uid){
        Node curr = map.get(s);
        if(curr == null) return false;
        if(curr.isLocked) return false;
        if(curr.desCount == 0) return false;
			
        if(checkDesc(curr,uid)){ // lockedndoes
            setDesc(curr, id); // lockednodes * log n 
            curr.isLocked = true;
            curr.lockedBy = uid;
            Node par = curr.parent;
          
          
            while(par != null){ // log n
                par.desCount++;
                par = par.parent;
            }
            return true;
        }

        return false;
    }

    private static boolean checkDesc(Node node, int id){
        if(node == null) return false;
        boolean hasALockedNode = false;

        // for(int i=0;i<node.children.size();i++){ // m -> no of locked nodes 
        //     Node child = node.children.get(i);
        //     if(child.isLocked){
        //         if(child.lockedBy != id) return false;
        //         nodeslocked = true;
        //     }else if( child.desCount > 0){
        //         ans = ans || nodeslocked || checkDesc(child, id); // log n
        //     }
        // }
        // return ans;
      
      	for(String name : node.lockedChildren){ //lockedndoes
          Node mynode = map.get(name);
              if(mynode.lockedBy != id) return false;
              hasALockedNode = true;
        }
      	return hasALockedNode;
    }
  

  	// lockednodes *  log n
    private static void setDesc(Node node, int id){
        if(node == null) return;

        // for(Node child: node.children){
        //     if(child.isLocked){
        //         child.isLocked = false;
        //         child.lockedBy = -1;
        //         Node par = child.parent;
        //         while(par != null){
        //             par.desCount--;
        //             par = par.parent;
        //         }
        //     }else if(child.desCount > 0){
        //         setDesc(child);
        //     }
        // }
      
      	 	for(String name : node.lockedChildren){ // locked nodes
          	Node mynode = map.get(name);
            unlock(mynode, id); // log n 
        }
      
    }

}
