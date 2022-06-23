class CourseScheduleIII_60{
    class Solution {
    public int scheduleCourse(int[][] courses) {
        int n = courses.length;
        Arrays.sort(courses, (a,b)->a[1]-b[1]); //ascending order of endtime/deadline
        // int maxtime = courses[n-1][1];
        // int[][] dp = new int[n+1][maxtime+1];
        // for(int[] d: dp) Arrays.fill(d,-1);
        
        // return knapSack(courses,0, 0, dp);
        return optimal_greedy(courses);
        
    }
    // time is bag size, max course number is value, course time is the weight of each stone. It's a DP problem.
    
    //time : nlogn +  2^n, space : n
    private int knapSack(int[][] courses, int time, int idx, int[][] dp){
        
        if(idx == courses.length) return dp[idx][time] = 0;
        
        if(dp[idx][time]!= -1) return dp[idx][time];
        
        int[] course = courses[idx];
        // net time = tiem + durstion(c[0])
        // deadline for this course : c[1]
        
        //chosse
        if(time+course[0] <= course[1])
            dp[idx][time] = 1 + knapSack(courses,time+course[0], idx+1,dp);
        
         //not choose
        dp[idx][time] = Math.max(dp[idx][time], knapSack(courses, time, idx+1,dp));
        
       return dp[idx][time];
    }
    
    //greedy O(nlogn) + o(nlogn) space:O( n)
    /*
    Take the earliest course that can be finished, otherwise, remove the longest course, and replaced it with multiple shorter courses. since the max course number is the goal.
    */
    
    private int optimal_greedy(int[][] courses){
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        int time = 0;
        
        for(int[] c : courses){
            time+=c[0];
            pq.add(c[0]);
            if(time> c[1]) // if time exceedes the deadline
                time-=pq.remove(); //remove the largeest duration(since we just have to increase the no. of courses)
        }
            
        // pq contains the no of completed courses
        //time : is the time taken to complete all pq.size() courses
        return pq.size();
    }
}
}