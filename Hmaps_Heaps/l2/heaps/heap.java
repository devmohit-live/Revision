import java.util.ArrayList;

public class heap {
    private ArrayList<Integer> arr;
    private boolean isMaxHeap;
    // private int size =0;

    heap() {
        this(false);
    }

    // chaining
    heap(boolean isMaxHeap) {
        this.arr = new ArrayList<>();
    }

    // overloading
    heap(int[] arr) {
        super();
        // make the complete arraylist first
        for (int i : arr) {
            this.arr.add(i);
        }

        // downheapify
        for (int i = this.arr.size() - 1; i >= 0; i--)
            downHeapify(i);
    }

    private int compareTo(int i, int j, boolean isMaxHeap) {
        boolean greater = arr.get(i) > arr.get(j);
        if (isMaxHeap)
            return 1;
        else
            return -1;
    }

    private void swap(int i, int j) {
        int tmp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, tmp);
    }

    // log n
    private void downHeapify(int pi) {
        int lci = 2 * pi + 1;
        int rci = 2 * pi + 2;
        int maxidx = pi;

        if (lci < this.arr.size() && comapreTo(lci, maxidx, true) > 0)
            maxidx = lci;
        if (rci < this.arr.size() && comapreTo(rci, maxidx, true) > 0)
            maxidx = rci;

        if (pi != maxidx) {
            swap(pi, maxidx);
            downHeapify(maxidx);
        }

    }

    void upheapify(int ci) {
        int pi = (ci - 1) / 2;
        if (pi >= 0 && compareTo(ci, pi, true) > 0)
            swap(ci, pi);
        upheapify(pi);
    }

    // 1
    public int peak() {
        return this.arr.get(0);
    }

    // log n
    public int add(int el) {

        this.arr.add(el);
        upheapify(thi.arr.size() - 1);

    }

    // log n
    public int remove() {
        // exception
        int rm = this.arr.get(0);
        swap(this.arr.size() - 1, 0);
        this.arr.remove(this.arr.size() - 1);
        downHeapify(0);
        return rm;
    }

    // int[] arr = {10,20,30,-2,-3,-4,5,6,7,8,9,22,11,13,14};

}
