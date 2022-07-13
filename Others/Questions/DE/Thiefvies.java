public class Thiefvies {
    static int catchThieves(char arr[], int n, int k) {
        int ans = 0;
        // Index of Police p and Thief t
        int p = 0, t = 0;

        while (p < n && t < n) {

            // Find the first appearing police
            // index from current police index
            while (p < n && arr[p] != 'P')
                p++;

            // Find the first appearing thief
            // index from current thief index
            while (t < n && arr[t] != 'T')
                t++;

            // If either p or t equals 0 that means there
            // is no police or thief in this segment iteration
            if (p == n || t == n)
                break;

            // If satisfies the given condition
            if (Math.abs(p - t) <= k) {
                ans++;
                p++;
                t++;
            } // else increment the lowest index of both p and t
            else {
                if (p <= t)
                    p++;
                else
                    t++;
            }
        }

        return ans;

    }
}
