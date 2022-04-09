public class BaseBallGame_682 {
    public int calPoints(String[] ops) {
        List<Integer> scores = new ArrayList<>();
        int sum = 0, size = 0, val = 0;

        for (String op : ops) {
            if (op.equals("C")) {
                scores.remove(scores.size() - 1);
            } else if (op.equals("D")) {
                size = scores.size();
                val = scores.get(size - 1) * 2;
                scores.add(val);
            } else if (op.equals("+")) {
                size = scores.size();
                val = scores.get(size - 1) + scores.get(size - 2);
                scores.add(val);
            } else {
                val = Integer.parseInt(op);
                scores.add(val);
            }

        }

        for (int el : scores)
            sum += el;

        return sum;

    }
}
