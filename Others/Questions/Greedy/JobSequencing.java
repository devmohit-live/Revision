public  class JobSequencing {
    int[] JobScheduling(Job arr[], int n)
    {
        int max = -1, deadline_limit = -1;
        int profitsf= 0, count =0;
        Arrays.sort(arr,(a,b)->{
            int profit1 = a.profit, profit2 = b.profit;
            return profit2 - profit1;
        });
        
        for(Job job: arr){
            deadline_limit = Math.max(deadline_limit,job.deadline);
        }
        // to make it easy(since deadline is 1 based)
        int[] deadlines = new int[deadline_limit+1];
        Arrays.fill(deadlines,-1);
        
        for(Job job: arr){
            int id = job.id, deadline = job.deadline, profit = job.profit;
            while(deadline>=1 && deadlines[deadline]!=-1) deadline-- ;
            if(deadline>0 && deadlines[deadline]==-1){
                deadlines[deadline] = id;
                count++;
                profitsf+=profit;
            }
        }
        return new int[]{count,profitsf};
    }
}
