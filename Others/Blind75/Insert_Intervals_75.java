class Insert_Intervals_75{
       
    //Brute: Add the new interval and oldintervals in a new list sort the list and apply merge interval : Time : nlogn
    
    // Optimal : O(n) : itertae and check accordinlgy as it is already sorted and non overlapping
    
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        
        for(int[] in: intervals){
            //normal : not overlapping with new interval
            if(in[1] < newInterval[0]){
                res.add(in);
            }else if(in[0] > newInterval[1]){
                // newinterval is lesser so just swap and add the lesser value : update the ineterval
                
                //basically : Move forward
                res.add(newInterval);
                newInterval = in; // updated
            }else{
                //merging required : update the new interval
                newInterval[0] = Math.min(in[0],newInterval[0]);
                newInterval[1] = Math.max(in[1],newInterval[1]);
                //don't add now as there can be merging to this very interval in later stage
                
            }
        }
        
        
        //add the new interval
        res.add(newInterval);
        
        return res.toArray(new int[res.size()][]);
}