package Questions.AMZ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Logs {
    public String[] reorderLogFiles(String[] logs) {

        int n = logs.length;
        String[] ans = new String[n];
        List<String> letters = new ArrayList<>();
        List<String> digits = new ArrayList<>();

        // segregate the letters and digits
        segregate(logs, letters, digits);

        // sort the letters on basis of their content, of equal then based on their
        // identifier

        Collections.sort(letters, (a, b) -> {
            int idx1 = a.indexOf(" "), idx2 = b.indexOf(" ");
            String contentA = a.substring(idx1 + 1), idetifierA = a.substring(0, idx1);
            String contentB = b.substring(idx2 + 1), idetifierB = b.substring(0, idx2);

            if (contentA.equals(contentB)) { // equal
                return idetifierA.compareTo(idetifierB);
            }
            return contentA.compareTo(contentB);
        });

        int idx = 0;
        for (String s : letters)
            ans[idx++] = s;
        for (String s : digits)
            ans[idx++] = s; // relatice order
        letters = digits = null;
        return ans;

    }

    private void segregate(String[] logs, List<String> letters, List<String> digits) {
        // all content except the identifier id ==s leeter in letter and number in digit
        for (String log : logs) {
            if (Character.isDigit(log.charAt(log.length() - 1)))
                digits.add(log);
            else
                letters.add(log);
        }
    }
}
