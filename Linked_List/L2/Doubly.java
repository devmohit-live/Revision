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

    // basics functions

    // ds functions
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

    // exceptions

}
