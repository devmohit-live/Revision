public class MinTimeTomakeRopeColorful_1578 {
    public int minCost(String s, int[] cost) {

        int minCost = 0;
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            if (!stack.isEmpty() && s.charAt(stack.peek()) == s.charAt(i)) {
                int cost1 = cost[stack.peek()];
                int cost2 = cost[i];
                if (cost1 < cost2) {
                    minCost += cost1;
                    stack.pop();
                    stack.push(i);
                } else {
                    minCost += cost2;
                }
            } else {
                stack.push(i);
            }
        }
        return minCost;
    }
}
