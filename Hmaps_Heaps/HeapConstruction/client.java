public class client {
    public static void test() {
        Heap pq = new Heap();
        int[] arr = { 1, 324, 32, 1, 6, 5632, 4, 225 };

        for (int ele : arr)
            pq.add(ele);

        while (pq.size() != 0)
            System.out.println(pq.remove());
    }
}