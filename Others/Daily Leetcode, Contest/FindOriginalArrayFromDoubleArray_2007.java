/**
 * FindOriginalArrayFromDoubleArray_2007
 */
public class FindOriginalArrayFromDoubleArray_2007 {

    final int[] BASE = new int[0];
    final int maxlen = 2 * (int) 1e5 + 1; // as we are checking for 2*el in freq

    public int[] findOriginalArray(int[] arr) {
        // return usingQueue(arr);
        return usingFreq(arr);
    }

    private int[] usingFreq(int[] arr) {
        int n = arr.length;
        if ((n & 1) == 1)
            return BASE;
        int[] freq = new int[maxlen];
        for (int el : arr)
            freq[el]++;
        Arrays.sort(arr);
        List<Integer> ans = new ArrayList<>();

        for (int el : arr) {
            if (el == 0) {
                if ((freq[0] & 1) == 1)
                    return BASE;
                int f = freq[0] / 2;
                freq[0] = 0;
                while (f-- > 0)
                    ans.add(0);
            } else if (freq[el] > 0) {
                if (freq[2 * el] < freq[el])
                    return BASE;
                freq[2 * el] -= freq[el];
                int f = freq[el];
                freq[el] = 0;
                while (f-- > 0)
                    ans.add(el);
            }
        }

        return ans.stream().mapToInt(x -> x).toArray();

    }

    private int[] usingQueue(int[] arr) {
        int n = arr.length;
        if ((n & 1) == 1)
            return BASE;
        int[] ans = new int[n / 2];
        Arrays.sort(arr);
        int idx = 0;

        LinkedList<Integer> q = new LinkedList<>();
        q.addLast(arr[0]);

        for (int i = 1; i < n; i++) {
            if (q.isEmpty() || q.getFirst() * 2 != arr[i])
                q.addLast(arr[i]);
            else
                ans[idx++] = q.removeFirst();
        }

        if (!q.isEmpty())
            return BASE;

        return ans;
    }
}