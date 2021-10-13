public class client {
    public static void test() throws Exception {
        // PQ pq = new PQ();
        int[] arr = { 1, 324, 32, 1, 6, 5632, 4, 225 };
        // Heap pq = new Heap(arr, false);
        PQ pq = new PQ(arr);

        System.out.println(pq);

        // for (int ele : arr)
        // pq.add(ele);
        for (int ele : new int[] { -8, 7, 1002 })
            pq.add(ele);

        while (pq.size() != 0)
            System.out.println(pq.remove());
    }

    public static void main(String[] args) throws Exception {
        test();
    }
}