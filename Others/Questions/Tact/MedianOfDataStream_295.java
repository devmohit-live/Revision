public class MedianOfDataStream_295 {
    class MedianFinder {
        PriorityQueue<Integer> left, right;
        // left oriented

        // Time ; n elements then half of them will be in left and half of them will bw
        // in right :
        // there are total n elemets till now nlogn(for addin in pq) + (n-2)log(n-2)
        // +O(2*log(n/2)) => nlogn
        public MedianFinder() {
            left = new PriorityQueue<>((a, b) -> b - a);
            right = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if (left.size() == 0 || left.peek() >= num) {
                // left should contain all the lesser numbers
                left.add(num);
            } else
                right.add(num);

            // re - balance : making both pq to have nearly same no of elemets so that I
            // will be able to find the median at any point of time
            // ==2 bcz I am making it left oriented
            if (left.size() - right.size() == 2)
                right.add(left.remove()); // left oriented(left can have 1 extra)
            else if (right.size() - left.size() == 1)
                left.add(right.remove()); // equi balance
        }

        public double findMedian() {
            int size = left.size() + right.size();
            if ((size & 1) == 1) {
                return 1.0 * left.peek();
            } else {
                return (1.0 * left.peek() + right.peek()) / 2;
            }
        }
    }

    // Brute: Make an arrayList add num to it, whenver we are asked median : sort
    // the list and find median
    // at any point the size is k(say) : klogk +1 => now if we find median at aevery
    // point k*klohk (worst)
}
