public class HashMapCreation {
    public static void main(String[] args) {
        // StringToInt();
        CustomToInt();
    }

    public static void IntToInt() {
        MyHashMap<Integer, Integer> map = new MyHashMap<>();
        for (int i = 1; i <= 10; i++)
            map.put(i, i * 10);
        System.out.println(map.size() + " " + map.containsKey(2));
        System.out.println(map);
        System.out.println(map.get(2));
        System.out.println(map.getOrDefault(50, -10));
        System.out.println(map.remove(5));
        System.out.println(map.put(2, 25));
        System.out.println(map.putIfAbsent(11, 99));
        System.out.println(map.put(-1, -100));
        System.out.println(map);
    }

    public static void StringToInt() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        for (int i = 1; i <= 10; i++)
            map.put("" + (char) ('a' + i), i * 10);
        System.out.println(map.size() + " " + map.containsKey("e"));
        System.out.println(map);
        System.out.println(map.get("c"));
        System.out.println(map.getOrDefault("Mohit", -10));
        System.out.println(map.remove("d"));
        System.out.println(map.put("Hello", 25));
        System.out.println(map.putIfAbsent("World", 99));
        System.out.println(map.put("xyxx", -100));
        System.out.println(map);
    }

    public static void CustomToInt() {

        Pair a = new Pair(2, "Mohit");
        Pair b = new Pair(2, "Mohit");
        System.out.println(a.equals(b));
        int hc1 = a.hashCode();
        int hc2 = b.hashCode();
        System.out.println((hc1 == hc2));

        Pair c = new Pair(3, "Mohit");
        Pair d = new Pair(2, "Random");
        System.out.println(a.equals(c));
        System.out.println(a.hashCode() + " " + d.hashCode());

        HashMap<Pair, Integer> map = new HashMap<>();
        Pair p;
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                p = new Pair(2, "Mohit");
            } else if (i % 3 == 0) {
                p = new Pair(3, "Hello");
            } else {
                String s = "" + (char) ('a' + i);
                p = new Pair(i, s);

            }
            // Pair p = new Pair(2,"Mohit");
            map.put(p, map.getOrDefault(p, 0) + 1);
        }
        System.out.println(map.size() + " " + map.containsKey(new Pair(2, "Mohit")));
        System.out.println(map);
        System.out.println(map.get(new Pair(3, "Hello")));
        Pair x = new Pair(2, "Mohit");
        System.out.println(map.getOrDefault(new Pair(-20, "Mohit"), -10));
        System.out.println(map.remove(x));
        System.out.println(map.put(x, 25));
        Pair r = new Pair(-1, "Random");
        System.out.println(map.putIfAbsent(r, 99));
        System.out.println(map);
        System.out.println(map.put(r, -100));
        System.out.println(map);
    }
}

class Pair {
    final int mod = 999999937;
    int id;
    String name;

    Pair(int id, String name) {
        this.id = id;
        this.name = name;
    }

    Pair() {
        this.id = -1;
        this.name = "";
    }

    // to get them into correct bucket
    @Override
    public int hashCode() {
        // bifercate on basis of their id

        int a = Math.abs(this.id);
        int b = this.name.hashCode();
        long c = (a + b) % mod;
        return (int) c;
    }

