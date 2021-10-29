public class CountSort {
    CountSort() {
        System.out.println("\nCalling CountSort\n");
    }

    void sort(int[] arr) {
        int min = (int) 1e9, max = -(int) 1e9;
        for (int el : arr) {
            min = Math.min(min, el);
            max = Math.max(max, el);
        }

        int range = max - min + 1;
        int[] freq = new int[range];
        int[] op = new int[arr.length];

        for (int el : arr)
            freq[el - min]++;

        // prefix sum of frweq to know the boundary pos of each ele
        for (int i = 1; i < freq.length; i++)
            freq[i] += freq[i - 1];

        // reverse dirextion loop
        for (int i = arr.length - 1; i >= 0; i--) {
            int el = arr[i];
            int idx = --freq[el - min]; // each pos el in freq is +1(so prefix--) more and we have to decrease the pos
                                        // after pushong to array
            op[idx] = el;
        }

        for (int i = 0; i < arr.length; i++)
            arr[i] = op[i];
    }

}
