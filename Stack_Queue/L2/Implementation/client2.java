public class client2 {
    public static void main(String[] args) throws Exception {
        // Q q = new Q(5);
        // System.out.println(q.getSize());
        // q.add(1);
        // q.add(2);
        // q.add(3);
        // q.add(4);
        // q.add(5);
        // System.out.println(q);
        // System.out.println(q.getSize());
        // System.out.println(q.remove());
        // System.out.println(q.remove());
        // System.out.println(q);
        // System.out.printf("Size of Q is : %d , Capacity is : %d \n", q.getSize(),
        // q.getMaxCapacity());

        DynamicQ q = new DynamicQ(5);
        System.out.println(q.getSize());
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);
        q.add(5);
        System.out.println(q);
        System.out.println(q.getSize());
        q.add(100);
        System.out.println(q);
        System.out.printf("Size of Q is : %d , Capacity is : %d \n", q.getSize(), q.getMaxCapacity());
        // System.out.println(q.remove());
        // System.out.println(q.remove());
        // System.out.println(q);
        // System.out.printf("Size of Q is : %d , Capacity is : %d \n", q.getSize(),
        // q.getMaxCapacity());

    }
}
