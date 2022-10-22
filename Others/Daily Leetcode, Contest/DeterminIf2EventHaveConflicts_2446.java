public class DeterminIf2EventHaveConflicts_2446 {
    public boolean haveConflict(String[] event1, String[] event2) {
        long[][] events = { convertTime(event1), convertTime(event2) };
        Arrays.sort(events, (a, b) -> Long.compare(a[0], b[0]));
        long[] e1 = events[0], e2 = events[1];
        System.out.println("Event1 " + Arrays.toString(e1));
        System.out.println("Event2 " + Arrays.toString(e2));

        return e1[1] >= e2[0] || e2[0] <= e1[1];
    }

    private long[] convertTime(String[] e) {
        long[] event = new long[2];
        int idx = 0;
        for (String s : e) {
            String[] t = s.split(":");
            int hr = Integer.parseInt(t[0]);
            int min = Integer.parseInt(t[1]);
            event[idx++] = min + hr * 60L;
        }

        return event;
    }
}
