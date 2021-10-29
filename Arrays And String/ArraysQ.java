class ArraysQ{
    //Leetcode 925 : Long Presses Name : Faulty Keyboard
      public boolean isLongPressedName(String name, String typed) {
      int n = name.length(), m= typed.length();
      
      if(n>m) return false;
      
      int l=0,i=0;
        while(i<n){
          char c = name.charAt(i);
           int count1=0,count2=0;
          //name
          while(i<n && name.charAt(i)==c){
            count1++;
            i++;
          } 
          //typed
          while(l<m && typed.charAt(l)==c) {
            count2++;
            l++;
          }
          // System.out.println("Character is "+c+" count for name is "+count1+" typed is "+count2);
          if(count1>count2) return false;

        }
      return l!=m?false:true;
    }

    //Leetcode 11: Container With Most Water
}