import java.util.ArrayList;

public class PQ {
    // Data Members

    private ArrayList<Integer> arr;
    private int size;
    private boolean isMinHeap;

    // Constructor
    private void initialize(boolean isMinHeap) {
        this.arr = new ArrayList<Integer>();
        this.size = 0;
        this.isMinHeap = isMinHeap;
    }

    public PQ(boolean isMinHeap) {
        initialize(isMinHeap);
    }

    public PQ() {
        this(true);
    }

    public PQ(int[] data) {
        this(data, true);
    }

    // O(n)
    public PQ(int[] data, boolean isMinHeap) {
        this(isMinHeap);

        for (int ele : data)
            this.arr.add(ele);

        this.size = data.length;
        for (int i = this.size - 1; i >= 0; i--) {
            downHeapify(i);
        }
    }

    // Basic Functions
    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public String toString() {
        // Remember PQ on printing doesn't gives items in sorted order(on removal the
        // sorted order is maintained)
        // Doesn't stores the item in sorted order
        return this.arr.toString();
    }

    // DS Functions

    public void add(int a) {
        this.arr.add(a);
        this.size++;
        int lastidx = this.size - 1;
        upHeapify(lastidx);
    }

    public int remove() throws Exception {
        underflow();
        int rm = this.arr.get(0);

        int lastidx = this.size - 1;
        swap(0, lastidx); // -> priority ele at last and last element at top
        this.arr.remove(lastidx); // -> removed last element -> priority element
        this.size--;
        downHeapify(0);
        return rm;
    }

    public int peek() throws Exception {
        underflow();
        return this.arr.get(0);
    }

    // Exceptions

    public void underflow() throws Exception {
        if (this.size == 0)
            throw new Exception("Priority Queue Underflow");
    }

    // Other dependecies

    private void swap(int i, int j) {
        int a = this.arr.get(i);
        int b = this.arr.get(j);
        this.arr.set(i, b);
        this.arr.set(j, a);
    }

    // one liner swap => using memory stack and left to right evaluation concept
    private void swap2(int i, int j) {

    }

    private int compareTo(int t, int o) {
        // compare on basis of type of PQ
        // normally -> min PQ
        int res = t - o;

        if (!this.isMinHeap)
            res *= -1;

        return res;
    }

    private void downHeapify(int pi) {

        int mxidx = pi, lci = (2 * pi) + 1, rci = (2 * pi) + 2;
        // if(arr.get(mxidx)<arr.get(rci)) mxidx = rci;
        // if(arr.get(mxidx)<arr.get(lci)) mxidx = lci;

        if (lci < this.size && compareTo(this.arr.get(mxidx), this.arr.get(lci)) > 0)
            mxidx = lci;
        if (rci < this.size && compareTo(this.arr.get(mxidx), this.arr.get(rci)) > 0)
            mxidx = rci;

        if (mxidx != pi) {
            swap(pi, mxidx);
            downHeapify(mxidx);
        }
    }

    private void upHeapify(int ci) {
        if (ci >= this.size)
            return;

        int pi = (ci - 1) / 2;
        if (pi >= 0 && compareTo(arr.get(pi), arr.get(ci)) > 0) {
            swap(pi, ci);
            upHeapify(pi);
        }

    }

}