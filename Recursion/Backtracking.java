public class Backtracking {

    //  LC 22.Generate Parentheses
    
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        if (n == 0)
            return ans;

        addBrackets(0, 0, n, new StringBuilder(), ans);
        return ans;
    }

    private void addBrackets(int opening, int closing, int n, StringBuilder sb, List<String> ans) {
        if (opening == n && closing == n) {
            ans.add(sb.toString());
            return;
        }

        // we have not exhausted all the opening brackets
        // and we should start with adding opening first as adding closing may lead to
        // invalid
        // ex: )

        if (opening < n) {
            sb.append("(");
            addBrackets(opening + 1, closing, n, sb, ans);
            sb.deleteCharAt(sb.length() - 1);
        }

        // there must be an opening brackets present to support the closing one
        // ex: ( for ) , '('(( ) for ')', that is apart from balanced brackets ther must
        // be atleast +1 op for closing
        if (opening > closing) {
            sb.append(")");
            addBrackets(opening, closing + 1, n, sb, ans);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
