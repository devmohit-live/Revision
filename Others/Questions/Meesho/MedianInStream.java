import java.util.PriorityQueue;

//similar to lc 295 : just a slitght difference that for even length of stream : n/2th el is the answer which is simply left oriented

// Test cases for Meesho : 
// ["MedianFinder","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian"]
// [[],[8],[],[2],[],[11],[],[9],[],[5],[]]
// =>[null,null,8.00000,null,2.00000,null,8.00000,null,8.00000,null,8.00000]

// ["MedianFinder","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian","addNum","findMedian"]
// [[],[2],[],[4],[],[6],[],[1],[],[3],[]]
// => [null,null,2.00000,null,2.00000,null,4.00000,null,2.00000,null,3.00000]

public class MedianInStream {
    private PriorityQueue<Integer> left; // maxpq (ledian => max of left)
    private PriorityQueue<Integer> right;// minpq => (median => min of right)
    private int n;

    public MedianInStream() {
        this.n = 0;
        this.left = new PriorityQueue<>((a, b) -> {
            return b - a;
        });
        this.right = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (left.size() == 0 || left.peek() >= num)
            left.add(num);
        else
            right.add(num);

        // balance
        if (left.size() - right.size() == 2) { // making left oriented gives it proveledge to take +1 elemets extra
            right.add(left.remove());

        } else if (right.size() - left.size() == 1)
            left.add(right.remove());

    }

    public double findMedian() {
        // n/2 in case of even => left , right have same elemets
        // if((n&1) == 0) return left.peek();
        return left.peek();

    }
}
