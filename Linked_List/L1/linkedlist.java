package Linked_List;

public class linkedlist {
    private class Node {
        int data = 0;
        Node next = null;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    private void addFirstNode(Node node) {
        if (this.size == 0)
            this.head = this.tail = node;
        else {
            node.next = this.head;
            this.head = node;
        }
        this.size++;
    }

    public void addFirst(int data) {
        Node node = new Node(data);
        addFirstNode(node);
    }

    private void addLastNode(Node node) {
        if (this.size == 0)
            this.head = this.tail = node;
        else {
            this.tail.next = node;
            this.tail = node;
        }
        this.size++;
    }

    public void addLast(int data) {
        Node node = new Node(data);
        addLastNode(node);
    }

    // =======================================================

    private Node removeFirstNode() {
        Node node = this.head;
        if (this.size == 1)
            this.head = this.tail = null;
        else {
            this.head = this.head.next;
            node.next = null;
        }

        this.size--;
        return node;
    }

    public int removeFirst() {
        if (this.size == 0)
            return -1;

        Node node = removeFirstNode();
        return node.data;
    }

    // =======================================================

    private Node getFirstNode() {
        return this.head;
    }

    public int getFirst() {
        if (this.size == 0)
            return -1;

        return getFirstNode().data;
    }

    private Node getLastNode() {
        return this.tail;
    }

    public int getLast() {
        if (this.size == 0)
            return -1;

        return getLastNode().data;
    }

    private Node getNodeAt(int idx) {
        Node curr = this.head;
        while (idx-- > 0) {
            curr = curr.next;
        }

        return curr;
    }

    public int getAt(int idx) {
        if (idx < 0 || idx >= this.size)
            return -1;

        return getNodeAt(idx).data;
    }

    public int removeLast() {
        Node node = this.head;
        while (node.next != tail) {
            node = node.next;
        }
        node.next = null;
        tail = node;
        this.size--;
        return tail.data;
    }

    public int removeAt(int i) {
        if (i < 0 || i >= this.size) {
            System.out.println("Invalid Index");
            return -1;
        }
        Node node = this.head;
        int ci = 1;
        while (ci < i) {
            ++ci;
            node = node.next;
        }
        int data = node.next.data;
        Node newnext = node.next.next;
        node.next = newnext;
        this.size--;
        return data;
    }

    public void addAt(int i, int data) {
        if (i < 0 || i > this.size) {
            System.out.println("Invalid Index");
            return;
        }

        int ci = 1;
        if (i == 0) {
            Node node = new Node(data);
            node.next = this.head;
            this.head = node;
            this.size++;
        }

        Node node = this.head;
        while (ci < i - 1) {
            node = node.next;
            ci++;
        }
        Node newnode = new Node(data);
        Node tmp = node.next;
        node.next = newnode;
        newnode.next = tmp;
        this.size++;
    }

    

}