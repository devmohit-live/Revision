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

    public Integer get(Integer key) {
        LinkedList<Node> group = getGroup(key);
        int groupSize = group.size();
        Integer ans = null;

        while (groupSize-- > 0) {
            Node rm = group.removeFirst();
            group.addLast(rm);

            if (rm.key == key) {
                ans = rm.value;
                break;
            }
        }
        return ans;
    }

    public boolean containsKey(Integer key) {
        LinkedList<Node> group = getGroup(key);
        int groupSize = group.size();
        boolean found = false;

        while (groupSize-- > 0) {
            Node rm = (Node) group.removeFirst();
            group.addLast(rm);

            if (rm.key == key) {
                found = true;
                break;
            }

        }
        return found;
    }

    public Integer getOrDefault(Integer key, Integer defaultValue) {
        Integer ans = get(key);
        return ans == null ? defaultValue : ans;
    }

    public void put(Integer key, Integer val) {
        LinkedList<Node> group = getGroup(key);
        group.addLast(new Node(key, val));

    }

    public Integer remove(Integer key) throws Exception {
        keyExisist(key);
        LinkedList<Node> group = getGroup(key);
        int groupSize = group.size();
        while (groupSize-- > 0) {
            Node rm = (Node) group.removeFirst();

            if (rm.key == key) {
                return rm.value;
            } else {
                group.addLast(rm);
            }
        }
        return null;
    }

    // Other
    // functions================================================================

    private int getHashCode(Integer key) {
        return Math.abs(key.hashCode()) % this.bucketLen;
    }

    private LinkedList getGroup(Integer key) {
        int hc = getHashCode(key);
        return this.buckets[hc];
    }

    // Exceptions ==========================================================
    private void keyExisist(Integer key) throws Exception {
        if (!containsKey(key))
            throw new Exception("Key you are trying to remove the key that doesn't exixts ");
    }

}
