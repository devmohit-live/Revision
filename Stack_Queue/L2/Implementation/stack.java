public class stack {

    private int[] arr;
    private int tos;
    private int size;
    private int maxCapacity;

    stack() {
        /**
         * Default size is 10;
         */
        this(10); // default size;
    }

    stack(int size) {
        intialize(size);
    }

    protected void intialize(int size) {
        this.arr = new int[size];
        this.tos = -1;
        this.size = 0;
        this.maxCapacity = size;
    }

    protected void stackIsFull() throws Exception {
        if (this.size() == this.maxCapacity)
            throw new Exception("Stack is Full");

    }

    protected void stackIsEmpty() throws Exception {
        if (this.size() == 0)
            throw new Exception("Stack is Empty");

    }

    protected int size() {
        return this.size;
    }

    protected void push(int element) throws Exception {
        stackIsFull();
        pushToStack(el);
    }

    private pushToStack(int el){
        this.tos++;
        this.arr[this.tos] = el;
    }

    protected int peek() throws Exception {
        stackIsEmpty();
        return peekFromStack();
    }

    private int peekFromStack() {
        return this.arr[this.tos];
    }

    protected int pop() throws Exception {
        stackIsEmpty();
        return removeFromStack();
    }

    private int removeFromStack() {
        int value = this.arr[this.tos];
        this.tos--;
        return value;
    }

}
