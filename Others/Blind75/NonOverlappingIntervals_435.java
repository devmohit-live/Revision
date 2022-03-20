public class NonOverlappingIntervals_435 {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->{
            return a[1] - b[1];
        });
        
        int count = 0, ltime = -(int)1e8, n = intervals.length;
        for(int i=0;i<n;i++){
            
            if(intervals[i][0] <  ltime) count++;
            
            else ltime = intervals[i][1];
        }
        
        return count;
    }
}
