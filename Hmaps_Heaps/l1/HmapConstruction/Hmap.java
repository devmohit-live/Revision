import java.util.ArrayList;
import java.util.LinkedList;

public class Hmap {
    // Data Members================================================================
    class Node {
        Integer key, value;

        Node(Integer key, Integer val) {
            this.key = key;
            this.value = val;
        }
    }

    private LinkedList<Node>[] buckets;
    private int bucketLen;
    private int noOfEl;

    // Constructors================================================================

    private void initialize(int size) {
        this.bucketLen = size;
        this.buckets = new LinkedList[size];
        this.noOfEl = 0;

        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<Node>();
        }
    }

    Hmap(int size) {
        initialize(size);
    }

    Hmap() {
        this(16);
    }

    // Basic
    // Functions================================================================
    // No need to search for item in get, remove manually as containskey will
    // autoatically move that item to start(if exists)

    // not needed
    // public Integer get(Integer key) {
    // LinkedList<Node> group = getGroup(key);
    // int groupSize = group.size();
    // Integer ans = null;

    // while (groupSize-- > 0) {
    // Node rm = group.removeFirst();
    // group.addLast(rm);

    // if (rm.key == key) {
    // ans = rm.value;
    // break;
    // }
    // }
    // return ans;
    // }

    public int size() {
        return this.noOfEl;
    }

    public boolean isEmpty() {
        return this.noOfEl == 0;
    }

    //

    // // Always use getFirst and then iff necessary use removefirst as removeFirst
    // when rm.key==key disturb the arrangement for other fuctions like remove,get

    public boolean containsKey(Integer key) {
        /**
         * in this process rearrangement of items happens and item to be searched will
         * come at 0th index
         */

        LinkedList<Node> group = getGroup(key);
        int gs = group.size();
        boolean res = false;
        while (gs-- > 0) {
            // Node rm = (Node) group.removeFirst();
            // group.addLast(rm);

            if (group.getFirst().key.equals(key)) {
                res = true;
                break;
            }

            group.addLast(group.removeFirst());
        }

        return res;
    }

    public Integer get(Integer key) {
        boolean present = containsKey(key);
        LinkedList<Node> group = getGroup(key);
        return present ? group.getFirst().value : null;
    }

    public Integer getOrDefault(Integer key, Integer defaultValue) {
        Integer ans = get(key);
        return ans == null ? defaultValue : ans;
    }

    public Integer put(Integer key, Integer val) {
        // check of key already present -> update
        boolean present = containsKey(key);
        LinkedList<Node> group = getGroup(key);
        if (present) {
            Integer old = group.getFirst().value;
            group.getFirst().value = val;
            return old;
        } else {
            group.addLast(new Node(key, val));
            this.noOfEl++;
            double lf = (group.size() * 1.0) / this.bucketLen;
            if (lf > 0.4)
                reHash();

        }
        return null;
    }

    public Integer putIfAbsent(Integer key, Integer defaultValue) {
        boolean present = containsKey(key);
        LinkedList<Node> group = getGroup(key);

        if (present) {
            return group.getFirst().value;
        }
        group.addLast(new Node(key, defaultValue));
        this.noOfEl++;
        double lf = (group.size() * 1.0) / this.bucketLen;
        if (lf > 0.4)
            reHash();
        return null;
    }

    public Integer remove(Integer key) {
        boolean res = containsKey(key);
        LinkedList<Node> group = getGroup(key);

        if (res) {
            this.noOfEl--;
            return group.removeFirst().value;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int tempSize = this.noOfEl;
        for (int i = 0; i < this.bucketLen; i++) {
            LinkedList<Node> group = this.buckets[i];
            int size = group.size();
            while (size-- > 0) {

                Node node = group.removeFirst();
                sb.append(node.key + "=" + node.value);
                group.addLast(node);

                if (--tempSize != 0)
                    sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    public ArrayList<Integer> keySet() {
        ArrayList<Integer> keys = new ArrayList<>();
        for (int i = 0; i < this.bucketLen; i++) {
            LinkedList<Node> group = this.buckets[i];
            int n = group.size();
            while (n-- > 0) {
                Node rm = group.removeFirst();
                keys.add(rm.key);
                group.addLast(rm);
            }
        }
        return keys;
    }

    //
    // DS dependencies
    // functions================================================================

    private int getHashCode(Integer key) {
        return Math.abs(key.hashCode()) % this.bucketLen;
    }

    private LinkedList getGroup(Integer key) {
        int hc = getHashCode(key);
        return this.buckets[hc];
    }

    private void reHash() {

        // System.out.println("Rehasing is Done");
        // load factor = 0.4
        // increasing factor = 1.5
        LinkedList[] oldbucket = this.buckets;
        int oldsize = oldbucket.length;

        initialize((int) (1.5 * oldsize));

        for (int i = 0; i < oldsize; i++) {
            LinkedList<Node> group = oldbucket[i];
            int groupSize = group.size();
            while (groupSize-- > 0) {
                Node rm = group.removeFirst();
                put(rm.key, rm.value);
            }

        }

    }

    // Exceptions ==========================================================
    // Doesn't gives exception on remove and get -> gives null
}
