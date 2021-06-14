public class client {
    public static void main(String[] args) {
        linkedlist ll = new linkedlist();
        ll.addLast(1);
        ll.addLast(2);
        ll.addLast(3);
        ll.addLast(4);
        ll.addLast(5);

        int data = 100;
        ll.addAt(2, data);
        System.out.println(ll.size());

    }
}