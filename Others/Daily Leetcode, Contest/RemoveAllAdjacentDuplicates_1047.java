class RemoveAllAdjacentDuplicates_1047 {

    public String removeDuplicates(String s) {
        // return approach1(s);
        return approach2(s);
    }

    // using Stack and StringBuilder
    private String approach1(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> st = new Stack<>();
        for (char ch : s.toCharArray()) {
            boolean isLoopExecuted = false;
            while (st.size() > 0 && st.peek() == ch) {
                st.pop();
                isLoopExecuted = true;
            }
            if (!isLoopExecuted)
                st.push(ch);

        }

        while (!st.isEmpty()) {
            sb.append(st.pop());
        }

        return sb.reverse().toString();
    }

    // Using strngBuilder only
    private String approach2(String s) {
        StringBuilder sb = new StringBuilder();
        for (char ch : s.toCharArray()) {
            if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ch)
                sb.deleteCharAt(sb.length() - 1);
            else
                sb.append(ch);
        }

        return sb.toString();

    }
}