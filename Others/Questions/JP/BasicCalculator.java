class BasicCalculator{



    // Leetcode 272 : Basic Calculator 2
    public int calculate(String s) {
        if (s == null || s.isEmpty())
            return 0;
        int len = s.length();
        Stack<Integer> stack = new Stack<>();

        int currentNumber = 0; // holds the complete number before any operator
        char operation = '+';
        for (int i = 0; i < len; i++) {
            char currentChar = s.charAt(i);

            // make it a number first ex: 321
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }

            // untile you encounter a sapce(end of a number) or it is the last digit of a
            // number ( if space was not present we will be encountring an operator)
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == len - 1) {
                if (operation == '-') {
                    stack.push(-currentNumber);
                } else if (operation == '+') {
                    stack.push(currentNumber);
                }
                // high priority operators : needs to be evaluated on the spot
                else if (operation == '*') {
                    stack.push(stack.pop() * currentNumber);
                } else if (operation == '/') {
                    stack.push(stack.pop() / currentNumber);
                }
                operation = currentChar; // update the operation for upcoming numbers
                currentNumber = 0; // reset for new number
            }
        }

        // intermediate results are precalculated for high priority operators along with
        // it's appropriate signs
        int result = 0;
        // calculate the main result from intermediates
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }
}