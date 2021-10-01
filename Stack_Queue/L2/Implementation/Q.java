public class Q {
    private int size, front, rear, maxCapacity;
    private int[] arr;

    Q() {
        this(10);
    }

    Q(int size) {
        intialize(size);
    }

    protected void intialize(int size) {
        this.size = 0;
        this.maxCapacity = size;
        this.arr = new int[maxCapacity];
        this.front = 0;
        this.rear = 0;
    }

    public int getSize() {
        return this.size;
    }

    public int getMaxCapacity() {
        return this.maxCapacity;
    }

    public boolean isEmpty() {
        return this.getSize() == 0;
    }

    private void QisEMptyException() throws Exception {
        if (this.isEmpty())
            throw new Exception("Underflow Ocuured : Queue is Empty");
    }

    private void QisFullException() throws Exception {
        if (this.getSize() == this.getMaxCapacity())
            throw new Exception("Overflow Occured : Queue is Full");
    }

    public void add(int data) throws Exception {
        QisFullException();
        add_(data);
    }

    private void add_(int data) {
        this.arr[this.rear] = data;
        this.rear = (this.rear + 1) % this.maxCapacity;
        this.size++;
    }

    public int remove() throws Exception {
        QisEMptyException();
        return remove_();
    }

    private int remove_() {
        int rv = this.arr[this.front];
        this.arr[this.front] = 0;
        this.front = (this.front + 1) % this.maxCapacity;
        this.size--;

        return rv;
    }

    public int peek() throws Exception {
        QisEMptyException();
        return this.arr[this.front];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int currSize = this.getSize();
        sb.append("[");
        for (int i = 0; i < currSize; i++) {
            int idx = (i + this.front) % this.maxCapacity;
            sb.append(this.arr[idx]);

            if (i != currSize - 1)
                sb.append(", ");
        }

        sb.append("]");
        return sb.toString();
    }

}
