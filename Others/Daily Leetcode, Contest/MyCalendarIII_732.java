public class MyCalendarIII_732{
    class MyCalendarThree {
        private TreeMap<Integer, Integer> timeline;
    
        MyCalendarThree(){
            timeline = new TreeMap<>();
        }
        
        public int book(int s, int e) {
            timeline.put(s, timeline.getOrDefault(s, 0) + 1); // 1 new event will be starting at [s]
            timeline.put(e, timeline.getOrDefault(e, 0) - 1); // 1 new event will be ending at [e];
            int ongoing = 0, k = 0;
            for (int v : timeline.values())
                k = Math.max(k, ongoing += v);
            return k;
        }
    }
}