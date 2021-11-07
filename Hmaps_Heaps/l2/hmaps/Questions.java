public class Questions {
    // Leetcode 128 : Longest Consecutive Subsequnce
    public int longestConsecutive(int[] nums) {

        // idea is to remove the consecutive elemets from the set as we explre them
        // once, so that all the consecutive elemets related to an element e is removed
        // at once
        if (nums.length == 0 || nums.length == 1)
            return nums.length;
        int maxlen = 0, start = -1;
        Set<Integer> set = new HashSet<>();
        for (int el : nums)
            set.add(el);
        int max = 0;
        for (int el : nums) {
            int prev = el - 1, next = el + 1;

            // set.remove actually decreases the search complexity/time of element in a set
            while (set.contains(prev))
                set.remove(prev--);
            while (set.contains(next))
                set.remove(next++);
            int len = next - prev - 1;
            if (len > maxlen) {
                maxlen = len;
                start = prev + 1;
            }
        }

        // System.out.println(start+" "+(start+maxlen-1));
        return maxlen;

    }

    // Leetcode 1218 : . Longest Arithmetic Subsequence of Given Difference

    public int longestSubsequence(int[] nums, int diff) {
        int max = -1, n = nums.length;
        // since here we aleady know the difference is cinstant ,
        // we eill be kwwp on checking for the previos element exists in map beforehand

        // why not recursion of lis bcz here we already know diff, it is not a normal
        // subseq, it is an arthmetic subseq

        // el, length of lis upto now
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int el : nums) {
            map.put(el, map.getOrDefault(el - diff, 0) + 1);
        }

        for (int el : map.keySet())
            max = Math.max(max, map.get(el));

        return max;
    }

    // Leetcode : 1424. Diagonal Traverse II
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        // diag no(i+j) (left to right), corresponding elements
        HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
        int size = 0, n = nums.size(), max = 0, idx = 0;

        // addFirst bcz we want the order such that => leftbottom -> upperright ie we
        // want to (3,1) before (1,3)
        // but we will be getting 1,3 first

        // (changint the loop in reverse order causes problem too)

        for (int i = 0; i < n; i++) {
            size += nums.get(i).size();
            for (int j = 0; j < nums.get(i).size(); j++) {
                int diag = i + j;
                int el = nums.get(i).get(j);
                map.putIfAbsent(diag, new LinkedList<Integer>());
                map.get(diag).addFirst(el); // added to corresponding diag list into correct order
                // get the maximum diag value so that we can traverse from 0->max(in order to
                // fill in the answer)
                max = Math.max(max, diag);
            }
        }

        int[] ans = new int[size];
        for (int i = 0; i <= max; i++) {
            LinkedList<Integer> tmp = map.get(i);
            while (tmp.size() != 0)
                ans[idx++] = tmp.removeFirst();

        }

        return ans;
    }

    // Leetcode 781 : Min number of Rabbits
    public int numRabbits(int[] arr) {
        // mygroup : no of rabbits answering the question in the group (posibilty)
        HashMap<Integer, Integer> map = new HashMap<>();
        int ans = 0, n = arr.length;
        for (int el : arr) {
            if (!map.containsKey(el)) {
                // new group explored
                ans += el + 1; // (other group members I know + myself)
                map.put(el, 1);
            } else {
                // possibility is other memeners of the same group answering the question:
                // he will tell the same thing that there el memember in the group except me
                map.put(el, map.get(el) + 1);
            }

            if (map.get(el) == el + 1) {
                map.remove(el); // we found all possible memebers of this group(including me)
                // if someone else be giving same answer then he belongs to the different group
                // of same size.
            }
        }

        return ans;
    }

    // if max was asked we would be treating every group as an individual group

    // 954 : Arrays of double pairs

    /*
     * we need to sort so that we can acheive the 2*el search in array Why not just
     * push elemets nd it's freq ,
     */

    /*
     * iterating over over map.keys and decrease both the freq of el, 2*el fails at
     * condition where el/2, el*2 both exists in map, and we will be getting the
     * keys in rando, orders say ; 2,1,4,8 => remove 2,4 first , left 1,8 => will
     * return false;
     * 
     * // so need to traverse in array only in a particular fashion/pattern, to
     * avoid check for half in case of negative numbers we can sort the array
     * according to the abs()
     * 
     * // int[] sort can't be done in cutome fashion , so we need to make an arary
     * of Integer(heap memo)
     */

    public boolean canReorderDoubled(int[] ar) {
        int n = ar.length;
        if ((n & 1) == 1)
            return false; // odd length
        Integer[] arr = new Integer[n];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            arr[i] = ar[i];
            map.put(ar[i], map.getOrDefault(ar[i], 0) + 1);
        }

        Arrays.sort(arr, (a, b) -> {
            return Math.abs(a) - Math.abs(b);
        });

        for (Integer el : arr) {
            // if freq of el = 0 either conitue or remove
            // if removing apply exitanance chek in put too

            if (map.get(el) == 0)
                continue; // it was nulled by some el who was actually el/2
            // since we are traversing in an array so we can encounter that elemet again

            if (map.getOrDefault(2 * el, 0) <= 0)
                return false;

            map.put(el, map.get(el) - 1);
            map.put(2 * el, map.get(2 * el) - 1);

        }
        return true;
    }

    // Leetcod 295 : Median in a data stream
    class MedianFinder {
        // we are making the median left oriented
        // basically median is by default supposed to lie on left side of number line
        // left -> ... -> (max of left) -> median -> (min of right) -> ... -> right

        // max of left = max pq, min of right => min pq

        private PriorityQueue<Integer> left;
        private PriorityQueue<Integer> right;
        // private int minsofar;

        public MedianFinder() {
            left = new PriorityQueue<>((a, b) -> {
                return b - a;
            });

            right = new PriorityQueue<>();
        }

        public void addNum(int num) {
            // default left oriented || number is in my range (in min < max in left)
            if (left.size() == 0 || num <= left.peek())
                left.add(num);
            else
                right.add(num);

            if (left.size() - right.size() == 2)
                right.add(left.remove());
            else if (right.size() - left.size() == 1)
                left.add(right.remove());

        }

        public double findMedian() {
            if (left.size() == right.size())
                return (1.0 * left.peek() + right.peek()) / 2;
            // defult left oriented
            return 1.0 * left.peek();

        }

        // 1027 : Longest Arithmetic Subsequence :TODO:toask
        public int longestArithSeqLength(int[] A) {
            int n = A.length;
            // dp => mujhe khtm hone wala lis with n differences like where d = 1,2,3,...
            // d= common differenece (ranges from A[x] - A[y] , x=0->n, y=1->n)
            HashMap<Integer, Integer>[] dp = new HashMap[n];

            for (int i = 0; i < n; i++)
                dp[i] = new HashMap<>();

            int len = 0;
            for (int i = 0; i < n; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    int diff = A[i] - A[j];

                    // present length of lis upto me with some differences
                    int currLen = dp[i].getOrDefault(diff, 0);
                    // length of lis befre me with same common diff
                    int newLen = dp[j].getOrDefault(diff, 1) + 1;
                    // my updated length for that common difference
                    dp[i].put(diff, Math.max(currLen, newLen));
                    len = Math.max(len, dp[i].get(diff));
                }
            }

            return len;
        }
    }

    // Leetcode contest 5898. Kth Distinct String in an Array
    public String kthDistinct(String[] arr, int k) {
        int n = arr.length, count = 0;
        HashMap<String, Integer> map = new HashMap<>();

        for (String s : arr) {
            map.put(s, map.getOrDefault(s, 0) + 1);
        }
        for (int i = 0; i < n; i++) {
            if (map.get(arr[i]) == 1)
                count++;
            if (count == k)
                return arr[i];

        }

        return "";
    }

    // /Leetcode 380get random
    class RandomizedSet {

        private int idx;
        private HashMap<Integer, Integer> map;
        private ArrayList<Integer> arr;

        public RandomizedSet() {
            this.arr = new ArrayList<>();
            this.idx = 0;
            this.map = new HashMap<>();
        }

        public boolean insert(int val) {
            if (map.containsKey(val))
                return false;
            arr.add(val);
            map.put(val, idx++);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val))
                return false;

            int i = map.get(val), n = arr.size();

            // swap with last elemetns of arr
            int last = arr.get(n - 1);
            arr.set(i, last);
            arr.remove(n - 1);

            // set proper index for last element sa it is changes now
            map.put(last, i); // new idex

            // erase the cal from map too
            map.remove(val);
            // remeber to decrease idx
            idx--;
            return true;

        }

        public int getRandom() {
            Random random = new Random();
            int n = arr.size();
            int i = random.nextInt(n);

            return arr.get(i);

        }
    }

    // Leetcode 447 : No of Boomerangs
    // 1. Basically humko saare arrangemetns chahiye iliye loop ka ya pattern hai

    // 2. mai hr ek point ko fix krke baaki points ki ditance dekh rha hu ex: first
    // i=0 wala point fix krke baakio ki distance

    // 3. boomerang bnane ke liye atleast map[i] ki value>=2 honi chahiye bcz centre
    // of boomerang(fixed point here ) has two extreems(points) with same distance,
    // even thoug agr hum check na b lgae to kaam krega as n*(n-1) n<0 n-1=>0

    // 4.humne ek point fix krke saare possible pairs ke (permutaion) nikal liye as
    // we are doing n(n-1)=? permutaion for map.value>2
    // ie. agr hume same distance pe 2 se jada same distance ke points milte hai to
    // mai unki nC2(n*n(n-1/2)) combinations or n(n-1) permutaions bna skta hu
    // ex: A,b ko 2 extremes maange fixed point ke liye, pehle A for first extreeme
    // mana then b ko

    // 5. next time jb dusre point ko fixed krenge to bilkul fresh distance milegi
    // so for this point we have to start fresh but we just have to add the
    // results(no of arrangemets in main result), to start fresh we are clearing the
    // map here
    public int numberOfBoomerangs(int[][] points) {
        final int n = points.length;
        int res = 0;
        HashMap<Long, Integer> map = new HashMap<>(n - 1); // (n cordinates make n-1 lines/pairs)
        for (int i = 0; i < n; i++) {
            // nneded all possible pairs: permutaions: arrangement matters
            for (int j = 0; j < n; j++) {
                if (i == j)
                    continue;
                long dis = distance(points, i, j);
                map.put(dis, map.getOrDefault(dis, 0) + 1);
            }

            for (int val : map.values()) {
                res += (val * (val - 1)); // permutaton
            }

            map.clear();

        }

        return res;
    }

    private long distance(int[][] points, int i, int j) {
        int x1 = points[i][0], y1 = points[i][1];
        int x2 = points[j][0], y2 = points[j][1];
        int x = Math.abs(x1 - x2), y = Math.abs(y1 - y2);
        long dis = x * x + y * y;
        return dis;
    }

    // Leetcode 149 : Max no of Points in a line

    // 1. Why String N=and gcd(), if you can use double=> when the slope is infinity
    // we can get into problem
    // this can be handeled by explicitly ccehking for infi and passing and
    // identification mark into map (something above the contraints=> >1e4)

    // why gcd=> m1 = 18/14 ans m2 = 9/7 points to same slope but on converting it
    // to string causes different keys

    // 2. in java there exists -0 and -0 !=0 , for slope -0 map will not be updated
    // properly
    // exp slope of points [2,3],[3,3] gives -0 as slope, m = dy/dx
    // dy = 3-3 =>0, dx = 2-3 => -1, now ideally it should be 0 but it gives -0
    // to avoid this we can use string and gcd thing

    // Using double
    public int maxPoints(int[][] points) {
        final int n = points.length;
        int ans = 0, max = 0;
        String slopeHavingMaxPoints = "";
        HashMap<Double, Integer> map = new HashMap<>();

        // no of points are asked => combinations
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // String slope = slope(points,i,j);
                double slope = getSlope(points, i, j);
                map.put(slope, map.getOrDefault(slope, 0) + 1);
                if (map.get(slope) > max) {
                    max = map.get(slope);
                    slopeHavingMaxPoints = (slope == 1.0 * 1e5) ? "infi" : "" + slope;

                }

            }

            // actual ans , max+1=> bcz we got n-1 points as we have fixed 1(i) point for
            // calculation slope
            ans = Math.max(max + 1, ans);
            map.clear(); // same work for other points being fixed
        }
        System.out.println("Slope " + slopeHavingMaxPoints + " having " + ans + " no. of points");
        return ans;
    }

    private double getSlope(int[][] points, int i, int j) {
        int dy = (points[i][1] - points[j][1]);
        int dx = (points[i][0] - points[j][0]);

        if (dx == 0)
            return 1.0 * 1e5; // handling infinity
        double slope = (1.0 * dy) / dx;
        if (slope == -0.0)
            slope = 0;
        // System.out.println(i+" "+j+" "+((1.0*dy)/dx));
        return slope;
    }

    // Using String
    public int maxPointsBetter(int[][] points) {
        final int n = points.length;
        int ans = 0, max = 0;
        String slopeHavingMaxPoints = "";
        HashMap<String, Integer> map = new HashMap<>();

        // no of points are asked => combinations
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                String slope = slope(points, i, j);
                map.put(slope, map.getOrDefault(slope, 0) + 1);

                if (map.get(slope) > max) {
                    max = map.get(slope);
                    slopeHavingMaxPoints = slope;

                }

            }

            // actual ans , max+1=> bcz we got n-1 points as we have fixed 1(i) point for
            // calculation slope
            ans = Math.max(max + 1, ans);
            map.clear(); // same work for other points being fixed
        }
        System.out.println("Slope " + slopeHavingMaxPoints + " having " + ans + " no. of points");
        return ans;
    }

    private int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    private String slope(int[][] points, int i, int j) {
        int dy = (points[i][1] - points[j][1]);
        int dx = (points[i][0] - points[j][0]);
        int gcd = gcd(dx, dy);
        dy /= gcd;
        dx /= gcd;
        String slope = dy + "@" + dx;
        return slope;
    }

}
