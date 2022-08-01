class MyCalendar {
    // private Map<Integer,Integer> map; // appraoch 1
    private TreeMap<Integer,Integer> map ; // TreeMap container clas to use floorkey / lowerkey as Map interface doesb't provides that
    
    public MyCalendar() {
        this.map = new TreeMap<>();
    }
       
    
    public boolean book(int start, int end){
           return appraoch2(start,end); 
           // return appraoch1(start,end); 
    }
//     Approach 2: 
    {
           //get the nearest previous meeting entry
        //get next (greater) meeting , if that's start time(key) is greater that mine end time : overlapping
    }
    private boolean appraoch2(int currStart, int currEnd){
        // start time is the key in map
        
        Map.Entry<Integer,Integer> floorEntry = map.floorEntry(currStart); // get k,v pair for key <= s
       Map.Entry<Integer,Integer> ceilEntry = map.ceilingEntry(currStart);
        
        // floorEntry.getValue() : correspoding end time of s 
  
        int previousStart = floorEntry != null ? floorEntry.getKey() : -1;
        int previousEnd = floorEntry != null ? floorEntry.getValue(): -1;
        int nextStart = ceilEntry!=null ? ceilEntry.getKey() : Integer.MAX_VALUE;
        int nextEnd = ceilEntry!=null ? ceilEntry.getValue() : Integer.MAX_VALUE;
        
        
        if(previousEnd > currStart || currEnd > nextStart) return false;
        
        // all are valid;
        map.put(currStart, currEnd);
        
        return true;
    }
    
    //nlogn
      //Approach 1 : 
    {
        // fails in Java : for HashMap and LinkedHashMap
    // We have to use TreeMap => nlogn => tle
        // Invlid consitions : s1 s2 e1 e2 or
    // s1 s2 e2 e1 
     // so basicallly we can say one pair(s,e) should complete each other without any intervention to be valid
    
    // it can be viewwed as s= ( ans e = )  or s= +1 , e= -1
    // if at eny time sum > 1 => invalid 
    
    //exapmle: s1 s2 e1 s2 => +1 +1 -1 -1 => after s1 ,s2  we have sum = 2
    // s1 s2 e2 e1 => same here after s1 s2 we have 2;
    
    //NOTE : they are represented on number line : ie on sorted order
    
    }
    private boolean appraoch1(int start, int end) {
        map.put(start, map.getOrDefault(start,0)+1); //logn
        map.put(end, map.getOrDefault(end,0)-1); //logn
        
        int sum = 0;
        
        // EntrySet complexity for Maps(hashMap, Linked or Treemap as Same 
        {
            // The point at which you call entrySet() there is no (significant) work to do      beyond handing back a reference, so it's constant time. The map maintains sorted        behavior as things are added to / removed from the TreeMap.

        }
        
        //n* 2logn
        
        for(Map.Entry<Integer,Integer> e: map.entrySet()){ // n
            sum+=e.getValue();
            if(sum>1){
                //revert changes
                map.put(start, map.getOrDefault(start,0)-1); //logn
                map.put(end, map.getOrDefault(end,0)+1); //logn
                return false;
            }
        }
        
        return true;
    }
    
}
