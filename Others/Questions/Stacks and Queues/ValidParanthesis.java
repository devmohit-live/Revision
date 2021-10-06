class ValidParanthsis {
    // Leetcode 20
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for (char ch : s.toCharArray()) {

            if (ch == '(' || ch == '{' || ch == '[')
                st.push(ch);

            else if (st.size() == 0)
                return false;
            else if (st.size() != 0 && ch == ')' && st.peek() != '(')
                return false;
            else if (st.size() != 0 && ch == '}' && st.peek() != '{')
                return false;
            else if (st.size() != 0 && ch == ']' && st.peek() != '[')
                return false;

            else
                st.pop();
        }

        return st.size() == 0; // if any opening brackets left

    }
}