    // to compare them : get equality
    @Override
    public boolean equals(Object ot) {
        if (ot == null)
            return false;
        Pair o = (Pair) ot;
        if (this.id != o.id)
            return false;
        if (!this.name.equals(o.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return this.name + " @ " + this.id;
    }
}


//  =================================== HashMap ========================================

class MyHashMap<K, V> {
    // ============================ Data Members
    private int size; // bucket length
    private int noOfEl;
    private double load_factor;
    final private double factor;
    final private double threshold = 0.4;

    private class Node {
        K key;
        V val;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }

    }

    private LinkedList<Node>[] buckets;

    // ============================

    // ================== Constructors and Intializers
    MyHashMap(int size) {
        intialize(size);
        this.factor = 1.5;
    }

    MyHashMap() {
        this(16); // default szize is 16
    }

    private void intialize(int size) {
        this.size = size;
        this.noOfEl = 0;
        this.load_factor = 0;
        this.buckets = new LinkedList[this.size];
        for (int i = 0; i < this.size; i++)
            this.buckets[i] = new LinkedList<Node>();

    }

    // ============================

    // ======== Basic DS Functions and dependecies

    public int size() {
        return this.noOfEl;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    private LinkedList<Node> getBucket(K key) {
        int bucketIdx = getHash(key);
        return this.buckets[bucketIdx];
    }

    private int getHash(K key) {
        int hc = Math.abs(key.hashCode()) % this.size;
        return hc;
    }

    private void reHash() {
        int oldSize = this.size;
        int oldNoOfEl = this.noOfEl;
        LinkedList<Node>[] oldBuckets = this.buckets;
        int newSize = (int) (this.factor * oldSize);
        intialize(newSize);
        for (int i = 0; i < oldSize; i++) {
            LinkedList<Node> bucket = oldBuckets[i];
            int bucketSize = bucket.size();
            while (bucketSize-- > 0) {
                Node node = bucket.removeFirst();
                put(node.key, node.val);
            }
        }

    }

    // =============

    // ============= Utility functions

    public boolean containsKey(K key) {
        LinkedList<Node> bucket = this.getBucket(key);
        int groupSize = bucket.size();
        boolean res = false;
        while (groupSize-- > 0) {
            Node node = bucket.getFirst();
            if (node.key == key) {
                res = true;
                break;
            } else
                bucket.addLast(bucket.removeFirst());
        }
        return res;
    }

    public V get(K key) {
        boolean contains = this.containsKey(key);
        LinkedList<Node> bucket = this.getBucket(key);
        if (contains)
            return bucket.getFirst().val;
        return null;
    }

    // get or default
    public V getOrDefault(K key, V defaultValue) {
        V value = this.get(key);
        return value == null ? defaultValue : value;
    }

    // put : 1. el is not present
    // 2. present => update
    public V put(K key, V value) {
        boolean contains = this.containsKey(key);
        LinkedList<Node> bucket = this.getBucket(key);

        V returnVal = null;
        if (contains) {
            returnVal = bucket.getFirst().val;
            bucket.getFirst().val = value; // updated
        } else {
            bucket.addLast(new Node(key, value));
            this.noOfEl++;
            this.load_factor = this.noOfEl * 1.0 / this.size;
            if (this.load_factor > this.threshold) {
                reHash();
            }
        }

        return returnVal;
    }

    // put if absent
    public V putIfAbsent(K key, V value) {
        boolean contains = this.containsKey(key);
        LinkedList<Node> bucket = this.getBucket(key);
        if (contains)
            return bucket.getFirst().val;
        ;
        bucket.addLast(new Node(key, value));
        this.noOfEl++;
        this.load_factor = this.noOfEl * 1.0 / this.size;
        if (this.load_factor > this.threshold) {
            reHash();
        }
        return null;
    }

    // remove
    public V remove(K key) {
        boolean contains = this.containsKey(key);
        LinkedList<Node> bucket = this.getBucket(key);
        if (contains) {
            V val = bucket.removeFirst().val;
            this.noOfEl--;
            return val;
        }
        return null;
    }

    // clear
    public void clear() {
        intialize(16);
    }

    // string

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        int size = this.size;
        int elements = this.noOfEl;
        for (int i = 0; i < size; i++) {
            LinkedList<Node> bucket = this.buckets[i];
            int bucketSize = bucket.size();
            while (bucketSize-- > 0) {
                Node node = bucket.getFirst();
                sb.append(node.key + " : " + node.val);
                sb.append(", ");
                this.buckets[i].addLast(bucket.removeFirst());
            }
        }
        sb.deleteCharAt(sb.length() - 1); // space
        sb.deleteCharAt(sb.length() - 1); // ,
        sb.append("}");
        return sb.toString();
    }

    // ================================
}