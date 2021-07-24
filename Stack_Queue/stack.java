package Stack_Queue;

public class stack {
    private int top;
    private int size;
    private int maxsize;
    private int[] arr;

    public stack() {
        maxsize = 5;
        top = -1;
        size = 0;
        arr = new int[5];

    }

    public stack(int limit) {
        maxsize = limit;
        top = -1;
        size = 0;
        arr = new int[limit];
    }

    private void stackIsEmpty() throws Exception {
        if (this.size == 0)
            throw new Exception("Underflow Ocuured: Stack is Empty");
    }

    private void stackIsFull() throws Exception {
        if (this.size == this.maxsize)
            throw new Exception("Overflow Occured : Stack is Full");
    }

    public boolean isEmpty() {
       return (this.size==0);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int ctop = this.top;
        sb.append('[');
        if (isEmpty()) {
            return "Stack is Empty";
        }
        sb.append("Stack is : ");
        while (ctop > -1) {
            sb.append(arr[ctop--] + " ");
        }
        sb.append(']');
        return sb.toString();
    }

    public int size() {
        return this.size;
    }

    public int getMaxsize() {
        return this.maxsize;
    }

    public void push(int data) throws Exception {
        stackIsFull();
        push_(data);
    }

    private void push_(int data) {
        this.top++;
        this.arr[this.top] = data;
        this.size++;
    }

    public int top() throws Exception {
        stackIsEmpty();
        return this.arr[this.top];
    }

    public int pop() throws Exception {
        stackIsEmpty();
        return pop_();
    }

    private int pop_() {
        int data = this.arr[this.top];
        this.top--;
        this.size--;
        return data;
    }

}
