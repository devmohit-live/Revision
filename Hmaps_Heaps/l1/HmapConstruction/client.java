public class client {
    public static void test() throws Exception {
        // Hmap map = new Hmap(100);
        // default size = 16
        Hmap map = new Hmap(10);
        // HashMap map = new HashMap();
        for (int i = 1; i <= 10; i++) {
            map.put(i, i * 10);
        }

        System.out.println("Size is " + map.size());
        System.out.println(map.containsKey(10));
        System.out.println(map.remove(20));
        System.out.println(map.remove(2));
        System.out.println(map.remove(1));
        System.out.println(map.remove(-1));
        System.out.println("Size is " + map.size());
        System.out.println(map.getOrDefault(-16, -15));
        System.out.println(map.putIfAbsent(-8, 100));
        System.out.println(map.putIfAbsent(-8, 110));
        System.out.println(map);
        System.out.println(map.keySet());
        System.out.println(map.remove(-8));

        // Checking Rehasing
        map.put(11, 0);
        map.put(21, 0);
        map.put(31, 0);
        map.put(41, 0);
        map.put(51, 0);
        map.put(61, 0);

        System.out.println(map);

    }

    public static void main(String[] args) throws Exception {
        test();
    }
}