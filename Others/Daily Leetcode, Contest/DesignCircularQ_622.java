public class DesignCircularQ_622 {
    class MyCircularQueue {
    
    private class QueueFullException extends Exception{
        QueueFullException(String msg){
            super(msg);
        }
    }
    
    private class QueueEmptyException extends Exception{
        QueueEmptyException(String msg){
            super(msg);
        }
    }
    
    
    private class ListNode{
        int val;
        ListNode prev,next;
        ListNode(int val){
            this.val = val;
            this.prev = null;
            this.next = null;
        }
    }
    
    private ListNode head, tail;
    private int size;
    private final int MAXSIZE;

    public MyCircularQueue(int k) {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.MAXSIZE = k;
    }
    
    public boolean enQueue(int value) {
        try{
            this.addLastNode(value);
        }catch(QueueFullException e){
            return false;
        }
        return true;
    }
    
    public boolean deQueue() {
        try{
            this.removeFirst();
        }catch(QueueEmptyException e){
            return false;
        }
        return true;
    }
    
    public int Front() {
        if(this.isEmpty()) return -1;
        
        return this.head.val;
    }
    
    public int Rear() {
        if(this.isEmpty()) return -1;
        return this.tail.val;
    }
    
    public boolean isEmpty() {
        return this.size == 0;
    }
    
    public boolean isFull() {
        return this.size == MAXSIZE;
    }

    private void addLastNode(int val) throws QueueFullException{
        if(this.isFull()){
            throw new QueueFullException("Queue is full can't add element");
        }
        ListNode node = new ListNode(val);
        if(this.head == null){
            this.head = node;
            this.tail = node;
        }else{
            this.tail.next = node;
            node.prev = tail;
            this.tail = node;
        }
        // this.tail.next = this.head; no need
        this.size++;
    }
    
    private ListNode removeFirst() throws QueueEmptyException{
        if(this.isEmpty()) throw new QueueEmptyException("Queue is Empty can't remove element");
        
        ListNode node = this.head;
        ListNode forw = node.next;
        
        if(forw!=null){
            forw.prev = null;
            node.next = null;
        }
        
        this.head = forw;
        // this.tail.next = this.head; //no need
        this.size--;
        return node;
    }
    
    
}

}
