import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MeetingRooms {

    class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

    }

    // meeting rooms 1 : A person can attend all the meetings or not : (x,y) and
    // (y,z) are non overlapping here

    // Lintcode :https://www.lintcode.com/problem/920/

    public boolean canAttendMeetings(List<Interval> intervals) {
        int letime = -1;
        Collections.sort(intervals, (a, b) -> {
            return a.end - b.end;
        });

        for (Interval in : intervals) {
            if (in.start < letime)
                return false;

            letime = in.end;
        }

        return true;
    }

    //Meeting Rooms 2 : minimum number of conference rooms required(no of conflicts / merges)
    // Lintcode:https:// www.lintcode.com/problem/919/
        public int minMeetingRooms(List<Interval> intervals) {
        Collections.sort(intervals, (a,b)->{
            if(a.start == b.start) return a.end - b.end;
            return a.start - b.start;
        });

        int rooms = 0;

        // represents teh ending times of meeting in all the rooms 
        // so that we can decide if there is ant room those meeting is about to end so that we can start another in that room :
        // pq.peek() : min end time meeting
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(Interval in : intervals){
            if(pq.size()>0 && in.start >= pq.peek() ){
                //so 1 meeting has ended and 1 room is vacant now;
                pq.remove();
            }else{
                // all rooms till now are full , so we will be needing 1 more room;
                rooms++;
            }

            pq.add(in.end); // end time of last started meeting;
        }
        return rooms;
    }
}


}
