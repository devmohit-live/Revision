class ZigZagOfGenericTree{
    //portal
      public static void levelOrderLinewiseZZ(Node node){
      int lv=0;
      LinkedList<Node>q =new LinkedList<>();
      q.addLast(node);
      
      while(q.size()>0){
        int curr= q.size();
        ArrayList<Integer> prints=new ArrayList<>();
        
        while(curr-->0){
            Node rm = q.removeFirst();
            prints.add(rm.data);
            for(Node child: rm.children) q.addLast(child);
            
        }
        if((lv&1)==1){
            Collections.reverse(prints);
        }
        for(int item : prints) System.out.print(item+" ");
        
        System.out.println();lv++;
        
        
     /*old : Wrong bcz at level=2 and onwards the addition order of children was disturbed for further children will print in different orders
        while(curr-->0){
          Node rm = q.removeFirst();
          
          System.out.print(rm.data+" ");
          
         if((lv&1)==1){
                for(Node child: rm.children) q.add(child);
         }else{
         
              for(int i=rm.children.size()-1;i>=0;i--){
                 q.add(rm.children.get(i) );
             }
             
         }
          
        }
       System.out.println(); lv++;
     */
        
      }
      
  }


  //Different Way (Class):


  

}