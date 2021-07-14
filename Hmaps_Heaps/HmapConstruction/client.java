public class client {
    public static void test() throws Exception {
        Hmap map = new Hmap(100);
        for (int i = 1; i <= 100; i++) {
            map.put(i, i * 10);
        }

        System.out.println(map.containsKey(10));
        System.out.println(map.remove(20));
        map.remove(-1);// gives exception
    }

    public static void main(String[] args) throws Exception {
        test();
    }
}