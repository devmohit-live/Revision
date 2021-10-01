public class stack {
    // ------------------ Data Memebers ------------------
    private int[] arr;
    private int tos;
    private int size;
    private int maxCapacity;

    // -------------- Constructor and Intializing functions ------------------
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

    // ---------- Exceptions ------------------

    protected void stackIsFull() throws Exception {
        if (this.getSize() == this.maxCapacity)
            throw new Exception("Stack is Full");

    }

    protected void stackIsEmpty() throws Exception {
        if (this.getSize() == 0)
            throw new Exception("Stack is Empty");

    }

    // ------------------------ Basic DS Functions ------------------------

    public int getSize() {
        return this.size;
    }

    public void push(int element) throws Exception {
        stackIsFull();
        push_(element);
    }

    private void push_(int element) {
        this.tos++;
        this.arr[this.tos] = element;
        this.size++;
    }

    public int peek() throws Exception {
        stackIsEmpty();
        return this.arr[this.tos];
    }

    public int pop() throws Exception {
        stackIsEmpty();
        return pop_();
    }

    private int pop_() {
        int value = this.arr[this.tos];
        this.tos--;
        this.size--;
        return value;
    }

    public int getMaxSize() {
        return this.maxCapacity;
    }

    // ------------- Common Class Functions -------------------

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        int tmpSize = this.getSize();
        for (int i = 0; i < tmpSize - 1; i++) {
            sb.append(this.arr[i] + ", ");
        }
        sb.append(this.arr[tmpSize - 1]);

        sb.append(']');

        return sb.toString();
    }

}
