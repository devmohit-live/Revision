public class Doubly {
    // dependent class
    private class Node {
        int val;
        Node next, prev;

        Node(int val) {
            this.val = val;
            this.next = null;
            this.prev = null;
        }
    }

    // constructor

    private int size;
    private Node head;
    private Node tail;

    // Exceptions========================================

    private boolean ListIsEmptyException() {
        if (this.size == 0) {
            System.out.print("ListIsEmpty: ");
            return true;
        }
        return false;
    }

    private boolean indexIsInvalidException(int index, int leftRange, int rightRange) {
        if (index < leftRange || index > rightRange) {
            System.out.print("IndexIsInValid: ");
            return true;
        }
        return false;
    }

    // BasicFunctions======================================

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node curr = this.head;
        sb.append("[");
        while (curr != null) {
            sb.append(curr.data);
            if (curr.next != null)
                sb.append(", ");
            curr = curr.next;
        }
        sb.append("]");

        return sb.toString();
    }

    public void displayForw() {
        if (ListIsEmptyException())
            return;

        System.out.print("[");
        Node curr = this.head;
        while (curr != null) {
            if (curr.next != null) {
                System.out.print(curr.data + ", ");
            } else
                System.out.print(curr.data);

            curr = curr.next;
        }
        System.out.println("]");

    }

    public void displayBack() {
        ListIsEmptyException();

        System.out.print("[");
        Node curr = this.tail;
        while (curr != null) {
            if (curr.prev != null) {
                System.out.print(curr.data + ", ");
            } else
                System.out.print(curr.data);

            curr = curr.prev;
        }
        System.out.println("]");
    }

    // ds functions ======================================

    // Get Functions

    public int getFirst() {
        if (ListIsEmptyException())
            return -1;

        return this.head.data;
    }

    public int getLast() {
        if (ListIsEmptyException())
            return -1;

        return this.tail.data;
    }

    private Node getNodeAt(int index) {
        Node curr = this.head;
        while (index-- > 0)
            curr = curr.next;

        return curr;
    }

    public int getAt(int index) {
        if (ListIsEmptyException())
            return -1;
        else if (indexIsInvalidException(index, 0, this.size - 1))
            return -1;

        Node node = getNodeAt(index);
        return node.data;
    }

    // Add functions:

    private void addFirstNode(Node node) {
        if (this.size == 0)
            this.head = this.tail = node;
        else {
            node.next = this.head;
            this.head.prev = node;
            this.head = node;
        }
        this.size++;
    }

    public void addFirst(int val) {
        Node node = new Node(val);
        addFirstNode(node);
    }

    private void addLastNode(Node node) {
        if (this.size == 0)
            this.head = this.tail = node;
        else {
            this.tail.next = node;
            node.prev = this.tail;
            this.tail = node;
        }
        this.size++;
    }

    public void addLast(int val) {
        Node node = new Node(val);
        addLastNode(node);
    }

    public void addAt(int index, int data) {
        // here this .size bcz we can add to the sizeth index => add last

        if (indexIsInvalidException(index, 0, this.size))
            System.out.println(-1);

        else {
            Node node = new Node(data);
            addNodeAt(index, node);
        }

    }

    private void addNodeAt(int index, Node node) {
        if (index == this.size)
            addLastNode(node);
        else if (index == 0)
            addFirstNode(node);

        // if(index==this.size) addLastNode(node); we have to use else if
        // bcz for empty list idx=0 will acts ac both idx==0==this.size so we don't
        // exacly know where to add so we will go with addFirst(can't execute both)=>
        // exception as first addLast will ocuur then addFirst will occur on same node

        else {

            Node ith = getNodeAt(index);
            Node prev = ith.prev;

            node.prev = prev;
            prev.next = node;

            node.next = ith;
            ith.prev = node;

            this.size++;
        }
    }

    public void addBefore(Node refNode, int data) {
        Node n = new Node(data);
        if (refNode.prev == null)
            addFirstNode(n);
        else {
            Node prev = refNode.prev;

            prev.next = n;
            n.prev = prev;

            n.next = refNode;
            refNode.prev = n;

            this.size++;

        }
    }

    public void addBefore(int idx, int data) {
        Node node = getNodeAt(idx);
        addBefore(node, data);
    }

    private void addAfter(Node refNode, int data) {
        Node x = new Node(data);
        // last node
        if (refNode.next == null)
            addLastNode(x);

        else {
            Node forw = refNode.next;

            forw.prev = x;
            x.next = forw;

            x.prev = refNode;
            refNode.next = x;

            this.size++;

        }
    }

    public void addAfter(int idx, int data) {
        Node node = getNodeAt(idx);
        addAfter(node, data);
    }

    // Remove Functions:
    private Node removeFirstNode() {
        Node node = this.head;
        if (this.size == 1)
            this.head = this.tail = null;
        else {
            Node nextNode = this.head.next;
            nextNode.prev = null;
            node.next = null;

            this.head = nextNode;
        }

        this.size--;
        return node;
    }

    public int removeFirst() {
        if (ListIsEmptyException())
            return -1;
        Node node = removeFirstNode();
        return node.data;
    }

    private Node removeLastNode() {
        Node node = this.tail;
        if (this.size == 1)
            this.head = this.tail = null;
        else {
            Node prevNode = this.tail.prev;
            prevNode.next = null;
            node.prev = null;

            this.tail = prevNode;
        }

        this.size--;
        return node;
    }

    public int removeLast() {
        if (ListIsEmptyException())
            return -1;
        Node node = removeLastNode();
        return node.data;
    }

    public int removeAt(int index) {
        if (ListIsEmptyException())
            return -1;
        else if (indexIsInvalidException(index, 0, this.size - 1))
            return -1;
        else {
            return removeNodeAt(index).data;
        }
    }

    private Node removeNodeAt(int index) {

        if (index == 0)
            return removeFirstNode();
        else if (index == this.size - 1)
            return removeLastNode();
        else {
            Node rm = getNodeAt(index);
            Node prev = rm.prev;
            prev.next = rm.next;
            rm.next.prev = prev;
            this.size--;
            rm.prev = rm.next = null;
            return rm;
        }
    }

    private Node removeBeforeNode(Node refnode) {
        if (refnode.prev.prev == null)
            return removeFirstNode();

        else {

            Node prev = refnode.prev;

            Node newPrev = prev.prev;

            refnode.prev = newPrev;
            newPrev.next = refnode;

            prev.next = prev.prev = null;

            this.size--;

            return prev;

        }
    }

    public int removeBefore(Node refNode) {
        if (refNode.prev == null) {
            System.out.print("LocationIsInvalid: ");
            return -1;
        }

        return removeBeforeNode(refNode).data;
    }

    public int removeBefore(int idx) {
        Node node = getNodeAt(idx);
        return removeBefore(node);
    }

    private Node removeNodeAfter(Node refNode) {
        if (refNode.next.next == null)
            return removeLastNode();

        else {

            Node forw = refNode.next;
            refNode.next = forw.next;
            forw.next.prev = refNode;

            forw.prev = null;
            forw.next = null;

            this.size--;
            return forw;

        }
    }

    public int removeAfter(Node refNode) {
        if (refNode.next == null) {
            System.out.println("LocationIsInvalid: ");
            return -1;
        }
        return removeNodeAfter(refNode).data;

    }

    public int removeAfter(int idx) {
        // get node hadles the excpetion
        Node node = getNodeAt(idx);
        return removeAfter(node);
    }

    // remove self
    public int removeNode(Node refNode) {
        Node prev = refNode.prev;
        Node forw = refNode.next;

        if (this.size == 1) {
            this.head = this.tail = null;
            this.size--;
        }

        if (prev == null)
            return removeFirstNode().data;
        else if (forw == null)
            return removeLastNode().data;
        else {
            prev.next = forw;
            forw.prev = prev;

            refNode.next = refNode.prev = null;

            this.size--;

            return refNode.data;
        }
    }

}
