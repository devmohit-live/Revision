public class StockSpanner {
    Stack<int[]> st;
    int day;

    // {0: index, 1: price}
    public StockSpanner() {
        this.day = 0; // acts as index global
        st = new Stack<int[]>();
        st.push(new int[] { -1, -1 }); // reprsents empty stack
    }

    public int next(int price) {
        while (st.peek()[0] != -1 && st.peek()[1] <= price) { // ngor
            st.pop();
        }

        int span = day - st.peek()[0];
        st.push(new int[] { day++, price });
        return span;
    }
}
