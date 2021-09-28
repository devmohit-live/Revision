import java.util.Arrays;

public class NMeetings {
    static class meeting {
        int st, et;

        meeting(int st, int et) {
            this.st = st;
            this.et = et;
        }
    }

    public static int maxMeetings(int start[], int end[], int n) {
        meeting[] m = new meeting[n];
        for (int i = 0; i < n; i++) {
            m[i] = new meeting(start[i], end[i]);
        }

        Arrays.sort(m, (a, b) -> {
            int d = a.et - b.et;
            if (d == 0) {
                return -1; // relative order is amintained in case of equal
            }
            return d;

        });

        int ltime = -1;

        int ans = 0;
        for (int i = 0; i < n; i++) {
            // System.out.print(m[i].st+" "+m[i].et);
            // System.out.println();
            if (m[i].st > ltime) {
                ans++;
                ltime = m[i].et;
            }
        }

        return ans;

    }
}
