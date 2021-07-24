package Stack_Queue;

public class queue {
    private int front;
    private int rear;
    private int size;
    private int max_size;
    private int[] arr;

    public void add(int data)throws Exception {
        isFull();
        
    }

    public int remove()throws Exception {

    }

    public int peek()throws Exception {
        //empty
        isEmpty();
        return this.arr[this.front];
    }

    private void isEmpty() throws Exception {
        if (this.size == 0)
            throw new Exception("Underflow Occured: Queue is Empty");
    }

    private void isFull() throws Exception {
        if (this.size == this.max_size)
            throw new Exception("Underflow Occured: Queue is Empty");

    }

    public int size() {
        return this.size;
    }

    public int getMaxSize() {
        return this.max_size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');

        sb.append(']');
        return sb.toString();
    }

    private int remove_() {

    }

    private int add_(int data) {

    }

}
