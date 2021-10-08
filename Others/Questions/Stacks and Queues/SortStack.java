public class SortStack {
	// https://www.geeksforgeeks.org/sort-stack-using-temporary-stack/

	// Sorting : Descending order
	// Using an another stack
	public Stack<Integer> sort(Stack<Integer> st) {
		Stack<Integer> tmp = new Stack<>();

		while (st.size() > 0) {
			int popped = st.pop();

			while (tmp.size() > 0 && tmp.peek() > popped) {
				st.push(tmp.pop());
			}

			tmp.push(popped);

		}

		return tmp;
	}

	// Using Recursion:

	// article: https://www.geeksforgeeks.org/sort-a-stack-using-recursion/
	// vid(GFG): https://www.youtube.com/watch?v=SjDq1xYxC44
	public Stack<Integer> sort(Stack<Integer> s) {
		sort_(s);
		return s;
	}

	private void sort_(Stack<Integer> s) {
		if (s.size() == 0)
			return;

		int el = s.pop();
		sort_(s);

		sort_in_desc(s, el);
	}

	private void sort_in_desc(Stack<Integer> s, int el) {

		if (s.size() == 0 || s.peek() < el) {
			s.push(el);
			return;
		}

		int tmp = s.pop();
		sort_in_desc(s, el);

		s.push(tmp);

	}
}