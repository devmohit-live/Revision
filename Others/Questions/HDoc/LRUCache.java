import java.util.HashMap;

public class LRUCache {

    private class Node {
        int key, val;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.val = value;
            prev = null;
            next = null;
        }

    }

    private HashMap<Integer, Node> map;

    private int size;
    private final int MAXSIZE;
    private Node head = null, tail = null;

    private void removeNode(Node node) {
        if (node == null)
            return;

        Node prev = node.prev;
        Node forw = node.next;

        if (node == this.tail)
            this.tail = prev;
        if (node == this.head)
            this.head = forw;

        node.prev = null;
        node.next = null;

        if (prev != null)
            prev.next = forw;
        if (forw != null)
            forw.prev = prev;
        map.remove(node.key);

        this.size--;

    }

    private void makeRecent(Node node) {
        Node recent = new Node(node.key, node.val);
        removeNode(node);

        addLast(recent);
    }

    private void addLast(Node node) {
        if (this.head == null) {
            this.head = this.tail = node;
        }

        map.put(node.key, node);

        Node prev = this.tail;

        prev.next = node;
        node.prev = prev;

        this.tail = node;

        this.size++;

    }

    public LRUCache(int capacity) {
        this.MAXSIZE = capacity;
        this.map = new HashMap<>();
        ;
        this.size = 0;
        this.head = this.tail = null;
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;

        Node node = map.get(key);
        makeRecent(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.val = value;
            makeRecent(node);
        } else {
            Node node = new Node(key, value);
            if (this.size == this.MAXSIZE) {
                removeNode(this.head);
            }

            addLast(node);

        }
    }
}