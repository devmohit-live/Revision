class LC_1689{
      public int minPartitions(String n) {
        // int max = 0;
        // for(char ch : n.toCharArray()) max = Math.max(max,ch-'0');
        // return max;
        return n.chars().max().getAsInt() - '0';


        // n.chars() -> gives streams's pipeline's head
        //.max() gives maximum in all chars (in ans optional type)
        // .getAsInt() -> gives the ascii codes (getAsChar) not available
        
    }
}