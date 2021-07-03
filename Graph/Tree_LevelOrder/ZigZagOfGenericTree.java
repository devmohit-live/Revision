package Tree_LevelOrder;

import java.util.*;

class ZigZagOfGenericTree {

  // portal
  public static void levelOrderLinewiseZZ(Node node) {
    int lv = 0;
    LinkedList<Node> q = new LinkedList<>();
    q.addLast(node);

    while (q.size() > 0) {
      int curr = q.size();
      ArrayList<Integer> prints = new ArrayList<>();

      while (curr-- > 0) {
        Node rm = q.removeFirst();
        prints.add(rm.data);
        for (Node child : rm.children)
          q.addLast(child);

      }
      if ((lv & 1) == 1) {
        // o(n) in reverse
        Collections.reverse(prints);
      }
      for (int item : prints)
        System.out.print(item + " ");

      System.out.println();
      lv++;

      /*
       * old : Wrong bcz at level=2 and onwards the addition order of children was
       * disturbed for further children will print in different orders => that's why
       * we are using stack and q in econd approach while(curr-->0){ Node rm =
       * q.removeFirst();
       * 
       * System.out.print(rm.data+" ");
       * 
       * if((lv&1)==1){ for(Node child: rm.children) q.add(child); }else{
       * 
       * for(int i=rm.children.size()-1;i>=0;i--){ q.add(rm.children.get(i) ); }
       * 
       * }
       * 
       * } System.out.println(); lv++;
       */

    }

  }

  // Different Way (Class): Using LinkedList for swapping st and q

  // add children to stack and after adding all children of a node swap the ref of
  // q and st

  // addition of children will be according to level =>

  // level ==even => left to right
  // level -- odd => right to left

  // (same approach used for generic tree)

  public static void levelOrderLinewiseZZ2(Node node) {
    int lv = 0;
    LinkedList<Node> q = new LinkedList<>();
    LinkedList<Node> st = new LinkedList<>(); // addfirst, removefirst
    q.addLast(node);

    while (q.size() > 0) {
      int curr = q.size();

      while (curr-- > 0) {
        Node rm = q.removeFirst();
        System.out.print(rm.data + " ");
        if (lv % 2 == 0) {
          for (Node child : rm.children)
            st.addFirst(child);
        } else {
          for (int i = rm.children.size() - 1; i >= 0; i--) {
            // adding in rev order
            Node child = rm.children.get(i);
            st.addFirst(child);
          }
        }

      }
      // /swapping refrence

      LinkedList<Node> tmp = q;
      q = st;
      st = tmp;

      System.out.println();
      lv++;

    }

  }

}